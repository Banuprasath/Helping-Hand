package com.example.cardview;

public class CardItem {
    private int imageResource;
    private String title;
    private Class<?> activityClass;

    public CardItem(int imageResource, String title, Class<?> activityClass) {
        this.imageResource = imageResource;
        this.title = title;
        this.activityClass = activityClass;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public Class<?> getActivityClass() {
        return activityClass;
    }
}
