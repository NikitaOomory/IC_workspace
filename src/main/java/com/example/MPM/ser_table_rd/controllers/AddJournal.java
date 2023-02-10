package com.example.MPM.ser_table_rd.controllers;

import com.example.MPM.contract.AdapterCat;
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
    private UserUtils userUtils;
    @Autowired
    UserRepo userRepo;
    @Autowired
    NumberJournalSL numberJournalSL;
    @Autowired
    MyPagePath path = new MyPagePath();

    @GetMapping("/add_form_table_rd")
    public String addFormTableRD(Model model){

        AdminSetting adminSetting = settingRepo.findBySettingName("User ability to add a journal");
        userUtils = new UserUtils(userRepo);
        boolean a = userUtils.getAuthUserRole().equals("[ADMIN]");

        if(adminSetting.getSettingStatus() == true || a==true){
            AdapterGeo adapterGeo = new AdapterGeo();
            ArrayList<String> areas = (ArrayList<String>) adapterGeo.getGeoList();
            model.addAttribute("area", areas);

            AdapterCat adapterCat = new AdapterCat();
            ArrayList <String> resCat = (ArrayList<String>) adapterCat.getCatlist();
            model.addAttribute("resCat",resCat);

            return path.ADD_JOURNAL;
        }else {
            return "redirect:/tableRD";
        }
    }



    @PostMapping("/add_form_table_rd")
    public String addFormButton(
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
        numberJournalSL = new NumberJournalSL();

        //получение года заведения дела для составления номера
        numberJournalSL.setYear(Year.now().format(DateTimeFormatter.ofPattern("uu")).toString());

        //установка типа розыскного дела для отображения в полном номере РД
        if(typeJ.equals("1 - сигнальная система")){
            numberJournalSL.setTypeJournal("1");
        } else if (typeJ.equals("5 - из ОПД в РД")) {
            numberJournalSL.setTypeJournal("5");
        } else if (typeJ.equals("4 - труп")) {
            numberJournalSL.setTypeJournal("4");
        } else {
            numberJournalSL.setTypeJournal("2");
        }

        //формирование короткого номера розыскного дела
        Long last = journalSLRepository.count();
        String numberShort = (Math.toIntExact(last) + 1) + "";
        if(numberShort.length() == 1){
            numberShort = "00000" + numberShort;
        }else if(numberShort.length() == 2){
            numberShort = "0000" + numberShort;
        }else if(numberShort.length() == 3){
            numberShort = "000" + numberShort;
        }else if(numberShort.length() == 4){
            numberShort = "00" + numberShort;
        } else if(numberShort.length() == 5){
            numberShort = "0" + numberShort;
        } else if(numberShort.length() == 6){
            numberShort = numberShort;
        }
        numberJournalSL.setShortNumber(numberShort);

        //установка категории дела для оторажения в номере
        //получаем значение категории дела для БД с помощью адаптера
        AdapterCat adapterCat = new AdapterCat();
        Integer cat = adapterCat.catInteger(number_category);
        numberJournalSL.setCategory(adapterCat.catInteger(number_category).toString());

        //получаем полный номер РД
        String fullNumber = numberJournalSL.getFinalNumber();
        //---------------------------------------------------------------------------------------


        //получаем значение района для БД с помощью адаптера
        AdapterGeo adapterGeo = new AdapterGeo();
        Integer geo = adapterGeo.geoInteger(area);

        //получение даты создания записи
        String getDateSL = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime());

        //создаём новый экземпляр класса и заполняем его данными полученными из формы, после чего сохраняем в БД
        JournalSL jurnalSL = new JournalSL(fullNumber, oper_name, roz_name, cat, geo, getDateSL, numberShort, phone_oper);

        //проверка на наличие пустых номеров, в случае если такие имеются, заполняются в первую очередь они
        JournalSL priority = journalSLRepository.findByOperName("");

        if (priority == null){
            journalSLRepository.save(jurnalSL);
        }else if (priority!= null) {
            priority.setOperName(oper_name);
            priority.setRozName(roz_name);
            priority.setNumberCat(cat);
            priority.setNumberGeo(geo);
            priority.setDataSL(getDateSL);
            priority.setShortNumber(numberShort);
            priority.setPhoneNumberCreator(phone_oper);
            priority.setReadyDocks("X");
            journalSLRepository.save(priority);
        }

        return "redirect:/tableRD";
    }

}
