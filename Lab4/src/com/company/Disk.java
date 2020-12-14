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

    public int checkEmptyMemoryForFile(File file){
        int startIndex = 1;
        int sizeFile = file.getSize();
        int countEmptyClusters = 0;
        for(int i = 0; i < countCluster; i++){
            if(physicalMemory[i].getClusterStatus() == ClusterStatus.IS_EMPTY){
                if(countEmptyClusters == 0){
                    startIndex = i;
                }
                countEmptyClusters++;
            }else{
                countEmptyClusters = 0;
            }
        }
        return startIndex;
    }

    public boolean loadFileInMemory(File file, int startIndex) {
        int countLoadClusters = 0;
        for(int i = startIndex; i < countCluster ||
                countLoadClusters < file.getClusterContainer().size(); i++){
            if(physicalMemory[i].getClusterStatus() == ClusterStatus.IS_EMPTY){
                physicalMemory[i].setClusterStatus(ClusterStatus.IS_LOAD);
                countLoadClusters++;
            }else{
                countLoadClusters = 0;
            }
        }
        return true;
    }

    public void removeClusterFromFile(Cluster cluster){
        int indexCurCluster = cluster.getIndexInPhysicalMemory();
        physicalMemory[indexCurCluster] = new Cluster(indexCurCluster, ClusterStatus.IS_EMPTY);
    }

    public int getCountCluster() {
        return countCluster;
    }
}
