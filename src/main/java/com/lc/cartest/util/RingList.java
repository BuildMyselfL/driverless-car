package com.lc.cartest.util;

import org.springframework.util.CollectionUtils;

import java.util.LinkedList;

/**
 * @program: driverless-car
 * @description: 简单的环形队列
 * @author: lc
 * @create: 2020-07-01 22:04
 **/
public class RingList<T> {
    private LinkedList<T> linkedList = new LinkedList<>();
    private int size;

    public RingList() {
    }

    public RingList(LinkedList<T> linkedList) {
        this.linkedList = linkedList;
        this.size = linkedList.size();
    }

    public T next (T t) {
        assert !CollectionUtils.isEmpty(linkedList)&&linkedList.contains(t);
        int i = linkedList.indexOf(t);
        if (i == size - 1) {
            return linkedList.getFirst();
        }
        return linkedList.get( i + 1);
    }

    public T next (T t,int nextNum) {
        assert !CollectionUtils.isEmpty(linkedList)&&linkedList.contains(t);
        if (size == 1) {
            return linkedList.getFirst();
        }
        int i = linkedList.indexOf(t);
        int currentIndex = (nextNum + i)%size;
        return linkedList.get(currentIndex);

    }

    public void add(T t){
        this.linkedList.add(t);
        this.size = linkedList.size();
    }
}
