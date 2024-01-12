package com.vatsuvaksi.requests.protocols.http.nonsecure.requestbody;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Document;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class RawRequestBody<K> implements HttpRequestBody<K, Map<RawRequestBody.ContentType, K>> {

    private K content;
    private ContentType contentType;

    public enum ContentType {
        TEXT,
        JAVASCRIPT,
        JSON,
        HTML,
        XML
    }

    @Override
    public void setContent(Map<ContentType, K> content) {
        Objects.requireNonNull(content, "Content map cannot be null");
        if (content.size() != 1) {
            throw new RuntimeException("Exactly one content type is expected");
        }

        // Extracting Key Value from the map.
        Map.Entry<ContentType, K> entry = content.entrySet().iterator().next();
        ContentType contentType1 = entry.getKey();
        K value = entry.getValue();

        switch (contentType1) {
            case XML -> handleDocument(value);
            case HTML -> handleDocument(value);
            case JSON -> handleJson(value);
            case JAVASCRIPT -> handleJavaScript(value);
            case TEXT -> handleText(value);
            default -> throw new IllegalArgumentException("Unexpected content type");
        }
    }
    ////** Notes To Self **////
    // Todo : Check if you actually need to pass and check seperately for all Content Types
    // I am pretty sure these functions are not needed, remove this once confirmed.
    // The body anyway is going to come as string, we can pass that directly or shoud we parse it
    // into a map ??
    private void handleText(K value) {
        Objects.requireNonNull(value, "Text content cannot be null");
        if (!(value instanceof String)) {
            throw new RuntimeException("The value passed is not of Text Type");
        }
        content = value;
    }

    private void handleJavaScript(K value) {
        Objects.requireNonNull(value, "JavaScript content cannot be null");
        try {
            // Sample JavaScript code
            String javascriptCode = (String) value;

            // Create a script engine
            ScriptEngineManager engineManager = new ScriptEngineManager();
            ScriptEngine engine = engineManager.getEngineByName("nashorn");

            engine.eval(javascriptCode);
        } catch (ScriptException e) {
            throw new RuntimeException("The Value passed is not Javascript Type", e);
        }
        content = value;
    }

    private void handleJson(K value) {
        Objects.requireNonNull(value, "JSON content cannot be null");
        try {// Parse JSON using Jackson
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree((JsonParser) value);
        } catch (Exception e) {
            throw new RuntimeException("The Value passed is not of Json Type", e);
        }
        content = value;
    }

    private void handleDocument(K value) {
        Objects.requireNonNull(value, "Document content cannot be null");
        if (!(value instanceof Document)) {
            throw new RuntimeException("The Value passed is not of Document Type");
        }
        content = value;
    }
}
