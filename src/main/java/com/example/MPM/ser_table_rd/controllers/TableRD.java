package com.example.MPM.ser_table_rd.controllers;

import com.example.MPM.contract.MyPagePath;
import com.example.MPM.repo.JournalSLRepo;
import com.example.MPM.ser_table_rd.model.JournalSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Controller
public class TableRD {

    @Autowired // указываем что данная переменная будет ссылаться на наш репозиторий
    private JournalSLRepo journalSLRepository;
    @Autowired
    private MyPagePath path = new MyPagePath();

    @GetMapping("/tableRD")
    public String tableRdMain(Model model) {

        //получаем все записи из таблицы
        Iterable<JournalSL> numbersSL = journalSLRepository.findAll();

        //меняем сортировку списка
        ArrayList<JournalSL> res = new ArrayList<>();
        numbersSL.forEach(res::add);
        Collections.sort(res, Collections.reverseOrder(new Comparator<JournalSL>() {
            @Override
            public int compare(JournalSL o1, JournalSL o2) {
                return o1.getShortNumber().compareTo(o2.getShortNumber());
            }
        }));

        //передаём полученные данные на страницу
        model.addAttribute("numbersSL", res);

        return path.TABLE_RD;
    }


    //----------------------------------------------------------------------------------------------------------------
    //Фильтр по номеру дела
    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<JournalSL> journalSLS;

        if (filter != null && !filter.isEmpty()) {
            String num = null;
            try {
                num = filter;
                if(num.length() == 1){
                    num = "00000" + num;
                }else if(num.length() == 2){
                    num = "0000" + num;
                }else if(num.length() == 3){
                    num = "000" + num;
                }else if(num.length() == 4){
                    num = "00" + num;
                } else if(num.length() == 5){
                    num = "0" + num;
                } else if(num.length() == 6){
                    num = num;
                }
                journalSLS = journalSLRepository.findByShortNumber(num);
                model.addAttribute("numbersSL", journalSLS);
            } catch (NumberFormatException e) {

            }
        } else {
            journalSLS = journalSLRepository.findAll();
            ArrayList<JournalSL> res = new ArrayList<>();
            journalSLS.forEach(res::add);
            Collections.sort(res, Collections.reverseOrder(new Comparator<JournalSL>() {
                @Override
                public int compare(JournalSL o1, JournalSL o2) {
                    return o1.getShortNumber().compareTo(o2.getShortNumber());
                }
            }));
            model.addAttribute("numbersSL", res);
        }
        return path.TABLE_RD;
    }



    //----------------------------------------------------------------------------------------------------------------
    //Фильтр по признаку сдачи документов
    @PostMapping("/filterCh")
    public String filterCh(@RequestParam String filterCh, Model model) {

        Iterable<JournalSL> jurnalSLS;

        if (!filterCh.equals("все")) {
            String res;
            try {
                if (filterCh.equals("есть")) {
                    res = "V";
                } else {
                    res = "X";
                }

                jurnalSLS = journalSLRepository.findByReadyDocks(res);

                ArrayList<JournalSL> resA = new ArrayList<>();
                jurnalSLS.forEach(resA::add);
                Collections.sort(resA, Collections.reverseOrder(new Comparator<JournalSL>() {
                    @Override
                    public int compare(JournalSL o1, JournalSL o2) {
                        return o1.getShortNumber().compareTo(o2.getShortNumber());
                    }
                }));

                model.addAttribute("numbersSL", resA);

            } catch (NumberFormatException e) {

            }
        } else {
            jurnalSLS = journalSLRepository.findAll();
            ArrayList<JournalSL> res = new ArrayList<>();
            jurnalSLS.forEach(res::add);
            Collections.sort(res, Collections.reverseOrder(new Comparator<JournalSL>() {
                @Override
                public int compare(JournalSL o1, JournalSL o2) {
                    return o1.getShortNumber().compareTo(o2.getShortNumber());
                }
            }));
            model.addAttribute("numbersSL", res);
        }
        return path.TABLE_RD;
    }
}
