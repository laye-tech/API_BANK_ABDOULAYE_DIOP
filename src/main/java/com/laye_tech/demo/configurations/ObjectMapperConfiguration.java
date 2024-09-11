package com.laye_tech.demo.configurations;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ObjectMapperConfiguration {
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    @Primary
    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        SimpleModule simpleModule = new SimpleModule();


        /*
         * The serializer should system zone string date to equivalent UTC LocalDateTime (with Z)
         */
        simpleModule.addSerializer(LocalDateTime.class, new JsonSerializer<>() {

            @Override
            public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString(dateTimeFormatter.format(localDateTime.atZone(ZoneId.of("UTC"))));
            }
        });

        /*
         * The deserializer should transform UTC string date (with Z) to equivalent system zone LocalDateTime
         */
        simpleModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<>() {
            @Override
            public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

                LocalDateTimeDeserializer localDateTimeDeserializer = LocalDateTimeDeserializer.INSTANCE;
                LocalDateTime localDateTimeUtc = localDateTimeDeserializer.deserialize(jsonParser, deserializationContext);

                // If text ends with Z, its a ZULU time, convert it to server time
                // If not, take it as it is
                if (jsonParser.getText().trim().endsWith("Z")) {
                    return LocalDateTime.parse(jsonParser.getText().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXX"));
                }
                return LocalDateTime.parse(jsonParser.getText().trim(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
        });

        objectMapper.registerModule(simpleModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;

    }
}
