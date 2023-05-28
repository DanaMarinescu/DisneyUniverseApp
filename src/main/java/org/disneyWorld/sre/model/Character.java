package org.disneyWorld.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Character {
    @Id
    private String name;
    private String ageCategory;
    private float price;
    private int stock;

    public Character() {
    }

    public Character(String name, String ageCategory, float price, int stock){
        this.name = name;
        this.ageCategory = ageCategory;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }

    public String getAgeCategory() {return ageCategory;}

    public float getPrice() { return price; }

    public int getStock() { return stock; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Character disney = (Character) obj;
        return Objects.equals(ageCategory, disney.ageCategory)&& Float.compare(disney.price, price) == 0 && stock == disney.stock && Objects.equals(name, disney.name);
    }

    public int hashCode() {
        return Objects.hash(name, ageCategory, price, stock);
    }
}

