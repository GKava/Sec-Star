package com.challenges.battleroyale;

public class Item {
    private String week_name;
    boolean locked;

    public Item(String week_name, boolean locked) {
        this.week_name = week_name;
        this.locked = locked;
    }

    public String getWeek_name() {
        return week_name;
    }

    public void setWeek_name(String week_name) {
        this.week_name = week_name;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
