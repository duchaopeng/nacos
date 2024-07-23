package com.example.demo.domain.xiniao;

/**
 * 溪鸟参数
 */
public class XiNiaoParamVo {
    /**
     * 对接码
     */
    private String logistic_provider_id;
    /**
     * 签名
     */
    private String data_digest;
    /**
     * 业务报文 IotAutomateArrivalDataQueryDTO
     */
    private String logistics_interface;

    public String isLogistic_provider_id() {
        return logistic_provider_id;
    }

    public void setLogistic_provider_id(String logistic_provider_id) {
        this.logistic_provider_id = logistic_provider_id;
    }

    public String getData_digest() {
        return data_digest;
    }

    public void setData_digest(String data_digest) {
        this.data_digest = data_digest;
    }

    public String getLogistic_provider_id() {
        return logistic_provider_id;
    }

    public String getLogistics_interface() {
        return logistics_interface;
    }

    public void setLogistics_interface(String logistics_interface) {
        this.logistics_interface = logistics_interface;
    }
}
