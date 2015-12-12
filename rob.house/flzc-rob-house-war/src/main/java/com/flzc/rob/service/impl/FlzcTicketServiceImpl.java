
package com.flzc.rob.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.flzc.base.dao.CommonDao;
import com.flzc.base.service.BaseService;
import com.flzc.rob.api.entity.FlzcTicket;
import com.flzc.rob.api.service.FlzcTicketService;

@Service("flzcTicketService")
public class FlzcTicketServiceImpl implements FlzcTicketService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FlzcTicketServiceImpl.class);

    @Resource
    private CommonDao commonDao;
    
    @Resource(name="baseServiceImpl")
    private BaseService baseService;
    
	@Override
	public Integer saveFlzcTicket(FlzcTicket flzcTicket) throws Exception {

		try {
			
			flzcTicket.setTicketCode(UUID.randomUUID().toString().replaceAll("-", ""));
			Number id = commonDao.save(flzcTicket);
			return id.intValue();
		} catch (Exception e) {
			LOGGER.error("保存用户房链券出错，"+e);
			throw e;
		}
	}

	@Override
	public List<FlzcTicket> queryFlzcTicketList(FlzcTicket flzcTicket) throws Exception {
		
		try {
			
			List<FlzcTicket> list = commonDao.findObjs(flzcTicket);
			return list;
		} catch (Exception e) {
			LOGGER.error("查询用户房链券列表出错,"+e);
			throw e;
		}
	}

	@Override
	public List<FlzcTicket> queryFlzcTicketListByUserId(Integer userId,
			Integer status, Integer curPage, Integer pageSize) {
		
		if(curPage==null || curPage==0) curPage=1;
		if(pageSize==null || pageSize==0) pageSize=10;
		try {
			String hql =" from FlzcTicket where userId ="+userId;
			if(status!=null)
				hql += " and status ="+status;
			
			return baseService.findByHql(hql, (curPage-1)*pageSize, pageSize);
		} catch (Exception e) {
			LOGGER.error("根据用户id查询房链券出错,"+e);
			throw e;
		}
	}
	
	@Override
	public int queryFlzcTicketListSize(Integer userId,
			Integer status) {
		try {
			String hql ="select count(id) from FlzcTicket where userId ="+userId;
			if(status!=null)
				hql += " and status ="+status;
			
			return baseService.findPageTotalCount(hql);
		} catch (Exception e) {
			LOGGER.error("根据用户id查询房链券出错,"+e);
			throw e;
		}
	}

	@Override
	public FlzcTicket queryFlzcTicketById(Integer id) throws Exception {
		
		try {
			
			return commonDao.findById(FlzcTicket.class, id);
		} catch (Exception e) {
			LOGGER.error("根据房链券id查询房链券出错,"+e);
			throw e;
		}
	}

}
