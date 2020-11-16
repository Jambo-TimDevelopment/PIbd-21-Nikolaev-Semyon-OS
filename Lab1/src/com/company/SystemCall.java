package com.company;

public class SystemCall {
    private int id;
    private Object[] args;
    private String description;

    SystemCall(int id, Object[] args, String description){
        this.id = id;
        this.args = args;
        this.description = description;
    }

    SystemCall(int id, Object[] args){
        this.id = id;
        this.args = args;
    }

    public String getDescription(){ return description; }

    public int getID(){ return id; }

    public Object[] getArgs(){return args; }
}
