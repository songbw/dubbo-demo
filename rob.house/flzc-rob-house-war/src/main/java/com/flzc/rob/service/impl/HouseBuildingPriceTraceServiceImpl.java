package com.flzc.rob.service.impl;

import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.HouseBuildingInfo;
import com.flzc.rob.api.entity.HouseBuildingPriceTrace;
import com.flzc.rob.api.service.HouseBuildingPriceTraceService;
import com.flzc.rob.api.service.HouseBuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by iverson on 2015/10/10.
 */
@Service("houseBuildingPriceTraceService")
public class HouseBuildingPriceTraceServiceImpl implements HouseBuildingPriceTraceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingPriceTraceServiceImpl.class);

    @Resource(name = "commonDao")
    private CommonDao commonDao;

    @Autowired
    private HouseBuildingService houseBuildingService;

    /**
     * 保存
     *
     * @param priceTrace
     * @return
     */
    @Override
    public Number save(HouseBuildingPriceTrace priceTrace) throws Exception {
        try {
            HouseBuildingInfo buildingInfo = this.houseBuildingService.queryById(priceTrace.getBuildingId());
            if(buildingInfo == null){
                throw new NullPointerException( String.format("根据楼盘id[%1s]查询不到楼盘信息",priceTrace.getBuildingId()));
            }
            Integer referPrice = buildingInfo.getReferPrice();
            priceTrace.setPrice(referPrice.floatValue());
            priceTrace.setBuildingId(buildingInfo.getId());
            priceTrace.setCreateTime(new Date());
            //?? 价格当时的年月数值
            return  this.commonDao.save(priceTrace);

        } catch (Exception e) {
            LOGGER.error("保存楼盘价格趋势异常",e);
            throw e;
        }
    }

    /**
     * 根据楼盘id查询
     *
     * @param buildingId
     * @return
     */
    @Override
    public List<HouseBuildingPriceTrace> queryByBuildingId(Integer buildingId) throws Exception {
        HouseBuildingPriceTrace param = new HouseBuildingPriceTrace();
        param.setBuildingId(buildingId);
        try {
            return  this.commonDao.findObjs(param);
        } catch (Exception e) {
            LOGGER.error("查询楼盘价格趋势异常",e);
            throw e;
        }
    }
}
