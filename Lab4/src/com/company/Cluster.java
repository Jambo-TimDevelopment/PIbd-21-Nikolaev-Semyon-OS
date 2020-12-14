package com.company;

public class Cluster {
    private int indexInPhysicalMemory;
    private ClusterStatus clusterStatus;

    Cluster(int index, ClusterStatus clusterStatus){
        this.indexInPhysicalMemory = index;
        this.clusterStatus = clusterStatus;
    }

    public void setIndexInPhysicalMemory(int index) {
        this.indexInPhysicalMemory = index;
    }

    public int getIndexInPhysicalMemory() {
        return indexInPhysicalMemory;
    }

    public void setClusterStatus(ClusterStatus clusterStatus) {
        this.clusterStatus = clusterStatus;
    }

    public ClusterStatus getClusterStatus() {
        return clusterStatus;
    }
}
