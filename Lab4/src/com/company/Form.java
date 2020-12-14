package com.company;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Form {
    private JFrame frame;
    private FileManager fileManager;
    private Disk disk;
    private Panel panel;
    private DefaultMutableTreeNode treeFile;
    private JTree treeFileSystem;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Form window = new Form();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Form() {
        initialize();
    }

    public void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1200, 800);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setLayout(null);


        JLabel lblName = new JLabel("Имя:");
        lblName.setBounds(1000, 250, 100, 30);
        frame.getContentPane().add(lblName);

        JTextField textFieldName = new JTextField("new");
        textFieldName.setBounds(1000, 290, 150, 30);
        textFieldName.setEnabled(true);
        frame.getContentPane().add(textFieldName);

        JLabel lblSizeFile = new JLabel("Размер:");
        lblSizeFile.setBounds(1000, 330, 100, 30);
        frame.getContentPane().add(lblSizeFile);

        JTextField textFieldFile = new JTextField("1");
        textFieldFile.setEnabled(true);
        textFieldFile.setBounds(1000, 370, 150, 30);
        frame.getContentPane().add(textFieldFile);

        JButton btnCreateFile = new JButton("Создать файл");
        btnCreateFile.setBounds(1000, 50, 150, 30);
        btnCreateFile.setEnabled(true);
        btnCreateFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                fileManager.createFile(textFieldName.getText(),
                        Integer.parseInt(textFieldFile.getText()));
                startChangeFileSystemTree(fileManager.getRootCatalog().getChild());
                frame.repaint();
            }
        });
        frame.getContentPane().add(btnCreateFile);

        JButton btnCreateFolder = new JButton("Создать папку");
        btnCreateFolder.setBounds(1000, 90, 150, 30);
        btnCreateFolder.setEnabled(true);
        btnCreateFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.createCatalog( textFieldName.getText() );
                startChangeFileSystemTree( fileManager.getRootCatalog().getChild() );
                frame.repaint();
            }
        });
        frame.getContentPane().add(btnCreateFolder);

        JButton btnCopy = new JButton("Скопировать");
        btnCopy.setBounds(1000, 130, 150, 30);
        btnCopy.setEnabled(true);
        btnCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fileManager.copyFile(fileManager.getCurrentFile()) == null){
                    System.out.println("Выбранный файл = null");
                }
                fileManager.setFileInClipBoard(fileManager.copyFile(fileManager.getCurrentFile()));
                startChangeFileSystemTree( fileManager.getRootCatalog().getChild() );
                frame.repaint();
            }
        });
        frame.getContentPane().add(btnCopy);

        JButton btnPaste = new JButton("Вставить");
        btnPaste.setBounds(1000, 170, 150, 30);
        btnPaste.setEnabled(true);
        btnPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fileManager.getCurrentFile().getClass() == Catalog.class){
                    fileManager.getFileInClipBoard().setParent((Catalog) fileManager.getCurrentFile());
                }else {
                    System.out.println("Нет возможности выполнить вставку, выберете каталог!");
                    return;
                }
                fileManager.addFile(fileManager.getFileInClipBoard());
                startChangeFileSystemTree( fileManager.getRootCatalog().getChild() );
                frame.repaint();
            }
        });
        frame.getContentPane().add(btnPaste);

        JButton btnDelete = new JButton("Удалить");
        btnDelete.setBounds(1000, 210, 150, 30);
        btnDelete.setEnabled(true);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.removeFile(fileManager.getCurrentFile());
                startChangeFileSystemTree( fileManager.getRootCatalog().getChild() );
                frame.repaint();
            }
        });
        frame.getContentPane().add(btnDelete);

        JLabel labelSizeDisc = new JLabel("Размер диска");
        labelSizeDisc.setBounds(1000, 520, 100, 30);
        frame.getContentPane().add(labelSizeDisc);

        JTextField textFieldSizeDisc = new JTextField();
        textFieldSizeDisc.setBounds(1000, 540, 100, 30);
        frame.getContentPane().add(textFieldSizeDisc);

        JLabel labelSizeSector = new JLabel("Размер сектора");
        labelSizeSector.setBounds(1000, 570, 100, 30);
        frame.getContentPane().add(labelSizeSector);

        JTextField textFieldSizeSector = new JTextField();
        textFieldSizeSector.setBounds(1000, 590, 100, 30);
        frame.getContentPane().add(textFieldSizeSector);

        JLabel lblZeroCellMeshMemory = new JLabel("");
        lblZeroCellMeshMemory.setBounds(300, 480, 300, 40);
        frame.getContentPane().add(lblZeroCellMeshMemory);

        JLabel lblFillCellMeshMemory = new JLabel("");
        lblFillCellMeshMemory.setBounds(300, 540, 300, 40);
        frame.getContentPane().add(lblFillCellMeshMemory);

        JLabel lblCurrentCellMeshMemory = new JLabel("");
        lblCurrentCellMeshMemory.setBounds(300, 590, 300, 40);
        frame.getContentPane().add(lblCurrentCellMeshMemory);

        JButton btnSpecifyMemorySize = new JButton("Задать размер памяти");
        btnSpecifyMemorySize.setBounds(1000, 630, 180, 30);
        btnSpecifyMemorySize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                disk = new Disk(Integer.parseInt(textFieldSizeDisc.getText()),
                        Integer.parseInt(textFieldSizeSector.getText()));
                fileManager = new FileManager(disk);
                panel = new Panel(disk);
                panel.setBackground(Color.lightGray);
                panel.setBounds(210, 10, 700, 550);
                frame.getContentPane().add(panel);
                startChangeFileSystemTree(fileManager.getRootCatalog().getChild());
                panel.repaint();
            }
        });
        frame.repaint();
        frame.getContentPane().add(btnSpecifyMemorySize);
    }

    private void startChangeFileSystemTree(LinkedList<File> child) {
        treeFile = new DefaultMutableTreeNode( fileManager.getRootCatalog() );
        changeFileTree( treeFile, child );
        if (!Objects.isNull( treeFileSystem )) {
            frame.getContentPane().remove( treeFileSystem );
        }
        treeFileSystem = new JTree( treeFile );
        treeFileSystem.setEnabled( true );
        treeFileSystem.setShowsRootHandles( true );
        treeFileSystem.setBounds( 0, 0, 210, 400 );
        treeFileSystem.addTreeSelectionListener( new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                fileManager.resetSelectedMarker();
                fileManager.setCurrentFile(
                        (File) ( (DefaultMutableTreeNode)Objects.requireNonNull(
                                treeFileSystem.getSelectionPath().getLastPathComponent() ))
                                .getUserObject());
                frame.repaint();
            }
        } );
        frame.getContentPane().setLayout( null );
        frame.getContentPane().add( treeFileSystem );
        treeFileSystem.updateUI();
        treeFileSystem.setScrollsOnExpand( true );
    }

    private void changeFileTree(DefaultMutableTreeNode treeFile, LinkedList<File> child) {
        for (File file : child) {
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode( file );
            treeFile.add( newNode );
            if (file.getClass() == Catalog.class) {
                changeFileTree( newNode, ((Catalog)file).getChild() );
            }
        }
    }
}
