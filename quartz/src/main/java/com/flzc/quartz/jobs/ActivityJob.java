package com.flzc.quartz.jobs;

import com.flzc.quartz.service.ActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 此定时任务，定时将到结束时间的活动状态转为已结束
 */
public class ActivityJob implements FlzcJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuctionActivityWinnerJob.class);
    private static final Object lock = new Object();

    @Autowired
    private ActivityService activityService;

    @Override
    public void execute() {

        synchronized (lock) {
            LOGGER.info("====================活动结束定时任务starting===============");
            try {
                LOGGER.info("====================修改所有在当日结束的活动开始-->updateActivityRecapOver===============");
                this.activityService.updateActivityRecapOver();
                LOGGER.info("====================修改所有在当日结束的活动结束-->updateActivityRecapOver===============");
            } catch (Exception e) {
                LOGGER.error("修改所有在当日结束的活动异常-->updateActivityRecapOver", e);
            }
            try {
                LOGGER.info("====================修改所有在当日结束的竞拍活动开始-->updateAuctionOver===============");
                this.activityService.updateAuctionOver();
                LOGGER.info("====================修改所有在当日结束的竞拍活动开始-->updateAuctionOver===============");
            } catch (Exception e) {
                LOGGER.error("修改所有在当日结束的竞拍活动异常-->updateAuctionOver", e);
            }
            try {
                LOGGER.info("============修改所有在当日结束的答题活动开始-->updateQuestionOver=============");
                this.activityService.updateQuestionOver();
                LOGGER.info("============修改所有在当日结束的答题活动结束-->updateQuestionOver=============");
            } catch (Exception e) {
                LOGGER.error("修改所有在当日结束的答题活动异常", e);
            }
            try {
                LOGGER.info("============修改所有在当日结束的定制活动开始-->updateCustomizationOver=============");
                this.activityService.updateCustomizationOver();
                LOGGER.info("============修改所有在当日结束的定制活动结束-->updateCustomizationOver=============");
            } catch (Exception e) {
                LOGGER.error("修改所有在当日结束的定制活动异常", e);
            }
            LOGGER.info("====================活动结束定时任务ending===============");
        }
    }

}
