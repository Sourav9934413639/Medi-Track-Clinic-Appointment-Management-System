package com.airtribe.meditrack.util;

import com.airtribe.meditrack.exception.InvalidDataException;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public final class Validator {
    private static final Pattern EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PHONE = Pattern.compile("^[0-9]{10}$");
    private Validator() { }
    public static void requireText(String value, String fieldName) { if (value == null || value.trim().isEmpty()) throw new InvalidDataException(fieldName + " cannot be blank"); }
    public static void requireAge(int age) { if (age <= 0 || age > 120) throw new InvalidDataException("age must be between 1 and 120"); }
    public static void requirePhone(String phone) { requireText(phone, "phone"); if (!PHONE.matcher(phone).matches()) throw new InvalidDataException("phone must contain exactly 10 digits"); }
    public static void requireEmail(String email) { requireText(email, "email"); if (!EMAIL.matcher(email).matches()) throw new InvalidDataException("email is invalid"); }
    public static void requirePositive(BigDecimal value, String fieldName) { requireNonNull(value, fieldName); if (value.compareTo(BigDecimal.ZERO) <= 0) throw new InvalidDataException(fieldName + " must be positive"); }
    public static <T> T requireNonNull(T value, String fieldName) { if (value == null) throw new InvalidDataException(fieldName + " cannot be null"); return value; }
}
