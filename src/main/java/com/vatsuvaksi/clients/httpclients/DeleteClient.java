package com.vatsuvaksi.clients.httpclients;

import com.vatsuvaksi.requests.CliRequest;

public class DeleteClient implements HttpClient<Void> {
    @Override
    public Void execute(CliRequest cliRequest) {
        return null;
    }
}
