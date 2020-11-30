package com.company;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class FileManager {

    private FileManager rootFile = new FileManager("root", null, true, 1);
    private FileManager selected = rootFile;
    private FileManager fileForCopy;
    private Memory physMemory;
    private String name;
    private FileManager parent;
    private boolean isFolder;
    public ArrayList child;
    private int indexFirstCell;
    private int size = -1;

    private FileManager(Memory PhisMemory) {
        this.physMemory = PhisMemory;
        rootFile.setSize(1);
        PhisMemory.searchPlace(rootFile);
    }

    public FileManager(String name, FileManager parent, boolean isFolder, int size) {
        this.name = name;
        this.parent = parent;
        this.isFolder = isFolder;
        this.size = size;

        if (isFolder) {
            child = new ArrayList();
        }
    }
    public FileManager() { }

    public FileManager clone() {
        FileManager newFile = new FileManager();
        newFile.setSize(size);
        setName(name);
        setFolder(isFolder);
        if (isFolder) {
            ArrayList child = new ArrayList();
            for (Object managerFile : this.child) {
                boolean add = child.add(managerFile);
            }
            newFile.setChild(child);
        }
        return newFile;
    }

    public FileManager getRootFile() {
        return rootFile;
    }

    public FileManager getSelected() {
        return selected;
    }

    public void setSelectedFile(DefaultMutableTreeNode node) {
        this.selected = (FileManager) node.getUserObject();
    }

    public FileManager copy() {
        return fileForCopy = selected;
    }

    public void copyFilesCatalog(FileManager newFile) {
        for (Object file : newFile.getChild()) {
            physMemory.searchPlace((FileManager)file);
            if (((FileManager) file).isFolder()) {
                copyFilesCatalog((FileManager)file);
            }
        }
    }

    public boolean paste() {
        if (selected.isFolder()) {
            try {
                FileManager newFile = fileForCopy.clone();
                newFile.setParent(selected);
                selected.getChild().add(newFile);
                physMemory.searchPlace(newFile);
                if (newFile.isFolder()) {
                    copyFilesCatalog(newFile);
                }
            } catch (Exception eх) {
                eх.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean createFile(String nameFile, boolean folder, int size) {
        if (selected.isFolder()) {
            FileManager newFile = new FileManager(nameFile, selected, folder, size);
            if (folder) {
                newFile.setSize(1);
            } else {
                newFile.setSize(size);
            }
            physMemory.searchPlace(newFile);
            selected.getChild().add(newFile);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete() {
        if (selected == rootFile) {
            return false;
        } else {
            selected.getParent().getChild().remove(selected);
            if (selected.isFolder()) {
                deleteFolder(selected.getChild());
            }
            physMemory.clearFile(selected);
        }
        return true;
    }

    public void deleteFolder(ArrayList<FileManager> files) {
        for (FileManager file : files) {
            if (file.isFolder()) {
                deleteFolder(file.getChild());
            }
            physMemory.clearFile(file);
        }
    }

    public void move() {
        for (int i = 0; i < selected.getParent().child.size(); i++) {
            if (selected.getParent().child.get(i) == selected) {
                selected.getParent().child.remove(i);
            }
        }
    }

    public String toString() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileManager getParent() {
        return parent;
    }

    public void setParent(FileManager parent) {
        this.parent = parent;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFolder() {
        return isFolder;
    }

    public void setFolder(boolean folder) {
        this.isFolder = folder;
    }

    public ArrayList getChild() {
        return child;
    }

    public void setChild(ArrayList child) {
        this.child = child;
    }

    public int getIndexFirstCell() {
        return indexFirstCell;
    }

    public void setIndexFirstCell(int indexFirstCell) {
        this.indexFirstCell = indexFirstCell;
    }
} 