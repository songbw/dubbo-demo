package com.flzc.rob.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 户型实体类
 * Created by iverson on 2015/10/21.
 */
@Entity
@Table(name = "house_info")
public class HouseInfo implements Serializable {
    private Integer id;
    private Integer buildingId;//
    private String houseCode;//户型code
    private String house;//户型名
    private String roomCode;//室code
    private String room;//室
    private String hallCode;//厅code
    private String hall;//厅
    private String kbCode;//卫code
    private String kb;//卫
    private String faceTo;//朝向
    private Double size;//面积
    private Integer referPrice;//参考价格
    private String imgUrl;//户型图片
    private Date createTime;
    private Date updateTime;
    private Integer version;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "building_id", nullable = true, insertable = true, updatable = true)
    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    @Basic
    @Column(name = "house_code", nullable = true, insertable = true, updatable = true, length = 10)
    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    @Basic
    @Column(name = "house", nullable = true, insertable = true, updatable = true, length = 10)
    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Basic
    @Column(name = "room_code", nullable = true, insertable = true, updatable = true, length = 10)
    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    @Basic
    @Column(name = "room", nullable = true, insertable = true, updatable = true, length = 10)
    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Basic
    @Column(name = "hall_code", nullable = true, insertable = true, updatable = true, length = 10)
    public String getHallCode() {
        return hallCode;
    }

    public void setHallCode(String hallCode) {
        this.hallCode = hallCode;
    }

    @Basic
    @Column(name = "hall", nullable = true, insertable = true, updatable = true, length = 10)
    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    @Basic
    @Column(name = "kb_code", nullable = true, insertable = true, updatable = true, length = 10)
    public String getKbCode() {
        return kbCode;
    }

    public void setKbCode(String kbCode) {
        this.kbCode = kbCode;
    }

    @Basic
    @Column(name = "kb", nullable = true, insertable = true, updatable = true, length = 10)
    public String getKb() {
        return kb;
    }

    public void setKb(String kb) {
        this.kb = kb;
    }

    @Basic
    @Column(name = "face_to", nullable = true, insertable = true, updatable = true, length = 10)
    public String getFaceTo() {
        return faceTo;
    }

    public void setFaceTo(String faceTo) {
        this.faceTo = faceTo;
    }

    @Basic
    @Column(name = "size", nullable = true, insertable = true, updatable = true)
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Basic
    @Column(name = "refer_price", nullable = true, insertable = true, updatable = true)
    public Integer getReferPrice() {
        return referPrice;
    }

    public void setReferPrice(Integer referPrice) {
        this.referPrice = referPrice;
    }

    @Basic
    @Column(name = "create_time", nullable = true, insertable = true, updatable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "update_time", nullable = true, insertable = true, updatable = true)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Version
    @Column(name = "version", nullable = true, insertable = true, updatable = true)
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "img_url", nullable = true, insertable = true, updatable = true, length = 500)
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


}
