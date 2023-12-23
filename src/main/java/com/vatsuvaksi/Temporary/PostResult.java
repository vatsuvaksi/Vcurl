package com.vatsuvaksi.Temporary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class PostResult {
    public Args args;
    public String data;
    public Files files;
    public Form form;
    public Headers headers;
    public Object json;
    public String origin;
    public String url;
    public class Args{
    }

    public class Files{
    }

    public class Form{

    }

    @ToString
    public class Headers{
        @JsonProperty("Accept")
        public String accept;
        @JsonProperty("Accept-Encoding")
        public String acceptEncoding;
        @JsonProperty("Accept-Language")
        public String acceptLanguage;
        @JsonProperty("Connection")
        public String connection;
        @JsonProperty("Content-Length")
        public String contentLength;
        @JsonProperty("Host")
        public String host;
        @JsonProperty("Origin")
        public String origin;
        @JsonProperty("Referer")
        public String referer;
        @JsonProperty("Sec-Ch-Ua")
        public String secChUa;
        @JsonProperty("Sec-Ch-Ua-Mobile")
        public String secChUaMobile;
        @JsonProperty("Sec-Ch-Ua-Platform")
        public String secChUaPlatform;
        @JsonProperty("Sec-Fetch-Dest")
        public String secFetchDest;
        @JsonProperty("Sec-Fetch-Mode")
        public String secFetchMode;
        @JsonProperty("Sec-Fetch-Site")
        public String secFetchSite;
        @JsonProperty("User-Agent")
        public String userAgent;
    }
}
