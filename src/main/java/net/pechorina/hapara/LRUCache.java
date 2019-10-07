package net.pechorina.hapara;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LRUCache<K> implements Cache<K> {

    public static final int CACHE_MISS = -1;

    private final int maxSize;
    private Map<K, Element<K>> map;

    private Element<K> first;
    private Element<K> last;

    public LRUCache(int maxSize) {
        if (maxSize < 1) throw new IllegalArgumentException("Cache size must be a positive number");
        this.maxSize = maxSize;
        this.map = new HashMap<>(maxSize);
    }

    @Override
    public int get(K key) {
        Objects.requireNonNull(key, "Cache key must be not null");
        Element<K> element = map.get(key);
        if (element == null) return CACHE_MISS;

        onAccess(element);
        return element.getValue();
    }

    @Override
    public void put(K key, int value) {
        Objects.requireNonNull(key, "Cache key must be not null");
        if (value <= 0) throw new IllegalArgumentException("Not a positive value");

        if (isCapacityReached()) {
            removeLast();
        }

        Element<K> element = addToTop(new Element<>(key, value));

        map.put(key, element);
    }

    private void onAccess(Element<K> element) {
        unlink(element);
        addToTop(element);
    }

    private void unlink(Element<K> element) {
        Element<K> previous = element.getPrevious();
        Element<K> next = element.getNext();

        if (previous != null) {
            previous.setNext(next);
        }

        if (next != null) {
            next.setPrevious(previous);
        }

        element.setPrevious(null);
        element.setNext(null);

        if (this.last == element) {
            this.last = previous;
        }

        if (this.first == element) {
            this.first = next;
        }
    }

    private Element<K> addToTop(Element<K> element) {
        Element<K> oldFirst = this.first;
        element.setNext(oldFirst);

        setFirst(element);

        if (this.last == null) {
            setLast(oldFirst != null ? oldFirst : element);
        }
        return element;
    }

    private void removeLast() {
        if (last == null) return;

        Element<K> element = this.last;
        unlink(element);
        map.remove(element.getKey());
    }

    private boolean isCapacityReached() {
        return map.size() >= maxSize;
    }

    private void setFirst(Element<K> element) {
        this.first = element;
    }

    private void setLast(Element<K> element) {
        this.last = element;
    }
}
