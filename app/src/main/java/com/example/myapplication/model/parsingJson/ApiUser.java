package com.example.myapplication.model.parsingJson;

public class ApiUser {
    //private int birthday;

    private String patronymic;

    private String registration_date;

    private String surname;

    private String tel_num;

    private String id;

    private String ok;

    private String first_name;

    private String email;

    private String update_date;

    /*public int getBirthday ()
    {
        return birthday;
    }

    public void setBirthday (int birthday)
    {
        this.birthday = birthday;
    }*/

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTel_num() {
        return tel_num;
    }

    public void setTel_num(String tel_num) {
        this.tel_num = tel_num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    @Override
    public String toString() {
        return "patronymic = " + patronymic + ", registration_date = " + registration_date + ", surname = " + surname + ", tel_num = " + tel_num + ", id = " + id + ", ok = " + ok + ", first_name = " + first_name + ", email = " + email + ", update_date = " + update_date;
    }

}
