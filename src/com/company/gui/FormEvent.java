package com.company.gui;

import java.util.EventObject;

public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private int ageCategory;
    private String empCat;
    private String taxId;
    private boolean usCitizen;
    private String gender;


    // source can be button, text field, or any swing component

    public FormEvent(Object source) {
        super(source);
    }


    public FormEvent(
            Object source,
            String name,
            String occupation,
            int ageCategory,
            String empCat,
            String taxId,
            boolean usCitizen,
            String gender
    )
    {


        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.empCat =  empCat;
        this.taxId = taxId;
        this.usCitizen = usCitizen;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public boolean isUsCitizen() {
        return usCitizen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }

    public String getEmpCat() {
        return empCat;
    }

    public String getTaxId() {
        return taxId;
    }

    public String getGender() {
        return gender;
    }

}
