package org.disneyWorld.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class DisneyCharacter {
    @Id
    private String name;
    private int age;
    private String category;
    private float price;
    private int stock;

    public DisneyCharacter() {
    }

    public DisneyCharacter(String name, int age, String category, float price, int stock){
        this.name = name;
        this.age = age;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }

    public int getAge() {return age;}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() { return price; }

    public int getStock() { return stock; }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
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
        DisneyCharacter disney = (DisneyCharacter) obj;
        return age == disney.age && Float.compare(disney.price, price) == 0 && stock == disney.stock && Objects.equals(name, disney.name) && Objects.equals(category, disney.category);
    }

    public int hashCode() {
        return Objects.hash(name, age, category, price, stock);
    }
}

