package com.example.book_rental.PayLoad;

public class ResponseData {
    private int code ;
    private String message ;
    private Object data ;
    private Boolean isTrue ;

    public ResponseData(int code, String message, Object data, Boolean isTrue) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.isTrue = isTrue;
    }

    public ResponseData() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }


}
