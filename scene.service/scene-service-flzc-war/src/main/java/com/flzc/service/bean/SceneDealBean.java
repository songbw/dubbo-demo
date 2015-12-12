package com.flzc.service.bean;

import com.flzc.scene.filing.api.entity.SceneDealImg;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by song on 2015/11/21.
 */
public class SceneDealBean {
        private Integer id;
        private Integer sceneId;
        private Integer houseId;
        private String houseTempletType;
        private String houseFace;
        private Integer floor;
        private Integer acreage;
        private Float dealSum;
        private Float originalSum;
        private Float couponSum;
        private Integer userId;
        private String username;
        private String name;
        private String userSex;
        private String userTelephone;
        private Integer agencyId;
        private String agencyName;
        private String agencyTelephone;
        private Float rebate;
        private Float builderReward;
        private Float flzcReward;
        private Date createAt;
        private Date updateAt;
        private Integer version;
        private List<SceneDealImg> sceneDealImgList = new ArrayList<SceneDealImg>() ;

        public Integer getId() {
            return this.id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getSceneId() {
            return this.sceneId;
        }

        public void setSceneId(Integer sceneId) {
            this.sceneId = sceneId;
        }

        public Integer getHouseId() {
            return this.houseId;
        }

        public void setHouseId(Integer houseId) {
            this.houseId = houseId;
        }

        public String getHouseTempletType() {
            return this.houseTempletType;
        }

        public void setHouseTempletType(String houseTempletType) {
            this.houseTempletType = houseTempletType;
        }

        public String getHouseFace() {
            return this.houseFace;
        }

        public void setHouseFace(String houseFace) {
            this.houseFace = houseFace;
        }

        public Integer getFloor() {
            return this.floor;
        }

        public void setFloor(Integer floor) {
            this.floor = floor;
        }

        public Integer getAcreage() {
            return this.acreage;
        }

        public void setAcreage(Integer acreage) {
            this.acreage = acreage;
        }

        public Float getDealSum() {
            return this.dealSum;
        }

        public void setDealSum(Float dealSum) {
            this.dealSum = dealSum;
        }

        public Float getOriginalSum() {
            return this.originalSum;
        }

        public void setOriginalSum(Float originalSum) {
            this.originalSum = originalSum;
        }

        public Float getCouponSum() {
            return this.couponSum;
        }

        public void setCouponSum(Float couponSum) {
            this.couponSum = couponSum;
        }

        public Integer getUserId() {
            return this.userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserSex() {
            return this.userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }

        public String getUserTelephone() {
            return this.userTelephone;
        }

        public void setUserTelephone(String userTelephone) {
            this.userTelephone = userTelephone;
        }

        public Integer getAgencyId() {
            return this.agencyId;
        }

        public void setAgencyId(Integer agencyId) {
            this.agencyId = agencyId;
        }

        public String getAgencyName() {
            return this.agencyName;
        }

        public void setAgencyName(String agencyName) {
            this.agencyName = agencyName;
        }

        public String getAgencyTelephone() {
            return this.agencyTelephone;
        }

        public void setAgencyTelephone(String agencyTelephone) {
            this.agencyTelephone = agencyTelephone;
        }

        public Float getRebate() {
            return this.rebate;
        }

        public void setRebate(Float rebate) {
            this.rebate = rebate;
        }

        public Float getBuilderReward() {
            return this.builderReward;
        }

        public void setBuilderReward(Float builderReward) {
            this.builderReward = builderReward;
        }

        public Float getFlzcReward() {
            return this.flzcReward;
        }

        public void setFlzcReward(Float flzcReward) {
            this.flzcReward = flzcReward;
        }

        public Date getCreateAt() {
            return this.createAt;
        }

        public void setCreateAt(Date createAt) {
            this.createAt = createAt;
        }

        public Date getUpdateAt() {
            return this.updateAt;
        }

        public void setUpdateAt(Date updateAt) {
            this.updateAt = updateAt;
        }

        public Integer getVersion() {
            return this.version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

    public List<SceneDealImg> getSceneDealImgList() {
        return sceneDealImgList;
    }

    public void setSceneDealImgList(List<SceneDealImg> sceneDealImgList) {
        this.sceneDealImgList = sceneDealImgList;
    }
}
