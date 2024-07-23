package com.example.demo.controller.portal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公告
 */
@Controller
public class NoticeController extends CommonController {

    /**
     * 查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/portal/notice/query")
    public Object resourceAnalyseQuery() {
        JSONObject json = new JSONObject();
        JSONArray resultList = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject obj = new JSONObject();
            obj.put("noticeId", "公告ID");
            obj.put("noticeTitle", "标题");
            obj.put("noticeType", "分类");
            obj.put("effectiveDate", "生效日期");
            obj.put("expirationDate", "失效日期");
            obj.put("top", "是否置顶");
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
    @RequestMapping("/portal/notice/detail")
    public Object companyAnalyseQueryDetail() {
        JSONObject obj = new JSONObject();
        obj.put("noticeId", "公告ID");
        obj.put("noticeTitle", "标题");
        obj.put("noticeType", "分类");
        obj.put("effectiveDate", "生效日期");
        obj.put("expirationDate", "失效日期");
        obj.put("top", "是否置顶");
        return successResult(obj);
    }


    /**
     * 保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/portal/notice/save")
    public Object flowDirectionQuery() {
        JSONObject json = new JSONObject();
        json.put("noticeId", "noticeID");
        return successResult(json);
    }
}
