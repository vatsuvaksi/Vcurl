package com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
public class RawRequestBody<T , K> implements HttpRequestBody<T , Map<RawRequestBody.ContentType , K> >{

    private T content;
    private ContentType contentType;
    public enum ContentType {
        TEXT,
        JAVASCRIPT,
        JSON,
        HTML,
        XML
    }


    @Override
    public void setContent(Map<ContentType, K> content) {
        // It should be only 1 length
        if(content.size() != 1){
            try {
                throw new Exception("");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

}
