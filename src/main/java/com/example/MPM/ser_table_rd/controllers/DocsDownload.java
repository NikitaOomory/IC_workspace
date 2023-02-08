package com.example.MPM.ser_table_rd.controllers;

import com.example.MPM.contract.MyPagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/tableRD/download")
public class DocsDownload {
    @Autowired
    MyPagePath path = new MyPagePath();

    @GetMapping
    public String getPage() {
        return path.REG_DOCS;
    }

    @GetMapping("/1")
    public void getFile(HttpServletResponse response) throws IOException {
        File file = new File("text.txt");
        if (file.exists()){
            response.setHeader("Content-disposition", "attachment;filename=" + file.getName());
            response.setContentType("application/vnd.ms-excel");

                Files.copy(file.toPath(), response.getOutputStream());
                response.getOutputStream().flush();

            //TODO: изучить и отредактировать!!!
        }
    }
}
