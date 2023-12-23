package com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody;




import java.util.Map;



public class FormDataRequestBody implements HttpRequestBody<Map<String, ?> , Map<String , ?>> {

    private  Map<String, ?> formData;

    @Override
    public Map<String, ?> getContent() {
        return this.formData;
    }

    @Override
    public void setContent(Map<String, ?> formData) {this.formData = formData;}

}
