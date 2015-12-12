package com.flzc.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by iverson on 2015/9/24.
 */
public class TestJob implements Job{
    public void execute(){
        System.out.println("dynamic creation of job is done ");

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
