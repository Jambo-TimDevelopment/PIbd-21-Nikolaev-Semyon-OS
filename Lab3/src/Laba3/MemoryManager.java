package Laba3;

import java.util.Random;

public class MemoryManager {
    private int countProcess;
    Disk disk = new Disk();
    RAM RAM = new RAM();

    public void createProcess(int countProcess) {
        this.countProcess = countProcess;
        for (int i = 0; i < countProcess; i++) {
            Process newProcess = new Process( i );
            disk.addProcess( newProcess );
            PagesTable pageTable = new PagesTable( newProcess );
            RAM.addTable( pageTable );
        }
    }

    public void work(int count) {
        RAM.initIDList();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            Process process = disk.getProcess( random.nextInt( countProcess ));
            Page page = process.getPage( random.nextInt( process.getPageList().size() ) );
            System.out.println( "ОС запрашивает у процесса " + page.getProcessID() + " страницу " + page.getID() );
            RAM.setInTableNRU( page );
        }

        System.out.println( "________________________________________________________________________\n" +
                "Финальные таблицы" );
        for (int i = 0; i < countProcess; i++) {
            RAM.getTable( i ).printTable();
        }
        RAM.printTable();
    }
}
