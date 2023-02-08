package com.example.MPM.ser_administrator.controllers;

import com.example.MPM.contract.MyPagePath;
import com.example.MPM.repo.UserRepo;
import com.example.MPM.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;

@Controller
@RequestMapping("/adm/info_user")
public class InfoUser {
    @Autowired
    UserRepo userRepo;
    @Autowired
    MyPagePath path = new MyPagePath();

    @GetMapping("/{id}")
    public String editUser(@PathVariable(value = "id") Long id, Model model){
        Optional<User> optionalUser = userRepo.findById(id);
        User user = optionalUser.get();

        model.addAttribute("user", user);

        return path.INFO_USER;
    }
}
