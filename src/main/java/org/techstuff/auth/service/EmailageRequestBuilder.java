package org.soaring.emailage.service;

import com.amazonaws.util.Base64;
import org.apache.log4j.Logger;
import org.soaring.emailage.data.ReportedEmail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
class EmailageRequestBuilder {
    private static final Logger logger = Logger.getLogger(EmailageRequestBuilder.class);
    
    @Resource
    private EmailageConfig emailageConfig;

    public List<String> build(List<ReportedEmail> emails) {
        logger.info("Calling URL builder method for all e-mails");
        return emails.stream().map(this::build).collect(Collectors.toList());
    }

    private String build(ReportedEmail email) {
        logger.info(String.format("Building Emailage URL for e-mail '%s'", email.getEmail()));

        StringBuffer sb = new StringBuffer();
        sb.append("flag=fraud&");
        sb.append("format=").append(emailageConfig.getResultFormat()).append("&");
        sb.append("query=").append(email.getEmail()).append("&");
        sb.append("oauth_consumer_key=").append(emailageConfig.getAccountSid()).append("&");
        sb.append("oauth_nonce=").append(getNonce(10)).append("&");
        sb.append("oauth_signature_method=HMAC-SHA1&");
        sb.append("oauth_timestamp=").append(getTimestamp()).append("&");
        sb.append("oauth_version=1.0");
        String signature = generateSignature(buildDataForSigning(sb.toString()));
        sb.append("&oauth_signature=").append(signature);
        return emailageConfig.getRequestBaseUrl() + "?" + sb.toString();
    }


    private String buildDataForSigning(String queryString) {
        StringBuffer sb = new StringBuffer();
        try {
            sb.append("GET&");
            sb.append(URLEncoder.encode(emailageConfig.getRequestBaseUrl(), "UTF-8"));
            sb.append("&");
            sb.append(URLEncoder.encode(queryString, "UTF-8"));
        } catch (UnsupportedEncodingException exception) {
            logger.error(String.format("Error when building data for signing: '%s'", exception.getMessage()), exception);
        }
        return sb.toString();
    }

    private String getTimestamp() {
        double date = new Date().getTime();
        return String.valueOf(Math.floor(date));
    }

    private String getNonce(int length) {
        Random random = new Random();
        String unreservedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.~";

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++) {
            sb.append(unreservedChars.charAt(random.nextInt(25)));
        }

        return sb.toString();
    }

    private String generateSignature(String data) {
        try {
            return URLEncoder.encode(new String(Base64.encode(encrypt(data, emailageConfig.getAuthenticationToken() + "&")), "ASCII"), StandardCharsets.UTF_8.displayName().toLowerCase());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException exception) {
            logger.error(String.format("Error when generating signature: '%s'", exception.getMessage()));
            return "";
        }
    }

    private byte[] encrypt(String data, String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        byte[] keyBytes = key.getBytes("UTF-8");
        Mac mac = Mac.getInstance("HMACSHA1");
        Key secretKey = new SecretKeySpec(keyBytes, "HMACSHA1");
        mac.init(secretKey);
        byte[] text = data.getBytes("UTF-8");
        return mac.doFinal(text);
    }
}
