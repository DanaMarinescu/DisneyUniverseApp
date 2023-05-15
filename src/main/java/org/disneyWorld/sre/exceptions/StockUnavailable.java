package org.disneyWorld.sre.exceptions;

public class StockUnavailable extends Exception{
    private int stock;
    public StockUnavailable(int stock){
        super(String.format("Stock unavailable!"));
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }
}


