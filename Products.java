package se2203b.assignments.adminapp;

import javafx.beans.property.*;


//model class for the entity class Products
public class Products {

    //attributes
    private final StringProperty productName;
    private final StringProperty catalogName;
    private final StringProperty brandName;
    private final StringProperty size;
    private final DoubleProperty price;

    //constructor
    public Products(String name, String catalog, String brand, String size, double price){
        this.productName = new SimpleStringProperty(name);
        this.catalogName = new SimpleStringProperty(catalog);
        this.brandName = new SimpleStringProperty(brand);
        this.size = new SimpleStringProperty(size);
        this.price = new SimpleDoubleProperty(price);
    }

    //set and get methods
    public StringProperty productNameProperty() {
        return productName;
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String name){productName.set(name);}

    public StringProperty catalogNameProperty() {
        return catalogName;
    }

    public String getCatalogName() {
        return catalogName.get();
    }

    public void setCatalogName(String name){catalogName.set(name);}

    public StringProperty brandNameProperty() {return brandName;}

    public String getBrandName() {
        return brandName.get();
    }

    public void setBrandName(String name){
        brandName.set(name);
    }

    public void setSize(String size){
        this.size.set(size);
    }

    public StringProperty sizeProperty() {
        return size;
    }

    public String getSize() {
        return size.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price){
        this.price.set(price);
    }
}
