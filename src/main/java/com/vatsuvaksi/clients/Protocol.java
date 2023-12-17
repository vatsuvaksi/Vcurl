package com.vatsuvaksi.clients;

import com.vatsuvaksi.requests.CliRequest;

public interface Protocol<T> {
    T sendRequest(CliRequest cliRequest);
}
