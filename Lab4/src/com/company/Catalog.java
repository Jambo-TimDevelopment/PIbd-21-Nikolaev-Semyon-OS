package com.company;

import javax.xml.namespace.QName;
import java.util.LinkedList;

public class Catalog extends File{
    private LinkedList<File> childFileContainer;
    private boolean isRootCatalog = false;

    Catalog(String name, Catalog parent, int size, LinkedList<Cluster> clusterContainer) {
        super(name, parent, size, clusterContainer);
        childFileContainer = new LinkedList<>();
        System.out.println("Инициализрован новый каталог ->" + this.name);
        hash = seedForHash;
        seedForHash++;
    }

    void addChild(File file){
        childFileContainer.add(file);
        System.out.println("Добавлен дочерний элемент в каталог -> " + this.name);
    }

    public LinkedList<File> getChild() {
        return childFileContainer;
    }

    public boolean isRootCatalog() {
        return isRootCatalog;
    }

    public void setRootCatalog(boolean rootCatalog) {
        isRootCatalog = rootCatalog;
    }

    @Override
    public String toString(){
        return name + " (" + hash + " Catalog)";
    }
}
