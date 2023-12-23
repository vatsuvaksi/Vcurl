package com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody;

public interface HttpRequestBody<T , K> {
    T getContent();
    void setContent(K content);
}
