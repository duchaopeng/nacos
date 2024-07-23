package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;

/**
 * CommonController公众类
 */
public class CommonController {
    /**
     * 返回数据通用
     *
     * @param data
     * @return
     */
    public Object successResult(JSONObject data) {
        JSONObject json = new JSONObject();
        json.put("code", 200);
        json.put("msg", "请求成功");
        json.put("data", data);
        return json;
    }

    /**
     * 返回数据通用
     *
     * @param data
     * @return
     */
    public Object successResultByPage(JSONObject data) {
        data.put("pageNo", 1);
        data.put("pageSize", 10);
        data.put("totals", 3);
        return successResult(data);
    }

    /**
     * 填充共用的数量
     *
     * @param data
     * @return
     */
    public Object fillNum(JSONObject data) {
        data.put("applicationNum", 0);
        data.put("moduleNum", 0);
        data.put("cloudNetWorkNum", 0);
        data.put("dataNum", 0);
        data.put("videoNum", 0);
        data.put("AINum", 0);
        data.put("GISNum", 0);
        return data;
    }
}
