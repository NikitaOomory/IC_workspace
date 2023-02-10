package com.example.MPM.contract;

import org.springframework.stereotype.Component;

@Component
public class MyPagePath {
    public final String MAIN_PAGE = "main_page/main"; //главная страница сайта
    public final String LOGIN = "security/login"; //страница авторизации
    public final String REGISTRATION = "security/registration"; //страница регистрации для пользователей
    public final String ADMINISTRATION = "administration/adm"; //страница администрирования сервиса
    public final String ADMIN_ADD_USER = "administration/addUserFromAdminPanel"; //страница создания пользователя через сервис администрирования
    public final String EDIT_USER = "administration/edit_user"; //редактирование пользователя через панель администратора
    public final String INFO_USER = "administration/info_user"; //отображение информации о пользователе

// -------------------------------------------------------------------------------------------------------------------
//Таблица розыскных дел

    public final String TABLE_RD = "table_rd/tableRD";//главная страница таблицы РД
    public final String ADD_JOURNAL = "table_rd/add_journal";//страница взятия номера РД
    public final String INFO_JOURNAL = "table_rd/info_journal";//страница отображения информации о РД
    public final String EDIT_JOURNAL = "table_rd/edit_journal";//страница редактирования РД
    public final String REG_DOCS = "table_rd/download_docs";//страница для скачивания документов

////для деплоя
//    public final String MAIN_PAGE = "main"; //главная страница сайта
//    public final String LOGIN = "login"; //страница авторизации
//    public final String REGISTRATION = "registration"; //страница регистрации для пользователей
//    public final String ADMINISTRATION = "adm"; //страница администрирования сервиса
//    public final String ADMIN_ADD_USER = "addUserFromAdminPanel"; //страница создания пользователя через сервис администрирования
//    public final String EDIT_USER = "edit_user"; //редактирование пользователя через панель администратора
//    public final String INFO_USER = "info_user"; //отображение информации о пользователе
//
//// -------------------------------------------------------------------------------------------------------------------
////Таблица розыскных дел
//
//    public final String TABLE_RD = "tableRD";//главная страница таблицы РД
//    public final String ADD_JOURNAL = "add_journal";//страница взятия номера РД
//    public final String INFO_JOURNAL = "info_journal";//страница отображения информации о РД
//    public final String EDIT_JOURNAL = "edit_journal";//страница редактирования РД
//    public final String REG_DOCS = "download_docs";//страница для скачивания документов
//
}
