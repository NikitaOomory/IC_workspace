package com.example.MPM.ser_table_rd.model;

import org.springframework.stereotype.Component;

@Component
public class NumberJournalSL {
    private String year;//год
    private String typeJournal;//тип РД
    private String category;//категория РД
    private final String AREA = "1158";//код Псковской области
    private String shortNumber;//порядковый номер дела

    public NumberJournalSL(){

    }

    public String getFinalNumber(){
        return year + typeJournal + category + AREA + shortNumber;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTypeJournal() {
        return typeJournal;
    }

    public void setTypeJournal(String typeJournal) {
        this.typeJournal = typeJournal;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAREA(){
        return AREA;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public void setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
    }
}