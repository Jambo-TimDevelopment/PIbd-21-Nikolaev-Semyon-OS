package com;

public class Main {
    public static void main(String[] args) {
        MemoryManager memoryManager = new MemoryManager();
        memoryManager.createProcess( 4 );
        memoryManager.work( 20 );
    }
}
