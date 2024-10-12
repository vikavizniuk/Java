package lab1.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class YMLSerializer<T> implements Serialization<T> {
    private final ObjectMapper yamlMapper;

    public YMLSerializer(){
        yamlMapper = new YAMLMapper();
        yamlMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public String serialize(T entity) throws IOException {
        return yamlMapper.writeValueAsString(entity);
    }

    @Override
    public T deserialize(String data, Class<T> entityType) throws IOException {
        return yamlMapper.readValue(data, entityType);
    }

    @Override
    public void writeToFile(T entity, File file) throws IOException {
        yamlMapper.writeValue(file, entity);
    }

    @Override
    public T readFromFile(File file, Class<T> entityType) throws IOException {
        return yamlMapper.readValue(file, entityType);
    }
}