package lab1.serializer;

import java.io.File;
import java.io.IOException;

public interface Serialization<T> {

    String serialize(T entity) throws IOException;

    T deserialize(String data, Class<T> entityType) throws IOException;

    void writeToFile(T entity, File file) throws IOException;

    T readFromFile(File file, Class<T> entityType) throws IOException;
}