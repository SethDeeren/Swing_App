package com.company.controller;

import com.company.gui.FormEvent;
import com.company.model.*;

import java.util.List;

public class Controller {

    Database db = new Database();

    public List<Person> getPeople() {
        return db.getPeople();
    }

    public void addPerson(FormEvent e) {
        String name = e.getName();
        String occupation = e.getOccupation();
        int ageCatId = e.getAgeCategory();
        String empCat = e.getEmpCat();
        boolean isUs = e.isUsCitizen();
        String taxId = e.getTaxId();
        String gender = e.getGender();

        AgeCategory ageCategory;

        switch(ageCatId){
            case 0:
                ageCategory = AgeCategory.child;
                break;
            case 1:
                ageCategory = AgeCategory.adult;
                break;
            default:
                ageCategory = AgeCategory.senoir;
                break;
        }

        EmploymentCategory empCategory;

        switch(empCat){
            case "employed":
                empCategory = EmploymentCategory.employed;
                break;
            case "self-employed":
                empCategory = EmploymentCategory.selfEmployed;
                break;
            case "unemployed":
                empCategory = EmploymentCategory.unemployed;
                break;
            default:
                empCategory = EmploymentCategory.other;
        }
        System.out.println(empCategory);

        Gender genderCat = gender.equals("male") ? Gender.male : Gender.female;


        Person person = new Person(name, occupation,ageCategory,empCategory,taxId,isUs, genderCat);
        System.out.println(person);
        db.addPerson(person);
    }
}
