package com.company;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private Cluster[] physicalMemory;
    private int sizeMemory;

    Panel(Disk disk){
        super();
        physicalMemory = disk.getPhysicalMemory();
        sizeMemory = disk.getCountCluster();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for(int i = 0; i < sizeMemory; i++){
            if(physicalMemory[i].getClusterStatus() == ClusterStatus.IS_EMPTY){
                g.setColor(Color.gray);
            }else if(physicalMemory[i].getClusterStatus() == ClusterStatus.IS_LOAD){
                g.setColor(Color.green);
            }else if(physicalMemory[i].getClusterStatus() == ClusterStatus.IS_SELECTED){
                g.setColor(Color.red);
            }
            g.fillRect( i % 87 * 8, i / 87 * 8,6,6);
            g.setColor(Color.red);
        }

    }

}
