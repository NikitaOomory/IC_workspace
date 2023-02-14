package com.example.MPM.security.controllers;

import com.example.MPM.contract.AdapterGeo;
import com.example.MPM.contract.MyPagePath;
import com.example.MPM.repo.UserRepo;
import com.example.MPM.security.model.Role;
import com.example.MPM.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

@Controller
@RequestMapping("/reg")
public class RegController {

    @Autowired
    MyPagePath path = new MyPagePath();
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String getRegistrationPage(Model model) {
        ArrayList<String> roles = new ArrayList<String>() {{
            add(Role.ADMIN.toString());
            add(Role.USER.toString());
        }};

        AdapterGeo adapterGeo = new AdapterGeo();
        Iterable<String> areas = adapterGeo.getGeoArrayList();

        model.addAttribute("areas", areas);
        model.addAttribute("roles", roles);

        return path.REGISTRATION;
    }

    @PostMapping
    public String saveNewUser(@RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String nameEmployee,
                              @RequestParam String lastNameEmployee,
                              @RequestParam String patronymicEmployee,
                              @RequestParam String area,
                              @RequestParam String divisionEmployee,
                              @RequestParam String position,
                              @RequestParam String phoneNumberEmployee,
                              @RequestParam String emailEmployee) {

        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRoles(Collections.singleton(Role.USER));
        newUser.setNameEmployee(nameEmployee);
        newUser.setLastNameEmployee(lastNameEmployee);
        newUser.setPatronymicEmployee(patronymicEmployee);
        newUser.setArea(area);
        newUser.setDivisionEmployee(divisionEmployee);
        newUser.setPosition(position);
        newUser.setPhoneNumberEmployee(phoneNumberEmployee);
        newUser.setEmailEmployee(emailEmployee);
        newUser.setActive(false);
        newUser.setCreateDate(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()));
        newUser.setEditedDate("УЗ не изменялась");

        User userFromDB = userRepo.findByUsername(newUser.getUsername());

        if (userFromDB == null) {
            userRepo.save(newUser);
            return "redirect:/login";
        } else {
            return "redirect:/reg";
        }
    }
}
