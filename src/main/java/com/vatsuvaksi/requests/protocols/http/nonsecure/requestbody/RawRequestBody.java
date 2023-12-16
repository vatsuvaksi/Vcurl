package com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RawRequestBody<T> implements HttpRequestBody<T>{

    private T content;
    private ContentType contentType;
    private enum ContentType {
        TEXT,
        JAVASCRIPT,
        JSON,
        HTML,
        XML
    }
    @Override
    public T getContent() {
        //Write logic to get the content according to content type
        return null;
    }

}
