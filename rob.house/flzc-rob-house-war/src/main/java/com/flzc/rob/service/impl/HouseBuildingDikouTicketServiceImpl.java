package com.flzc.rob.service.impl;

import com.flzc.base.dao.CommonDao;
import com.flzc.rob.api.entity.HouseBuildingDikouTicket;
import com.flzc.rob.api.service.HouseBuildingDikouTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by iverson on 2015/11/17.
 */
@Service("houseBuildingDikouTicketService")
public class HouseBuildingDikouTicketServiceImpl implements HouseBuildingDikouTicketService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HouseBuildingDikouTicketServiceImpl.class);

    @Autowired
    private CommonDao commonDao;
    /**
     * 根据楼盘查询抵扣
     *
     * @param buildingId
     * @return
     */
    @Override
    public HouseBuildingDikouTicket queryByBuildingId(Integer buildingId) throws Exception {
        HouseBuildingDikouTicket param = new HouseBuildingDikouTicket();
        param.setBuildingId(buildingId);
        param.setStatus(0);
        try {
           return  this.commonDao.findUniqueObj(param);
        } catch (Exception e) {
            LOGGER.error("",e);
            throw e;
        }
    }
    
	@Override
	public void updateDikouActivityInfo(HouseBuildingDikouTicket ticket)
			throws Exception {
		
		try {
			
			commonDao.update(ticket);
		} catch (Exception e) {
			LOGGER.error("更新红包抵扣可售数异常",e);
			throw e;
		}
	}
    
}
