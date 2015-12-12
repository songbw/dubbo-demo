package com.flzc.quartz.util;

import com.flzc.quartz.entity.SchedulerConfigInfo;
import org.quartz.CronExpression;

/**任务时间表达式帮助类
 * Created by iverson on 2015/9/19.
 */
public class SchedulerUtils {
    /**
     * 验证cron expression
     * @param cronExp
     * @return
     */
    public  static final boolean  validateCronExpress(String cronExp){
        return CronExpression.isValidExpression(cronExp);
    }

    /**
     * 拼接cron expression
     * @param info
     * @return
     */
    public static final String concatExpress(SchedulerConfigInfo info){
        StringBuffer cron = new StringBuffer();
        cron.append(info.getSecond()).append(" ").append(info.getMinute()).append(" ").
                append(info.getHour()).append(" ").append(info.getDay()).append(" ").
                append(info.getMonth()).append(" ").append(info.getWeek()).append(" ").append(info.getYear());
        String cronExp = cron.toString().trim();
       return cronExp;
    }

}
