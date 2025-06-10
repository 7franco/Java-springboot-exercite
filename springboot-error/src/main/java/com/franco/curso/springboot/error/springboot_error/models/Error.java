package com.franco.curso.springboot.error.springboot_error.models;

import java.util.Date;

public class Error {

    private String message;
    private String error;
    private int status;
    private Date date;

    
    public Error() {
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getError() {
        return error;
    }
    public void setError(String erros) {
        this.error = erros;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    

}
