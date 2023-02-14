package com.example.MPM.contract;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптер-словарь районов Псковской области
 */
@Component
public class AdapterGeo {

    private static ArrayList<String> geoArrayList = new ArrayList<>();

    static {
        geoArrayList.add("УМВД России по городу Пскову");
        geoArrayList.add("ОМВД России по городу Великие Луки");
        geoArrayList.add("МО МВД России «Бежаницкий»");
        geoArrayList.add("МО МВД России «Великолукский»");
        geoArrayList.add("ОМВД России по Гдовскому району");
        geoArrayList.add("МО МВД России «Дедовичский»");
        geoArrayList.add("ОП по Дновскому району");
        geoArrayList.add("ОП по Красногородскому району");
        geoArrayList.add("ОП по Куньинскому району");
        geoArrayList.add("ОП по Локнянскому району");
        geoArrayList.add("МО МВД России «Невельский»");
        geoArrayList.add("ОП по Новоржевскому району");
        geoArrayList.add("МО МВД России «Новосокольнический»");
        geoArrayList.add("МО МВД России «Опочецкий»");
        geoArrayList.add("ОМВД России по Островскому району");
        geoArrayList.add("ОП по Палкинскому району");
        geoArrayList.add("МО МВД России «Печорский»");
        geoArrayList.add("ОП по Плюсскому району");
        geoArrayList.add("ОМВД России по Порховскому району");
        geoArrayList.add("ОМВД России по Псковскому району");
        geoArrayList.add("ОП по Пустошкинскому району");
        geoArrayList.add("Отделение МВД России по Пушкиногорскому району");
        geoArrayList.add("Отделение МВД России по Пыталовскому району");
        geoArrayList.add("МО МВД России «Себежский»");
        geoArrayList.add("МО МВД России «Струго-Красненский»");
        geoArrayList.add("ПП по Усвятскому району");
    }

    public AdapterGeo() {

    }

    public String getStringFromIntegerGeo(Integer numberGeo) {//получение строки по номеру
        String result;
        if (numberGeo >= 0 && numberGeo < geoArrayList.size()) {
            result = geoArrayList.get(numberGeo);
        } else {
            result = "Реквизит не заполнен";
        }
        return result;
    }

    public Integer getIntegerFromStringGeo(String geo) { //получение номера по строке
        Integer result = geoArrayList.indexOf(geo);
        return result;
    }

    public List<String> getGeoArrayList() {
        return geoArrayList;
    }
}
