package com.example.demo.controller.analyse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 单位盘点分析
 */
@Controller
public class OrgController extends CommonController {

    /**
     * 查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/orgAnalyseQuery")
    public Object orgAnalyseQuery() {
        JSONObject json = new JSONObject();
        JSONArray resultList = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject obj = new JSONObject();
            obj.put("orgID", 1);
            obj.put("orgName", "政府部门");
            obj.put("resNum", 3);
            obj.put("prjNum", 0);
            obj.put("systemNum", 0);
            obj.put("funcNum", 0);
            obj = (JSONObject) fillNum(obj);
            resultList.add(obj);
        }
        json.put("resultList", resultList);
        return successResultByPage(json);
    }

}
