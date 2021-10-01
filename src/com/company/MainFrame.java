package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;


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


        setSize(800,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
