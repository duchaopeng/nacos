package com.example.demo.controller.analyse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 职能事项盘点分析
 */
@Controller
public class FunctionController extends CommonController {

    /**
     * 查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/functionAnalyseQuery")
    public Object companyAnalyseQuery() {
        JSONObject json = new JSONObject();
        JSONArray resultList = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject obj = new JSONObject();
            obj.put("funcID", 1);
            obj.put("funcNma", "职能事项名称");
            obj.put("resNum", 3);
            obj.put("prjNum", 0);
            obj.put("systemNum", 0);
            obj = (JSONObject) fillNum(obj);
            resultList.add(obj);
        }
        json.put("resultList", resultList);
        return successResultByPage(json);
    }

}
