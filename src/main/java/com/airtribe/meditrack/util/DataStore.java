package com.airtribe.meditrack.util;

import com.airtribe.meditrack.entity.MedicalEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DataStore<T extends MedicalEntity> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = 1L;
    private final Map<String, T> records = new HashMap<>();
    public synchronized void save(T entity) { records.put(entity.getId(), entity); }
    public synchronized Optional<T> findById(String id) { return Optional.ofNullable(records.get(id)); }
    public synchronized List<T> findAll() { return new ArrayList<>(records.values()); }
    public synchronized boolean delete(String id) { return records.remove(id) != null; }
    public synchronized int size() { return records.size(); }
    public synchronized List<T> sorted(Comparator<T> comparator) { List<T> list = findAll(); list.sort(comparator); return list; }
    @Override public synchronized Iterator<T> iterator() { return findAll().iterator(); }
}
