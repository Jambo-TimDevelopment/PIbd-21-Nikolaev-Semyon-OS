package com;

import java.util.Random;

public class MemoryManager {
    private int countProcess;
    Disk disk = new Disk();
    RAM RAM = new RAM();

    public void createProcess(int countProcess) {
        this.countProcess = countProcess;
        for (int i = 0; i < countProcess; i++) {
            Process newProcess = new Process( i );
            RAM.addProcess( newProcess );
            PagesTable pageTable = new PagesTable( newProcess );
            RAM.addTable( pageTable );
        }
    }

    public void work(int count) {
        RAM.initIDList();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Process process = RAM.getProcess( random.nextInt( countProcess ));
            Page page = process.getPage( random.nextInt( process.getPageList().size() ) );
            System.out.println( "ОС запрашивает у процесса " + page.getProcessID() + " страницу " + page.getID() );
            RAM.setInTableNRU( page, disk );
        }

        System.out.println( "________________________________________________________________________\n" +
                "Финальные таблицы" );
        for (int i = 0; i < countProcess; i++) {
            RAM.getTable( i ).printTable();
        }
        RAM.printTable();
    }
}
