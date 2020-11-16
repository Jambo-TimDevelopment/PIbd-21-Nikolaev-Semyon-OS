package Laba3;

import java.util.LinkedList;
import java.util.List;

public class Disk {
    private List<Process> processes = new LinkedList<Process>();

    public void addProcess(Process process) {
        processes.add( process );
    }

    public Process getProcess(int index) {
        return processes.get( index );
    }
}