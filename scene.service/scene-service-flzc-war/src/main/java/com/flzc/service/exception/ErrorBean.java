package com.flzc.service.exception;

import java.io.Serializable;

/**
 *   异常错误码对象
 * Created by song on 2015/11/19.
 */
public class ErrorBean implements Serializable {
    private String errno ;
    private String msg ;
    private Integer status = 1 ;

    public String getErrno() {
        return errno;
    }

    public void setErrno(String errno) {
        this.errno = errno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
