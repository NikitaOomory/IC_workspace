package com.example.MPM.ser_administrator.controllers;

import com.example.MPM.contract.AdapterGeo;
import com.example.MPM.contract.MyPagePath;
import com.example.MPM.repo.UserRepo;
import com.example.MPM.security.model.Role;
import com.example.MPM.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

@Controller
@RequestMapping("/adm/add_user/")
public class AddUserFromAdminPanel {

    @Autowired
    UserRepo userRepo;
    @Autowired
    MyPagePath path = new MyPagePath();

    @GetMapping
    public String getPage(Model model) {
        ArrayList<String> roles = new ArrayList<String>() {{
            add(Role.ADMIN.toString());
            add(Role.USER.toString());
        }};

        AdapterGeo adapterGeo = new AdapterGeo();
        Iterable<String> areas = adapterGeo.getGeoList();

        model.addAttribute("area", areas);
        model.addAttribute("roles", roles);

        return path.ADMIN_ADD_USER;
    }


    @PostMapping
    public String saveNewUser(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String role,
                              @RequestParam String nameEmployee,
                              @RequestParam String lastNameEmployee,
                              @RequestParam String patronymicEmployee,
                              @RequestParam String divisionEmployee,
                              @RequestParam String position,
                              @RequestParam String phoneNumberEmployee,
                              @RequestParam String emailEmployee,
                              @RequestParam String area) {

        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(password);

        if (role.equals("ADMIN")) {
            newUser.setRoles(Collections.singleton(Role.ADMIN));
        }
        if (role.equals("USER")) {
            newUser.setRoles(Collections.singleton(Role.USER));
        }

        newUser.setNameEmployee(nameEmployee);
        newUser.setLastNameEmployee(lastNameEmployee);
        newUser.setPatronymicEmployee(patronymicEmployee);
        newUser.setDivisionEmployee(divisionEmployee);
        newUser.setPosition(position);
        newUser.setArea(area);
        newUser.setPhoneNumberEmployee(phoneNumberEmployee);
        newUser.setEmailEmployee(emailEmployee);
        newUser.setActive(false);
        newUser.setCreateDate(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()));
        newUser.setEditedDate("УЗ не изменялась");

        User userFromDB = userRepo.findByUsername(newUser.getUsername());
        if (userFromDB == null) {
            userRepo.save(newUser);
            return "redirect:/adm";
        } else{
            return "redirect:/adm/add_user/";
        }
    }
}
