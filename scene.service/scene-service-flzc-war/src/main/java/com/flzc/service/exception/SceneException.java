package com.flzc.service.exception;

import com.flzc.service.bean.SceneInfoBean;

/**
 * Created by song on 2015/11/19.
 */
public class SceneException extends Exception {
    private static final long serialVersionUID = 1L;
    public ErrorBean errorBean = null ;

    public SceneException() {
        super();
    }

    public SceneException(ErrorBean errorBean) {
        this.errorBean = errorBean ;
    }

    public ErrorBean getErrorBean(){
        return errorBean ;
    }
}
