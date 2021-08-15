package com.cskaoyan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetTest {
    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        HashSet<Integer> set1 = new HashSet<>(list);

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(6);
        set.add(7);

        HashSet<Integer> clone = (HashSet<Integer>) set.clone();

        boolean b = set.retainAll(set1);

    }
}
