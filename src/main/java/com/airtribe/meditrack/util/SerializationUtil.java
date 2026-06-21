package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public final class SerializationUtil {
    private SerializationUtil() { }
    public static void save(String filePath, Serializable value) { Path path = Path.of(filePath); try { if (path.getParent() != null) Files.createDirectories(path.getParent()); try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) { out.writeObject(value); } } catch (IOException ex) { throw new InvalidDataException("Unable to serialize data", ex); } }
    public static <T> Optional<T> load(String filePath, Class<T> type) { Path path = Path.of(filePath); if (!Files.exists(path)) return Optional.empty(); try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) { return Optional.of(type.cast(in.readObject())); } catch (IOException | ClassNotFoundException | ClassCastException ex) { throw new InvalidDataException("Unable to deserialize data", ex); } }
}
