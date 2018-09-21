package com.itunion.springbootstartermybatis.web.dto;

import java.io.Serializable;
import java.util.Date;

public class UserInfoReqListDto implements Serializable{
    private static final long serialVersionUID = 3200000027712714326L;
    //实现序列化 必须
    private int page = 1;
    private int size = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "UserInfoReqListDto{" +
                "page=" + page +
                ", size=" + size +
                '}';
    }
}