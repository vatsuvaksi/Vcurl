package com.vatsuvaksi.clients.httpclients;

import com.vatsuvaksi.clients.Protocol;
import com.vatsuvaksi.requests.CliRequest;
import com.vatsuvaksi.requests.protocols.http.nonsecure.HttpRequest;

public interface HttpClient<T extends CliRequest, K> extends Protocol<HttpRequest , K> {
}
