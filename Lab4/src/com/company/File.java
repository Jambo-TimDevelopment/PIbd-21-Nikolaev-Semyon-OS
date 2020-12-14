package com.company;

import java.util.LinkedList;

public class File {
    private Catalog parent;
    protected String name;
    protected static int seedForHash = 0;
    protected int hash = 0;
    private int size;
    LinkedList<Cluster> clusterContainer;

    File(String fileName, Catalog parent, int size, LinkedList<Cluster> clusterContainer){
        this.name = fileName;
        this.parent = parent;
        this.size = size;
        this.clusterContainer = clusterContainer;
        hash = seedForHash;
        seedForHash++;
    }

    public LinkedList<Cluster> getClusterContainer() {
        return clusterContainer;
    }

    public void setClusterContainer(LinkedList<Cluster> clusterContainer) {
        this.clusterContainer = clusterContainer;
    }

    public Catalog getParent() {
        return parent;
    }

    public void setParent(Catalog parent) {
        this.parent = parent;
    }

    public int getSize() {
        return size;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){                   // я помещаю простейший хеш в назавние файла,
        return name + " (" + hash + " File)";   // чтобы не было нужды каждый раз вводить название
    }
}
