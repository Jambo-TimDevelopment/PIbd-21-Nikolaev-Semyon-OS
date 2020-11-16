package com.company;

import java.util.ArrayList;

public class Stack<T> {

    private ArrayList<T> arr = null;

    public void push(T data) {
        if (arr == null) {
            arr = new ArrayList<T>();
        }
        arr.add(data);
    }

    public T pop() {
        T last;

        if(arr.size() > 0) {
            last = arr.get(arr.size() - 1);
            arr.remove(arr.size() - 1);
        }else {
            last = null;
        }
        return last;
    }

    public int size() {
        return arr.size();
    }

    public void print() {
        T[] array = (T[]) arr.toArray();

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i].toString());
        }
    }

    public void clear() {
        arr.clear();
    }

}