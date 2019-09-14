package com.example.myapplication.model.api.parsingJson;

import com.google.gson.annotations.Expose;

import java.util.Set;
import java.util.TreeSet;

public class ApiClient {
    @Expose
    private long birthday;

    @Expose
    private String patronymic;

    @Expose
    private Boolean isBirthday;

    @Expose
    private String surname;

    @Expose
    private String phone;

    @Expose(serialize = false)
    private String id;

    @Expose(serialize = false)
    private String ok;

    @Expose
    private String name;

    @Expose
    private String email;

    @Expose(serialize = false)
    private String updateDate;

    @Expose(serialize = false)
    private String description;

    @Expose(serialize = false)
    private String detail;

    @Expose
    private Set<Integer> carsId = new TreeSet<>();

    public Set<Integer> getCarsId() {
        return carsId;
    }

    public String getDetail() {
        return detail;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setIsBirthday(Boolean isBirthday) {
        this.isBirthday = isBirthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

    public String getOk() {
        return ok;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public String getDescription() {
        return description;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {

        String response = "id: " + id + "\n"
                + "name: " + name + "\n"
                + "surname: " + surname + "\n"
                + "patronymic: " + patronymic + "\n"
                + "phone: " + phone + "\n"
                + "email: " + email + "\n"
                + "birthday: " + birthday + "\n"
                + "isBirthday: " + isBirthday + "\n"
                + "updateDate: " + updateDate + "\n"
                + "description: " + description + "\n"
                + "detail: " + detail + "\n";
        for (int id : carsId) {
            response = response + id + ", ";
        }

        return response;
    }

}
