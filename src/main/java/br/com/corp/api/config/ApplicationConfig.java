package br.com.corp.api.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "br.com.corp.api.*")
public class ApplicationConfig implements WebMvcConfigurer {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ISO_LOCAL_TIME;
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Autowired
    protected Environment environment;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true).allowedOrigins(environment.getProperty("allow.origins", "*")).allowedHeaders("*").allowedMethods("*");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper()));
        converters.add(new StringHttpMessageConverter());
        converters.add(new BufferedImageHttpMessageConverter());
    }

    @Bean
    public ObjectMapper objectMapper(){
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer());
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer());
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        javaTimeModule.addDeserializer(Double.TYPE, new DoubleDeserializer());
        javaTimeModule.addDeserializer(Double.class, new DoubleDeserializer());

        builder.modules(javaTimeModule);

        ObjectMapper mapper = builder.build();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        return mapper;
    }

    public class DoubleDeserializer extends JsonDeserializer<Double> {

        @Override
        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return Double.parseDouble(jsonParser.getValueAsString().replaceAll(",", "."));
        }
    }

    public class LocalDateSerializer extends JsonSerializer<LocalDate> {

        @Override
        public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider serializers) throws IOException, JsonProcessingException {
            generator.writeString(value.format(DATE_FORMAT));
        }
    }

    public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return LocalDate.parse(parser.getValueAsString(), DATE_FORMAT);
        }
    }

    public class LocalTimeSerializer extends JsonSerializer<LocalTime> {
        @Override
        public void serialize(LocalTime value, JsonGenerator generator, SerializerProvider serializers) throws IOException, JsonProcessingException {
            generator.writeString(value.format(TIME_FORMAT));
        }
    }

    public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {
        @Override
        public LocalTime deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return LocalTime.parse(parser.getValueAsString(), TIME_FORMAT);
        }
    }

    public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider serializers) throws IOException, JsonProcessingException {
            generator.writeString(value.format(DATE_TIME_FORMAT));
        }
    }

    public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return LocalDateTime.parse(parser.getValueAsString(), DATE_TIME_FORMAT);
        }
    }
}
