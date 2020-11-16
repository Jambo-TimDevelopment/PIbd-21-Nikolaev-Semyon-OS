package com;

import java.util.List;

public class PagesTable {
    private List<Page> pageList;
    private Process process;
    private int[] addressTable;

    PagesTable(Process process) {
        this.pageList = process.getPageList();
        this.process = process;
        initRAMIdList();
    }

    private void initRAMIdList() {
        addressTable = new int[pageList.size()];
        for (int i = 0; i < addressTable.length; i++) {
            int defaultValue = -1;
            addressTable[i] = defaultValue;
        }
    }

    public void setAddressInRAM(int index, int newAddress) {
        addressTable[index] = newAddress;
    }

    public void deleteFromRAM(int address) {
        for (int i = 0; i < pageList.size(); i++) {
            if (addressTable[i] == address) {
                int defaultValue = -1;
                addressTable[i] = defaultValue;
            }
        }
    }

    public void printTable() {
        System.out.println("Процесс № " + process.getID() + " таблица: ");
        System.out.println("Virtual || Physical ||Referenced|| Modified");
        for (int i = 0; i < pageList.size(); i++) {
            print(i);
            System.out.print("||");
            if (addressTable[i] != -1) {
                print(addressTable[i]);
                System.out.print("\t||");
            } else {
                System.out.print("\t-\t\t||");
            }
            print(process.getPage(i).getReferenced());
            System.out.print("\t||");
            print(process.getPage(i).getModified());
            System.out.println();
        }
        System.out.println();
    }

    public void print(int digit) {
        System.out.print("\t" + digit + "\t");
    }
}