package com.flzc.service.bean;

import com.flzc.message.api.entity.MessageSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息列表BEAN
 * Created by song on 2015/11/30.
 */
public class MessagesBean {
    private List<MessageSystem> messageSystems = new ArrayList<MessageSystem>() ;
    private Integer status = 0 ;
    private Integer totals = 0;

    public List<MessageSystem> getMessageSystems() {
        return messageSystems;
    }

    public void setMessageSystems(List<MessageSystem> messageSystems) {
        this.messageSystems = messageSystems;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotals() {
        return totals;
    }

    public void setTotals(Integer totals) {
        this.totals = totals;
    }
}
