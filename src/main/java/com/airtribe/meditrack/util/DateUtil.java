package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class DateUtil {
    private DateUtil() { }
    public static LocalDate parseDate(String input) { try { return LocalDate.parse(input); } catch (DateTimeParseException ex) { throw new InvalidDataException("date must use yyyy-MM-dd format", ex); } }
    public static String formatDate(LocalDate date) { return date.toString(); }
}
