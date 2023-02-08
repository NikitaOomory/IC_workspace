package com.example.MPM.ser_table_rd.controllers;

import com.example.MPM.contract.MyPagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tableRD/download")
public class DocsDownload {
    @Autowired
    MyPagePath path = new MyPagePath();

    @GetMapping
    public String getPage(){
        return path.REG_DOCS;
    }
}
