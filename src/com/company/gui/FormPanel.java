package com.company.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private FormListener formListener;
    private JList ageList;
    private JComboBox empCombo;
    private JCheckBox citzenCheck;
    private JTextField taxField;
    private JLabel taxLabel;

    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;




    public FormPanel(){
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        ageList = new JList();
        empCombo = new JComboBox();
        citzenCheck = new JCheckBox();
        taxField = new JTextField(10);
        taxLabel = new JLabel("Tax ID: ");
        okBtn = new JButton("OK");

        // Setup up mneomics
        okBtn.setMnemonic(KeyEvent.VK_O);

        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");

        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        genderGroup = new ButtonGroup();



        maleRadio.setSelected(true);

        // setup gender radios
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Setup up tax Id
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        citzenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTicked = citzenCheck.isSelected();
                taxLabel.setEnabled(isTicked);
                taxField.setEnabled(isTicked);
            }
        });



        // Set up list box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0,"Under 18"));
        ageModel.addElement(new AgeCategory(1,"18 to 65"));
        ageModel.addElement(new AgeCategory(2,"65 or over"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(new Dimension(110, 66));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        // Set up combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("unemployed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);



        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCat = (AgeCategory)ageList.getSelectedValue();
                String empCat = (String) empCombo.getSelectedItem();
                String taxId = taxField.getText();
                boolean usCitizen = citzenCheck.isSelected();

                String gender= genderGroup.getSelection().getActionCommand();



                System.out.println(empCat);


                System.out.println(ageCat.getId());

                FormEvent ev = new FormEvent(this, name, occupation,ageCat.getId(), empCat, taxId, usCitizen, gender);

                if(formListener != null){
                    formListener.formEventOccurred(ev);
                }
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();

    }

    public void layoutComponents(){
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 1;
        gc.weighty = 1;

        /////////////////////// First Row ////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;

        // column 1
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(nameLabel, gc);


        // column 2
        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0,0,0,0);;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);


        /////////////////////// Second Row ////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.1;


        // column 1
        gc.gridy = 1;
        gc.gridx = 0;
        gc.insets = new Insets(0,0,0,5);
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(occupationLabel, gc);

        // column 2
        gc.gridy = 1;
        gc.gridx = 1;
        gc.insets = new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(occupationField, gc);

        /////////////////////// Third Row ////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.2;

        // column 1
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Age: "), gc);


        // column 2
        gc.gridy = 2;
        gc.gridx = 1;
        new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(ageList, gc);

        /////////////////////// Fourth Row ////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.2;

        // column 1
        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Employment: "), gc);

        // column 2
        gc.gridy = 3;
        gc.gridx = 1;
        new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(empCombo, gc);

        /////////////////////// Fith Row ////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.2;

        // column 1
        gc.gridx = 0;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("US Citizen: "), gc);

        // column 2
        gc.gridy = 4;
        gc.gridx = 1;
        new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(citzenCheck, gc);

        /////////////////////// sixth Row ////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.2;

        // column 1
        gc.gridx = 0;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(taxLabel, gc);

        // column 2
        gc.gridy = 5;
        gc.gridx = 1;
        new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(taxField, gc);

        /////////////////////// seventh Row ////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.05;

        // column 1
        gc.gridx = 0;
        gc.gridy = 6;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(new JLabel("Gender: "), gc);

        // column 2
        gc.gridy = 6;
        gc.gridx = 1;
        new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(maleRadio, gc);

        /////////////////////// eighth Row ////////////////////////

        gc.weightx = 1;
        gc.weighty = 0.2;


        // column 2
        gc.gridy = 7;
        gc.gridx = 1;
        new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(femaleRadio, gc);

        /////////////////////// nineth Row ////////////////////////

        gc.weightx = 1;
        gc.weighty = 2.0;

        // column 2
        gc.gridy = 8;
        gc.gridx = 1;
        new Insets(0,0,0,0);
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn, gc);
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }
}

class AgeCategory {
    private int id;
    private String text;

    public AgeCategory(int id, String text){
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }



    @Override
    public String toString() {
        return text;
    }
}
