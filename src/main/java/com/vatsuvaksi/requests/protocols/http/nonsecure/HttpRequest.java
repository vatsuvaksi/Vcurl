package com.vatsuvaksi.requests.protocols.http.nonsecure;

import com.vatsuvaksi.requests.CliRequest;
import com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody.HttpRequestBody;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class HttpRequest extends CliRequest {
    private String httpMethod;
    private Map<String,String> headers;
    private Map<String,String> queryParams;
    private String httpRedirects;
    private HttpRequestBody httpRequestBody;
}
