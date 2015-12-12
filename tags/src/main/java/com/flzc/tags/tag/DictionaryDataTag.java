package com.flzc.tags.tag;

import com.flzc.tags.bean.SystemDic;
import com.flzc.tags.service.SystemDicService;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class DictionaryDataTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String pcode;
	private String var;
	private String code;

	public String getPcode() {
		return this.pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getVar() {
		return this.var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int doStartTag() throws JspException {
		WebApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(this.pageContext.getServletContext());

		SystemDicService systemDicService = (SystemDicService) ctx.getBean(SystemDicService.class);
		if ((this.code != null) && (isBlank(this.pcode))) {
			SystemDic data = systemDicService.getSystemDic(this.code);
			this.pageContext.setAttribute(this.var, data);
		}
		if ((!isBlank(this.pcode)) && (this.code == null)) {
			List datas = systemDicService.getSystemDics(this.pcode);
			this.pageContext.setAttribute(this.var, datas);
		}
		return 1;
	}

	public int doEndTag() throws JspException {
		this.pageContext.removeAttribute(this.var);
		return 6;
	}

	private boolean isBlank(String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0))
			return true;
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
