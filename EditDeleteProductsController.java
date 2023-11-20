package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditDeleteProductsController implements Initializable {

    private Connection conn;

    @FXML
    private ComboBox productCombo;

    private ProductAdapter productAdapter;

    private CatalogAdapter catalogAdapter;

    @FXML
    private Button cBu;

    //data variable to populate ComboBox
    final ObservableList<String> products = FXCollections.observableArrayList();


    public void setModel(ProductAdapter productAdapter, CatalogAdapter catalogAdapter){
        this.productAdapter = productAdapter;
        this.catalogAdapter = catalogAdapter;
        buildCombo();
    }

    //closes stage
    @FXML
    public void cancel() {
        Stage stage = (Stage) cBu.getScene().getWindow();
        stage.close();
    }


    private void buildCombo() {
        try { //getting all the product names for the comboBox
            products.addAll(productAdapter.getProductsNamesList());
        } catch (SQLException ex) {
        }
    }

    //deletes product
    @FXML
    public void delete() throws SQLException {
        String s = productCombo.getValue().toString();
        productAdapter.delete(s);
        Stage stage = (Stage) cBu.getScene().getWindow();
        stage.close();
    }

    //loads new fxml file if a product is to be edited
    @FXML
    public void openEdit() throws IOException, SQLException {
        catalogAdapter = new CatalogAdapter(conn);
        productAdapter = new ProductAdapter(MainApplicationController.conn, catalogAdapter);
        String s = productCombo.getValue().toString();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditProduct.fxml"));
        Parent editProduct = (Parent) fxmlLoader.load();
        EditProductController editProductController = (EditProductController) fxmlLoader.getController();
        editProductController.setModel(productAdapter,catalogAdapter,s);
        Scene scene = new Scene(editProduct);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.show();
        Stage st = (Stage) cBu.getScene().getWindow();
        st.close();
    }

    //sets connection and builds combo box
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productCombo.setItems(products);
        conn = MainApplicationController.conn;
    }
}
