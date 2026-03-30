package com.example.reproject;

public class Item
{
    private int avatar;
    private String name;
    private double rating;
    private double calo;
    private int protein;
    private int carbs;
    private int fats;
    private boolean favorite = false;
    private boolean hot;

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setCalo(double calo) {
        this.calo = calo;
    }

    public double getCalo() {
        return calo;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getProtein() {
        return protein;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getCarbs() {
        return carbs;
    }
    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getFats() {
        return fats;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite){
        this.favorite = favorite;
    }

    public void sethot(boolean hot) {
        this.hot = hot;
    }
    public boolean isHot() {
        return hot;
}

    public Item(int avatar, String name, double rating, double calo, int protein, int carbs, int fats, boolean hot)
    {
        setAvatar(avatar);
        setName(name);
        setRating(rating);
        setCalo(calo);
        setProtein(protein);
        setCarbs(carbs);
        setFats(fats);
        setFavorite(favorite);
        sethot(hot);
    }
}
