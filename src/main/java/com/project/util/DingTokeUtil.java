package com.project.util;



import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DingTokeUtil {
    private static String secret ="SEC87afe7ad81286c618aebc7702992c66d52e8672848043050cae02d43c23ed31e";
    public static String getSignUrl(String webHook){
        try {
            Long timestamp = System.currentTimeMillis();
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String encode = URLEncoder.encode(new String(Base64.encode(signData)), "UTF-8");
            return webHook+"&timestamp="+timestamp+"&sign="+encode;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
