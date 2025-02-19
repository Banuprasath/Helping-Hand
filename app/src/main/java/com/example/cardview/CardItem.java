package com.example.cardview;


public class CardItem {
    private int imageRes;
    private String title;

    public CardItem(int imageRes, String title) {
        this.imageRes = imageRes;
        this.title = title;
    }

    public int getImageRes() { return imageRes; }
    public String getTitle() { return title; }
}
