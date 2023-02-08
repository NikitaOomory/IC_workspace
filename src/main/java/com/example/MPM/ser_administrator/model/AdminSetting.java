package com.example.MPM.ser_administrator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdminSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String settingName; //название настройки
    Boolean settingStatus; //поле для функциональной части настройки
    Integer settingInteger; //поле для функциональной части настройки
    String settingString; //поле для функциональной части настройки
    String settingsCategory; //категория настройки (к какому сервису относится настройка)
    String aboutSettings; //описание настройки


    public AdminSetting(String settingName, Boolean settingStatus, Integer settingInteger, String settingString, String settingsCategory, String aboutSettings) {
        this.settingName = settingName;
        this.settingStatus = settingStatus;
        this.settingInteger = settingInteger;
        this.settingString = settingString;
        this.settingsCategory = settingsCategory;
        this.aboutSettings = aboutSettings;
    }

    public AdminSetting() {
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public Boolean getSettingStatus() {
        return settingStatus;
    }

    public void setSettingStatus(Boolean settingStatus) {
        this.settingStatus = settingStatus;
    }

    public Integer getSettingInteger() {
        return settingInteger;
    }

    public void setSettingInteger(Integer settingInteger) {
        this.settingInteger = settingInteger;
    }

    public String getSettingString() {
        return settingString;
    }

    public void setSettingString(String settingString) {
        this.settingString = settingString;
    }

    public String getSettingsCategory() {
        return settingsCategory;
    }

    public void setSettingsCategory(String settingsCategory) {
        this.settingsCategory = settingsCategory;
    }

    public String getAboutSettings() {
        return aboutSettings;
    }

    public void setAboutSettings(String aboutSettings) {
        this.aboutSettings = aboutSettings;
    }
}
