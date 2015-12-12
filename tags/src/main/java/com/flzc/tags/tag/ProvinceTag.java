package com.flzc.tags.tag;

import com.flzc.tags.bean.Province;
import com.flzc.tags.service.AddressService;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ProvinceTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String var;
	private Integer provId;

	public String getVar() {
		return this.var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public Integer getProvId() {
		return this.provId;
	}

	public void setProvId(Integer provId) {
		this.provId = provId;
	}

	public static long getSerialversionuid() {
		return 1L;
	}

	public int doStartTag() throws JspException {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.pageContext.getServletContext());
		AddressService addressService = (AddressService) ctx.getBean(AddressService.class);
		if (this.provId != null) {
			Province datas = addressService.getProvinceFromMemcached(this.provId);
			this.pageContext.setAttribute(this.var, datas);
		} else {
			List datas = addressService.getProvinceFromMemcached();
			this.pageContext.setAttribute(this.var, datas);
		}

		return 1;
	}

	public int doEndTag() throws JspException {
		this.pageContext.removeAttribute(this.var);
		return 6;
	}
}
