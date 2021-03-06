package com.hometest.walmart.taskList.model;

import java.util.concurrent.atomic.AtomicInteger;


public class Sequence {

    private static final AtomicInteger counter = new AtomicInteger();

    public int nextValue() {
        return counter.getAndIncrement();
    }

}
