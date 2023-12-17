package com.vatsuvaksi.clients.httpclients;

import com.vatsuvaksi.requests.CliRequest;

public interface HttpClient<T> {
    T execute(CliRequest cliRequest);
}
