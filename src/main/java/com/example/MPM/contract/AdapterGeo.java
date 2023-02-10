package com.example.MPM.contract;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Адаптер-словарь районов Псковской области
@Component
public class AdapterGeo {

    private static ArrayList<String> geoList = new ArrayList<>();

    static {
        geoList.add("УМВД России по городу Пскову");
        geoList.add("ОМВД России по городу Великие Луки");
        geoList.add("МО МВД России «Бежаницкий»");
        geoList.add("МО МВД России «Великолукский»");
        geoList.add("ОМВД России по Гдовскому району");
        geoList.add("МО МВД России «Дедовичский»");
        geoList.add("ОП по Дновскому району");
        geoList.add("ОП по Красногородскому району");
        geoList.add("ОП по Куньинскому району");
        geoList.add("ОП по Локнянскому району");
        geoList.add("МО МВД России «Невельский»");
        geoList.add("ОП по Новоржевскому району");
        geoList.add("МО МВД России «Новосокольнический»");
        geoList.add("МО МВД России «Опочецкий»");
        geoList.add("ОМВД России по Островскому району");
        geoList.add("ОП по Палкинскому району");
        geoList.add("МО МВД России «Печорский»");
        geoList.add("ОП по Плюсскому району");
        geoList.add("ОМВД России по Порховскому району");
        geoList.add("ОМВД России по Псковскому району");
        geoList.add("ОП по Пустошкинскому району");
        geoList.add("Отделение МВД России по Пушкиногорскому району");
        geoList.add("Отделение МВД России по Пыталовскому району");
        geoList.add("МО МВД России «Себежский»");
        geoList.add("МО МВД России «Струго-Красненский»");
        geoList.add("ПП по Усвятскому району");
    }

    public AdapterGeo() {

    }

    public String geoString(Integer numberGeo) {//получение строки по номеру
        String geo;
        if (numberGeo >= 0 && numberGeo < geoList.size()) {
            geo = geoList.get(numberGeo);
        } else {
            geo = "Реквезит не заполнен";
        }
        return geo;
    }

    public Integer geoInteger(String geo) { //получение номера по строке
        Integer numberGeo = geoList.indexOf(geo);
        return numberGeo;
    }

    public List<String> getGeoList() {
        return geoList;
    }
}
