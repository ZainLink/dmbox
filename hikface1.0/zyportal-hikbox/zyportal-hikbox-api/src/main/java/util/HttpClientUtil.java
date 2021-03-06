package util;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;


/**
 * @author wyk
 * @version 2019/08/14
 */
public class HttpClientUtil {
    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static final String CHARACTER_ENCODING = "UTF-8";
    private static CloseableHttpClient httpClient = null;
    private static PoolingHttpClientConnectionManager poolConnectionManager = null;

    static {
        try {
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null,
                    new TrustSelfSignedStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                            return true;
                        }

                    })
                    .build();
            //"TLSv1.2"
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, new String[]{"SSLv2Hello", "SSLv3", "TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", new PlainConnectionSocketFactory())
                    .register("https", sslsf)
                    .build();
            poolConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            poolConnectionManager.setMaxTotal(50);
            poolConnectionManager.setDefaultMaxPerRoute(10);
            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setConnectTimeout(5000).setSocketTimeout(5000).build();
            httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(poolConnectionManager).build();
        } catch (Exception e) {
            logger.error("InterfacePhpUtilManager init Exception" + e.toString());
        }
    }

    /**
     * get
     *
     * @param url ????????????
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        //??????????????????
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        return parse(response);
    }

    /**
     * post json
     *
     * @param url              ????????????
     * @param requestParams    ????????????
     * @param requestCharacter ????????????
     * @return
     */
    public static String postForm(Map<String, String> requestParams, String url, String requestCharacter) throws IOException {
        if (StringUtils.isBlank(url) || StringUtils.isBlank(requestCharacter)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> formParams = initNameValuePair(requestParams);
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, requestCharacter);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return parse(response);
    }

    /**
     * POST????????????????????????????????????????????????utf8??????
     */
    public static String postForm(Map<String, String> requestParas, String url) throws IOException {
        return postForm(requestParas, url, CHARACTER_ENCODING);
    }

    private static List<NameValuePair> initNameValuePair(Map<String, String> params) {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        if (params != null && params.size() > 0) {
            // ???key????????????
            List<String> keys = new ArrayList<String>(params.keySet());
            Collections.sort(keys);
            for (String key : keys) {
                //LOG.info(key+" = " +params.get(key));
                formParams.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        return formParams;
    }


    /**
     * post json
     *
     * @param json ??????json body
     * @param url  ????????????
     * @return
     */
    public static String postJson(String json, String url) throws IOException {
        if (StringUtils.isBlank(json) || StringUtils.isBlank(url)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return parse(response);
    }

    public static Header[] postJsonWithHeader(String json, String url, Map<String, String> header) throws IOException {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        if (header != null && header.size() > 0) {
            for (String s : header.keySet()) {
                httpPost.setHeader(s, header.get(s));
            }
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return response.getAllHeaders();
    }

    /**
     * post json
     *
     * @param json ??????json body
     * @param url  ????????????
     * @return
     */
    public static InputStream postJsonDownload(String json, String url) throws IOException {
        if (StringUtils.isBlank(json) || StringUtils.isBlank(url)) {
            return null;
        }
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        return entity.getContent();
    }

    /**
     * post json
     *
     * @param json  json???????????????
     * @param heads ?????????
     * @param url   ????????????
     * @return
     * @throws IOException
     */
    public static String postJson(String json, Map<String, String> heads, String url) throws IOException {
        if (StringUtils.isBlank(json) || StringUtils.isBlank(url)) {
            return null;
        }
        logger.info("url:{},json:{}", url, json);
        //??????json
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(entity);
        //??????????????????
        if (heads != null && !heads.isEmpty()) {
            Set<String> headKeys = heads.keySet();
            for (String key : headKeys) {
                String value = heads.get(key);
                httpPost.addHeader(key, value);
            }
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String responseString = parse(response);
        logger.info("response:{}", responseString);
        return responseString;
    }


    /**
     * ???????????????
     *
     * @param response
     * @return
     * @throws IOException
     */
    protected static String parse(CloseableHttpResponse response) throws IOException {
        String result = null;
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            result = "";
            if (statusCode != 200) {
                return result;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            logger.error("parse error{}", ExceptionUtils.getStackFrames(e));
        } finally {
            response.close();
        }
        return result;
    }

    /**
     * ????????????????????????
     *
     * @param url
     * @return
     */
    public static InputStream download(String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        //??????????????????
        HttpGet httpGet = new HttpGet(url);
        InputStream inputStream = null;
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            inputStream = entity.getContent();
        } catch (IOException e) {
            logger.info("download error:{}", ExceptionUtils.getStackFrames(e));
        }
        return inputStream;
    }

    public static String readDataFromHttpPost(String url, String content, String charset) {
        HttpURLConnection connection = null;
        DataOutputStream out = null;
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL postUrl = new URL(url);
            connection = (HttpURLConnection) postUrl.openConnection();
            //????????????????????????20???
            connection.setConnectTimeout(20000);
            //????????????????????????20???
            connection.setReadTimeout(20000);
            // ???????????????connection??????
            connection.setDoOutput(true);
            // ???????????????connection??????
            connection.setDoInput(true);
            // Default is GET
            connection.setRequestMethod("POST");
            // Post????????????????????????
            connection.setUseCaches(false);

            //connection.setFollowRedirects(true);

            connection.setInstanceFollowRedirects(true);

            // ??????application/x-www-form-urlencoded
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(content.getBytes().length));
            // ??????????????????connect????????????
            connection.connect();
            out = new DataOutputStream(connection.getOutputStream());
            //out.writeBytes(content);
            out.write(content.getBytes(charset));
            out.flush();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } finally {
            try {
                if (out != null) out.close();
                if (reader != null) reader.close();
                if (connection != null) connection.disconnect();
            } catch (IOException e) {
            }
        }

        return sb.toString();
    }

}


