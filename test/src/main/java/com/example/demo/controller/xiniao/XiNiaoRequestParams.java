package com.example.demo.controller.xiniao;

/**
 * Created by wsl on 2022/12/13.
 */
public class XiNiaoRequestParams {
    private String scCode;              //共配中心编码
    private String automateDeviceId;  //自动化设备编码
    private String cpCode;             //快递公司编码
    private String startOperateTime;  //开始分拣时间如不传递，默认以当天0点为准
    private String endOperateTime;    //结束分拣时间
    private String waybillNos;         //包裹运单列表，传递多个则以逗号隔开
    private Integer pageNum;          //分页页码

    public String getScCode() {
        return scCode;
    }

    public void setScCode(String scCode) {
        this.scCode = scCode;
    }

    public String getAutomateDeviceId() {
        return automateDeviceId;
    }

    public void setAutomateDeviceId(String automateDeviceId) {
        this.automateDeviceId = automateDeviceId;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public String getStartOperateTime() {
        return startOperateTime;
    }

    public void setStartOperateTime(String startOperateTime) {
        this.startOperateTime = startOperateTime;
    }

    public String getEndOperateTime() {
        return endOperateTime;
    }

    public void setEndOperateTime(String endOperateTime) {
        this.endOperateTime = endOperateTime;
    }

    public String getWaybillNos() {
        return waybillNos;
    }

    public void setWaybillNos(String waybillNos) {
        this.waybillNos = waybillNos;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
