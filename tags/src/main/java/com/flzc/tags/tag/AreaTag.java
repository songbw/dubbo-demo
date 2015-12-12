package com.flzc.tags.tag;

import com.flzc.tags.bean.Area;
import com.flzc.tags.service.AddressService;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AreaTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Integer cityId;
	private String var;
	private Integer areaId;

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getVar() {
		return this.var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public static long getSerialversionuid() {
		return 1L;
	}

	public int doStartTag() throws JspException {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.pageContext.getServletContext());
		AddressService addressService = (AddressService) ctx.getBean(AddressService.class);
		if ((this.areaId != null) && (this.cityId == null)) {
			Area data = addressService.getAreaFromMemcached(this.areaId);
			this.pageContext.setAttribute(this.var, data);
		}
		if ((this.areaId == null) && (this.cityId != null)) {
			List datas = addressService.getAreasFromMemcached(this.cityId);
			this.pageContext.setAttribute(this.var, datas);
		}
		return 1;
	}

	public int doEndTag() throws JspException {
		this.pageContext.removeAttribute(this.var);
		return 6;
	}
}
