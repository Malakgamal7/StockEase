package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CatalogAdapter {

    Connection connection;

    //constructor
    public CatalogAdapter(Connection conn) throws SQLException {
            connection = conn;
            Statement stmt = connection.createStatement();
                try {
                    // This will throw an exception if the table does not exist
                    stmt.execute("SELECT * FROM Catalogs");

                } catch (SQLException ex) {
                    //if table does not exist it is created
                    stmt.execute("CREATE TABLE Catalogs ("
                            + "CatalogName CHAR(15) NOT NULL PRIMARY KEY, "
                            + "Products INT)");

                    //populate table
                    populateSampls();
                }
            }


    private void populateSampls() throws SQLException {
        // Add some catalogs
        this.insertCatalog("Dresses");
        this.insertCatalog("Activeware");
        this.insertCatalog("Skirts");
        this.insertCatalog("Swimware");
        this.insertCatalog("Pants");
        this.insertCatalog("Jackets");
        this.insertCatalog("Tops");
        this.insertCatalog("Sweaters");
    }


    //inserts a catalog into table
    public void insertCatalog(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Catalogs (CatalogName, Products) VALUES ('" + name + "', 0)");
    }

    //retruns a list of all catalogs in the table
    public ObservableList<Catalogs> getCatalogList() throws SQLException {
        ObservableList<Catalogs> list = FXCollections.observableArrayList();
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM Catalogs";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        //creates a catalog object with the name and number of products found in the table
        //then adds is to the list
        while (rs.next()) {
            list.add(new Catalogs(rs.getString("CatalogName"),
                    rs.getInt("Products")));
        }
        return list;
    }


    //returns the list of catalog names
    //used to build combo boxes
    public ObservableList<String> getCatalogNames() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();

        //getting all the columns from the table
        String s = "SELECT * FROM Catalogs";
        ResultSet rs = stmt.executeQuery(s);
        while (rs.next()) {
            //selecting only the catalog names and adding it to the list
            String st = rs.getString("CatalogName");
            list.add(st);
        }
        return list;
    }

    //deletes a catalog
    public void delete(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM Catalogs WHERE CatalogName = '" + name + "'");
    }

    //updates catalog name in table
    public void updateCatalog(String oldName, String newName) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE  Catalogs SET CatalogName = '"+newName+"' WHERE CatalogName = '"+oldName+"'");
    }

    //increments product count of the specified catalog
    public void addProduct(String catalogName) throws SQLException {
       //getting current product count
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Products FROM Catalogs WHERE CatalogName = '"+catalogName+"'");
        int num = 0;
        if(rs.next()){
            num = rs.getInt(1);
        }
        //incrementing product count
        num ++;
        //setting the new product count
        stmt.executeUpdate("UPDATE  Catalogs SET Products = "+num+" WHERE CatalogName = '"+catalogName+"'");
    }

    //decrements product count of the specified catalog
    public void deleteProduct(String catalogName) throws SQLException {
        //getting current product count
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Products FROM Catalogs WHERE CatalogName = '"+catalogName+"'");
        int num = 0;
        if(rs.next()){
            num = rs.getInt(1);
        }
        //decrementing product count
        num --;

        //setting the new product count
        stmt.executeUpdate("UPDATE  Catalogs SET Products = "+num+" WHERE CatalogName = '"+catalogName+"'");
    }
}

