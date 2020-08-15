package com.example.bookstore;

public class Book {

    public final String image;
    public final String title;
    public final String subtitle;
    public final String price;
    public final String url;

    public Book(String image, String title, String subtitle, String price, String url) {
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.price = price;
        this.url = url;
    }


    String getImage() {
        return image;
    }

    String getTitle() {
        return title;
    }

    String  getSubtitle(){
        return subtitle;
    }
    String getPrice(){
        return price;

    }

    public String getUrl() {
        return url;
    }
}

