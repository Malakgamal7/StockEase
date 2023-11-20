package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ProductAdapter {
    Connection connection;
    CatalogAdapter catalogAdapter;
    public ProductAdapter(Connection conn, CatalogAdapter catalogAdapter) throws SQLException {
        connection = conn;
        this.catalogAdapter = catalogAdapter;
        Statement stmt = connection.createStatement();
        try {
            // This will throw an exception if the tables do not exist
            stmt.execute("SELECT * FROM Products");
        } catch (SQLException ex) {
            //if table doesnt exist it creates it
            stmt.execute("CREATE TABLE Products ("
                    + "productName CHAR(15) NOT NULL PRIMARY KEY, "
                    + "catalogName CHAR(15) ,"
                    + "brand CHAR(15) ,"
                    + "size CHAR(3),"
                    + "price DOUBLE )");

            //populate table
            populateSampls();
        }
    }

    private void populateSampls() throws SQLException {
        // Add some Products
        this.insertProduct("Pink T-shirt", "Tops", "Nike", "M", 29.99);
        this.insertProduct("Flare Jeans", "Pants", "Levis", "XL", 39.99);
        this.insertProduct("Leather Jacket", "Jackets", "Zara", "L", 84.99);
        this.insertProduct("Grey Sweatpants", "Pants", "Roots", "S", 74.99);
    }

    //insets product in data base
    public void insertProduct(String name, String catalogName, String brand, String size, double price) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Products (productName, catalogName, brand, size, price) VALUES ('" + name + "','" + catalogName + "','" +brand+"','" +size+"',"+price+")");
        catalogAdapter.addProduct(catalogName);
    }

    //return list of products
    public ObservableList<Products> getProductList() throws  SQLException{
            ObservableList<Products> list = FXCollections.observableArrayList();
            ResultSet rs;
            // Create a Statement object
        Statement stmt = connection.createStatement();

            // Create a string with a SELECT statement
            String sqlStatement = "SELECT * FROM Products";

            // Execute the statement and return the result
            rs = stmt.executeQuery(sqlStatement);
            while (rs.next()) {
                //creates a product with values from the table and adds it to list
                list.add(new Products(rs.getString("productName"),
                        rs.getString("catalogName"),
                        rs.getString("brand"),
                        rs.getString("size"),
                        rs.getDouble("price")));
            }
            return list;
        }

        //return list of product names
    public ObservableList<String> getProductsNamesList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Products");


        while(result.next()) {
            String s = result.getString("productName");
            list.add(s);
        }
        return list;
    }

    //deletes product
    public void delete(String name) throws SQLException {

        Statement stmt = connection.createStatement();
        //searches for catalog of product to decrement product count
        ResultSet result = stmt.executeQuery("SELECT catalogName FROM Products WHERE productName = '"+name+"'");
        String s = "";
        if(result.next()){
            s = result.getString(1);
        }
        //delete from catalog
        catalogAdapter.deleteProduct(s);
        //delete from products
        stmt.executeUpdate("DELETE FROM Products WHERE ProductName = '" + name + "'");

        }

        //updates product
    public void updateProduct(String productName, String newName, String newCatalog, String newBrand, String newSize, Double newPrice) throws SQLException {
        Statement stmt = connection.createStatement();

        //only updates attribute if it is not  null
        if(!newName.equals("")) {
            stmt.executeUpdate("UPDATE  Products SET productName = '" + newName + "' WHERE productName = '" + productName + "'");
        }

        //changing catalog
        if(!newCatalog.equals("")){
            //to update product count in old catalog
            ResultSet result = stmt.executeQuery("SELECT catalogName FROM Products WHERE productName = '"+productName+"'");
            String s = "";
            if(result.next()){
                s = result.getString(1);
            }
            catalogAdapter.deleteProduct(s);
            //update product count in new catalog
            stmt.executeUpdate("UPDATE  Products SET catalogName = '" + newCatalog + "' WHERE productName = '" + productName + "'");
            catalogAdapter.addProduct(newCatalog);
        }

        if(!newBrand.equals("")){
            stmt.executeUpdate("UPDATE  Products SET brand = '" + newBrand + "' WHERE productName = '" + productName + "'");
        }

        if(!newSize.equals("")){
            stmt.executeUpdate("UPDATE  Products SET size = '" + newSize + "' WHERE productName = '" + productName + "'");
        }

        if(newPrice != 0.00){
            stmt.executeUpdate("UPDATE  Products SET price = '" + newPrice + "' WHERE productName = '" + productName + "'");
        }
    }
    }
