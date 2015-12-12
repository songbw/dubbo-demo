package com.flzc.quartz.jobs;

import com.flzc.quartz.entity.Agency;
import com.flzc.quartz.entity.AgencyReportCount;
import com.flzc.quartz.entity.AgencySeeCount;
import com.flzc.quartz.service.AgencyContributeService;
import com.flzc.quartz.service.AgencyStatisticsService;
import com.flzc.quartz.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 经纪人统计定时任务.统计按天，周，月
 */
public class AgencyStatisticsJob implements FlzcJob {
    private static  final Logger LOGGER = LoggerFactory.getLogger(AgencyStatisticsJob.class);
    private static final Object lock = new Object();

   @Autowired
   private AgencyStatisticsService agencyStatisticsService;

    @Autowired
    private AgencyContributeService contributeService;

    @Override
    public void execute() {
        synchronized (lock){
            LOGGER.info("===================经纪人业绩统计分析任务start===================");
            long start = System.currentTimeMillis();
            try {
                List<Agency> agencies = contributeService.queryAllAgency();
                //报备
                List<Map<String, Object>> dayStat = agencyStatisticsService.queryReportByDay();
                List<Map<String, Object>> weekStat = agencyStatisticsService.queryReportByWeek();
                List<Map<String, Object>> monthStat = agencyStatisticsService.queryReportByMonth();
                for (Agency a : agencies){

                    if(a.getOfficeId()==null) continue; //未绑定门店

                    Integer id = a.getId();
                    Integer dayNum = getNum(id, dayStat);
                    Integer weekNum  = getNum(id, weekStat);
                    Integer monNum = getNum(id, monthStat);
                    AgencyReportCount report = new AgencyReportCount();
                    report.setAgencyId(id);
                    report.setDayCount(dayNum);
                    report.setWeekCount(weekNum);
                    report.setMonthCount(monNum);
                    report.setAoId(a.getOfficeId());
                    report.setTotalCount(0);
                    this.agencyStatisticsService.saveOrUpdateStatistics(report);
                }

                //带看
                List<Map<String, Object>> seeDayStat = agencyStatisticsService.querySeeByDay();
                List<Map<String, Object>> seeWeekStat = agencyStatisticsService.querySeeByWeek();
                List<Map<String, Object>> seeMonthStat = agencyStatisticsService.querySeeByMonth();
                List<Map<String, Object>> totals = agencyStatisticsService.querySeeTotal();
                for (Agency a : agencies){

                    if(a.getOfficeId()==null) continue; //未绑定门店

                    Integer id = a.getId();
                    Integer dayNum = getNum(id, seeDayStat);
                    Integer weekNum  = getNum(id, seeWeekStat);
                    Integer monNum = getNum(id, seeMonthStat);
                    Integer total = getNum(id, totals);
                    AgencySeeCount see = new AgencySeeCount();
                    see.setAgencyId(id);
                    see.setDayCount(dayNum);
                    see.setWeekCount(weekNum);
                    see.setMonthCount(monNum);
                    see.setTotalCount(total);
                    see.setAoId(a.getOfficeId());
                    see.setTotalCount(0);
                    this.agencyStatisticsService.saveOrUpdateStatistics(see);
                }

            } catch (Exception e) {
                LOGGER.error("经纪人业绩统计分析任务异常",e);
            }
            LOGGER.info("===================经纪人业绩统计分析任务end===================耗时=" + DateUtil.calIntervalSec(start,System.currentTimeMillis()));
        }

    }

    private Integer getNum(Integer agencyId, List<Map<String, Object>> rows) {
        for (Map<String, Object> m : rows) {
            Integer agencyIdTmp = Integer.valueOf(m.get("agencyId").toString());
            if (agencyId.intValue() == agencyIdTmp.intValue()) {
                return Integer.valueOf(m.get("total").toString());
            }
        }
        return 0;
    }
}
