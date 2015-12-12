package com.flzc.service.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *   异常类
 * Created by song on 2015/11/19.
 */
public class SceneResponseException extends WebApplicationException {

    private static final long serialVersionUID = 1L;

    public SceneResponseException() {
    }

    public SceneResponseException(Exception e) {
        super(Response.serverError().status(200).entity(e).build());
    }

    public SceneResponseException(ErrorBean errorBean) {
        super(Response.serverError().status(200).entity(errorBean).build());
    }
}
