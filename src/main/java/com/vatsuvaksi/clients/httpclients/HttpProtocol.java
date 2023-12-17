package com.vatsuvaksi.clients.httpclients;

import com.vatsuvaksi.clients.Protocol;
import com.vatsuvaksi.requests.CliRequest;


public class HttpProtocol<T> implements Protocol<T> {
    private final HttpClient<T> httpClient;

    public HttpProtocol(HttpClient<T> httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * @param cliRequest
     */
    @Override
    public T sendRequest(CliRequest cliRequest) {
        //According to http method call the class
        return httpClient.execute(cliRequest);
    }
}
