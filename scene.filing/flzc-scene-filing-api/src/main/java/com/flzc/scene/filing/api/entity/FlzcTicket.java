package com.flzc.scene.filing.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *   房链券汇总表实体类
 * Created by song on 2015/12/7.
 */
@Entity
@Table(name = "flzc_ticket")
public class FlzcTicket implements Serializable{
    //核销码ID
    private Integer id;
    //活动i汇总d
    private Integer activityRecapId;
    //核销码
    private String ticketCode;
    //用户id
    private Integer userId;
    //房链券面值
    private Integer amount;
    //房链卷开始有效期',
    private Date startDate;
    //房链卷结束有效期
    private Date endDate;
    //状态:0未消费，1已消费，2已过期，3：已做废
    private Integer status;
    //活动类型(39001 答题抽奖；39002私人订制；39003,幸运竞拍,39004红包宝)
    private Integer actType;
    //券类型(0:房链券；1：电子券)
    private Integer ticketType;
    //创建时间
    private Date createTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "activity_recap_id")
    public Integer getActivityRecapId() {
        return this.activityRecapId;
    }

    public void setActivityRecapId(Integer activityRecapId) {
        this.activityRecapId = activityRecapId;
    }

    @Column(name = "ticket_code", length = 100)
    public String getTicketCode() {
        return this.ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "amount")
    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", length = 10)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", length = 10)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "act_type")
    public Integer getActType() {
        return this.actType;
    }

    public void setActType(Integer actType) {
        this.actType = actType;
    }

    @Column(name = "ticket_type")
    public Integer getTicketType() {
        return this.ticketType;
    }

    public void setTicketType(Integer ticketType) {
        this.ticketType = ticketType;
    }

    @Column(name = "create_time", length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
