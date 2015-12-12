package com.flzc.quartz.service.impl;

import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.entity.FlzcTicket;
import com.flzc.quartz.entity.HomePageHeadline;
import com.flzc.quartz.service.AuctionActivityService;
import com.flzc.quartz.util.DateUtil;
import com.flzc.quartz.util.HttpUtils;
import com.flzc.quartz.util.LinkEncrypt;
import com.flzc.quartz.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 竞拍活动处理接口 。
 */
@Service("auctionActivityService")
public class AuctionActivityServiceImpl implements AuctionActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuctionActivityServiceImpl.class);

    @Value("auction_order_url")
    private String url;

    @Value("${auction.return.money}")
    private String returnUrl;

    @Autowired
    private CommonDao commonDao;


    @PostConstruct
    public void init() {
        if (StringUtils.isBlank(returnUrl)) {
            throw new NullPointerException("竞拍订单退款API URL 地址为空");
        }
    }

    /**
     * 查出今天结束的活动,并且未处理过的活动
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryEndRecordToday() {
        String today = DateUtil.getCurrenDate();
        String sql = " select id , building_id as bid   " +
                " from aution_activity_info where status=1 and handle_status = 0  and act_end_date > '%1s' and act_end_date < now()  ";
        List<Map<String, Object>> maps = this.commonDao.findBySql(String.format(sql, today));
        if (maps == null) return Collections.emptyList();
        return maps;
    }

    /**
     * 查询出中拍人，以及最后定义的拍得价格
     *
     * @param activityId
     * @return
     */
    @Override
    public Map<String, Object> queryWinner(Integer activityId, Integer targetPrice) {
        //取出每个用户出的最高价
        String priceSql = " SELECT " +
                " max( p.price ) as price, " +
                " p.user_id as userId " +
                " FROM " +
                " `aution_price` p " +
                " where p.activity_id =  " + activityId +
                " GROUP BY p.user_id   " +
                " order by p.price desc";
        List<Map<String, Object>> rows = this.commonDao.findBySql(priceSql);

        if (rows == null) return null;
        //以价格做key,出价相同的用户id拼接成 "userId,userId2,"
        Map<String, String> container = new HashMap<>();//价格做key
        String userId;
        String price;
        for (Map<String, Object> m : rows) {
            userId = m.get("userId").toString();
            price = m.get("price").toString();
            String userIds = container.get(price);
            if (userIds == null) {
                container.put(price, userId + ",");
            } else {
                container.put(price, userIds + userId + ",");
            }
        }
        //将出价相同的用户排除
        //value 是userId , key是价格
        Map<String, String> uniquePriceMap = new HashMap<>();
        List<Integer> priceList = new ArrayList<>();
        //
        Iterator<Map.Entry<String, String>> iterator = container.entrySet().iterator();
        String[] split;
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String value = entry.getValue();
            split = value.split(",");//拆分用户id
            if (split.length == 1) {//说明这个价格只有一个出价人
                uniquePriceMap.put(entry.getKey(), split[0]);
                //把价格存入数组中,方便排序
                priceList.add(Integer.valueOf(entry.getKey()));
            }
        }

        //选出拍得者
        int size = priceList.size();
        Integer[] prices = new Integer[size];
        Arrays.sort(priceList.toArray(prices));
        Integer finalPrice;
        Integer topPrice;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            finalPrice = prices[0];
            topPrice = finalPrice;
        } else {
            finalPrice = prices[size - 2]; //取出价次高
            topPrice = prices[size-1] ;// 取出最高价
        }
        if (finalPrice < targetPrice)  //未达到保护价格
            return null;
        //最终的拍得者 .出价最高的以次价成交
        Map<String, Object> result = new HashMap<>();
        result.put("price", finalPrice);
        result.put("userId", uniquePriceMap.get(topPrice.toString()));
        return result;
    }

    /**
     * 设定活动拍得者.生成订单
     *
     * @param activityId
     * @param targetPrice 保留价格价格
     */
    @Override
    public void updateForWinner(Integer activityId, Integer targetPrice) throws Exception {

        Map<String, Object> winner = this.queryWinner(activityId, targetPrice);
        if (winner == null || winner.isEmpty()) {
            LOGGER.info("竞拍活动失败,无竞拍者id=" + activityId);
            updateHandleStatus(activityId);
            return;
        }
        Integer userId = Integer.valueOf(winner.get("userId").toString());
        Integer price = Integer.valueOf(winner.get("price").toString());

        String updateSql = "update aution_price set winner=1 where  user_id = %1s and winner = 0 and activity_id=" + activityId;
        this.commonDao.updateBySql(String.format(updateSql, userId));
        LOGGER.info("竞拍活动id=" + activityId + ",拍得者id=" + userId + ",成交价" + price);
        //设置保证金不能退
        String freezeDepositSql = "update auction_order set status=6 ,update_time=now() where user_id=%1s and activity_id=%2s ";
        this.commonDao.updateBySql(String.format(freezeDepositSql, userId, activityId));
        freezeDepositSql = "update order_recap  set status=6 ,update_time = now() where user_id=%1s and activity_id=%2s and order_type='39003'";
        this.commonDao.updateBySql(String.format(freezeDepositSql, userId, activityId));

        // 添加房链券
        addTicket(userId, price, activityId);

        //设置已处理
        updateHandleStatus(activityId);

        //把拍得都信息存入，头条表
        addHeadline(activityId, userId);



        //生成订单
        // tokenId, amount activityId orderType（订单类型：39003）
//        Map<String,Object> form = new HashMap<>();
//        form.put("tokenId", LinkEncrypt.generateToken(winnerUserId.toString()));
//        form.put("activityId",activityId);
//        form.put("orderType",39003);
//        try {
//            HttpUtils.post(url,form);
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//            throw   e;
//        }
    }


    /**
     * 退款
     *
     * @param userId
     * @param orderSeq
     * @throws Exception
     */
    @Override
    public void queryMoneyBack(Integer userId, String orderSeq) throws Exception {
        if (userId == null) throw new NullPointerException("用户ID为空");
        if (StringUtils.isBlank(orderSeq)) throw new NullPointerException("订单序列号为空,userId=" + userId);
        LOGGER.info("用户userId=" + userId + "开始退款，订单号=" + orderSeq);
        String tokenId = LinkEncrypt.generateToken(userId.toString());
        Map<String, Object> form = new HashMap<>();
        form.put("tokenId", tokenId);
        form.put("orderId", orderSeq);
        try {
            HttpUtils.post(this.returnUrl, form);
        } catch (Exception e) {
            //todo 日志记录
            LOGGER.error("用户userId=" + userId + "退款失败，订单号=" + orderSeq);
        }
    }

    /**
     * 取得退款用户id ,与订单号
     *
     * @param actId 活动id
     * @return
     */
    @Override
    public List<Map<String, Object>> queryReturnList(Integer actId) {
        //查询出该活动，已付款的订单
        String sql = "select biz_seq as orderSeq ,user_id as userId  from  auction_order  where status=1 and  activity_id=" + actId;
        List<Map<String, Object>> maps = this.commonDao.findBySql(sql);
        if (maps == null || maps.isEmpty()) return Collections.EMPTY_LIST;
        return maps;
    }


    /**
     * 给竞拍参加者发放券
     *
     * @param winnerUserId ,中奖用户
     * @param price        竞拍的价格
     * @param activityId
     */
    public void addTicket(Integer winnerUserId, int price, Integer activityId) throws Exception {

        String recapIdSql = "select id  from  activity_recap where type=39003 and act_id=" + activityId;
        Map<String, Object> recapMap = this.commonDao.findUniqueBySql(recapIdSql);
        if (recapMap == null || recapMap.isEmpty()) {
            throw new NullPointerException("活动汇总表中无竞拍活动信息:ID=" + activityId);
        }
        Integer recapId = Integer.valueOf(recapMap.get("id").toString());
        //查询出未拍情况下的奖励
        String auctionSql = "select  date_format(unwin_prize_start_time ,'%Y-%m-%d %T')  as unStartTime , " +
                "  date_format(unwin_prize_end_time ,'%Y-%m-%d %T')  as unEndTime , unwin_prize_value as littlePrize , " +
                "   date_format(win_prize_start_time ,'%Y-%m-%d %T')   as startTime ,   date_format(win_prize_end_time ,'%Y-%m-%d %T')  as endTime " +
                " from aution_prize where   act_id=" + activityId;
        Map<String, Object> prize = this.commonDao.findUniqueBySql(auctionSql);
        if (prize == null || prize.isEmpty()) {
            throw new NullPointerException("无竞拍活动中奖信息:ID=" + activityId);
        }
        String unStartTime = prize.get("unStartTime").toString();
        String unEndTime = prize.get("unEndTime").toString();
        String startTime = prize.get("startTime").toString();
        String endTime = prize.get("endTime").toString();
        String littlePrize = prize.get("littlePrize").toString();
        int ticketType = 0;//表示房链券

        //给未拍得用户生成券
        String looserSql = "SELECT  DISTINCT p.user_id as userId FROM `aution_price` p where p.winner = 0 and p.activity_id=" + activityId;
        List<Map<String, Object>> loosers = this.commonDao.findBySql(looserSql);
        FlzcTicket ticket;
        if (loosers != null) {
            for (Map<String, Object> l : loosers) {
                Integer loId = Integer.valueOf(l.get("userId").toString());
                ticket = new FlzcTicket();
                ticket.setCreateTime(new Date());
                ticket.setUserId(loId);
                ticket.setStatus(0);
                ticket.setActivityRecapId(recapId);
                ticket.setAmount(Integer.valueOf(littlePrize));
                ticket.setActType(39003);
                ticket.setStartDate(DateUtil.convertStringToDate(unStartTime));
                ticket.setEndDate(DateUtil.convertStringToDate(unEndTime));
                ticket.setTicketCode(UUID.randomUUID().toString().replace("-", ""));
                ticket.setTicketType(ticketType);
                this.commonDao.save(ticket);
            }
        }
        ticket = new FlzcTicket();
        ticket.setCreateTime(new Date());
        ticket.setUserId(winnerUserId);
        ticket.setStatus(0);
        ticket.setActivityRecapId(recapId);
        ticket.setAmount(price);
        ticket.setActType(39003);
        ticket.setStartDate(DateUtil.convertStringToDate(startTime));
        ticket.setEndDate(DateUtil.convertStringToDate(endTime));
        ticket.setTicketCode(UUID.randomUUID().toString().replace("-", ""));
        ticket.setTicketType(1);//电子凭证
        this.commonDao.save(ticket);
    }

    /**
     * 拍得者，入头条
     *
     * @param activityId
     * @param winnerUserId
     * @throws Exception
     */
    @Override
    public void addHeadline(Integer activityId, Integer winnerUserId)  {
        try {
            Map<String, Object> user = this.commonDao.findUniqueBySql("select nick_name as nickName , user_name as userName from user where id = " + winnerUserId);
            if (user == null || user.isEmpty()) return;
            Object nickName = user.get("nickName");
            if (nickName == null) nickName = user.get("userName");
            String actName = "";
            Map<String, Object> auction = this.commonDao.findUniqueBySql("select activity_name as actName from aution_activity_info where id =" + activityId);
            if (auction != null) {
                actName = auction.get("actName").toString();
            }
            String memo = nickName.toString() + "获得竞拍活动奖项";
            //此活动在活动汇总表中的id
            Map<String, Object> recapInfo = this.commonDao.findUniqueBySql("select id from activity_recap where type=39003 and act_id=" + activityId);
            if(recapInfo==null || recapInfo.isEmpty()){
                recapInfo.put("id",0);
                LOGGER.error("活动汇总表中无竞拍活动ID=" + activityId + "的记录");
            }
            HomePageHeadline headline = new HomePageHeadline();
            headline.setUserName(nickName.toString());
            headline.setActName(actName);
            headline.setStatus(0);
            headline.setCreateTime(new Date());
            headline.setMemo(memo);
            headline.setRecapId(Integer.valueOf(recapInfo.get("id").toString()));
            this.commonDao.save(headline);
        }catch (Exception e){
            LOGGER.error("添加拍得者头条异常", e);
        }

    }

    /**
     * 如果活动未达到目标价，则活动失败
     *
     * @param activityId
     * @return 返回目标价格。未达到目标价格返回0
     */
    @Override
    public int queryInvalid(Integer activityId) {
        String targetSql = "select  target_price as targetPrice from aution_activity_info where id=" + activityId;
        Map<String, Object> m = this.commonDao.findUniqueBySql(targetSql);
        if (m == null || m.isEmpty())
            throw new NullPointerException("竞拍活动不存在，或活动目标价格未设置;ID=" + activityId);
        Integer targetPrice = Integer.valueOf(m.get("targetPrice").toString());
        String priceSql = "SELECT count(p.id) FROM `aution_price` p where  p.price >= %1s and  p.activity_id =" + activityId;
        int flag = this.commonDao.findCountBySql(String.format(priceSql, targetPrice));
        if (flag == 0) return 0;
        return targetPrice;
    }

    /**
     * 将活动设置为已处理
     *
     * @param activityId
     */
    @Override
    public void updateHandleStatus(Integer activityId) {
        String sql = "update  aution_activity_info set handle_status=1 where id= " + activityId;
        this.commonDao.updateBySql(sql);
    }


}
