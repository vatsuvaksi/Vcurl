package com.vatsuvaksi.clients.httpclients;

import com.vatsuvaksi.requests.CliRequest;
import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;
import com.vatsuvaksi.utils.json.JsonFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class DeleteRequest<T extends CliRequest, K> implements HttpClient<T, K> {
    private static final String DELETE = "DELETE";

    @Override
    public K executeRequest(HttpRequest httpRequest, Class<K> responseType) {
        HttpURLConnection con = null;
        try {
            URL url = httpRequest.getUrl().toURL();
            con = openConnection(url);

            setRequestMethod(con);

            setRequestHeaders(con, httpRequest);

            setConnectTimeout(con);

            int responseCode = con.getResponseCode();
            if (responseCode == 200) {
                return processResponse(con, responseType);
            } else {
                throw new RuntimeException("Response Status Code Not Equal to OK[200]");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            disconnectSafely(con);
        }
    }

    private HttpURLConnection openConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    private void setRequestMethod(HttpURLConnection con) throws IOException {
        con.setRequestMethod(DELETE);
    }

    private void setRequestHeaders(HttpURLConnection con, HttpRequest httpRequest) {
        if (httpRequest.getHeaders() != null) {
            for (Map.Entry<String, String> entry : httpRequest.getHeaders().entrySet()) {
                con.addRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }

    private void setConnectTimeout(HttpURLConnection con) {
        con.setConnectTimeout(10000);
    }

    private K processResponse(HttpURLConnection con, Class<K> responseType) throws Exception {
        StringBuilder responseStringBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseStringBuilder.append(inputLine);
            }
        }
        return JsonFactory.convertJsonToObject(responseStringBuilder.toString(), responseType);
    }

    private void disconnectSafely(HttpURLConnection con) {
        if (con != null) {
            con.disconnect();
        }
    }
}
