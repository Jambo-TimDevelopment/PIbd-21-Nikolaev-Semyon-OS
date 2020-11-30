package com.company;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MemoryPanel {
    private FileManager fileManager;
    private Memory physMemory;
    private FormMemory formMemory;

    private JFrame frame = new JFrame();
    private JButton buttonCreateFile = new JButton("Создать файл");
    private JButton buttonCreateFolder = new JButton("Создать папку");
    private JButton buttonCopy = new JButton("Копировать");
    private JButton buttonPaste = new JButton("Вставить");
    private JButton buttonDelete = new JButton("Удалить");
    private JButton buttonMove = new JButton("Переместить");
    private JButton buttonSetMemory = new JButton("Установить размер памяти");
    private JLabel labelName = new JLabel("Имя");
    private JLabel labelSizeFile = new JLabel("Размер файла");
    private JLabel labelSizeDisK = new JLabel("Размер диска");
    private JLabel labelSizeSector = new JLabel("Размер кластера");
    private JTextField textFieldName = new JTextField();
    private JTextField textFieldFile = new JTextField();
    private JTextField textFieldDisk = new JTextField();
    private JTextField textFieldSector = new JTextField();
    private DefaultMutableTreeNode treeFile;
    private JTree tree;

    public MemoryPanel() {
        frame.setBounds(0, 0, 1400, 800);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.getContentPane().setLayout(null);
        buttonCreateFile.setBounds(1000, 100, 150, 20);
        buttonCreateFolder.setBounds(1000, 130, 150, 20);
        buttonCopy.setBounds(1000, 160, 150, 20);
        buttonPaste.setBounds(1000, 190, 150, 20);
        buttonDelete.setBounds(1000, 220, 150, 20);
        buttonMove.setBounds(1000, 450, 150, 20);
        buttonSetMemory.setBounds(1000, 520, 150, 20);
        labelName.setBounds(1060, 300, 100, 20);
        textFieldName.setBounds(1000, 320, 150, 20);
        labelSizeFile.setBounds(1050, 360, 100, 20);
        textFieldFile.setBounds(1000, 380, 150, 20);
        labelSizeDisK.setBounds(400, 520, 70, 20);
        textFieldDisk.setBounds(480, 520, 100, 20);
        labelSizeSector.setBounds(600, 520, 80, 20);
        textFieldSector.setBounds(680, 520, 100, 20);
        textFieldDisk.setText("1000");
        textFieldSector.setText("2");
        textFieldFile.setText("1");
        frame.getContentPane().add(buttonCreateFile);
        frame.getContentPane().add(buttonCreateFolder);
        frame.getContentPane().add(buttonCopy);
        frame.getContentPane().add(buttonPaste);
        frame.getContentPane().add(buttonDelete);
        frame.getContentPane().add(buttonMove);
        frame.getContentPane().add(labelName);
        frame.getContentPane().add(textFieldName);
        frame.getContentPane().add(labelSizeDisK);
        frame.getContentPane().add(textFieldDisk);
        frame.getContentPane().add(labelSizeSector);
        frame.getContentPane().add(textFieldSector);
        frame.getContentPane().add(labelSizeFile);
        frame.getContentPane().add(textFieldFile);
        frame.getContentPane().add(buttonSetMemory);
        frame.repaint();
    }

    public void start() {
        buttonCreateFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.createFile(textFieldName.getText(), false, Integer.parseInt(textFieldFile.getText()));
                startChangeTree(fileManager.getRootFile().getChild());
                frame.repaint();
            }
        });

        buttonCreateFolder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.createFile(textFieldName.getText(), true, 1);
                startChangeTree(fileManager.getRootFile().getChild());
                frame.repaint();
            }
        });

        buttonCopy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.copy();
            }
        });

        buttonPaste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.paste();
                startChangeTree(fileManager.getRootFile().getChild());
                frame.repaint();
            }
        });

        buttonDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.delete();
                startChangeTree(fileManager.getRootFile().getChild());
                frame.repaint();
            }
        });

        buttonMove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileManager.move();
                startChangeTree(fileManager.getRootFile().getChild());
                frame.repaint();
            }
        });

        buttonSetMemory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldDisk.setEditable(false);
                textFieldSector.setEditable(false);
                buttonSetMemory.setEnabled(false);
                buttonCreateFile.setEnabled(true);
                buttonDelete.setEnabled(true);
                buttonMove.setEnabled(true);
                buttonCopy.setEnabled(true);
                buttonCreateFolder.setEnabled(true);
                buttonPaste.setEnabled(true);
                labelName.setEnabled(true);
                physMemory = new Memory(Integer.parseInt(textFieldDisk.getText()), Integer.parseInt(textFieldSector.getText()));
                formMemory = new FormMemory(physMemory);
                formMemory.setBounds(220, 10, 900, 650);
                frame.getContentPane().add(formMemory);
                fileManager = new FileManager();
                physMemory.chooseFile(fileManager.getRootFile());
                frame.repaint();
                startChangeTree(fileManager.getRootFile().getChild());
            }
        });
    }

    private void startChangeTree(ArrayList<FileManager> child) {
        treeFile = new DefaultMutableTreeNode(fileManager.getRootFile());
        changeTree(treeFile, child);
        if (!Objects.isNull(tree)) {
            frame.getContentPane().remove(tree);
        }
        tree = new JTree(treeFile);
        tree.setEnabled(true);
        tree.setShowsRootHandles(true);
        tree.setBounds(0, 0, 200, 600);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                fileManager.setSelectedFile((DefaultMutableTreeNode) Objects.requireNonNull(tree.getSelectionPath()).getLastPathComponent());
                physMemory.chooseFile(fileManager.getSelected());
                frame.repaint();
            }
        });
        frame.getContentPane().setLayout(null);
        frame.getContentPane().add(tree);
        tree.updateUI();
        tree.setScrollsOnExpand(true);
    }

    private void changeTree(DefaultMutableTreeNode treeFile, ArrayList<FileManager> child) {
        for (FileManager file : child) {
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file);
            treeFile.add(newNode);
            if (file.isFolder()) {
                changeTree(newNode, file.getChild());
            }
        }
    }
}