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
    private Map<String, String> headers;
    private Map<String, String> queryParams;
    private String httpRedirects;
    private HttpRequestBody httpRequestBody;

    private HttpRequest(Builder builder) {
        this.httpMethod = builder.httpMethod;
        this.headers = builder.headers;
        this.queryParams = builder.queryParams;
        this.httpRedirects = builder.httpRedirects;
        this.httpRequestBody = builder.httpRequestBody;
    }

    // Static method to obtain a builder instance
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private String httpMethod;
        private Map<String, String> headers;
        private Map<String, String> queryParams;
        private String httpRedirects;
        private HttpRequestBody httpRequestBody;

        private Builder() {
            // Private constructor to enforce the use of the static builder() method
        }

        public Builder httpMethod(String httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Builder queryParams(Map<String, String> queryParams) {
            this.queryParams = queryParams;
            return this;
        }

        public Builder httpRedirects(String httpRedirects) {
            this.httpRedirects = httpRedirects;
            return this;
        }

        public Builder httpRequestBody(HttpRequestBody httpRequestBody) {
            this.httpRequestBody = httpRequestBody;
            return this;
        }

        // Build method to create an instance of HttpRequest
        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}