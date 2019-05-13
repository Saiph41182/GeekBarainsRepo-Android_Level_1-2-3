package com.example.weatherproject.models;

public class SettingsItem {
    private int idRes;
    private int name;

    public SettingsItem(int idRes, int name) {
        this.idRes = idRes;
        this.name = name;
    }

    public int getIdRes() {
        return idRes;
    }

    public int getName() {
        return name;
    }
}
