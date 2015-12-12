package com.flzc.base.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 左侧菜单
 * @ClassName: AppMenu 
 * @Description: TODO
 * @author: chenqi
 * @date: 2015年8月4日 上午11:26:07
 */
@Entity
@Table(name="app_menu")
public class AppMenu implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 5904510655086311376L;

	//id
	private Integer id;
	
	//appid
	private Integer appId;
	
	//左侧菜单名
	private String menuName;
	
	//类型
	private String menuType;

	//地址
    private String menuUrl;
    
    //父id
    private Long parentId;

    //排序
    private String sort;

    //创建时间
    private Date createDate;
    
    //描述
    private String description;
    
    private String elements;
    
    public AppMenu(){}
    
    public AppMenu(Integer id){
    	this.id = id;
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
	
		return id;
	}

	
	public void setId(Integer id) {
	
		this.id = id;
	}

	@Column(name="app_id")
	public Integer getAppId() {
	
		return appId;
	}

	
	public void setAppId(Integer appId) {
	
		this.appId = appId;
	}
	
	@Column(name="elements")
	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
	}

	@Column(name="menu_name")
	public String getMenuName() {
	
		return menuName;
	}

	
	public void setMenuName(String menuName) {
	
		this.menuName = menuName;
	}


	@Column(name="menu_type")
	public String getMenuType() {
	
		return menuType;
	}


	
	public void setMenuType(String menuType) {
	
		this.menuType = menuType;
	}


	@Column(name="menu_url")
	public String getMenuUrl() {
	
		return menuUrl;
	}


	
	public void setMenuUrl(String menuUrl) {
	
		this.menuUrl = menuUrl;
	}


	@Column(name="parent_id")
	public Long getParentId() {
	
		return parentId;
	}


	
	public void setParentId(Long parentId) {
	
		this.parentId = parentId;
	}


	
	public String getSort() {
	
		return sort;
	}


	
	public void setSort(String sort) {
	
		this.sort = sort;
	}


	@Column(name="create_date")
	public Date getCreateDate() {
	
		return createDate;
	}


	
	public void setCreateDate(Date createDate) {
	
		this.createDate = createDate;
	}


	
	public String getDescription() {
	
		return description;
	}


	
	public void setDescription(String description) {
	
		this.description = description;
	}
	
}
