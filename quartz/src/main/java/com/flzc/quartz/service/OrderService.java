package com.flzc.quartz.service;

import com.flzc.quartz.entity.OrderRecap;

import java.util.List;

/**
 *  定单操作服务类
 */
public interface OrderService {

    /**
     * 查询所有超过支付时间的订单
     * @return
     */
    public List<OrderRecap> queryTimeoutOrder(Integer orderType) throws Exception;

    /**
     * 设置订单状态
     */
    public void updateOrderRecapStatus(OrderRecap recap);

    /**
     * 更新定制订单状态
     */
    public void updateCustomization(OrderRecap recap);

    /**
     * 更新竞拍订单状态
     */
    public void updateAuction(OrderRecap recap);

}
