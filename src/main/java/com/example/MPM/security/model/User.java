package com.example.MPM.security.model;

import javax.persistence.*;
import java.util.Set;

//Класс User - это модель для таблицы со списком пользователей.
@Entity
@Table(name = "usr")//название таблици в БД
public class User{

//-------------------------------------------------------------------------------------------------------------
//Поля класса
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username; //имя пользователя / логин
    private String password; //пароль
    private boolean active; //активность пользователя
    private String nameEmployee; // имя сотрудника
    private String lastNameEmployee; // фамилия сотрудника
    private String patronymicEmployee; //отчество сотрудника
    private String area; //район сотрудника
    private String divisionEmployee; // подразделение
    private String position; // должность
    private String phoneNumberEmployee; // номер телефона
    private String emailEmployee; // адрес электронной почты
    private String createDate; // дата создания учётной записи
    private String editedDate; // дата редактирования учётной записи
    private String editedNameUser; //имя пользователя, который изменял учётную запись

//-----------------------------------------------------------------------------------------------------------------
//Конструкторы
    public User() {
    }

//-----------------------------------------------------------------------------------------------------------------
//Связываение таблицы пользователей с таблицей ролей
    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role>roles;


//-----------------------------------------------------------------------------------------------------------------
//Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getLastNameEmployee() {
        return lastNameEmployee;
    }

    public void setLastNameEmployee(String lastNameEmployee) {
        this.lastNameEmployee = lastNameEmployee;
    }

    public String getPatronymicEmployee() {
        return patronymicEmployee;
    }

    public void setPatronymicEmployee(String patronymicEmployee) {
        this.patronymicEmployee = patronymicEmployee;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDivisionEmployee() {
        return divisionEmployee;
    }

    public void setDivisionEmployee(String divisionEmployee) {
        this.divisionEmployee = divisionEmployee;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumberEmployee() {
        return phoneNumberEmployee;
    }

    public void setPhoneNumberEmployee(String phoneNumberEmployee) {
        this.phoneNumberEmployee = phoneNumberEmployee;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public void setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(String createEdited) {
        this.editedDate = createEdited;
    }

    public String getEditedNameUser() {
        return editedNameUser;
    }

    public void setEditedNameUser(String editedNameUser) {
        this.editedNameUser = editedNameUser;
    }

    public String getFullName(){
        return lastNameEmployee + nameEmployee + patronymicEmployee;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
