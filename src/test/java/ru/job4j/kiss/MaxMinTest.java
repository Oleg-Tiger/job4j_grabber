package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MaxMinTest {

    @Test
    public void testMaxWhenNaturalOrder() {
        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("Def");
        String rsl = MaxMin.max(list, Comparator.naturalOrder());
        assertThat(rsl, is("Def"));
    }

    @Test
    public void testMaxWhenReverseOrder() {
        List<String> list = new ArrayList<>();
        list.add("Abc");
        list.add("Def");
        String rsl = MaxMin.max(list, Comparator.reverseOrder());
        assertThat(rsl, is("Abc"));
    }

    @Test
    public void testMinWhenNaturalOrder() {
        List<Integer> list = new ArrayList<>();
        list.add(-10);
        list.add(10);
        list.add(0);
        assertThat(MaxMin.min(list, Comparator.naturalOrder()), is(-10));
    }

    @Test
    public void testMinWhenReverseOrder() {
        List<Integer> list = new ArrayList<>();
        list.add(-10);
        list.add(10);
        list.add(0);
        assertThat(MaxMin.min(list, Comparator.reverseOrder()), is(10));
    }
}