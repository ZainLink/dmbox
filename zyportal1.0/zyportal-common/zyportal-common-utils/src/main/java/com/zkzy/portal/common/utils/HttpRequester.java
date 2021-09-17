package com.zkzy.portal.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author edward
 * @date 2017/11/12
 */
public class HttpRequester {

    public static final int CONNECTION_TIMEOUT_MILLISECONDS = 1000;
    public static final int SO_TIMEOUT_MILLISECONDS = 2000;
    public static final int DEFAULT_MAX_CONNECTIONS_PER_HOST = 100;
    public static final int MAX_TOTAL_CONNECTIONS = 1000;
    private static final HttpClient HTTP_CLIENT;
    private static final Object objectMapperLocker = new Object();
    private static ObjectMapper objectMapper = null;

    static {
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = connectionManager.getParams();
        params.setConnectionTimeout(CONNECTION_TIMEOUT_MILLISECONDS);
        params.setSoTimeout(SO_TIMEOUT_MILLISECONDS);
        params.setDefaultMaxConnectionsPerHost(DEFAULT_MAX_CONNECTIONS_PER_HOST);
        params.setMaxTotalConnections(MAX_TOTAL_CONNECTIONS);
        HTTP_CLIENT = new HttpClient(connectionManager);
    }

    private static ObjectMapper getMapper() {
        if (objectMapper == null) {
            synchronized (objectMapperLocker) {
                if (objectMapper == null) {
                    objectMapper = new ObjectMapper();
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                }
            }
        }
        return objectMapper;
    }

    public static <T> T deserialize(String value, Class<T> valueType) throws IOException {
        return getMapper().readValue(value, valueType);
    }

    public static String getUrlWithContextPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + (request.getServerPort() == 80 ? ""
            : request.getServerPort()) + request.getContextPath();
    }

    public static String post(String url, NameValuePair[] nameValuePairs) throws IOException {
        return post(url, nameValuePairs, SO_TIMEOUT_MILLISECONDS);
    }

    public static String post(String url, NameValuePair[] nameValuePairs, int soTimeoutMilliseconds) throws IOException {
        PostMethod postMethod = new PostMethod(url);
        if (nameValuePairs != null && nameValuePairs.length > 0) {
            postMethod.addParameters(nameValuePairs);
        }
        return executeMethod(postMethod, soTimeoutMilliseconds);
    }

    private static String executeMethod(HttpMethod httpMethod, int soTimeoutMilliseconds) throws IOException {
        httpMethod.getParams().setContentCharset("UTF-8");
        httpMethod.getParams().setSoTimeout(soTimeoutMilliseconds);
        try {
            int statusCode = HTTP_CLIENT.executeMethod(httpMethod);
            if (statusCode == HttpStatus.OK.value()) {
                return httpMethod.getResponseBodyAsString();
            } else {
                throw new IOException(httpMethod.getStatusLine().toString());
            }
        } finally {
            httpMethod.releaseConnection();
        }
    }
}
