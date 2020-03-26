package com.example.todaysquotes.card;

public class CardItem {

    private String Name;//FireBase check
    private String Quote;//FireBase check

    public CardItem(){}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getQuote() {
        return Quote;
    }

    public void setQuote(String quote) {
        Quote = quote;
    }

}