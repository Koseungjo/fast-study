package org.delivery.api.config.objectMapper;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.text.html.ObjectView;

@Configuration
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper(){
        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module()); // jdk 8 이휴

        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 모르는 json 값은 파싱하지 않음

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // 날짜 관련 직렬화;

        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy()); // 스네이크 케이스

        return objectMapper;
    }
}
