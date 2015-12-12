package com.flzc.tags.tag;

import com.flzc.tags.bean.City;
import com.flzc.tags.service.AddressService;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CityTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private Integer provId;
	private String var;
	private Integer cityId;

	public Integer getProvId() {
		return this.provId;
	}

	public void setProvId(Integer provId) {
		this.provId = provId;
	}

	public String getVar() {
		return this.var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public static long getSerialversionuid() {
		return 1L;
	}

	public int doStartTag() throws JspException {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.pageContext.getServletContext());
		AddressService addressService = (AddressService) ctx.getBean(AddressService.class);
		if ((this.cityId != null) && (this.provId == null)) {
			City data = addressService.getCityFromMemcached(this.cityId);
			this.pageContext.setAttribute(this.var, data);
		}
		if ((this.provId != null) && (this.cityId == null)) {
			List datas = addressService.getCitysFromMemcached(this.provId);
			this.pageContext.setAttribute(this.var, datas);
		}
		return 1;
	}

	public int doEndTag() throws JspException {
		this.pageContext.removeAttribute(this.var);
		return 6;
	}
}
