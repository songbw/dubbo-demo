package com.flzc.quartz.service.impl;

import com.flzc.quartz.dao.CommonDao;
import com.flzc.quartz.entity.OrderRecap;
import com.flzc.quartz.service.OrderService;
import com.flzc.quartz.util.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 订单接口实现类
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private  static  final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Value("${order.pay.timeout}")
    private Integer orderTimeout;

    @Autowired
    private CommonDao commonDao;

    @PostConstruct
    public void init(){
        if(orderTimeout == null){
            orderTimeout = 24;
        }
    }

    /**
     * 查询所有超过支付时间的订单
     *
     * @return
     */
    @Override
    public List<OrderRecap> queryTimeoutOrder(Integer orderType) throws Exception {
        //查询超过1天状态为未支付的订单
        String sql = "SELECT " +
                " id, " +
                " order_id AS orderId, " +
                " biz_seq AS bizSeq " +
                "FROM " +
                " order_recap r " +
                "WHERE " +
                " r.order_type =  " + orderType +
                "AND r.`status` = 0 " +
                "AND DATE_ADD(r.create_time, INTERVAL %1s hour) > now()";
        List<Map<String, Object>> orders = this.commonDao.findBySql(String.format(sql, orderTimeout));
        if(orders==null) return Collections.emptyList();
        List<OrderRecap> list = ReflectUtils.convert(OrderRecap.class, orders);
        return list;
    }

    /**
     * 设置订单状态
     */
    @Override
    public void updateOrderRecapStatus(OrderRecap recap) {
        String sql = "update order_recap set status=%1s , update_time=now() where id=%2s";
        this.commonDao.updateBySql(String.format(sql,recap.getStatus(),recap.getId()));
    }

    /**
     * 更新定制订单状态
     *
     */
    @Override
    public void updateCustomization(OrderRecap recap) {
        this.updateOrderRecapStatus(recap);
        String  sql = "update property_customization_order set status=%1s ,update_time=now() where id=%2s";
        this.commonDao.updateBySql(String.format(sql,recap.getStatus(),recap.getOrderId()));
    }

    /**
     * 更新竞拍订单状态
     *
     */
    @Override
    public void updateAuction(OrderRecap recap) {
        this.updateOrderRecapStatus(recap);
        String  sql = "update auction_order set status=%1s ,update_time=now() where id=%2s";
        this.commonDao.updateBySql(String.format(sql,recap.getStatus(),recap.getOrderId()));
    }
}
