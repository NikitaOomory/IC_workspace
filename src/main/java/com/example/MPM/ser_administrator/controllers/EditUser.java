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
import java.util.Optional;

@Controller
@RequestMapping("/adm/edit_user")
public class EditUser {

    @Autowired
    UserRepo userRepo;
    @Autowired
    MyPagePath path = new MyPagePath();

    @GetMapping("/{id}")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        Optional<User> optionalUser = userRepo.findById(id);
        User user = optionalUser.get();
        ArrayList<String> roles = new ArrayList<String>() {{
            add(Role.ADMIN.toString());
            add(Role.USER.toString());
        }};

        AdapterGeo adapterGeo = new AdapterGeo();
        Iterable<String> areas = adapterGeo.getGeoList();

        model.addAttribute("area", areas);
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);

        return path.EDIT_USER;
    }


    @PostMapping("/{id}")
    public String saveEditedUser(@PathVariable(value = "id") Long id,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String role,
                                 @RequestParam String nameEmployee,
                                 @RequestParam String lastNameEmployee,
                                 @RequestParam String patronymicEmployee,
                                 @RequestParam String area,
                                 @RequestParam String divisionEmployee,
                                 @RequestParam String position,
                                 @RequestParam String phoneNumberEmployee,
                                 @RequestParam String emailEmployee) {

        User userFromDB = userRepo.findById(id).get();
        User editUser = new User();

        editUser.setId(userFromDB.getId());
        editUser.setUsername(username);
        editUser.setPassword(password);
        editUser.setNameEmployee(nameEmployee);
        editUser.setLastNameEmployee(lastNameEmployee);
        editUser.setPatronymicEmployee(patronymicEmployee);
        editUser.setArea(area);
        editUser.setDivisionEmployee(divisionEmployee);
        editUser.setPosition(position);
        editUser.setPhoneNumberEmployee(phoneNumberEmployee);
        editUser.setEmailEmployee(emailEmployee);
        editUser.setCreateDate(userFromDB.getCreateDate());
        editUser.setEditedDate(new SimpleDateFormat("HH:mm:ss dd.MM.yyyy").format(Calendar.getInstance().getTime()));

        if (role.equals("ADMIN")) {
            editUser.setRoles(Collections.singleton(Role.ADMIN));
        }
        if (role.equals("USER")) {
            editUser.setRoles(Collections.singleton(Role.USER));
        }

        userFromDB = editUser;

        userRepo.save(userFromDB);

        return "redirect:/adm/info_user/{id}";
    }
}
