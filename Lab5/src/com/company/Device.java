package com.company;

public class Device {
    private int timeExecute;
    private int index;

    Device(int index, int timeExecute){
        //System.out.println("\t timeExecute = " + timeExecute);
        this.timeExecute = timeExecute;
        this.index = index;
    }

    public void work(){
        System.out.println("\t\t Произошло прерывание. Выполнение работы устройства...");
    }

    public int getIndex(){
        return index;
    }

    public int getTimeExecute(){ return timeExecute; }
}
