package com.example.demo.controller.portal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 栏目
 */
@Controller
public class ProgramController extends CommonController {

    /**
     * 查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/portal/program/Query")
    public Object resourceAnalyseQuery() {
        JSONObject json = new JSONObject();
        JSONArray resultList = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject obj = new JSONObject();
            obj.put("programId", "栏目ID");
            obj.put("programName", "栏目名称");
            obj.put("pProgramId", "所属栏目id");
            obj.put("pProgramName", "所属栏目");
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
    @RequestMapping("/portal/program/detail")
    public Object companyAnalyseQueryDetail() {
        JSONObject json = new JSONObject();
        json.put("programId", "栏目ID");
        json.put("programName", "栏目名称");
        json.put("pProgramId", "所属栏目id");
        json.put("pProgramName", "所属栏目");
        json.put("sortNum", "排序值");
        json.put("status", "栏目状态");
        return successResult(json);
    }


    /**
     * 保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/portal/program/save")
    public Object flowDirectionQuery() {
        JSONObject json = new JSONObject();
        json.put("programId", "栏目ID");
        return successResult(json);
    }
}
