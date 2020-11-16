package com;

public class Page {
    private int ID;
    private int processID;
    private String data;
    private int modified;
    private int referenced;
    private int timeAfterAppeal = 0;
    private byte memory = 4;

    public Page(int processID, int ID, String data, int referenced, int modified) {
        this.processID = processID;
        this.ID = ID;
        this.data = data;
        this.modified = modified;
        this.referenced = referenced;
    }

    public String getData() {
        return data;
    }

    public int getID() {
        return ID;
    }

    public int getProcessID() {
        return processID;
    }

    public int getMemory() {
        return memory;
    }

    public int getModified() {
        return modified;
    }

    public void setModified(int modified) {
        if(modified == 1){
            timeAfterAppeal = 0;
        }
        this.modified = modified;
    }

    public int getTimeAfterReferenced() {
        return timeAfterAppeal;
    }

    public void setReferenced(int referenced) {
        if(referenced == 1){
            timeAfterAppeal = 1;
        }else {
            timeAfterAppeal = 0;
        }
        this.referenced = referenced;
    }

    public int getReferenced() {
        return referenced;
    }
}