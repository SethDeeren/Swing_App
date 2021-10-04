package com.company.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Toolbar extends JPanel implements ActionListener {

    private JButton helloButton;
    private JButton goodByeButton;
    //private TextPanel textPanel;

    private StringListener textListener;

    public Toolbar(){
        setBorder(BorderFactory.createEtchedBorder());

        helloButton = new JButton("Hello");
        goodByeButton = new JButton("GoodBye");

        helloButton.addActionListener(this);
        goodByeButton.addActionListener(this);




        // left to right layout
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(helloButton);
        add(goodByeButton);

    }



    public void setStringListener(StringListener listener) {
        this.textListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton)e.getSource();
        //System.out.println("A button has been clicked");
        if(clicked == helloButton){
            if(textListener != null){
                textListener.textEmitted("Hello\n");
            }
        } else if(clicked == goodByeButton){
            if(textListener != null){
                textListener.textEmitted("Goodbye\n");
            }
        }
    }
}
