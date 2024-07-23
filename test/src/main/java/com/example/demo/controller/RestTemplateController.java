package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.xiniao.XiNiaoRequestParams;
import com.example.demo.domain.xiniao.XiNiaoResultVo;
import com.example.demo.util.SignUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 使用 RestTemplate进行https调用
 */
@Log4j2
@Controller
public class RestTemplateController {
    @Autowired
    private RestTemplate restTemplate;

    public static final String XINNIAO_URL = "https://edi.56xiniao.com/api/queryAutomateArrivalData";
    public static final String XINNIAO_SECRETKEY = "039f8e8964fe4590a63cec147beca43b";
    public static final String XINNIAO_AUTOMATE_DEVICE_ID = "MOUTH1665904366900";
    public static final String XINNIAO_SC_CODE = "SC_30240334";

    /**
     * http://127.0.0.1:8700/getXiNiaoData
     *
     * @param request
     * @return
     */
    @GetMapping("/getXiNiaoData")
    @ResponseBody
    public Object getXiNiaoData(HttpServletRequest request) {
        XiNiaoRequestParams info = new XiNiaoRequestParams();
        info.setAutomateDeviceId(XINNIAO_AUTOMATE_DEVICE_ID);
        info.setScCode(XINNIAO_SC_CODE);
        info.setPageNum(1);
        String startOperateTime = request.getParameter("startOperateTime");
        if (StringUtils.isNotEmpty(startOperateTime)) {
            info.setStartOperateTime(startOperateTime);
        }
        JSONObject params = new JSONObject();
        String logistics_json = JSONObject.toJSONString(info);
        log.info("logistics_json = {} ", logistics_json);
        params.put("logistic_provider_id", "HNEMS");
        params.put("logistics_interface", logistics_json);
        params.put("data_digest", SignUtil.doSign(logistics_json, "UTF-8", XINNIAO_SECRETKEY));
        log.info("params = {} ", params.toJSONString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(params.toJSONString(), headers);
        XiNiaoResultVo xiNiaoResultVo = restTemplate.postForObject(XINNIAO_URL, entity, XiNiaoResultVo.class);
        return xiNiaoResultVo;
    }
}
