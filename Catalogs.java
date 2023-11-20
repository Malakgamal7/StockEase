package se2203b.assignments.adminapp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


//model class for the entity class Catalogs
public class Catalogs {


    //attributes
    private final StringProperty catalogName;
    private final IntegerProperty numberOfCategories;
    private final IntegerProperty numberOfProducts;


    //constructor
    public Catalogs(String name, int products){
        this.catalogName = new SimpleStringProperty(name);
        this.numberOfCategories = new SimpleIntegerProperty(0);
        this.numberOfProducts = new SimpleIntegerProperty(products);
    }


    //set and get methods
    public StringProperty catalogNameProperty() {
        return catalogName;
    }

    public IntegerProperty productsProperty() {
        return numberOfProducts;
    }

    public String getCatalogName() {
        return catalogName.get();
    }

    public int getProducts() {
        return numberOfProducts.get();
    }

    public void setCatalogName(String name){
        catalogName.set(name);
    }

    public void setProducts(int products){
        this.numberOfProducts.set(products);
    }

    public int getNumberOfCategories() {
        return numberOfCategories.get();
    }

    public IntegerProperty numberOfCategoriesProperty() {
        return numberOfCategories;
    }

    public void setNumberOfCategories(int i){
        numberOfCategories.set(i);
    }
}
