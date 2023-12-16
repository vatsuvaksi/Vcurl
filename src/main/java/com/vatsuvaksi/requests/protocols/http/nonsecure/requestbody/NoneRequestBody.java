package com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody;



public class NoneRequestBody implements HttpRequestBody<String>{
    @Override
    public String getContent() {
        return "";
    }
}
