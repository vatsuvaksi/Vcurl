package com.vatsuvaksi;

import com.vatsuvaksi.Temporary.GetResult;
import com.vatsuvaksi.Temporary.PostResult;
import com.vatsuvaksi.clients.Protocol;
import com.vatsuvaksi.clients.httpclients.DeleteRequest;
import com.vatsuvaksi.clients.httpclients.GetRequest;
import com.vatsuvaksi.clients.httpclients.PostRequest;
import com.vatsuvaksi.clients.httpclients.PutRequest;

import com.vatsuvaksi.requests.CliRequest;
import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class App {
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
        // GET Request
        CliRequest getCliRequest = HttpRequest
                .builder()
                .httpMethod("GET")
                .build();
        getCliRequest.setUrl(new URI("http://localhost/get"));
        Protocol<HttpRequest, GetResult> getProtocol = new GetRequest<>();
        System.out.println(getProtocol.executeRequest((HttpRequest) getCliRequest, GetResult.class).toString());

        // POST Request
        HttpRequest postHttpRequest = HttpRequest
                .builder()
                .httpMethod("POST")
                .build();
        postHttpRequest.setUrl(new URI("http://localhost/post"));
        Protocol<HttpRequest, PostResult> postProtocol = new PostRequest<>();
        System.out.println(postProtocol.executeRequest((HttpRequest) postHttpRequest, PostResult.class).toString());

        // PUT Request
        CliRequest putCliRequest = HttpRequest
                .builder()
                .httpMethod("PUT")
                .build();
        putCliRequest.setUrl(new URI("http://localhost/put"));
        Protocol<HttpRequest, PostResult> putProtocol = new PutRequest<>();
        System.out.println(putProtocol.executeRequest((HttpRequest) putCliRequest, PostResult.class).toString());

        // DELETE Request
        CliRequest deleteCliRequest = HttpRequest
                .builder()
                .httpMethod("DELETE")
                .build();
        deleteCliRequest.setUrl(new URI("http://localhost/delete"));
        Protocol<HttpRequest, PostResult> deleteProtocol = new DeleteRequest<>();
        System.out.println(deleteProtocol.executeRequest((HttpRequest) deleteCliRequest, PostResult.class).toString());

        System.out.println("Hello World!");
    }
}
