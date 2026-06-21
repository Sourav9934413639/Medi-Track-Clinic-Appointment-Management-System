package com.airtribe.meditrack.interfaces;

import java.util.List;

public interface Searchable<T> {
    List<T> searchByName(String name);
    default boolean matches(String value, String query) { return value != null && query != null && value.toLowerCase().contains(query.toLowerCase()); }
}
