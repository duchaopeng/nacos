package com.example.demo.domain.xiniao;

/**
 * 溪鸟返回结果
 */
public class XiNiaoResultVo {
    private boolean success;
    private String errorCode;
    private String errorMsg;
    private PageInfo data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public PageInfo getData() {
        return data;
    }

    public void setData(PageInfo data) {
        this.data = data;
    }
}
