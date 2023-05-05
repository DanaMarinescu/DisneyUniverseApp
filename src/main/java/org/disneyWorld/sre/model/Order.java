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
    private String name;
    private int age;
    private float price;
    private String status;
    private int stock;
    private String user;

    public Order(){}

    public Order(String name, int age, float price, String status, int stock, String user) {
        this.name = name;
        this.age = age;
        this.price = price;
        this.status = status;
        this.stock = stock;
        this.user = user;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        return age == order.age && Float.compare(order.price, price) == 0 && stock == order.stock && Objects.equals(name, order.name) && Objects.equals(status, order.status) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,age, price, status, stock, user);
    }
}
