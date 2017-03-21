package com.caesar.checkdemo.model;

import java.util.List;

/**
 * Created by caesar on 2016/12/16.
 * 修订历史:
 */
public class UploadPicture1 {

    /**
     * status : 1
     * msg : 上传成功
     * data : []
     */

    private String status;
    private String msg;
    private List<?> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
