package com.example.demo.controller.analyse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 数字资源分析
 */
@Controller
public class ResourceController extends CommonController {

    /**
     * 数字资源查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/resourceAnalyseQuery")
    public Object resourceAnalyseQuery() {
        JSONObject json = new JSONObject();
        JSONArray resultList = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject obj = new JSONObject();
            obj.put("funcID", "职能事项ID");

            obj = (JSONObject) fillNum(obj);
            resultList.add(obj);
        }
        json.put("resultList", resultList);
        return successResultByPage(json);
    }

    /**
     * 数字资源明细
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/resourceQueryDetail")
    public Object companyAnalyseQueryDetail() {
        JSONObject json = new JSONObject();
        JSONArray resultList = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject obj = new JSONObject();
            obj.put("resID", 1);
            obj.put("resName", "应用资源名称");
            obj.put("resDesc", "这是一段数字资源简介这是一段数字资源简介");
            obj.put("resType", "应用");
            obj.put("resCode", "名称#1K3123GDKL");
            obj.put("registrant", "华为");
            obj.put("orgCode", "所属单位编码");
            obj.put("orgName", "所属单位名称");
            resultList.add(obj);
        }
        json.put("resultList", resultList);
        return successResult(json);
    }


    /**
     * 流量流向
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/flowDirectionQuery")
    public Object flowDirectionQuery() {
        JSONObject json = new JSONObject();
        JSONArray resultList = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject obj = new JSONObject();
            obj.put("prjName", "系统名称");
            obj.put("resVersion", "V1.5");
            obj.put("publisher", "WEB端");

//            JSONArray labelList = new JSONArray();
//            JSONObject label = new JSONObject();
//            label.put("resTag", "活体检测");
//            labelList.add(label);
//            JSONObject label2 = new JSONObject();
//            label.put("name", "重点");
//            labelList.add(label2);
//            obj.put("resTag", labelList);
            obj.put("resTag", "活体检测,重点");

            obj.put("sourceOrgName", "单位名称");
            obj.put("sourcePrj", "项目名称");
            obj.put("sourceSystem", "系统名称");
            obj.put("useOrgName", "单位名称");
            obj.put("usePrj", "项目名称");
            obj.put("useSystem", "系统名称");
            resultList.add(obj);
        }
        json.put("resultList", resultList);
        return successResult(json);
    }
}
