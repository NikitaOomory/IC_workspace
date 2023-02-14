package com.example.MPM.ser_table_rd.model;

import com.example.MPM.contract.AdapterCategory;
import com.example.MPM.repo.JournalSLRepo;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@Component
public class NumberJournalSL {
    private String yearCreated;
    private String typeJournal;
    private String category;
    private final String AREA = "1158";//код Псковской области
    private String shortNumber;//порядковый номер дела из 6 цифр

    private AdapterCategory adapterCategory = new AdapterCategory();

    public NumberJournalSL(){

    }

    public NumberJournalSL(String typeJournalFromPage, String category, JournalSLRepo journalSLRepo) {
        this.yearCreated = Year.now().format(DateTimeFormatter.ofPattern("uu"));
        this.typeJournal = adapterTypeJournalSLFromPageForCreateNumberSL(typeJournalFromPage);
        this.category = adapterCategory.getIntegerCategoryFromString(category)+"";
        this.shortNumber = createShortNumberSLFromLastNumberItemDB(journalSLRepo);
    }

    private String createShortNumberSLFromLastNumberItemDB(JournalSLRepo journalSLRepo){
        String result = (Math.toIntExact(journalSLRepo.count()) + 1) + "";
        switch (result.length()){
            case 1:
                result = "00000" + result;
                break;
            case 2:
                result = "0000" + result;
                break;
            case 3:
                result = "000" + result;
                break;
            case 4:
                result = "00" + result;
                break;
            case 5:
                result = "0" + result;
                break;
            default:
                result = result;
                break;
        }
        return result;
    }

    public String adapterTypeJournalSLFromPageForCreateNumberSL(String stringFromPage){
        String result;
        if(stringFromPage.equals("1 - сигнальная система")){
            result = "1";
        } else if (stringFromPage.equals("5 - из ОПД в РД")) {
            result = "5";
        } else if (stringFromPage.equals("4 - труп")) {
            result = "4";
        } else {
            result = "2";
        }
        return result;
    }

    public String getFinalNumber(){
        return yearCreated + typeJournal + category + AREA + shortNumber;
    }

    public String getYear() {
        return yearCreated;
    }

    public void setYear(String year) {
        this.yearCreated = year;
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