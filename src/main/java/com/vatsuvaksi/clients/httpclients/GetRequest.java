package com.vatsuvaksi.clients.httpclients;

import com.vatsuvaksi.requests.CliRequest;
import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;
import com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody.HttpRequestBody;
import com.vatsuvaksi.utils.json.JsonFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

// TODO :  Break down the function into smaller funcctions
public class GetRequest<T extends CliRequest , K> implements HttpClient<T , K> {
    private static final String GET = "GET";
    @Override
    public K executeRequest(HttpRequest httpRequest, Class<K> responseType) {
        HttpURLConnection con = null;
        try {
            URL url = httpRequest.getUrl().toURL();

            con.setRequestMethod(GET);

            if(!httpRequest.getHeaders().isEmpty()){
                for(Map.Entry<String, String> entry : httpRequest.getHeaders().entrySet()){
                    con.addRequestProperty(entry.getKey() , entry.getValue());
                }
            }
            // Not required as URL will set the query params hence commenting this for now
            //** if(httpRequest.getQueryParams() != null){} **//

            // TODO :  Remove this and don't allow GET to have a request body
            HttpRequestBody httpRequestBody = httpRequest.getHttpRequestBody();
            if(httpRequestBody.getContent() != null){
                con.setDoOutput(true);
                try (OutputStream os = con.getOutputStream()) {
                    String requestBodyContent = JsonFactory
                            .convertObjectToJson(httpRequestBody.getContent());
                    os.write(requestBodyContent.getBytes("UTF-8"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            con.setConnectTimeout(10000);
            con = (HttpURLConnection) url.openConnection();

            int responseCode = con.getResponseCode();
            if(responseCode == 200){
                StringBuilder responseStringBuilder = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        responseStringBuilder.append(inputLine);
                    }
                }
                return JsonFactory.convertJsonToObject(responseStringBuilder.toString() , responseType);
            }else{
                throw new RuntimeException("Response Status Code Not Equal to OK[200]");
            }
        }  catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            con.disconnect();
        }
    }
}

