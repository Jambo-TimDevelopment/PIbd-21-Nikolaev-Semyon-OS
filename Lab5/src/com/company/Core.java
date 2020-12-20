package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Core {
    ArrayList<Process> processes;
    ArrayList<Device> devices;
    Stack<Process> blockedProcesses;
    int countProcesses;
    int timeManager = 0;
    int timeWithBlock = 0;
    int timeWithNotBlock = 0;

    private void createDriver() {
        Random rnd = new Random();
        int countDevice = 10;
        devices = new ArrayList<>();
        for (int i = 0; i < countDevice; i++) {
            devices.add(new Device(i, 3 + rnd.nextInt(30)));
        }
    }

    private void createProcesses() {
        processes = new ArrayList<>();
        blockedProcesses = new Stack<>();
        Random rnd = new Random();
        countProcesses = 20;
        for (int i = 0; i < countProcesses; i++) {
            int time = 3 + rnd.nextInt(10);
            Process process = new Process(i, rnd.nextBoolean(), time);
            processes.add(process);
        }
    }

    private void executeUnBlockedProcess() {
        for (int i = 0; i < countProcesses; i++) {
            processes.get(i).start();
            //System.out.println("-- sumTime = " + sumTime);
            if (processes.get(i).getIsBlocked()) {
                devices.get(processes.get(i).getDeviceIndex()).work();
                timeWithNotBlock += devices.get(processes.get(i).getDeviceIndex()).getTimeExecute();
                timeWithNotBlock += processes.get(i).getTimeExecute();
                processes.get(i).continued();
            } else {
                timeWithNotBlock += processes.get(i).getTimeExecute();
            }
        }
    }

    private void executeBlockedProcess() {
        for (int i = 0; i < countProcesses; i++) {
            processes.get(i).start();
            //System.out.println("-- sumTime = " + sumTime);
            if (processes.get(i).getIsBlocked()) {
                blockedProcesses.add(processes.get(i));
                devices.get(blockedProcesses.peek().getDeviceIndex()).work();
                continue;
            } else {
                timeWithBlock += processes.get(i).getTimeExecute();
                timeManager += processes.get(i).getTimeExecute();
            }

            if (!blockedProcesses.isEmpty() &&
                    devices.get(blockedProcesses.peek().getDeviceIndex()).getTimeExecute() < timeManager) {
                timeManager -= devices.get(blockedProcesses.peek().getDeviceIndex()).getTimeExecute();
                //System.out.println("-- sumTime = " + sumTime);
                int time = blockedProcesses.peek().getTimeExecute();
                blockedProcesses.pop().continued();
                timeWithBlock += time;
                timeManager += time;
                //System.out.println("-- sumTime = " + sumTime);
            }
        }

        while (!blockedProcesses.isEmpty()) {
            blockedProcesses.pop().continued();
        }
    }

    public void startProgram() {
        createDriver();
        createProcesses();
        executeBlockedProcess();
        System.out.println("\n \n \n__________________________________________________\n \n \n");
        executeUnBlockedProcess();
        System.out.println("\n \n \nВремя с блокировкой " + timeWithBlock);
        System.out.println("Время без блокриовки " + timeWithNotBlock);
    }
}
