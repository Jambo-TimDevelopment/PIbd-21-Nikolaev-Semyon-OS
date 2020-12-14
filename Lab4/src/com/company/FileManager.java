package com.company;

import java.util.LinkedList;

public class FileManager {
    private File currentFile;
    private Catalog rootCatalog;
    private File fileInClipBoard;
    private Disk disk;

    FileManager(Disk disk) {
        System.out.println("Загружен файловый менеджер");
        this.disk = disk;
        rootCatalog = new Catalog("root", null, 1, new LinkedList<>());
        rootCatalog.getClusterContainer().add(new Cluster(0, ClusterStatus.IS_LOAD));
        rootCatalog.setRootCatalog(true);
        addFile(rootCatalog);
        setCurrentFile(rootCatalog);
        fileInClipBoard = new File("fileInClipBoard", null, -1, new LinkedList<>());
    }

    public void resetSelectedMarker() {
        System.out.println("Снимаем маркер с выбранных кластеров");
        for (Cluster cluster : disk.getPhysicalMemory()) {
            if (cluster.getClusterStatus() == ClusterStatus.IS_SELECTED) {
                cluster.setClusterStatus(ClusterStatus.IS_LOAD);
            }
        }
    }

    public void setCurrentFile(File file) {
        System.out.println("Обновлен выбранный файл");
        for (Cluster cluster : file.getClusterContainer()) {
            cluster.setClusterStatus(ClusterStatus.IS_SELECTED);
        }
        if (file.getClass() == Catalog.class) {
            for (File childFile : ((Catalog) file).getChild()) {
                setCurrentFile((childFile));
            }
        }
        currentFile = file;
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public File createFile(String fileName, int size) {
        if (currentFile.getClass() == Catalog.class) {
            LinkedList<Cluster> clusterContainer = new LinkedList<>();
            int clusterSaveOutOfDisk = -1;
            for (int i = 0; i < size; i++) {
                clusterContainer.add(new Cluster(clusterSaveOutOfDisk, ClusterStatus.IS_EMPTY));
            }
            File file = new File(fileName, ((Catalog) currentFile), size, clusterContainer);
            System.out.println("Файл --" + fileName + "-- был инициализирован");
            if (addFile(file)) {
                ((Catalog) currentFile).addChild(file);
            }
            return file;
        } else {
            System.out.println("Текущий файл не является каталогом");
            LinkedList<Cluster> clusterContainer = new LinkedList<>();
            int clusterSaveOutOfDisk = -1;
            for (int i = 0; i < size; i++) {
                clusterContainer.add(new Cluster(clusterSaveOutOfDisk, ClusterStatus.IS_EMPTY));
            }
            File file = new File(fileName, null, size, clusterContainer);
            System.out.println("Файл --" + fileName + "-- был инициализирован");
            return file;
        }
    }

    public Catalog createCatalog(String nameCatalog) {
        if(currentFile.getClass() == Catalog.class) {
            int size = 1;
            LinkedList<Cluster> clusterContainer = new LinkedList<>();
            int clusterSaveOutOfDisk = -1;
            for (int i = 0; i < size; i++) {
                clusterContainer.add(new Cluster(clusterSaveOutOfDisk, ClusterStatus.IS_EMPTY));
            }
            Catalog catalog = new Catalog(nameCatalog, ((Catalog) currentFile), size, clusterContainer);
            System.out.println("Каталог --" + nameCatalog + "-- был инициализирован");
            if (addFile(catalog)) {
                ((Catalog) currentFile).addChild(catalog);
                return catalog;
            } else {
                System.out.println("Добавление файла невозможно, недостаточно места на диске");
                return null;
            }
        } else {
            int size = 1;
            LinkedList<Cluster> clusterContainer = new LinkedList<>();
            Catalog catalog = new Catalog(nameCatalog, ((Catalog) currentFile), size, clusterContainer);
            System.out.println("Каталог --" + nameCatalog + "-- был инициализирован");
            return catalog;
        }
    }

    public boolean addFile(File file) {
        if (file == null) {
            System.out.println("Создать файл не удалось file = null!!");
            return false;
        }
        if (!disk.checkEmptyMemoryForFile(file)) {
            System.out.println("Недостаточно метсада на диске для данного файла!");
            return false;
        }
        if(file.getParent() == null){
            file.setParent((Catalog) currentFile);
            System.out.println("Добавлен родитель из текущего файла");
        }
        for (Cluster cluster : file.getClusterContainer()) {
            disk.loadClusterInMemory(cluster);
        }
        if (file.getClass() == Catalog.class) {
            System.out.println("Добавление дочерних элементов каталога --" + file.getName());
            for (File child : ((Catalog) file).getChild()) {
                addFile(child);
            }
        }
        System.out.println("--->Файл --" + file.name + "-- успешно загружен на диск!");
        return true;
    }

    public void removeFile(File file) {
        if (file.getClass() == Catalog.class && ((Catalog) file).isRootCatalog()) {
            System.out.println("Корневой каталог нельзя удалять!");
            return;
        }
        for (Cluster cluster : file.getClusterContainer()) {
            disk.removeClusterFromFile(cluster);
        }
        file.getParent().getChild().remove(currentFile);
        if (file.getClass() == Catalog.class) {
            for (File childFile : ((Catalog) file).getChild()) {
                removeFile(childFile);
            }

        }

        System.out.println("Удаление файла прошло успешно");
    }

    public File copyFile(File file) {
        System.out.println("Копировние файла --" + file.getName() + "--...");
        File newFile = null;
        if (file.getName() == "root") {
            System.out.println("Корневой каталог копированию не подлежит!!!");
            return null;
        }
        if (file.getClass() == Catalog.class) {
            newFile = createCatalog(file.getName());
            for (File child : ((Catalog) file).getChild()) {
                ((Catalog) newFile).addChild(child);
            }
        } else {
            newFile = createFile(file.getName(), file.getSize());
            System.out.println("Создана копия файла --" + file.getName() + "--" );
        }
        System.out.println("Копировние файла --" + file.getName() + "-- завершено");
        return newFile;
    }

    public void reparentFile(Catalog newParent, File file) {
        LinkedList<File> oldParentChild = file.getParent().getChild();
        newParent.addChild(file);
        oldParentChild.remove(file);
    }

    public Catalog getRootCatalog() {
        return rootCatalog;
    }

    public File getFileInClipBoard() {
        return fileInClipBoard;
    }

    public void setFileInClipBoard(File fileInClipBoard) {
        this.fileInClipBoard = fileInClipBoard;
    }
}
