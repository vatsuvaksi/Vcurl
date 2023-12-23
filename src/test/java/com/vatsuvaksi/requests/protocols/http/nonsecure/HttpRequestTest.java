package com.vatsuvaksi.requests.protocols.http.nonsecure;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestTest {

    @Test()
    public void testHttpRequestBuilder() {
        // Arrange
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        // Act
        HttpRequest request = HttpRequest.builder()
                .httpMethod("GET")
                .headers(headers)
                .queryParams(null)
                .httpRedirects("follow")
                .httpRequestBody(null)
                .build();

        // Assert
        assertEquals("GET", request.getHttpMethod());
        assertEquals(headers, request.getHeaders());
        assertNull(request.getQueryParams());
        assertEquals("follow", request.getHttpRedirects());
        assertNull(request.getHttpRequestBody());
    }
}
