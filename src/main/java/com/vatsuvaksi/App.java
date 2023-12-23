package com.vatsuvaksi;

import com.vatsuvaksi.Temporary.GetResult;
import com.vatsuvaksi.Temporary.PostResult;
import com.vatsuvaksi.clients.Protocol;
import com.vatsuvaksi.clients.httpclients.DeleteRequest;
import com.vatsuvaksi.clients.httpclients.GetRequest;
import com.vatsuvaksi.clients.httpclients.PostRequest;
import com.vatsuvaksi.clients.httpclients.PutRequest;


import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;


import java.net.URI;
import java.net.URISyntaxException;

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


        // POST Request
        HttpRequest postHttpRequest = HttpRequest
                .builder()
                .httpMethod("POST")
                .build();
        postHttpRequest.setUrl(new URI(url + "post"));
        Protocol<HttpRequest, PostResult> postProtocol = new PostRequest<>();
        postProtocol.executeRequest(postHttpRequest , PostResult.class);


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
