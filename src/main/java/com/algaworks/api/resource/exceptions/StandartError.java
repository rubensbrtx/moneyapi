package com.algaworks.api.resource.exceptions;

import java.io.Serializable;

public class StandartError implements Serializable {

    /***
     * Classe para um erro padrao do projeto
     */

    private static final long serialVersionUID = 1L;

    private Integer status;
    private Object msg;
    private Long timeStamp;

    public StandartError(Integer status, Object msg, Long timeStamp) {
        super();
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
