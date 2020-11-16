package com;

import java.util.LinkedList;
import java.util.List;

public class RAM {
   private List<Process> processes = new LinkedList<Process>();
    private List<PagesTable> pagesTables = new LinkedList<PagesTable>();
    private List<Page> physicalTable = new LinkedList<>();
    private final int sizeRAM = 10;

    public void addProcess(Process process) {
        processes.add( process );
    }

    public Process getProcess(int index) {
        return processes.get( index );
    }

    public void addTable(PagesTable pagesTable) {
        pagesTables.add(pagesTable);
    }

    public PagesTable getTable(int index) {
        return pagesTables.get(index);
    }

    public void initIDList() {
        for (int i = 0; i < sizeRAM; i++) {
            physicalTable.add(i, null);
        }
    }

    public void setInTableNRU(Page page, Disk disk) {
        if (physicalTable.contains(page)) { //
            System.out.print("Изменений в таблице физической памяти нет\n\n");
            return;
        }
        page.setReferenced(1);
        for (int i = 0; i < sizeRAM; i++) {
            if (physicalTable.get(i) == null) {
                physicalTable.add(i, page);
                pagesTables.get(page.getProcessID()).setAddressInRAM(page.getID(), i);
                System.out.println("Изменение таблицы страниц");
                getTable(page.getProcessID()).printTable();
                System.out.println("Изменение таблицы физической памяти");
                printTable();
                break;
            }
        }
        NRUAlgorithm( page, disk );
    }

    private void NRUAlgorithm(Page page, Disk disk) {
        int pageID = 0;
        for (int i = 0; physicalTable.get(i) != null && i < sizeRAM; i++) {
            if (physicalTable.get(i).getReferenced() != 1 && physicalTable.get(i).getModified() == 1) {
                pageID = i;
                disk.addPage(physicalTable.get(i));
                pagesTables.get(page.getProcessID()).deleteFromRAM(page.getID());
            } else if (physicalTable.get(i).getReferenced() == 1) {
                if(physicalTable.get(i).getTimeAfterReferenced() == 1){
                    physicalTable.get(i).setModified(1);
                }
                else{
                    physicalTable.get(i).setReferenced(0);
                }
                physicalTable.get(i).setModified(1);
            }
        }
        pagesTables.get(physicalTable.get(pageID).getProcessID()).deleteFromRAM(pageID);
        physicalTable.remove(pageID);
        physicalTable.add(pageID, page);
        pagesTables.get(page.getProcessID()).setAddressInRAM(page.getID(), pageID);
        getTable(page.getProcessID()).printTable();
        printTable();
        //disk.printPageFromDisk();
    }

    public void printTable() {
        System.out.println("Физическая таблица");
        System.out.println("Адрес \t|| Данные страницы");
        for (int i = 0; i < sizeRAM; i++) {
            print(i);
            if (physicalTable.get(i) != null) {
                System.out.println("\t  " + physicalTable.get(i).getData() +
                        ", Modified: " + physicalTable.get(i).getModified());
            } else {
                System.out.println("\t-");
            }
        }
        System.out.print("\n" + "\n");
    }

    public void print(int digit) {
        if (digit < 10) {
            System.out.print("\t" + digit);
        } else {
            System.out.print("\t" + digit);
        }
    }
}