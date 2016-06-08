package com.example;

import java.util.List;


public class ResultT<T> {

    /**
     * code : 0
     * message : success
     * data : [] or data:{}
     */

    private String code;
    private String message;
    //T是Person或List<Person>
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultT{" +
                       "code='" + code + '\'' +
                       ", message='" + message + '\'' +
                       ", data=" + data +
                       '}';
    }
}
