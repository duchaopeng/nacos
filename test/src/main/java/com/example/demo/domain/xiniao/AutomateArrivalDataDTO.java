package com.example.demo.domain.xiniao;

/**
 * 返回结果明细
 */
public class AutomateArrivalDataDTO {
    /**
     * 共配中⼼
     */
    private String scCode;
    /**
     * ⾃动化设备编码
     */
    private String automateDeviceId;
    /**
     * 供包台设备Id
     */
    private String deviceId;
    /**
     * 分拣任务编号
     */
    private String taskId;
    /**
     * 分拣⽅案名称
     */
    private String schemeName;
    /**
     * 快递编码
     */
    private String cpCode;
    /**
     * 运单号
     */
    private String waybillNo;
    /**
     * 分配格⼝号
     */
    private String cellCode;
    /**
     * 实际格⼝号
     */
    private String actualCellCode;
    /**
     * 识别状态
     */
    private String identifyStatus;
    /**
     * 分拣状态
     */
    private String sortingStatus;
    /**
     * 拦截件标识
     */
    private String intercept;
    /**
     * 增值件标识
     */
    private String appreciation;
    /**
     * 供包台编号
     */
    private String gbtNum;
    /**
     * 准确落格标识
     */
    private String isSame;
    /**
     * 供包时间
     */
    private String inboundTime;
    /**
     * 落格时间
     */
    private String outboundTime;
    /**
     * 重量
     */
    private String weight;
    /**
     * 包袋号
     */
    private String packageNum;

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public String getWaybillNo() {
        return waybillNo;
    }

    public void setWaybillNo(String waybillNo) {
        this.waybillNo = waybillNo;
    }

    public String getCellCode() {
        return cellCode;
    }

    public void setCellCode(String cellCode) {
        this.cellCode = cellCode;
    }

    public String getActualCellCode() {
        return actualCellCode;
    }

    public void setActualCellCode(String actualCellCode) {
        this.actualCellCode = actualCellCode;
    }

    public String getIdentifyStatus() {
        return identifyStatus;
    }

    public void setIdentifyStatus(String identifyStatus) {
        this.identifyStatus = identifyStatus;
    }

    public String getSortingStatus() {
        return sortingStatus;
    }

    public void setSortingStatus(String sortingStatus) {
        this.sortingStatus = sortingStatus;
    }

    public String getIntercept() {
        return intercept;
    }

    public void setIntercept(String intercept) {
        this.intercept = intercept;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public String getGbtNum() {
        return gbtNum;
    }

    public void setGbtNum(String gbtNum) {
        this.gbtNum = gbtNum;
    }

    public String getIsSame() {
        return isSame;
    }

    public void setIsSame(String isSame) {
        this.isSame = isSame;
    }

    public String getInboundTime() {
        return inboundTime;
    }

    public void setInboundTime(String inboundTime) {
        this.inboundTime = inboundTime;
    }

    public String getOutboundTime() {
        return outboundTime;
    }

    public void setOutboundTime(String outboundTime) {
        this.outboundTime = outboundTime;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(String packageNum) {
        this.packageNum = packageNum;
    }
}
