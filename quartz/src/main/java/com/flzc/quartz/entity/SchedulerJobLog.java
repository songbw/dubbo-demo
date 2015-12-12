package com.flzc.quartz.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by iverson on 2015/9/19.
 */
@Entity
@Table(name = "scheduler_job_log")
public class SchedulerJobLog {
    private Integer id;
    private String jobCode;
    private String oldExpr;
    private String newExpr;
    private Timestamp createTime;
    private String operator;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "job_code", nullable = true, insertable = true, updatable = true, length = 50)
    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    @Basic
    @Column(name = "old_expr", nullable = true, insertable = true, updatable = true, length = 50)
    public String getOldExpr() {
        return oldExpr;
    }

    public void setOldExpr(String oldExpr) {
        this.oldExpr = oldExpr;
    }

    @Basic
    @Column(name = "new_expr", nullable = true, insertable = true, updatable = true, length = 50)
    public String getNewExpr() {
        return newExpr;
    }

    public void setNewExpr(String newExpr) {
        this.newExpr = newExpr;
    }

    @Basic
    @Column(name = "create_time", nullable = false, insertable = true, updatable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "operator", nullable = true, insertable = true, updatable = true, length = 20)
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
