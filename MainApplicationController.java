package se2203b.assignments.adminapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author Abdelkader Ouda
 */
public class MainApplicationController implements Initializable {

    @FXML
    private MenuBar mainMenu;
    private CatalogAdapter catalogs;
    private ProductAdapter products;
    public static Connection conn;



    public void showAbout() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("About-view.fxml"));
        // create the root node
        Parent About =  fxmlLoader.load();
        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(About));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    //used to show catalog list
    @FXML
    public void showCatalogs() throws Exception {
        // create Teams model
        catalogs = new CatalogAdapter(conn);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CatalogList.fxml"));
        Parent standings = (Parent) fxmlLoader.load();
        CatalogListController catalogListController = (CatalogListController) fxmlLoader.getController();
        catalogListController.setModel(catalogs);

        Scene scene = new Scene(standings);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Catalog List");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.show();
    }


    //used to show product list
    @FXML
    public void showProducts() throws Exception {
        // create Teams model
        catalogs = new CatalogAdapter(conn);
        products = new ProductAdapter(conn,catalogs);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProductList.fxml"));
        Parent standings = (Parent) fxmlLoader.load();
        ProductListController productListController = (ProductListController) fxmlLoader.getController();
        productListController.setModel(products);

        Scene scene = new Scene(standings);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Product List");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.show();
    }

    //used when adding a catalog
    @FXML
    public void addCatalog() throws Exception {

        catalogs = new CatalogAdapter(conn);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCatalog.fxml"));
        Parent addCatalog = (Parent) fxmlLoader.load();
        AddCatalogController addCatalogController = (AddCatalogController) fxmlLoader.getController();
        addCatalogController.setModel(catalogs);

        Scene scene = new Scene(addCatalog);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Add New Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.show();
    }

    //used when adding a product
    @FXML
    public void addProduct() throws Exception {


        catalogs = new CatalogAdapter(conn);
        products = new ProductAdapter(conn,catalogs);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        Parent addProduct = (Parent) fxmlLoader.load();
        AddProductController addProductController = (AddProductController) fxmlLoader.getController();
        addProductController.setModel(products,catalogs);

        Scene scene = new Scene(addProduct);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add New Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.show();
    }

    //used when editing or deleting a catalog
    @FXML
    public void editDeleteCatalog() throws Exception {
        catalogs = new CatalogAdapter(conn);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditDeleteCatalog.fxml"));
        Parent editDeleteCatalog = (Parent) fxmlLoader.load();
        EditDeleteCatalogController editDeleteCatalogController = (EditDeleteCatalogController)fxmlLoader.getController();
        editDeleteCatalogController.setModel(catalogs);

        Scene scene = new Scene(editDeleteCatalog);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Edit/Delete Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.show();
    }

    //used when edittig or deleting a product
    @FXML
    public void editDeleteProduct() throws Exception {
        catalogs = new CatalogAdapter(conn);
        products = new ProductAdapter(conn,catalogs);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditDeleteProducts.fxml"));
        Parent editDeleteProduct = (Parent) fxmlLoader.load();
        EditDeleteProductsController editDeleteProductsController = (EditDeleteProductsController) fxmlLoader.getController();
        editDeleteProductsController.setModel(products, catalogs);
        Scene scene = new Scene(editDeleteProduct);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit/Delete Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.show();
    }


    public void exit() {
        // Get current stage reference
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:CatalogDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
        }

    }

}
