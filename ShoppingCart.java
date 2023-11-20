package se2203b.assignments.adminapp;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

//model class for the entity class ShoppingCart
public class ShoppingCart {

    //attributes
    private final IntegerProperty numOfItems;
    private final DoubleProperty totalCost;

    //constructor
    public ShoppingCart(int items, double cost){
        numOfItems = new SimpleIntegerProperty(items);
        totalCost = new SimpleDoubleProperty(cost);
    }

    //get and set methods
    public int getNumOfItems() {
        return numOfItems.get();
    }

    public IntegerProperty numOfItemsProperty() {
        return numOfItems;
    }

    public void setNumOfItems(int i){
        numOfItems.set(i);
    }

    public double getTotalCost() {
        return totalCost.get();
    }

    public DoubleProperty totalCostProperty() {
        return totalCost;
    }

    public void setTotalCost(double cost){
        totalCost.set(cost);
    }
}
