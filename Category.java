package se2203b.assignments.adminapp;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//model class for category entity class
public class Category {

    //attributes
    private final StringProperty name;
    private final IntegerProperty numOfProducts;

    //constructor
    public Category(String n, int num){
        name = new SimpleStringProperty(n);
        numOfProducts = new SimpleIntegerProperty(num);
    }

    //get and set methods

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String n){
        name.set(n);
    }

    public int getNumOfProducts() {
        return numOfProducts.get();
    }

    public IntegerProperty numOfProductsProperty() {
        return numOfProducts;
    }

    public void setNumOfProducts(int i){
        numOfProducts.set(i);
    }
}
