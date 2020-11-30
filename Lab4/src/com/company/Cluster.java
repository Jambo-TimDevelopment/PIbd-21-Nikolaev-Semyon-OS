package com.company;

public class Cluster {

    private int size;
    private int index;
    private int status = 0;

    public Cluster(int size) {
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
} 