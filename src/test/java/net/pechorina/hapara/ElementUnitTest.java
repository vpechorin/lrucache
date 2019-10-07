package net.pechorina.hapara;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementUnitTest {

    @Test
    public void setNextShouldSetPrevious() {
        Element<Integer> first = new Element<>(1, 100);
        Element<Integer> second = new Element<>(2, 200);

        first.setNext(second);

        assertThat(first.getNext()).isEqualTo(second);
        assertThat(second.getPrevious()).isEqualTo(first);
    }

    @Test
    public void setPreviousShouldSetNext() {
        Element<Integer> first = new Element<>(1, 100);
        Element<Integer> second = new Element<>(2, 200);

        second.setPrevious(first);

        assertThat(second.getPrevious()).isEqualTo(first);
        assertThat(first.getNext()).isEqualTo(second);
    }
}
