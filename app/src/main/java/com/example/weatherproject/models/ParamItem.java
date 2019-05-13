package com.example.weatherproject.models;

public class ParamItem {
    private String itemName;
    private boolean checked;

    public ParamItem(String itemName,boolean visibility) {
        this.itemName = itemName;
        this.checked = visibility;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isChecked() {
        return checked;
    }
}
