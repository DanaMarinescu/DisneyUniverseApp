package org.disneyWorld.sre.model;


import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.util.Objects;

@Indices({
        @Index(value = "user",type= IndexType.NonUnique)
})
public class Order {
    @Id
    private String characterName;
    private String ageCategoryName;
    private float price;
    private String status;
    private int stock;
    private String user;

    public Order(){}

    public Order(String characterName, String ageCategoryName, float price, String status, int stock, String user) {
        this.characterName = characterName;
        this.ageCategoryName = ageCategoryName;
        this.price = price;
        this.status = status;
        this.stock = stock;
        this.user = user;
    }


    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getAgeCategoryName() {
        return ageCategoryName;
    }

    public void setAgeCategoryName(String ageCategoryName) {
        this.ageCategoryName = ageCategoryName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return Objects.equals(ageCategoryName, order.ageCategoryName) && Float.compare(order.price, price) == 0 && stock == order.stock && Objects.equals(characterName, order.characterName) && Objects.equals(status, order.status) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterName,ageCategoryName, price, status, stock, user);
    }
}
