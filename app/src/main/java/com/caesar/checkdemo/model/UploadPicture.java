package com.caesar.checkdemo.model;

import java.util.List;

/**
 * Description:
 * Function:
 * Created by caesar on 2016/11/28.
 * 修订历史:
 */
public class UploadPicture {

    /**
     * code : 1
     * status : 1
     * msg : 上传成功
     * data : []
     */

    private String code;
    private String status;
    private String msg;
    private List<?> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
