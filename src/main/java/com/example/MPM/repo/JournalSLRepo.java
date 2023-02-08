package com.example.MPM.repo;

import com.example.MPM.ser_table_rd.model.JournalSL;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JournalSLRepo extends CrudRepository<JournalSL, Long> {
    List<JournalSL> findByShortNumber (String shortNumber);
    List<JournalSL> findByReadyDocks(String readyDocks);
    JournalSL findByOperName(String operName);


}
