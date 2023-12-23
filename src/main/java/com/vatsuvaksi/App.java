package com.vatsuvaksi;

import com.vatsuvaksi.Temporary.GetResult;
import com.vatsuvaksi.Temporary.PostResult;
import com.vatsuvaksi.clients.Protocol;
import com.vatsuvaksi.clients.httpclients.DeleteRequest;
import com.vatsuvaksi.clients.httpclients.GetRequest;
import com.vatsuvaksi.clients.httpclients.PostRequest;
import com.vatsuvaksi.clients.httpclients.PutRequest;


import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;
import com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody.FormDataRequestBody;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class App {

    // TODO : ALL THESE NEEDS TO BE REMOVED AND MOVED TO TEST
    public static void main(String[] args) throws URISyntaxException {

        InitializeApplication.start();

        String url = "http://localhost/";
        // GET Request
        HttpRequest getCliRequest = HttpRequest
                .builder()
                .httpMethod("GET")
                .build();
        getCliRequest.setUrl(new URI( url + "get"));
        Protocol<HttpRequest, GetResult> getProtocol = new GetRequest<>();
        getProtocol.executeRequest(getCliRequest , GetResult.class);

//        HttpRequestBody<String, Void> httpRequestBody = new NoneRequestBody();

        Map<String, String> content = new HashMap<>();
        content.put("ket1", "val");
        content.put("ket2", "val");
        content.put("ket3", "val");
        content.put("ket4", "val");
        FormDataRequestBody httpRequestBody = new FormDataRequestBody();
        httpRequestBody.setContent(content);
        // POST Request
        Map<String , String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");

        HttpRequest postHttpRequest = HttpRequest
                .builder()
                .httpMethod("POST")
                .httpRequestBody(httpRequestBody)
                .headers(headers)
                .build();
        postHttpRequest.setUrl(new URI(url + "post"));
        Protocol<HttpRequest, String> postProtocol = new PostRequest<>();
        String postResult = postProtocol.executeRequest(postHttpRequest , String.class);

        System.out.println(postResult);


        // PUT Request
        HttpRequest putCliRequest = HttpRequest
                .builder()
                .httpMethod("PUT")
                .build();
        putCliRequest.setUrl(new URI(url + "put"));
        Protocol<HttpRequest, PostResult> putProtocol = new PutRequest<>();
        putProtocol.executeRequest(putCliRequest , PostResult.class);


        // DELETE Request
        HttpRequest deleteCliRequest = HttpRequest
                .builder()
                .httpMethod("DELETE")
                .build();
        deleteCliRequest.setUrl(new URI(url + "delete"));
        Protocol<HttpRequest, PostResult> deleteProtocol = new DeleteRequest<>();
        deleteProtocol.executeRequest(deleteCliRequest , PostResult.class);

    }
}
