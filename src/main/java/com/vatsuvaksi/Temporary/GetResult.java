package com.vatsuvaksi.Temporary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;

@ToString
public class GetResult implements Serializable {
    public Args args;
    public Headers headers;
    public String origin;
    public String url;
    public class Args{
    }

    public class Headers{
        @JsonProperty("Accept")
        public String accept;
        @JsonProperty("Connection")
        public String connection;
        @JsonProperty("Host")
        public String host;
        @JsonProperty("User-Agent")
        public String userAgent;
    }




}
