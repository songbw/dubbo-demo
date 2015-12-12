package com.flzc.scene.filing.api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *  成交信息
 * Created by song on 2015/11/17.
 */
@Entity
@Table(name = "scene_deal")
public class SceneDeal implements Serializable{
    //成交ID
    private Integer id ;
    //案场ID
    private Integer sceneId;
    //楼盘ID
    private Integer houseId;
    //合同图片
//    private List<SceneDealImg> dealImg;
    //户型
    private String houseTempletType;
    //朝向
    private String houseFace;
    //楼层
    private Integer floor ;
    //面积
    private Float acreage ;
    //成交金额
    private Float dealSum;
    //原价
    private Float originalSum ;
    //优惠金额
    private Float couponSum;
    //用户ID
    private Integer userId ;
    //用户名
    private String username;
    //用户姓名
    private String name;
    //用户性别
    private String userSex;
    //用户手机号
    private String userTelephone ;
    //经纪人ID
    private Integer agencyId;
    //经纪人姓名
    private String agencyName ;
    //经纪人手机号
    private String agencyTelephone ;
    //佣金
    private Float rebate;
    //开发商打赏
    private Float builderReward;
    //房链打赏
    private Float flzcReward;
    //创建时间
    private Date createAt;
    //更新时间
    private Date updateAt;
    //版本号
    private Integer version ;
    //报备ID
    private Integer filingId ;
    //房链券汇ID
    private Integer ticketId ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "scene_id")
    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }
    @Column(name = "house_id")
    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    @Column(name = "house_templet_type")
    public String getHouseTempletType() {
        return houseTempletType;
    }

    public void setHouseTempletType(String houseTempletType) {
        this.houseTempletType = houseTempletType;
    }
    @Column(name = "house_face")
    public String getHouseFace() {
        return houseFace;
    }

    public void setHouseFace(String houseFace) {
        this.houseFace = houseFace;
    }
    @Column(name = "floor")
    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
    @Column(name = "acreage")
    public Float getAcreage() {
        return acreage;
    }

    public void setAcreage(Float acreage) {
        this.acreage = acreage;
    }
    @Column(name = "deal_sum")
    public Float getDealSum() {
        return dealSum;
    }

    public void setDealSum(Float dealSum) {
        this.dealSum = dealSum;
    }
    @Column(name = "original_sum")
    public Float getOriginalSum() {
        return originalSum;
    }

    public void setOriginalSum(Float originalSum) {
        this.originalSum = originalSum;
    }
    @Column(name = "coupon_sum")
    public Float getCouponSum() {
        return couponSum;
    }

    public void setCouponSum(Float couponSum) {
        this.couponSum = couponSum;
    }
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "user_sex")
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    @Column(name = "user_telephone")
    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }
    @Column(name = "agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }
    @Column(name = "agency_name")
    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    @Column(name = "agency_telephone")
    public String getAgencyTelephone() {
        return agencyTelephone;
    }

    public void setAgencyTelephone(String agencyTelephone) {
        this.agencyTelephone = agencyTelephone;
    }
    @Column(name = "rebate")
    public Float getRebate() {
        return rebate;
    }

    public void setRebate(Float rebate) {
        this.rebate = rebate;
    }
    @Column(name = "builder_reward")
    public Float getBuilderReward() {
        return builderReward;
    }

    public void setBuilderReward(Float builderReward) {
        this.builderReward = builderReward;
    }
    @Column(name = "flzc_reward")
    public Float getFlzcReward() {
        return flzcReward;
    }

    public void setFlzcReward(Float flzcReward) {
        this.flzcReward = flzcReward;
    }
    @Column(name = "create_at")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    @Column(name = "update_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "filing_id")
    public Integer getFilingId() {
        return filingId;
    }

    public void setFilingId(Integer filingId) {
        this.filingId = filingId;
    }

    @Column(name = "ticket_id")
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }
}
