package com.challenges.battleroyale;

public class ItemChallenges {
    int imageCount;
    int weekNumber;
    String season_storage;

    public ItemChallenges(int imageCount, int weekNumber, String season_storage) {
        this.imageCount = imageCount;
        this.weekNumber = weekNumber;
        this.season_storage = season_storage;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setSeason_storage(String season_storage) {
        this.season_storage = season_storage;
    }

    public int getImageCount() {
        return imageCount;
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public String getSeason_storage() {
        return season_storage;
    }
}
