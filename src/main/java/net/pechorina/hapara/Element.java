package net.pechorina.hapara;

import java.util.Objects;

public class Element<K> {
    private K key;
    private int value;
    private Element<K> previous;
    private Element<K> next;

    public Element(K key, int value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public Element<K> getPrevious() {
        return previous;
    }

    public Element<K> setPrevious(Element<K> previous) {
        if (previous != null) {
            previous.next = this;
        }
        this.previous = previous;
        return this;
    }

    public Element<K> getNext() {
        return next;
    }

    public Element<K> setNext(Element<K> next) {
        if (next != null) {
            next.previous = this;
        }
        this.next = next;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element<?> element = (Element<?>) o;
        return getValue() == element.getValue() &&
               Objects.equals(getKey(), element.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }
}
