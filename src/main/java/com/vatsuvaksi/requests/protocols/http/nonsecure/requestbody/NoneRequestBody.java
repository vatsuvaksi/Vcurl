package com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody;



public class NoneRequestBody implements HttpRequestBody<String , Void>{
    @Override
    public String getContent() {
        return "";
    }

    @Override
    public void setContent(Void content) {
        // Sets No Content
    }
}
