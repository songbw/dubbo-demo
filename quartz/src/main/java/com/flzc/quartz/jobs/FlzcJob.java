package com.flzc.quartz.jobs;

/**
 * 所有定时任务必须实现此类
 */
public interface FlzcJob {
    public void execute();
}
