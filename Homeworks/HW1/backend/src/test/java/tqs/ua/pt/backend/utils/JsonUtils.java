package tqs.ua.pt.backend.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.TimeZone;

public class JsonUtils {
    public static byte[] toJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // Para serializar as datas no formato ISO-8601
        mapper.setTimeZone(TimeZone.getDefault()); // Define o fuso horário padrão
        return mapper.writeValueAsBytes(object);
    }
}
