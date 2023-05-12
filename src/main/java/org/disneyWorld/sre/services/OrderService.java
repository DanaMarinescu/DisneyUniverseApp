package org.disneyWorld.sre.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.disneyWorld.sre.model.Order;

import static org.disneyWorld.sre.services.FileSystemService.getPathToFile;
public class OrderService {
    private static ObjectRepository<Order>orderRepository;

    public static void initDatabase(String publishingHouse) {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile(publishingHouse+"orders.db").toFile())
                .openOrCreate("test", "test");

        orderRepository = database.getRepository(Order.class);
    }
    public static void close(){
        orderRepository.close();
    }

    public static boolean isClosed(){
        if(orderRepository!=null)
            return orderRepository.isClosed();
        else return true;
    }
    public static void addOrder(String username, Character characterOrdered, String status) {
        characterOrdered.setStock(characterOrdered.getStock()-1);
        orderRepository.insert(new Order(characterOrdered.getName(),characterOrdered.getAge(),characterOrdered.getPrice(),status,characterOrdered.getStock(),username));
    }

    public static void editStatus(Order order, String status){
        orderRepository.remove(order);
        orderRepository.insert(new Order(order.getName(),order.getAge(),order.getPrice(),status, order.getStock(),order.getUser()));
    }

    public static void editPrice(Order order, float price){
        orderRepository.remove(order);
        orderRepository.insert(new Order(order.getName(),order.getAge(),price,order.getStatus(),order.getStock(),order.getUser()));
    }

    public static void clearDatabase(){
        orderRepository.remove(ObjectFilters.ALL);
    }

    public static ObservableList<Order> getCurrentOrders(){
        ObservableList ordersList= FXCollections.observableArrayList();
        ordersList.removeAll();
        for (Order order:orderRepository.find()){
            if(order.getStatus().equals("Placed") || order.getStatus().equals("Rejected"))
                ordersList.add(order);
        }
        return ordersList;
    }

    public static ObservableList<Order> getPastOrders(){
        ObservableList ordersList= FXCollections.observableArrayList();
        ordersList.removeAll();
        for (Order order:orderRepository.find()){
            if(order.getStatus().equals("Delivered"))
                ordersList.add(order);
        }
        return ordersList;
    }

    public static ObservableList<Order> getClientOrders(String username){
        ObservableList ordersList= FXCollections.observableArrayList();
        ordersList.removeAll();
        for (Order order:orderRepository.find()){
            if(order.getUser().equals(username))
                ordersList.add(order);
        }
        return ordersList;
    }
}
