package com.zhonggg.commonUtils.log;



import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * @author wangxiaolong
 * @date 2019/12/6 13:30
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    public final String requestData;
    private final byte[] body;

    public RequestWrapper(HttpServletRequest request) {
        super(request);
        requestData = getBodyString(request);
        byte[] requestBody = null;
        if (StringUtils.isNotBlank(requestData)) {
            JSONObject jsonObject;
            try {
                jsonObject = JSONObject.parseObject(requestData);
                request.setAttribute("operateInfo", jsonObject.getString("operateInfo"));
                jsonObject.remove("operateInfo");
                requestBody = jsonObject.toString().getBytes();
            } catch (JSONException e) {
                requestBody = requestData.getBytes();
            }
        }
        body = requestBody;
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {

        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    public static String getBodyString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try (
                InputStream inputStream = request.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
