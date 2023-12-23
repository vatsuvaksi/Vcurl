package com.vatsuvaksi.clients.httpclients;


import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;

import java.net.HttpURLConnection;
import java.net.URL;


public class PostRequest<K> extends HttpClient<K> {
    private static final String POST = "POST";

    @Override
    public K executeRequest(HttpRequest httpRequest, Class<K> responseType) {
        HttpURLConnection con = null;
        try {
            URL url = httpRequest.getUrl().toURL();
            con = openConnection(url);

            setRequestMethod(con , POST);

            setRequestHeaders(con, httpRequest);

            writeRequestBody(con, httpRequest);

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


}
