package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditProductController implements Initializable {

    @FXML
    Button cB;

    @FXML
    TextField newName, newBrand, newSize, newPrice;


    @FXML
    ComboBox catalog;

    //label with the present name of the product
    @FXML
    Label oldName;

    final ObservableList<String> catalogs = FXCollections.observableArrayList();

    ProductAdapter productAdapter;
    CatalogAdapter catalogAdapter;
    //product to be edited
    String productName;


    public void setModel(ProductAdapter productAdapter,CatalogAdapter catalogAdapter, String s) {
        this.productAdapter = productAdapter;
        this.catalogAdapter = catalogAdapter;
        this.productName = s;
        buildCombo();
        oldName.setText(productName);
    }

    private void buildCombo() {
        try { //getting all the catalog names for the comboBox
            catalogs.addAll(catalogAdapter.getCatalogNames());
        } catch (SQLException ex) {
        }
    }

    @FXML
    public void cancel() {
        Stage stage = (Stage) cB.getScene().getWindow();
        stage.close();
    }


    //saves new product information
    @FXML
    public void saveNew() throws SQLException {
        String newNameText = newName.getText();
        String newCatalog = "";
        if(catalog.getValue() != null) {
           newCatalog = catalog.getValue().toString();
        }
        String newBrandText = newBrand.getText();
        String newSizeText = newSize.getText();
        double newPriceText = 0.0;
        if(!newPrice.getText().equals("")) {
            newPriceText = Double.parseDouble(newPrice.getText());
        }

        //updates product with inputted values
        productAdapter.updateProduct(productName,newNameText,newCatalog,newBrandText,newSizeText,newPriceText);
        Stage stage = (Stage) cB.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        catalog.setItems(catalogs);
    }
}
