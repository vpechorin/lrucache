package net.pechorina.hapara;

public interface Cache<K> {
    int get(K key);

    void put(K key, int value);
}
