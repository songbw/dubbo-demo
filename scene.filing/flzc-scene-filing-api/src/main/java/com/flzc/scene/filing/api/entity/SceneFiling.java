package com.flzc.scene.filing.api.entity;

import javax.persistence.*;
import java.util.Date;

/**
 *  案场报备信息
 * Created by song on 2015/11/16.
 */
@Entity
@Table(name = "scene_filing")
public class SceneFiling implements java.io.Serializable{
    //报备ID
    private Integer id ;
    //用户ID
    private Integer userId ;
    // 用户名
    private String username ;
    //用户姓名
    private String name ;
    //用户性别
    private String userSex ;
    //用户手机号
    private String userTelephone ;
    //案场ID
    private Integer sceneId ;
    //案场名称
    private String sceneName;
    //楼盘ID
    private Integer houseId;
    //楼盘名称
    private String houseName;
    //活动ID
    private Integer activityId ;
    //活动名称
    private String activityName ;
    //活动开始时间
    private Date activityStartAt;
    //活动结束时间
    private Date activityEndAt;
    //优惠券ID
    private Integer couponId;
    //优惠券名称
    private String couponName;
    //优惠券信息
    private String couponInfo;
    //优惠券金额
    private Float couponSum;
    //经纪人ID
    private Integer agencyId;
    //经纪人名称
    private String agencyName;
    //经纪人电话
    private String agencyTelephone;
    //经纪人公司ID
    private Integer agencyCompanyId ;
    //经纪人公司名称
    private String agencyCompanyName;
    //报备状态
    private Integer status;
    //是否有效
    private Integer valid ;
    //创建时间
    private Date createAt ;
    //预约看房时间
    private Date reserveAt;
    //佣金
    private Float rebate;
    //开发商打赏
    private Float builderReward;
    //房链打赏
    private Float flzcReward;
    //意向
    private String intent;
    //更新时间
    private Date updateAt;
    //版本号
    private Integer version ;
    // 原价
    private Float originalSum ;
    //经纪人报备ID
    private Integer intentId ;
    //用户头像
    private String headImg ;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "scene_id")
    public Integer getSceneId() {
        return sceneId;
    }

    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }
    @Column(name = "scene_name")
    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }
    @Column(name = "house_id")
    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }
    @Column(name = "house_name")
    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
    @Column(name = "activity_id")
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }
    @Column(name = "activity_name")
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    @Column(name = "activity_start_at")
    public Date getActivityStartAt() {
        return activityStartAt;
    }

    public void setActivityStartAt(Date activityStartAt) {
        this.activityStartAt = activityStartAt;
    }
    @Column(name = "activity_end_at")
    public Date getActivityEndAt() {
        return activityEndAt;
    }

    public void setActivityEndAt(Date activityEndAt) {
        this.activityEndAt = activityEndAt;
    }
    @Column(name = "coupon_id")
    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }
    @Column(name = "coupon_name")
    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
    @Column(name = "coupon_info")
    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }
    @Column(name = "coupon_sum")
    public Float getCouponSum() {
        return couponSum;
    }

    public void setCouponSum(Float couponSum) {
        this.couponSum = couponSum;
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
    @Column(name = "agency_company_id")
    public Integer getAgencyCompanyId() {
        return agencyCompanyId;
    }

    public void setAgencyCompanyId(Integer agencyCompanyId) {
        this.agencyCompanyId = agencyCompanyId;
    }
    @Column(name = "agency_company_name")
    public String getAgencyCompanyName() {
        return agencyCompanyName;
    }

    public void setAgencyCompanyName(String agencyCompanyName) {
        this.agencyCompanyName = agencyCompanyName;
    }
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Column(name = "valid")
    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
    @Column(name = "create_at")
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
    @Column(name = "reserve_at")
    public Date getReserveAt() {
        return reserveAt;
    }

    public void setReserveAt(Date reserveAt) {
        this.reserveAt = reserveAt;
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
    @Column(name = "intent")
    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }
    @Column(name = "update_at")
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    @Column(name = "version" )
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "original_sum")
    public Float getOriginalSum() {
        return originalSum;
    }

    public void setOriginalSum(Float originalSum) {
        this.originalSum = originalSum;
    }

    @Column(name = "intent_id")
    public Integer getIntentId() {
        return intentId;
    }

    public void setIntentId(Integer intentId) {
        this.intentId = intentId;
    }
    @Column(name = "head_img")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
