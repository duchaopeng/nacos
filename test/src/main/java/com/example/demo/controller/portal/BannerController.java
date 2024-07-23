package com.example.demo.controller.portal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * banner
 */
@Controller
public class BannerController extends CommonController {

    /**
     * 查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/portal/banner/query")
    public Object resourceAnalyseQuery() {
        JSONObject json = new JSONObject();
        JSONArray resultList = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject obj = new JSONObject();
            obj.put("bannerId", "BannerID");
            obj.put("bannerName", "栏目名称");
            obj.put("position", "Banner位置");
            JSONArray imgList = new JSONArray();
            for (int j = 0; j < 3; j++) {
                JSONObject img = new JSONObject();
                img.put("fileId", "图片名称");
                img.put("fileName", "图片地址");
                img.put("fileUrl", "图片路径");
                imgList.add(img);
            }
            obj.put("imgList", imgList);
            obj.put("sortNum", "排序值");
            obj.put("status", "栏目状态");
            obj.put("createBy", "创建人");
            obj.put("createTime", "创建时间");
            resultList.add(obj);
        }
        json.put("resultList", resultList);
        return successResultByPage(json);
    }

    /**
     * 明细
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/portal/banner/detail")
    public Object companyAnalyseQueryDetail() {
        JSONObject json = new JSONObject();
        json.put("bannerId", "BannerID");
        json.put("bannerName", "栏目名称");
        json.put("position", "Banner位置");
        JSONArray imgList = new JSONArray();
        for (int j = 0; j < 3; j++) {
            JSONObject img = new JSONObject();
            img.put("fileId", "图片名称");
            img.put("fileName", "图片地址");
            img.put("fileUrl", "图片路径");
            imgList.add(img);
        }
        json.put("imgList", imgList);
        json.put("sortNum", "排序值");
        json.put("status", "栏目状态");
        json.put("status", "栏目状态");
        json.put("desc", "备注");
        return successResult(json);
    }


    /**
     * 保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/portal/banner/save")
    public Object flowDirectionQuery() {
        JSONObject json = new JSONObject();
        json.put("bannerId", "BannerID");
        return successResult(json);
    }
}
