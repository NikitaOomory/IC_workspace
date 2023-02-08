package com.example.MPM.ser_table_rd.controllers;

import com.example.MPM.contract.AdapterCat;
import com.example.MPM.contract.AdapterGeo;
import com.example.MPM.contract.MyPagePath;
import com.example.MPM.repo.JournalSLRepo;
import com.example.MPM.ser_table_rd.model.JournalSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

//Контроллер страницы info_form_tableRD
@Controller
public class InfoFormTableRD {
    @Autowired // указываем что данная переменная будет ссылаться на наш репозиторий
    private JournalSLRepo journalSLRepository;
    @Autowired
    private MyPagePath path = new MyPagePath();


    @PreAuthorize("hasAuthority('ADMIN')") //ограничение доступа в зависимости от роли
    @GetMapping("/tableRD/{id}") // динамический параметр
    public String informationFormTableRD(@PathVariable(value = "id") Long id, Model model) {

        //проверка присутствие id  в БД
        if (!journalSLRepository.existsById(id)) {
            return "redirect:/tableRD";
        }

        //получение данных из экземпляра класса
        Optional<JournalSL> jurnalSL = journalSLRepository.findById(id);
        ArrayList<JournalSL> res = new ArrayList<>();
        jurnalSL.ifPresent(res::add);
        AdapterCat adapterCat = new AdapterCat();
        AdapterGeo adapterGeo = new AdapterGeo();
        String geo = adapterGeo.geoString(jurnalSL.get().getNumberGeo());
        String cat = adapterCat.catString(jurnalSL.get().getNumberCat());
        final ArrayList<String> check = new ArrayList<String>() {{
            add("есть");
            add("отсутствуют");
        }};

        //передача данных в форму
        model.addAttribute("check", check);
        model.addAttribute("post", res);
        model.addAttribute("geoloc", geo);
        model.addAttribute("cat", cat);

        return path.INFO_JOURNAL;
    }

    //изменение статуса о сдаче документов
    @PostMapping("/tableRD/{id}")
    public String checkedDocksMet(@PathVariable(value = "id") Long id, @RequestParam(value = "checkedDocks") String checkedDocks, Model model) {

        String res;

        if (checkedDocks.equals("есть")) {
            res = "V";
        } else {
            res = "X";
        }

        JournalSL jurnalSL = journalSLRepository.findById(id).get();
        jurnalSL.setReadyDocks(res);

        journalSLRepository.save(jurnalSL);


        return "redirect:/tableRD/{id}";
    }
}
