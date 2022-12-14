package com.olleb.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Àngel Ollé Blázquez
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelegramMessage {

    private String id;
    private String username;
    private String text;
    private String subscriptionCode;

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubscriptionCode() {
        return subscriptionCode;
    }

    public void setSubscriptionCode(String subscriptionCode) {
        this.subscriptionCode = subscriptionCode;
    }

    @JsonProperty("message")
    private void unpackNameFromNestedObject(JsonNode json) {
        id = unpackChatValueFromMessageChat(json, "id");
        username = unpackChatValueFromMessageChat(json, "username");
        text = json.get("text").asText();
    }

    private String unpackChatValueFromMessageChat(JsonNode json, String property) {
        return json.get("chat").get(property).asText();
    }

    @Override
    public String toString() {
        return "TelegramMessage [id=" + id + ", username=" + username + ", text=" + text + ", subscriptionCode="
                + subscriptionCode + "]";
    }
}
