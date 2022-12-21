package com.olleb;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.olleb.service.TelegramMessage;

public class SerializationTest {

    private static final String json = "[{\"update_id\":981138569,\"message\":{\"message_id\":9,\"from\":{\"id\":1111111,\"is_bot\":false,\"first_name\":\"Àngel\",\"last_name\":\"O.\",\"username\":\"anyusername\",\"language_code\":\"en\"},\"chat\":{\"id\":1111111,\"first_name\":\"Àngel\",\"last_name\":\"O.\",\"username\":\"anyusername\",\"type\":\"private\"},\"date\":1671026112,\"text\":\"834529\"}},{\"update_id\":981138570,\"message\":{\"message_id\":10,\"from\":{\"id\":1111111,\"is_bot\":false,\"first_name\":\"Àngel\",\"last_name\":\"O.\",\"username\":\"anyusername\",\"language_code\":\"en\"},\"chat\":{\"id\":1111111,\"first_name\":\"Àngel\",\"last_name\":\"O.\",\"username\":\"anyusername\",\"type\":\"private\"},\"date\":1671026420,\"text\":\"421120\"}}]";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testJsonNodeToTelegramMessageList() throws JsonParseException, JsonProcessingException, IOException {
        JsonNode jsonNode = mapper.readTree(mapper.getFactory().createParser(json));
        List<TelegramMessage> list = mapper.convertValue(jsonNode,
                new TypeReference<List<TelegramMessage>>() {
                });
        assertThat(list.isEmpty(), is(false));
    }

}
