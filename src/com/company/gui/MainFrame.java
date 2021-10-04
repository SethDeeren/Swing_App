package com.company.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;


    public MainFrame(){
        super("Hello World");
        // the super
        //JFrame frame = new JFrame("Hello World");

        // Could just run this block on it's own but this is the approved manner
        // Incase multi threading is needed later on

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        setJMenuBar(createMenuBar());

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        // was a tightly coupled way
        //toolbar.setTextPanel(textPanel);

        // de coupled way
        toolbar.setStringListener(new StringListener(){
            @Override
            public void textEmitted(String text) {
                System.out.print(text);
            }
        });

        formPanel.setFormListener(new FormListener() {
            @Override
            public void formEventOccurred(FormEvent e){
                String name = e.getName();
                String occupation = e.getOccupation();
                int ageCat = e.getAgeCategory(); // from FormEvent is an int can be confusing way he wrote it
                String empCat = e.getEmpCat();

                textPanel.appendText(name + ":" + occupation + ": "+ ageCat + ": " + empCat +"\n");
            }
        });



        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.WEST);


        setMinimumSize(new Dimension(500, 400));
        setSize(800,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();



        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");

        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);

        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) event.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });

        // Mnemonics and Accelerators
        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);

        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

        importDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    System.out.println(fileChooser.getSelectedFile());
                };
            }
        });

        exportDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
                    System.out.println(fileChooser.getSelectedFile());
                };
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you really want to exit?",
                        "Confirm Exit",JOptionPane.OK_CANCEL_OPTION);
                if(action == JOptionPane.OK_OPTION){
                    System.exit(0);
                }

            }
        });


        return menuBar;
    }
}
