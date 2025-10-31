package cn.icexmoon.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

/**
 * @ClassName JsonConverter
 * @Description 
 * @Author icexmoon@qq.com
 * @Date 2025/10/27 下午5:23
 * @Version 1.0
 */
public class JsonConverter<T> implements AttributeConverter<T, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(T attribute) {
        try {
            return attribute == null ? null : objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T convertToEntityAttribute(String dbData) {
        try {
            return dbData == null ? null : (T) objectMapper.readValue(dbData, Object.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }
}
