package com.vatsuvaksi.clients.httpclients;

import com.vatsuvaksi.clients.Protocol;
import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;
import com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody.HttpRequestBody;
import com.vatsuvaksi.utils.json.JsonFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.logging.Logger;

public abstract class HttpClient<K> implements Protocol<HttpRequest , K> {

    private static final Logger logger = Logger.getLogger(HttpClient.class.getName());

    protected final HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    protected final void setRequestMethod(HttpURLConnection con , String httpMethod) throws IOException {
        con.setRequestMethod(httpMethod);
    }
    protected final void setRequestHeaders(HttpURLConnection con, HttpRequest httpRequest) {
        if (httpRequest.getHeaders() != null) {
            for (Map.Entry<String, String> entry : httpRequest.getHeaders().entrySet()) {
                con.addRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }
    public final void writeRequestBody(HttpURLConnection con, HttpRequest httpRequest) {
        HttpRequestBody<?> httpRequestBody = httpRequest.getHttpRequestBody();
        if (httpRequestBody != null && httpRequestBody.getContent() != null) {
            try {
                setDoOutput(con);
                writeRequestBodyContent(con, httpRequestBody);
            } catch (Exception e) {
                String msg = "Error in Writing Request Body for " +
                        "-" + con.getURL().getHost() + "/" + con.getURL().getPath();
                logger.warning(msg);
            }
        }
    }
    protected final void setDoOutput(HttpURLConnection con) {
        con.setDoOutput(true);
    }
    protected final void writeRequestBodyContent(HttpURLConnection con, HttpRequestBody<?> httpRequestBody) throws Exception {
        try (OutputStream os = con.getOutputStream()) {
            String requestBodyContent = JsonFactory.convertObjectToJson(httpRequestBody.getContent());
            os.write(requestBodyContent.getBytes(StandardCharsets.UTF_8));
        }
    }
    protected final void setConnectTimeout(HttpURLConnection con) {
        con.setConnectTimeout(10000);
    }

    protected final K processResponse(HttpURLConnection con, Class<K> responseType) throws Exception {
        StringBuilder responseStringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseStringBuilder.append(inputLine);
            }
        }
        return JsonFactory.convertJsonToObject(responseStringBuilder.toString(), responseType);
    }

    protected final void disconnectSafely(HttpURLConnection con) {
        if (con != null) {
            con.disconnect();
        }
    }

}
