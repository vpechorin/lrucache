package net.pechorina.hapara;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LRUCacheUnitTest {

    @Test
    public void taskValidationTest() {
        LRUCache<Integer> cache = new LRUCache<>(2 /* capacity */);

        cache.put(1, 1);
        cache.put(2, 2);
        assertThat(cache.get(1)).isEqualTo(1);       // returns 1

        cache.put(3, 3);    // evicts key 2
        assertThat(cache.get(2)).isEqualTo(-1);       // returns -1 (not found)

        cache.put(4, 4);    // evicts key 1
        assertThat(cache.get(1)).isEqualTo(-1);       // returns -1 (not found)
        assertThat(cache.get(3)).isEqualTo(3);       // returns 3
        assertThat(cache.get(4)).isEqualTo(4);       // returns 4
    }

    @Test
    public void emptyCacheCheck() {
        Cache<String> cache = new LRUCache<>(2);
        assertThat(cache.get("A")).isEqualTo(-1);
    }

    @Test
    public void noKeyExistsCheck() {
        Cache<String> cache = new LRUCache<>(2);
        cache.put("A", 1);

        assertThat(cache.get("B")).isEqualTo(-1);
    }

    @Test
    public void shouldEvictTheOldestElementOnNoAccess_withMaxSize_1() {
        Cache<String> cache = new LRUCache<>(1);

        cache.put("A", 1);
        cache.put("B", 2);

        assertThat(cache.get("A")).isEqualTo(-1);
    }

    @Test
    public void shouldEvictTheOldestElementOnNoAccess_withMaxSize_2() {
        Cache<String> cache = new LRUCache<>(2);

        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);

        assertThat(cache.get("A")).isEqualTo(-1);
    }

    @Test
    public void shouldEvictTheOldestElementOnNoAccess_withMaxSize_3() {
        Cache<String> cache = new LRUCache<>(3);

        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        cache.put("D", 3);

        assertThat(cache.get("A")).isEqualTo(-1);
    }

    @Test
    public void shouldEvictLRUElement_withMaxSize_3() {
        Cache<String> cache = new LRUCache<>(3);

        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);

        assertThat(cache.get("A")).isEqualTo(1);
        assertThat(cache.get("B")).isEqualTo(2);
        cache.put("D", 4);
        assertThat(cache.get("C")).isEqualTo(-1);
    }


}
