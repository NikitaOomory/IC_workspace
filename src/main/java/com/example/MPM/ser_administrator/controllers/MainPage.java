package com.example.MPM.ser_administrator.controllers;

import com.example.MPM.contract.MyPagePath;
import com.example.MPM.ser_administrator.model.AdminSetting;
import com.example.MPM.repo.SettingRepo;
import com.example.MPM.repo.UserRepo;
import com.example.MPM.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/adm")
public class MainPage {

    @Autowired
    SettingRepo settingRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    MyPagePath path = new MyPagePath();

    @GetMapping
    public String getPage(){
        return path.ADMINISTRATION;
    }

    @ModelAttribute
    public void sentSettings(Model model){
        AdminSetting adminSetting = settingRepo.findBySettingName("User ability to add a journal");
        model.addAttribute("setting", adminSetting);

        String activeSettingsString = adminSetting.getSettingStatus().toString();
        if(activeSettingsString.equals("true")){
            activeSettingsString = "включена";
        } else if (activeSettingsString.equals("false")) {
            activeSettingsString = "выключена";
        }
        model.addAttribute("activeSet", activeSettingsString);
    }

    @ModelAttribute
    public void sentUsers(Model model){
        Iterable<User> users = userRepo.findAll();
        model.addAttribute("users", users);
    }

    @PostMapping("/s") //метод включения и выключения работы настройки
    public String setSetting(){
        AdminSetting adminSetting = settingRepo.findBySettingName("User ability to add a journal");
        if(adminSetting.getSettingStatus() == true){
            adminSetting.setSettingStatus(false);
            settingRepo.save(adminSetting);
        }else{
            adminSetting.setSettingStatus(true);
            settingRepo.save(adminSetting);
        }
        return "redirect:/adm";
    }

    @GetMapping("/edit_user/act/{id}")//метод активации и блокировки пользователя
    public String setActiveUser(@PathVariable(value = "id") Long id){
        Optional<User> userOptional = userRepo.findById(id);
        User user = userOptional.get();
        if(user.isActive() == true){
            user.setActive(false);
            userRepo.save(user);
        }else{
            user.setActive(true);
            userRepo.save(user);
        }
        return "redirect:/adm";
    }
}
