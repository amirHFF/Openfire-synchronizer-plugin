package io.projectZ.kafka;
/*
  Project : KeyCloak-kafka-plugin
  Author  : AmirHFF
  Created : 6/16/2026 - 8:16 AM
*/

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.projectZ.dto.UserEvent;
import io.projectZ.dto.UserInfo;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class JsonDeserializer implements Deserializer<UserEvent> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UserEvent deserialize(String s, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, UserEvent.class);
        } catch (IOException e) {
            System.out.println("exception thrown in deserializing: "+e);

            return null;
        }
    }
}

