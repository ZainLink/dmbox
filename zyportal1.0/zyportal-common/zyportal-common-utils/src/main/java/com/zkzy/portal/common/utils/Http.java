package com.zkzy.portal.common.utils;

import okhttp3.*;
import com.alibaba.fastjson.JSONObject;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Http {

    private static final Logger LOGGER = LoggerFactory.getLogger(Http.class);
    /**
     * 访问超时时间
     */
    private static final int READ_TIMEOUT = 180;


    public String getRes(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).build();
        HttpUrl getUrl = HttpUrl.parse(url).newBuilder().build();
        String message = "";
        Request request = new Request.Builder()
                .url(getUrl)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                message = response.body().string();
//				Object rateBean = new Gson().fromJson(message, Object.class);
            }
        } catch (IOException e) {
            LOGGER.error("请求失败", e);
        }
        return message;
    }

    public String postRes(String url, JSONObject json) {
        OkHttpClient client = new OkHttpClient().newBuilder().readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).build();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, json.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response;
        String message = "";
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                message = response.body().string();
            }
        } catch (IOException e) {
            LOGGER.error("请求失败", e);
        }
        return message;
    }
}
