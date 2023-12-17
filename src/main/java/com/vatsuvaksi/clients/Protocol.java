package com.vatsuvaksi.clients;

import com.vatsuvaksi.requests.CliRequest;

public interface Protocol<T extends CliRequest, K> {
//    public K executeRequest(T requestBody);

    public K executeRequest(T requestBody , Class<K> responseType);
}
