package org.soaring.emailage.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
class EmailageCommService {
    private static final Logger logger = Logger.getLogger(EmailageCommService.class);

    @Resource
    private EmailageConfig emailageConfig;

    public EmailageResponseStatus sendRequest(String url) {
        logger.info("Calling sendRequest method");
        return parse(connect(url));
    }

    private List<String> connect(String url) {
        List<String> response = new LinkedList<>();
        try {
            URL requestUrl = new URL(url);
            URLConnection connection = requestUrl.openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
            connection.setReadTimeout(Integer.parseInt(emailageConfig.getTimeout()));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setDoOutput(true);
            connection.connect();

            BufferedReader br = new BufferedReader(inputStreamToReader(connection.getInputStream(), "UTF-8"));

            String line;
            while((line = br.readLine()) != null) {
                response.add(line);
            }

            br.close();
        } catch (IOException exception) {
            logger.error(String.format("Error when connecting to Emailage: '%s'", exception.getMessage()), exception);
        }
        return response;
    }

    private EmailageResponseStatus parse(List<String> response) {
        EmailageResponseStatus responseStatus = new EmailageResponseStatus();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String s = URLDecoder.decode(response.get(0), "UTF-8");
            Map<String, Object> lineMap = mapper.readValue(s, new TypeReference<Map<String, Object>>() {});
            Map<String, Object> responseStatusJson = (HashMap<String, Object>) lineMap.get("responseStatus");
            String status = (String) responseStatusJson.get("status");
            String errorCode = (String) responseStatusJson.get("errorCode");

            Map<String, Object> responseQueryJson = (HashMap<String, Object>) lineMap.get("query");
            String email = (String) responseQueryJson.get("email");

            responseStatus.setStatus(status);
            EmailageErrorCode emailAgeErrorCode = EmailageErrorCode.findByCode(Integer.parseInt(errorCode));
            responseStatus.setErrorCode(emailAgeErrorCode);
            responseStatus.setEmail(email);
        } catch (IOException exception) {
            logger.error(String.format("Error when parsing response from Emailage: '%s'", exception.getMessage()), exception);
        }
        return responseStatus;
    }

    private Reader inputStreamToReader(InputStream in, String encoding) throws IOException {
        in.mark(3);
        int byte1 = in.read();
        int byte2 = in.read();
        if (byte1 == 0xFF && byte2 == 0xFE) {
            return new InputStreamReader(in, "UTF-16LE");
        } else if (byte1 == 0xFF && byte2 == 0xFF) {
            return new InputStreamReader(in, "UTF-16BE");
        } else {
            int byte3 = in.read();
            if (byte1 == 0xEF && byte2 == 0xBB && byte3 == 0xBF) {
                return new InputStreamReader(in, "UTF-8");
            } else {
                if (in.markSupported())
                    in.reset();
                return new InputStreamReader(in, Charset.forName(encoding));
            }
        }
    }
}
