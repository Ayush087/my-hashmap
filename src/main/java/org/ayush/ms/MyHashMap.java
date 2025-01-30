package org.ayush.ms;

import java.util.LinkedList;

public class MyHashMap<K, V> {
    private static final int DEFAULT_SIZE = 16;
    private final int capacity;
    private final LinkedList<Entry<K, V>>[] table;

    public MyHashMap() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        table = new LinkedList[capacity];
    }

    public void put(K key, V value) {
        int idx = hash(key);

        if (table[idx] == null) {
            table[idx] = new LinkedList<>();
        }

        Entry<K, V> entry = getEntry(key, table[idx]);
        if (entry == null) {
            table[idx].add(new Entry<>(key, value));
        } else {
            entry.value = value;
        }
    }

    private Entry<K, V> getEntry(K key, LinkedList<Entry<K, V>> bucket) {
        for (Entry<K, V> entry : bucket) {
            if (key.equals(entry.key)) {
                return entry;
            }
        }
        return null;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    public V getKey(K key) {
        int idx = hash(key);
        LinkedList<Entry<K, V>> bucket = table[idx];

        if (bucket != null) {
            Entry<K, V> entry = getEntry(key, bucket);
            if (entry != null) {
                return entry.value;
            }
        }
        return null;
    }

    static class Entry<K, V> {
        final K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
