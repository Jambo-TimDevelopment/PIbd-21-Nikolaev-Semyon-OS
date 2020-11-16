package com;

import java.util.LinkedList;
import java.util.List;

public class Disk {
    private List<Page> pageTable = new LinkedList<>();

    void addPage(Page page){
        pageTable.add(page);
    }

    void printPageFromDisk(){
        System.out.println("___Находится на жестком диске___");
        for(int i = 0; i < pageTable.size(); i++){
            System.out.println( " ProcessId " + pageTable.get(i).getProcessID() + " PageId: " + pageTable.get(i).getID());
        }
        System.out.println();
    }
}