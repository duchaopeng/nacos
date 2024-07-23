package com.example.demo.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

public class SignUtil {
    /**
     * @param content 加密报文 * @param charset 编码，UTF-8 * @param secretKey 密钥 * @return
     */
    public static String doSign(String content, String charset, String secretKey) {
        String sign = "";
        content = content + secretKey;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content.getBytes(charset));
            sign = new String(Base64.encodeBase64(md.digest()), charset);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sign;
    }
}
