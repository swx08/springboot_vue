package com.springboot.utils;

import org.apache.xmlbeans.SchemaTypeSystem;

/**
 * ClassName:ResultType
 * Package:com.springboot.utils
 * Description
 *
 * @Author:@wenxueshi
 * @Create:2023/1/28 - 12:12
 * @Version:v1.0
 * 返回给前端的json数据类型
 */
public class ResultType<E> {
    private Integer statue;
    private String message;
    private E data;

    public ResultType(){

    };

    public ResultType(Integer statue){
        this.statue = statue;
    }

    public ResultType(Throwable e){
        this.message = e.getMessage();
    }

    public ResultType(Integer statue, E data){
        this.statue = statue;
        this.data = data;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
