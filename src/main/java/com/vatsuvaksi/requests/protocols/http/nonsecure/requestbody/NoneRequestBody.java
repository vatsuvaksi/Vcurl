package com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody;



public class NoneRequestBody implements HttpRequestBody<Void, Void> {
    @Override
    public Void getContent() {
        return null;
    }

    @Override
    public void setContent(Void content) {
        // Sets No Content
    }
}
