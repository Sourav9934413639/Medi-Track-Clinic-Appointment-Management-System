package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CSVUtil {
    private CSVUtil() { }
    public static List<List<String>> readCsv(String filePath) {
        List<List<String>> rows = new ArrayList<>(); Path path = Path.of(filePath); if (!Files.exists(path)) return rows;
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line; boolean header = true;
            while ((line = reader.readLine()) != null) { if (header) { header = false; continue; } if (!line.trim().isEmpty()) rows.add(Arrays.asList(line.split(",", -1))); }
            return rows;
        } catch (IOException ex) { throw new InvalidDataException("Unable to read CSV: " + filePath, ex); }
    }
    public static void writeCsv(String filePath, String header, List<String> lines) {
        Path path = Path.of(filePath);
        try { if (path.getParent() != null) Files.createDirectories(path.getParent()); try (BufferedWriter writer = Files.newBufferedWriter(path)) { writer.write(header); writer.newLine(); for (String line : lines) { writer.write(line); writer.newLine(); } } }
        catch (IOException ex) { throw new InvalidDataException("Unable to write CSV: " + filePath, ex); }
    }
    public static String join(Object... values) { StringBuilder b = new StringBuilder(); for (int i = 0; i < values.length; i++) { if (i > 0) b.append(','); b.append(String.valueOf(values[i]).replace(",", " ")); } return b.toString(); }
}
