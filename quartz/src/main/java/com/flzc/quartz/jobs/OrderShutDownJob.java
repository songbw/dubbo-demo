package com.flzc.quartz.jobs;

import com.flzc.quartz.entity.OrderRecap;
import com.flzc.quartz.service.OrderService;
import com.flzc.quartz.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 关闭超过支付时间的订单
 */
public class OrderShutDownJob implements FlzcJob {

    private static  final Logger LOGGER = LoggerFactory.getLogger(OrderShutDownJob.class);
    private static  final  Object lock = new Object();
    private static final Integer AUCTION = 39003;
    private static final Integer CUSTOMER = 39002;
    private static final Integer SHUTDOWN = 4;


    @Autowired
    private OrderService orderService;


    @Override
    public void execute() {
        synchronized (lock){
            long start = System.currentTimeMillis();
            LOGGER.info("============== 关闭订单定时任务starting=================");
            try {
                List<OrderRecap> auctions = this.orderService.queryTimeoutOrder(AUCTION);
                for (OrderRecap  recap : auctions){
                    recap.setStatus(SHUTDOWN);
                    try {
                        this.orderService.updateAuction(recap);
                        LOGGER.info("关闭竞拍订单：seq=:" + recap.getBizSeq());
                    } catch (Exception e) {
                        LOGGER.error("关闭竞拍订单异常：seq=:" + recap.getBizSeq(),e);
                    }
                }

                //
                List<OrderRecap> customers = this.orderService.queryTimeoutOrder(CUSTOMER);
                for (OrderRecap  recap : customers){
                    recap.setStatus(SHUTDOWN);
                    try {
                        this.orderService.updateCustomization(recap);
                        LOGGER.info("关闭私人定制订单：seq=:" + recap.getBizSeq());
                    } catch (Exception e) {
                        LOGGER.error("关闭定制订单异常：seq=:" + recap.getBizSeq(),e);
                    }
                }

            } catch (Exception e) {
                LOGGER.error("========关闭订单定时任务异常===========",e);
            }
            LOGGER.info("============== 关闭订单定时任务ending=================耗时="+ DateUtil.calIntervalSec(start,System.currentTimeMillis()));
        }

    }
}
