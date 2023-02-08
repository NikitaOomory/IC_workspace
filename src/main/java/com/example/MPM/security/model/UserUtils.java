package com.example.MPM.security.model;

import com.example.MPM.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    @Autowired
    private UserRepo userRepo;

    public UserUtils(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public String getAuthUserRole(){
        String res = userRepo.findByUsername(getCurrentUsername()).getRoles().toString();
        return res;
    }

}
