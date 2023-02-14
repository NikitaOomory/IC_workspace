package com.example.MPM.ser_table_rd.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JournalSL {

    //параметры класса
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numberSL,//номер дела
            shortNumber;//короткий номер дела
    private String operName,//ФИО оперативного сотрудника
            rozName,//ФИО розыскиваемого
            dataSL,//дата регистрации дела
            dataIzm,//дата изменения дела
            readyDocks,//статус готовности документов
            creator,//имя учётной записи зарегистрировавшего номер дела
            phoneNumberCreator;//номер оперативного сотрудника для связи
    private Integer numberCat,//номер категории дела
            numberGeo;//номер района
    private String editorIP;//ip адрес пользователя который последним осуществлял взаимодействие (создание, редактирование)
    private boolean checkSearchCards;//отметка о сдаче розыскной карточки


    //Конструкторы
    //-------------------------------------------------------------------------------------------------------------------------------
    public JournalSL(String numberSL, String operName, String rozName, Integer numberCat, Integer numberGeo, String dataSl, String shortNumber, String phoneNumberCreator) {
        this.numberSL = numberSL;
        this.operName = operName;
        this.rozName = rozName;
        this.dataSL = dataSl;
        this.numberCat = numberCat;
        this.numberGeo = numberGeo;
        this.shortNumber = shortNumber;
        this.phoneNumberCreator = phoneNumberCreator;
        this.dataIzm = "не изменялось";
        this.readyDocks = "X";
        this.checkSearchCards = true;
    }

    public JournalSL() {
    }

    //Getters and setters
    //-------------------------------------------------------------------------------------------------------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumberSL() {
        return numberSL;
    }

    public void setNumberSL(String numberSL) {
        this.numberSL = numberSL;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getRozName() {
        return rozName;
    }

    public void setRozName(String rozName) {
        this.rozName = rozName;
    }

    public String getDataSL() {
        return dataSL;
    }

    public void setDataSL(String dataSL) {
        this.dataSL = dataSL;
    }

    public Integer getNumberCat() {
        return numberCat;
    }

    public void setNumberCat(Integer numberCat) {
        this.numberCat = numberCat;
    }

    public Integer getNumberGeo() {
        return numberGeo;
    }

    public void setNumberGeo(Integer numberGeo) {
        this.numberGeo = numberGeo;
    }

    public String getDataIzm() {
        return dataIzm;
    }

    public void setDataIzm(String dataIzm) {
        this.dataIzm = dataIzm;
    }

    public String getReadyDocks() {
        return readyDocks;
    }

    public void setReadyDocks(String readyDocks) {
        this.readyDocks = readyDocks;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public void setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPhoneNumberCreator() {
        return phoneNumberCreator;
    }

    public void setPhoneNumberCreator(String phoneNumberCreator) {
        this.phoneNumberCreator = phoneNumberCreator;
    }

    public boolean isCheckSearchCards() {
        return checkSearchCards;
    }

    public void setCheckSearchCards(boolean checkSearchCards) {
        this.checkSearchCards = checkSearchCards;
    }
}