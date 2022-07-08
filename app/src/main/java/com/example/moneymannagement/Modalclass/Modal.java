package com.example.moneymannagement.Modalclass;

public class Modal {

    String amount,type,mounthdate,todaydate,time;

    public Modal(String amount, String type, String mounthdate, String todaydate, String time) {
        this.amount = amount;
        this.type = type;
        this.mounthdate = mounthdate;
        this.todaydate = todaydate;
        this.time = time;
    }
    public Modal() {

    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMounthdate() {
        return mounthdate;
    }

    public void setMounthdate(String mounthdate) {
        this.mounthdate = mounthdate;
    }

    public String getTodaydate() {
        return todaydate;
    }

    public void setTodaydate(String todaydate) {
        this.todaydate = todaydate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
