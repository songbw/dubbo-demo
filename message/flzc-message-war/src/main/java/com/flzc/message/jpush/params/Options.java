package com.flzc.message.jpush.params;

public class Options {
	private Integer sendno;
	private Integer time_to_live;
	private Long override_msg_id;
	private Boolean apns_production;
	private Integer big_push_duration;
	public Integer getSendno() {
		return sendno;
	}
	public void setSendno(Integer sendno) {
		this.sendno = sendno;
	}
	public Integer getTime_to_live() {
		return time_to_live;
	}
	public void setTime_to_live(Integer time_to_live) {
		this.time_to_live = time_to_live;
	}
	public Long getOverride_msg_id() {
		return override_msg_id;
	}
	public void setOverride_msg_id(Long override_msg_id) {
		this.override_msg_id = override_msg_id;
	}
	public Boolean getApns_production() {
		return apns_production;
	}
	public void setApns_production(Boolean apns_production) {
		this.apns_production = apns_production;
	}
	public Integer getBig_push_duration() {
		return big_push_duration;
	}
	public void setBig_push_duration(Integer big_push_duration) {
		this.big_push_duration = big_push_duration;
	}
}
