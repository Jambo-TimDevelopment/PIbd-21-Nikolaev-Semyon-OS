package com.company;

import java.util.ArrayList;
import java.util.LinkedList;

public class Disk {
    private int sizeMemory; //в байтах
    private int sizeCluster;//в байтах
    private int countCluster;
    private Cluster[] physicalMemory;

    Disk(int sizeMemory, int sizeCluster){
        this.sizeMemory = sizeMemory;
        this.sizeCluster = sizeCluster;
        countCluster = sizeMemory / sizeCluster;
        physicalMemory = new Cluster[countCluster];
        for(int i = 0; i < countCluster; i++){
            physicalMemory[i] = new Cluster(i, ClusterStatus.IS_EMPTY);
        }
    }

    public Cluster[] getPhysicalMemory() {
        return physicalMemory;
    }

    public boolean checkEmptyMemoryForFile(File file){
        int sizeFile = file.getSize();
        int countEmptyClusters = 0;
        for(int i = 0; i < countCluster; i++){
            if(physicalMemory[i].getClusterStatus() == ClusterStatus.IS_EMPTY){
                countEmptyClusters++;
            }
        }
        return sizeFile <= countEmptyClusters;
    }

    public void loadClusterInMemory(Cluster cluster) {
        for(int i = 0; i < countCluster; i++){
            if(physicalMemory[i].getClusterStatus() == ClusterStatus.IS_EMPTY){
                cluster.setIndexInPhysicalMemory(i);
                cluster.setClusterStatus(ClusterStatus.IS_LOAD);
                physicalMemory[i] = cluster;
                return;
            }
        }
    }

    public void removeClusterFromFile(Cluster cluster){
        int indexCurCluster = cluster.getIndexInPhysicalMemory();
        physicalMemory[indexCurCluster] = new Cluster(indexCurCluster, ClusterStatus.IS_EMPTY);
    }

    public int getCountCluster() {
        return countCluster;
    }
}
