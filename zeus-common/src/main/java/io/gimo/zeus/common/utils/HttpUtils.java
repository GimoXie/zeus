package io.gimo.zeus.common.utils;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    private static final CloseableHttpClient HTTP_CLIENT;
    private static final int HTTP_OK = 200;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String RESULT_TYPE_BYTE = "Byte";
    private static final String RESULT_TYPE_STRING = "String";
    private static final String WEB_PROXY = "WEB_PROXY";
    private static final String REQUEST_TIME_OUT = "REQUEST_TIME_OUT";
    private static final String CONTENT_TYPE = "Content-Type";

    private enum RequestType {GET, POST, PUT, DELETE}

    static {
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLSv1.2");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            sslContext.init(null, new TrustManager[]{tm}, null);

        } catch (Exception e) {
            LOGGER.error("now use default ssl version");
            sslContext = SSLContexts.createSystemDefault();
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("https", new SSLConnectionSocketFactory(sslContext, new DefaultHostnameVerifier()))
                .register("http", new PlainConnectionSocketFactory())
                .build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(400);
        cm.setDefaultMaxPerRoute(cm.getMaxTotal());
        HTTP_CLIENT = HttpClientBuilder.create().setConnectionManager(cm).build();
    }

    public static String doGet(String url, Map<String, String> params) throws Exception {
        return doGet(url, params, null, null, null);
    }

    public static String doGet(String url, Map<String, String> headers, Map<String, String> config) throws Exception {
        return doGet(url, null, headers, config, null);
    }

    public static String doGet(String url, Map<String, String> params, Map<String, String> headers, Map<String, String> config, String charset) throws Exception {
        return (String) execute(RequestType.GET, "", url, null, null, params, headers, config, charset);
    }

    public static byte[] doGetForByte(String url, Map<String, String> headers) throws Exception {
        Object result = execute(RequestType.GET, RESULT_TYPE_BYTE, url, null, null, null, headers, null, null);
        return (byte[]) result;
    }

    public static String doPost(String url, Map<String, String> params) throws Exception {
        return doPost(url, null, null, params, null, null);
    }

    public static String doPost(String url, byte[] bytes, Map<String, String> headers) throws Exception {
        return doPost(url, null, bytes, headers, null, null);
    }

    public static String doPost(String url, String contents) throws Exception {
        return doPost(url, contents, null, null, null, null);
    }

    public static String doPost(String url, String contents, byte[] bytes, Map<String, String> headers, Map<String, String> config, String charset)
            throws Exception {
        return (String) execute(RequestType.POST, "", url, bytes, contents, null, headers, config, charset);
    }

    public static String doDelete(String url, Map<String, String> headers, Map<String, String> config)
            throws Exception {
        return (String) execute(RequestType.DELETE, "", url, null, null, null, headers, config, null);
    }

    /**
     * @param requestMethod 请求类型Get-Post
     * @param url           请求URl
     * @param contents      请求数据
     * @param params        请求数据
     * @param headers       请求header
     * @param config        请求配置
     * @param charset       请求语言格式
     * @return
     * @throws Exception warn:contents、params二选一
     *                   get: 只支持params
     */
    private static HttpRequestBase createRequest(RequestType requestMethod, String url, byte[] bytes, String contents,
                                                 Map<String, String> params, Map<String, String> headers,
                                                 Map<String, String> config, String charset) throws Exception {
        Preconditions.checkArgument(StringUtils.isBlank(url), "url is null");
        switch (requestMethod) {
            case POST:
                HttpPost httpPost = new HttpPost(url);
                //设置配置
                if (config != null && !config.isEmpty()) {
                    httpPost.setConfig(getRequestConfig(config));
                }
                //设置请求数据
                if (StringUtils.isNotBlank(contents)) {
                    StringEntity stringEntity = new StringEntity(contents, charset);
                    if (headers != null && headers.get(CONTENT_TYPE) != null) {
                        stringEntity.setContentType(headers.get(CONTENT_TYPE));
                    }
                    httpPost.setEntity(stringEntity);
                }
                //请求参数为bytes
                if (bytes != null && bytes.length > 0) {
                    httpPost.setEntity(new ByteArrayEntity(bytes));
                }
                //设置请求数据
                if (params != null && !params.isEmpty()) {
                    httpPost.setEntity(new UrlEncodedFormEntity(getNameValuePairs(params), charset));
                }
                //设置header
                if (headers != null && !headers.isEmpty()) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        Header header = new BasicHeader(entry.getKey(), entry.getValue());
                        httpPost.addHeader(header);
                    }
                }
                return httpPost;
            case GET:
                if (params != null && !params.isEmpty()) {
                    url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(getNameValuePairs(params), charset));
                }
                HttpGet httpGet = new HttpGet(url);
                //设置配置
                if (config != null && !config.isEmpty()) {
                    httpGet.setConfig(getRequestConfig(config));
                }
                //设置header
                if (headers != null && !headers.isEmpty()) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        Header header = new BasicHeader(entry.getKey(), entry.getValue());
                        httpGet.addHeader(header);
                    }
                }
                return httpGet;
            case DELETE:
                HttpDelete httpDelete = new HttpDelete(url);
                //设置配置
                if (config != null && !config.isEmpty()) {
                    httpDelete.setConfig(getRequestConfig(config));
                }
                //设置header
                if (headers != null && !headers.isEmpty()) {
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        Header header = new BasicHeader(entry.getKey(), entry.getValue());
                        httpDelete.addHeader(header);
                    }
                }
                return httpDelete;
            default:
                return null;
        }
    }

    private static List<NameValuePair> getNameValuePairs(Map<String, String> params) {
        List<NameValuePair> pairs = new ArrayList<>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String value = entry.getValue();
            if (value != null) {
                pairs.add(new BasicNameValuePair(entry.getKey(), value));
            }
        }
        return pairs;
    }

    private static RequestConfig getRequestConfig(Map<String, String> config) {
        HttpHost proxy = null;
        if (!StringUtils.isBlank(config.get(WEB_PROXY))) {
            proxy = new HttpHost(config.get(WEB_PROXY).split(":")[0],
                    Integer.valueOf(config.get(WEB_PROXY).split(":")[1]));
        }
        return RequestConfig.custom()
                .setSocketTimeout(Integer.valueOf(config.get(REQUEST_TIME_OUT)))
                .setConnectTimeout(Integer.valueOf(config.get(REQUEST_TIME_OUT))).setProxy(proxy).build();
    }

    /**
     * @param requestMethod 请求方式
     * @param resultType    返回数据类型
     * @param url           请求URL
     * @param bytes         请求数据为byte
     * @param contents      请求数据为String
     * @param params        请求数据为map
     * @param headers       请求header
     * @param config        请求配置
     * @param charset       字符集
     * @return
     * @throws Exception
     */
    private static Object execute(RequestType requestMethod, String resultType, String url, byte[] bytes, String contents, Map<String, String> params, Map<String, String> headers, Map<String, String> config, String charset) throws Exception {
        HttpRequestBase requestBase = createRequest(requestMethod, url, bytes, contents, params, headers, config, StringUtils.isBlank(charset) ? DEFAULT_CHARSET : charset);
        CloseableHttpResponse response = HTTP_CLIENT.execute(requestBase);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HTTP_OK) {
            throw new HttpException("HttpClient execute fail,error status code :" + statusCode);
        }
        HttpEntity entity = response.getEntity();
        Object result = null;
        if (entity != null) {
            if (StringUtils.isBlank(resultType) || RESULT_TYPE_STRING.equals(resultType)) {
                result = EntityUtils.toString(entity, DEFAULT_CHARSET);
            } else {
                result = EntityUtils.toByteArray(entity);
            }
        }
        EntityUtils.consume(entity);
        response.close();
        return result;
    }

}
