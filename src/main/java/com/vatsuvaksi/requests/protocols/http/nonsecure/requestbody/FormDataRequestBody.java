package com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody;


import lombok.Setter;

import java.util.Map;


@Setter
public class FormDataRequestBody implements HttpRequestBody<Map<String, ?>>{

    private Map<String, ?> formData;
    @Override
    public Map<String, ?> getContent() {
        return formData;
    }

}
