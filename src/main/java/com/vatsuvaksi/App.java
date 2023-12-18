package com.vatsuvaksi;

import com.vatsuvaksi.Temporary.Result;
import com.vatsuvaksi.clients.Protocol;
import com.vatsuvaksi.clients.httpclients.GetRequest;
import com.vatsuvaksi.requests.CliRequest;
import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws MalformedURLException, URISyntaxException {
        CliRequest cliRequest = HttpRequest
                .builder()
                .httpMethod("GET")
                .build();
        cliRequest.setUrl(new URI("http://localhost/get"));
        Protocol<HttpRequest , Result> protocol = new GetRequest<>();
        System.out.println(protocol.executeRequest((HttpRequest) cliRequest, Result.class).toString());

        System.out.println( "Hello World!" );
    }
}
