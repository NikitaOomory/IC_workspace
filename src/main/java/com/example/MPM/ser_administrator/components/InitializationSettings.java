package com.example.MPM.ser_administrator.components;

import com.example.MPM.ser_administrator.model.AdminSetting;
import com.example.MPM.repo.SettingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

/**Класс инициализации настроек в БД после загрузки Spring
Если настройки из списка есть в БД, то ничего не будет, но если ранее была добавлена новая настройка, то она будет добавлена
в БД, после перезапуска приложения, в таблицу настроек.*/

@Component
public class InitializationSettings {

    @Autowired
    SettingRepo settingRepo;

    @EventListener(ApplicationReadyEvent.class) //указываем момент инициализации и запуска класса
    public void initial() {
        ArrayList<AdminSetting> settingsArray = new ArrayList<AdminSetting>() {{ //список настроек для инициализации
            add(new AdminSetting("User ability to add a journal", true,
                    null, null, "Таблица РД",
                    "Активность кнопки добавления номера РД для USER"));
        }};

        for (AdminSetting setting : settingsArray) {//цикл инициализации настроек
            AdminSetting adminSetting = settingRepo.findBySettingName(setting.getSettingName());
            try {
                if (adminSetting == null) {
                    settingRepo.save(setting);
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }
}

