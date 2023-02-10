package com.example.MPM.ser_table_rd.controllers;

import com.example.MPM.contract.AdapterCat;
import com.example.MPM.contract.AdapterGeo;
import com.example.MPM.contract.MyPagePath;
import com.example.MPM.repo.JournalSLRepo;
import com.example.MPM.ser_table_rd.model.JournalSL;
import com.example.MPM.ser_table_rd.model.NumberJournalSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

//Контроллер страницы add_form_tableRD

@Controller
public class EditJournal {
    @Autowired // указываем что данная переменная будет ссылаться на наш репозиторий
    private JournalSLRepo journalSLRepository;
    @Autowired
    MyPagePath path = new MyPagePath();

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/tableRD/{id}/edit")
    public String editJurnalSL(@PathVariable(value = "id") Long id, Model model){

        //проверка присутствие id в БД
        if (!journalSLRepository.existsById(id)) {
            return "redirect:/tableRD";
        }

        //получаем по id нужный нам экземпляр класса для отображения в форме
        JournalSL oldJournalSL = journalSLRepository.findById(id).get();

        //преобразовываем числовые значения из БД о районе и категории в текстовый вид с помощью адаптера-словаря
        AdapterCat adapterCat = new AdapterCat();
        ArrayList<String> cat = (ArrayList<String>) adapterCat.getCatlist();
        AdapterGeo adapterGeo = new AdapterGeo();
        ArrayList<String> geo = (ArrayList<String>) adapterGeo.getGeoList();

        //передаём значения в форму
        model.addAttribute("post", oldJournalSL);
        model.addAttribute("resCat", cat);
        model.addAttribute("res", geo);

        //TODO: сделать показ выбранного элемента категории и гео, а не просто списка селект начиная с 1

        return path.EDIT_JOURNAL;
    }

    @PostMapping("/tableRD/{id}/edit")
    public String updateFormButton(@PathVariable(value = "id") Long id, @RequestParam String oper_name, @RequestParam String roz_name,
                                   @RequestParam String number_cat, @RequestParam String number_geo, @RequestParam String phoneNumber,
                                   @RequestParam String typeJ, Model model){

        //находим соответствующий экземпляр в БД по id, для дальнейшего внесения в него изменений
        JournalSL jurnalSL = journalSLRepository.findById(id).get();

        //устанавливаем изменения из формы
        jurnalSL.setOperName(oper_name);
        jurnalSL.setRozName(roz_name);
        AdapterCat adapterCat = new AdapterCat();
        AdapterGeo adapterGeo = new AdapterGeo();
        jurnalSL.setNumberCat(adapterCat.catInteger(number_cat));
        jurnalSL.setNumberGeo(adapterGeo.geoInteger(number_geo));
        jurnalSL.setPhoneNumberCreator(phoneNumber);

        NumberJournalSL numberJournalSL = new NumberJournalSL();
        numberJournalSL.setShortNumber(jurnalSL.getShortNumber());
        if(typeJ.equals("1 - сигнальная система")){
            numberJournalSL.setTypeJournal("1");
        } else if (typeJ.equals("5 - из ОПД в РД")) {
            numberJournalSL.setTypeJournal("5");
        } else if (typeJ.equals("4 - труп")) {
            numberJournalSL.setTypeJournal("4");
        } else {
            numberJournalSL.setTypeJournal("2");
        }
        numberJournalSL.setCategory(adapterCat.catInteger(number_cat).toString());
        numberJournalSL.setYear(jurnalSL.getNumberSL().substring(0,2));
        jurnalSL.setNumberSL(numberJournalSL.getFinalNumber());

        //получаем дату изменения и устанавливаем её
        String getDateIzm = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime());
        jurnalSL.setDataIzm(getDateIzm);

        //сохраняем изменения экземпляра
        journalSLRepository.save(jurnalSL);

        return "redirect:/tableRD";
    }
}