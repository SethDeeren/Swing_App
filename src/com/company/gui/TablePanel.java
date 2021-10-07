package com.company.gui;

import com.company.model.Person;

import javax.swing.*;
import java.util.List;
import java.awt.BorderLayout;

public class TablePanel extends JPanel{

    private JTable table;
    private PersonTableModel tableModel;

    public TablePanel(){

        tableModel = new PersonTableModel();
        table = new JTable(tableModel);

        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Person> db){
        tableModel.setData(db);
    }

    public void refresh(){
        tableModel.fireTableDataChanged();
    }
}
