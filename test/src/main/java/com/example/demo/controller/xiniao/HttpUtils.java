package com.example.demo.controller.xiniao;

import com.alibaba.csb.sdk.ContentBody;
import com.alibaba.csb.sdk.HttpCaller;
import com.alibaba.csb.sdk.HttpCallerException;
import com.alibaba.csb.sdk.HttpParameters;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class HttpUtils {
    static int timeout = 45000;
    public static HttpClient myclient = new HttpClient();
    protected static Log logger = LogFactory.getLog(HttpUtils.class);

    /**
     * post 方式提交
     *
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, NameValuePair[] params) {
        logger.debug("接口:" + url + "--start!!!");
        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.addRequestHeader("Connection", "close");
        if (params != null) {
            postMethod.setRequestBody(params);
        }
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        try {
            int respCode = client.executeMethod(postMethod);
            logger.debug("接口:" + url + ",respCode:" + respCode);
            if (respCode == HttpStatus.SC_OK) {
                String result = postMethod.getResponseBodyAsString();

                logger.debug("接口:" + url + ",result:" + result);
                return result;
            } else {
                logger.debug("----调用接口：失败");
                return null;
            }
        } catch (Exception e) {
            logger.debug("接口:" + url + "异常:" + e.getMessage());
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    public static String postJson(String url, JSONObject jsonObject) {
        HttpClient client = new HttpClient();
        PostMethod method = null;
        try {
            method = new PostMethod(url);
            String transJson = jsonObject.toString();
            RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
            method.setRequestEntity(se);
            //使用系统提供的默认的恢复策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            //设置超时的时间
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);
            int respCode = client.executeMethod(method);
            if (respCode == HttpStatus.SC_OK) {
                String result = method.getResponseBodyAsString();
                return result;
            } else {
                logger.debug("----调用接口：失败");
                return null;
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
        return null;
    }

    /**
     * get方式提交
     *
     * @param url
     * @param params
     * @return
     */
    public static String get(String url, String params) {
        logger.debug("接口:" + url + "--start!!!");
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url + "?" + params);
        getMethod.addRequestHeader("Connection", "close");
        try {
            int respCode = client.executeMethod(getMethod);
            logger.debug("接口:" + url + ",respCode:" + respCode);
            if (respCode == HttpStatus.SC_OK) {
                String result = getMethod.getResponseBodyAsString();
                logger.debug("接口:" + url + ",result:" + result);
                return result;
            } else {
                logger.debug("----调用接口：失败");
                return null;
            }
        } catch (Exception e) {
            logger.debug("接口:" + url + "异常:" + e.getMessage());
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        }
        return null;
    }

    /**
     * 发起Ajax请求
     */
    public static JSONObject invokeAjaxCall(String url, Map<String, String> nvsMap) {
        //logger.info("Send Ajax to " + url + ": " + ApiUtils.getKVStringFromHashMap(nvsMap));

		/* 使用说明
		参数初始化
		NameValuePair[] data = { 
			new NameValuePair("domentid", ""),
			new NameValuePair("epwd",""),
			new NameValuePair("aid", ""),
			new NameValuePair("apwd", ""),
			new NameValuePair("adn", ""),
		};
	
		使用返回值
		String token = jsonobj.getString("token");
		...*/

        int size = nvsMap.size();
        NameValuePair[] nvs = new NameValuePair[size];
        int idx = 0;
        for (String key : nvsMap.keySet()) {
            nvs[idx++] = new NameValuePair(key, nvsMap.get(key));
        }

        HttpClient client = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");

        //20160406  确保断开连接后关闭socket
        postMethod.addRequestHeader("Connection", "close");
        //client.getParams().setBooleanParameter( "http.protocol.expect-continue" , false);

        postMethod.setRequestBody(nvs);
        try {
            int respCode = client.executeMethod(postMethod);
            if (respCode == HttpStatus.SC_OK) {
                String result = postMethod.getResponseBodyAsString();
                JSONObject obj = JSONObject.parseObject(result);
                return obj;
            }
        } catch (Exception e) {
            //20161201
            logger.error("invokeAjaxCall to " + url + " fail: " + e.getMessage());
        } finally {
            postMethod.releaseConnection();
        }

        return null;
    }

    public static String scbpost(String url, String datas) {
        HttpParameters.Builder builder = new HttpParameters.Builder();
        builder.requestURL(url) // 设置请求的URL
                .api("") // 设置服务名
                .version("1.0.0") // 设置版本号
                .method("post") // 设置调用方式, get/post
                .accessKey(null).secretKey(null); // 设置accessKey 和 设置secretKey
        builder.putParamsMap("data", datas);
        try {
            String ret = HttpCaller.invoke(builder.build());
            //JSONObject obj = JSONObject.fromObject(ret);
            //成功
            return ret;
        } catch (HttpCallerException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 表单提交
     *
     * @param url
     * @param params
     * @return
     */
    public static String scbformpost(String url, Map<String, String> params) {
        HttpParameters.Builder builder = new HttpParameters.Builder();
        builder.requestURL(url) // 设置请求的URL
                .api("") // 设置服务名
                .version("1.0.0") // 设置版本号
                .method("post") // 设置调用方式, get/post
                .accessKey(null).secretKey(null); // 设置accessKey 和 设置secretKey
        builder.putParamsMapAll(params);
//		builder.contentBody(cb);
        try {
            String ret = HttpCaller.invoke(builder.build());
            JSONObject obj = JSONObject.parseObject(ret);
            //成功
            return ret;
        } catch (HttpCallerException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * json  提交
     *
     * @param url
     * @param jsonStr
     * @return
     */
    public static String scbjsonpost(String url, String jsonStr) {
        HttpParameters.Builder builder = new HttpParameters.Builder();
        builder.requestURL(url) // 设置请求的URL
                .api("") // 设置服务名
                .version("1.0.0") // 设置版本号
                .method("post") // 设置调用方式, get/post
                .accessKey(null).secretKey(null); // 设置accessKey 和 设置secretKey

        builder.contentBody(new ContentBody(jsonStr));
        try {
            String ret = HttpCaller.invoke(builder.build());
            return ret;
        } catch (HttpCallerException e) {
            e.printStackTrace();
        }
        return "";
    }


}
