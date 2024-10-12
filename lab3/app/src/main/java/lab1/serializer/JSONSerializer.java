package lab1.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class JSONSerializer<T> implements Serialization<T> {
    private ObjectMapper objectMapper;

    public JSONSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public String serialize(T entity) throws IOException {
        return objectMapper.writeValueAsString(entity);
    }

    @Override
    public T deserialize(String data, Class<T> entityType) throws IOException {
        return objectMapper.readValue(data, entityType);
    }

    @Override
    public void writeToFile(T entity, File file) throws IOException {
        objectMapper.writeValue(file, entity);
    }

    @Override
    public T readFromFile(File file, Class<T> entityType) throws IOException {
        return objectMapper.readValue(file, entityType);
    }
}