package lab1.serializer;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

public class XMLSerializer<T> implements Serialization<T> {
    private final XmlMapper xmlMapper;

    public XMLSerializer() {
        xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public String serialize(T entity) throws IOException {
        return xmlMapper.writeValueAsString(entity);
    }

    @Override
    public T deserialize(String data, Class<T> entityType) throws IOException {
        return xmlMapper.readValue(data, entityType);
    }

    @Override
    public void writeToFile(T entity, File file) throws IOException {
        xmlMapper.writeValue(file, entity);
    }

    @Override
    public T readFromFile(File file, Class<T> entityType) throws IOException {
        return xmlMapper.readValue(file, entityType);
    }
}