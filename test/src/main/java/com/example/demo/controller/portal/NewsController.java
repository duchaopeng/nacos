package com.example.demo.controller.portal;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.CommonController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * news
 */
@Controller
public class NewsController extends CommonController {

    /**
     * 查询
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/portal/news/query")
    public Object resourceAnalyseQuery() {
        JSONObject json = new JSONObject();
        JSONArray resultList = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject obj = new JSONObject();
            obj.put("newsId", "newsID");
            obj.put("newsTitle", "资讯标题");
            obj.put("newsType", "资讯分类");
            obj.put("newsAbstract", "资讯摘要");
            obj.put("newsContent", "资讯内容");
            obj.put("newsCover", "资讯封面路径");
            obj.put("status", "状态");
            obj.put("visits", "访问量");
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
    @RequestMapping("/portal/news/detail")
    public Object companyAnalyseQueryDetail() {
        JSONObject obj = new JSONObject();
        obj.put("newsId", "newsID");
        obj.put("newsTitle", "资讯标题");
        obj.put("newsType", "资讯分类");
        obj.put("status", "状态");
        obj.put("newsAbstract", "资讯摘要");
        obj.put("newsContent", "资讯内容");
        obj.put("newsCover", "资讯封面路径");
        return successResult(obj);
    }


    /**
     * 保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/portal/news/save")
    public Object flowDirectionQuery() {
        JSONObject json = new JSONObject();
        json.put("newsId", "newsID");
        return successResult(json);
    }
}
