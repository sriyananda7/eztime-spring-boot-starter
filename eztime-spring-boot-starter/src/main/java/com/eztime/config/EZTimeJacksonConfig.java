package com.eztime.config;

import com.eztime.util.EZDate;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class EZTimeJacksonConfig {

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(EZDate.class, new JsonSerializer<>() {
            @Override
            public void serialize(EZDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(value.toApiString());
            }
        });
        mapper.registerModule(module);
    }
}
