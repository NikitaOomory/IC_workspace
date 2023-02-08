package com.example.MPM.repo;

import com.example.MPM.ser_administrator.model.AdminSetting;
import org.springframework.data.repository.CrudRepository;

public interface SettingRepo extends CrudRepository<AdminSetting, Long> {
    AdminSetting findBySettingName(String settingName);
}
