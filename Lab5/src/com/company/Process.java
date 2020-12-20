package com.company;

import java.util.Random;

public class Process {
    private int selfIndex;
    private boolean isBlocked;
    private int timeExecute;
    private int deviceIndex;

    Process(int selfIndex, boolean isBlocked, int timeExecute ){
        this.selfIndex = selfIndex;
        this.isBlocked = isBlocked;
        this.timeExecute = timeExecute;
        if(isBlocked) {
            Random rnd = new Random();
            this.deviceIndex = rnd.nextInt(10);
        }
    }

    public void start(){
        if(isBlocked){
            System.out.println("Процесс --" + selfIndex + "-- прерван");
        }else{
            System.out.println("Процесс --" + selfIndex
                    + "-- выполнен за время --> " + timeExecute);
        }
    }

    public void continued(){
        if(isBlocked){
            System.out.println("Процесс --" + selfIndex
                    + "-- завершился после прерывания за время --> " + timeExecute);
        }
    }

    public boolean getIsBlocked(){ return isBlocked; }

    public int getTimeExecute(){ return timeExecute; }

    public int getDeviceIndex(){return deviceIndex; }
}
