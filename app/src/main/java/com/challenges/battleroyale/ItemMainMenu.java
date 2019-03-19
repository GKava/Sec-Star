package com.challenges.battleroyale;

public class ItemMainMenu {
    private String week_name;
    boolean locked;
    private int weekNumber;
    private String week_numbers;

    public String getWeek_numbers() {
        return week_numbers;
    }

    public void setWeek_numbers(String week_numbers) {
        this.week_numbers = week_numbers;
    }

    public ItemMainMenu(String week_name, boolean locked, String week_numbers, int weekNumber) {
        this.week_name = week_name;
        this.locked = locked;
        this.week_numbers = week_numbers;
        this.weekNumber = weekNumber;
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

    public int getWeekNumber() {
        return weekNumber;
    }
}
