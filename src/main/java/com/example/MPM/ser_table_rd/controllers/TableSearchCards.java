package com.example.MPM.ser_table_rd.controllers;

import com.example.MPM.contract.MyPagePath;
import com.example.MPM.repo.JournalSLRepo;
import com.example.MPM.ser_table_rd.model.JournalSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TableSearchCards {

    @Autowired
    private MyPagePath path = new MyPagePath();
    @Autowired
    private JournalSLRepo journalSLRepo;

    @GetMapping("/table_rd/table_search_cards")
    public String getTableSearchCardsPage(Model model){
        model.addAttribute("cards", getCardsFromDB());
        return path.SEARCH_CARDS;
    }

    private ArrayList<JournalSL> getCardsFromDB(){
        ArrayList<JournalSL> cards = journalSLRepo.findByCheckSearchCards(false);
        return cards;
    }

    @GetMapping("/table_rd/table_search_cards/{id}")
    public String editCheckSearchCard(@PathVariable(value = "id") Long id){
        JournalSL newJournal = journalSLRepo.findById(id).get();
        newJournal.setCheckSearchCards(true);
        journalSLRepo.save(newJournal);
        return "redirect:/table_rd/table_search_cards";
    }
}
