package com.vatsuvaksi.requests;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;


@Setter
@Getter
public abstract class CliRequest {
    private URI url;
}
