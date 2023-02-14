package com.example.MPM.ser_table_rd.controllers;

import com.example.MPM.contract.AdapterCategory;
import com.example.MPM.contract.AdapterGeo;
import com.example.MPM.contract.MyPagePath;
import com.example.MPM.repo.JournalSLRepo;
import com.example.MPM.repo.SettingRepo;
import com.example.MPM.repo.UserRepo;
import com.example.MPM.security.model.User;
import com.example.MPM.security.model.UserUtils;
import com.example.MPM.ser_administrator.model.AdminSetting;
import com.example.MPM.ser_table_rd.model.JournalSL;
import com.example.MPM.ser_table_rd.model.NumberJournalSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

@RequestMapping("/tableRD")
@Controller
public class AddJournal {

    @Autowired
    private JournalSLRepo journalSLRepository;
    @Autowired
    private SettingRepo settingRepo;
    @Autowired
    private UserUtils enteredUser;
    @Autowired
    UserRepo userRepo;
    @Autowired
    NumberJournalSL numberJournalSL;
    @Autowired
    MyPagePath path = new MyPagePath();

    @GetMapping("/add_form_table_rd")
    public String getPageAddFormTableRDIfActiveButtonOrYouAdmin(Model model){
        if(checkSettingsAddButton()){
            AdapterGeo adapterGeo = new AdapterGeo();
            model.addAttribute("area", (ArrayList<String>) adapterGeo.getGeoArrayList());
            AdapterCategory adapterCat = new AdapterCategory();
            model.addAttribute("resCat",(ArrayList<String>) adapterCat.getArrayListCategorys());
            return path.ADD_JOURNAL;
        }else {
            return "redirect:/tableRD";
        }
    }

    public boolean checkSettingsAddButton(){
        AdminSetting adminSettingAddButton = settingRepo.findBySettingName("User ability to add a journal");
        enteredUser = new UserUtils(userRepo);
        //администратор при любой настройке должен обладать полным функционалом и доступом
        boolean isAdmin = enteredUser.getAuthUserRole().equals("[ADMIN]");
        if(adminSettingAddButton.getSettingStatus() || isAdmin){
            return true;
        }else {
            return false;
        }
    }



    @PostMapping("/add_form_table_rd")
    public String addJournalSLInDB(
            @AuthenticationPrincipal User user,
            @RequestParam String oper_name,
            @RequestParam String roz_name,
            @RequestParam String number_category,
            @RequestParam String area,
            @RequestParam String typeJ,
            @RequestParam String phone_oper,
            Model model){
        //---------------------------------------------------------------------------------------
        //ФОРМИРОВАНИЕ НОМЕРА ДЕЛА
        numberJournalSL = new NumberJournalSL(typeJ,number_category,journalSLRepository);
        String fullNumber = numberJournalSL.getFinalNumber();

        //получаем значение района для БД с помощью адаптера
        AdapterGeo adapterGeo = new AdapterGeo();
        Integer geo = adapterGeo.getIntegerFromStringGeo(area);

        AdapterCategory adapterCategory = new AdapterCategory();
        Integer cat = adapterCategory.getIntegerCategoryFromString(number_category);

        String shortNumber = numberJournalSL.getShortNumber();

        //получение даты создания записи
        String getDateSL = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //создаём новый экземпляр класса и заполняем его данными полученными из формы, после чего сохраняем в БД
        JournalSL journalSL = new JournalSL(fullNumber, oper_name, roz_name, cat, geo, getDateSL, shortNumber, phone_oper);


        //проверка на наличие пустых номеров, в случае если такие имеются, заполняются в первую очередь они
        JournalSL priorityNumberSL = journalSLRepository.findByOperName("");

        if (priorityNumberSL == null){
            isSpecialCategoryThenSetFalseCheckedSearchCard(journalSL);
            journalSLRepository.save(journalSL);
        }else if (priorityNumberSL!= null) {
            priorityNumberSL.setOperName(oper_name);
            priorityNumberSL.setRozName(roz_name);
            priorityNumberSL.setNumberCat(cat);
            priorityNumberSL.setNumberGeo(geo);
            priorityNumberSL.setDataSL(getDateSL);
            priorityNumberSL.setPhoneNumberCreator(phone_oper);
            priorityNumberSL.setReadyDocks("X");
            priorityNumberSL.setDataIzm("внесено в свободный номер от которого отказались");
            isSpecialCategoryThenSetFalseCheckedSearchCard(priorityNumberSL);
            journalSLRepository.save(priorityNumberSL);
        }

        return "redirect:/tableRD";
    }

    public boolean isSpecialCategoryThenSetFalseCheckedSearchCard(JournalSL journalSL){
        if(journalSL.getNumberCat() >= 396 && journalSL.getNumberCat() <= 623){
            journalSL.setCheckSearchCards(false);
            return true;
        }else {
            return false;
        }
    }

}
