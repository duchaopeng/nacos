package com.example.demo.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * restTemplate输出日志
 *
 * @author wl
 */
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final ThreadLocal<RequestLog> REQUEST_LOG = new ThreadLocal<RequestLog>();

    private final static Logger logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        RequestLog log = new RequestLog();
        log.setUri(request.getURI().toString());
        log.setMethod(request.getMethod().toString());
        log.setRequestHeader(JSON.toJSONString(request.getHeaders()));
        log.setRequestBody(new String(body, "UTF-8"));
        REQUEST_LOG.set(log);
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        RequestLog log = REQUEST_LOG.get();
        log.setStatusCode(response.getStatusCode().toString());
        log.setStatusText(response.getStatusText());
        log.setResponseHeader(JSON.toJSONString(response.getHeaders()));
        log.setResponseBody(IOUtils.toString(response.getBody(), "UTF-8"));
        logger.info(log.toString());
        REQUEST_LOG.remove();
    }

    @Data
    public static class RequestLog {

        private static final String fmt = "\n===========================request===========================\nURI          : %s\nMethod       : %s\nHeaders      : %s\nRequest body : %s\nStatus code  : %s\nStatus text  : %s\nHeaders      : %s\nResponse body: %s\nUsing time   : %sms\n===========================request===========================\n";

        private String uri;
        private String method;
        private String requestHeader;
        private String requestBody;
        private String statusCode;
        private String statusText;
        private String responseHeader;
        private String responseBody;

        private long start = System.currentTimeMillis();
    }
}