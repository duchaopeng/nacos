package com.example.demo.controller.xiniao;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.SignUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试获取溪鸟数据
 */
@Log4j2
@Controller
@RequestMapping("/api/v1")
public class ApiController {

    public static final String XINNIAO_HOST = "https://edi.56xiniao.com/";
    public static final String XINNIAO_SECRETKEY = "039f8e8964fe4590a63cec147beca43b";

//    automateDeviceId：MOUTH1665904366900
//    sccode：SC_30240334
//    logistic_provider_id：HNEMS
//    secretkey：039f8e8964fe4590a63cec147beca43b


    /**
     * 获取溪鸟数据
     * http://127.0.0.1:8700//api/v1/getData?startOperateTime=2023-09-14
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getData")
    public Object getData(HttpServletRequest request, HttpServletResponse response) {
        try {
            XiNiaoRequestParams info = new XiNiaoRequestParams();
            info.setAutomateDeviceId("MOUTH1665904366900");
            info.setScCode("SC_30240334");
            info.setPageNum(1);
            String startOperateTime = request.getParameter("startOperateTime");
            if (StringUtils.isNotEmpty(startOperateTime)) {
                info.setStartOperateTime(startOperateTime);
            }
            Map<String, String> params = new HashMap<>();
            String logistics_json = JSONObject.toJSONString(info);
            log.info("logistics_json = {} ", logistics_json);
            params.put("logistic_provider_id", "HNEMS");
            params.put("logistics_interface", logistics_json);
            params.put("data_digest", SignUtil.doSign(logistics_json, "UTF-8", XINNIAO_SECRETKEY));
            log.info("params = {} ", JSONObject.toJSONString(params));
            String result = HttpUtils.scbformpost(XINNIAO_HOST + "/api/queryAutomateArrivalData", params);
            log.info("result = {} ", result);
            JSONObject retJson = JSONObject.parseObject(result);
            return retJson.getJSONObject("data");
        } catch (Exception e) {
            log.info("接口异常->" + e.getMessage());
            return "接口异常";
        }
    }
}
