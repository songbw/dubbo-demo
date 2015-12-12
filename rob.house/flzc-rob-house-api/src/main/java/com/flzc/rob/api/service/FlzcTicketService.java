
package com.flzc.rob.api.service;

import java.util.List;

import com.flzc.rob.api.entity.FlzcTicket;

/**
 * 用户房链券
  * @ClassName: FlzcTicketService
  * @Description: TODO
  * @date 2015-11-4 下午8:48:03
  *
 */
public interface FlzcTicketService {

	/**
	 * 保存用户房链券
	 * @Title: saveFlzcTicket
	 * @param flzcTicket
	 * @return
	 * @throws Exception 
	 */
	public Integer saveFlzcTicket(FlzcTicket flzcTicket) throws Exception; 
	
	/**
	 * 查询用户房链券列表
	 * @Title: queryFlzcTicketList
	 * @param flzcTicket
	 * @return
	 * @throws Exception 
	 */
	public  List<FlzcTicket> queryFlzcTicketList(FlzcTicket flzcTicket) throws Exception;
	
	/**
	 * 根据用户id查询用户获得房链列表
	 * @Title: queryFlzcTicketListByUserId
	 * @param userId
	 * @param status
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	public  List<FlzcTicket> queryFlzcTicketListByUserId(Integer userId,Integer status ,Integer curPage , Integer pageSize);
	
	/**
	 * 根据用户id查询用户获得房链数量
	 * @Title: queryFlzcTicketListSize
	 * @param userId
	 * @param status
	 * @return
	 */
	public  int queryFlzcTicketListSize(Integer userId,Integer status);
	
	/**
	 * 根据id获取房链券信息
	 * @Title: saveFlzcTicket
	 * @param flzcTicket
	 * @return
	 * @throws Exception 
	 */
	public FlzcTicket queryFlzcTicketById(Integer id) throws Exception;
}
