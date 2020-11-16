package com;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Process {
    Random random = new Random();
    private List<Page> pages = new LinkedList<Page>();
    private int ID;
    private int countPages = random.nextInt( 10 ) + 1;

    Process(int ID) {
        this.ID = ID;
        createPages();
    }

    private void createPages() {
        for (int i = 0; i < countPages; i++) {
            Page page = new Page( ID, i, "processID: " + ID + ", pageID:  " + i, 0, 0);
            pages.add( page );
        }
    }

    public int getID() {
        return ID;
    }

    public List<Page> getPageList() {
        return pages;
    }

    public Page getPage(int index) {
        return pages.get( index );
    }
}