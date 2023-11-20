package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {

    @FXML
    Button cBtn;

    @FXML
    Button sBtn;

    @FXML
    TextField productName,brand, size, price;

    @FXML
    ComboBox catalog;

    private CatalogAdapter catalogAdapter;
    private ProductAdapter productAdapter;

    final ObservableList<String> catalogs = FXCollections.observableArrayList();

    public void setModel(ProductAdapter productAdapter,CatalogAdapter catalog) {
        this.productAdapter = productAdapter;
        catalogAdapter = catalog;
        buildCombo();
    }


    //closes stage
    @FXML
    public void cancel() {
        Stage stage = (Stage) cBtn.getScene().getWindow();
        stage.close();
    }

    private void buildCombo() {
        try { //getting all the catalog names for the comboBox
            catalogs.addAll(catalogAdapter.getCatalogNames());
        } catch (SQLException ex) {
        }
    }


    @FXML
    public void save() throws SQLException {
        //all fields must be filled out
        if (!productName.getText().equals("")  && !catalog.getValue().toString().equals("") && !brand.getText().equals("") && !size.getText().equals("") && Double.parseDouble(price.getText()) != 0) {
            try {
                //new product added
                productAdapter.insertProduct(productName.getText(), catalog.getValue().toString(), brand.getText(), size.getText(), Double.parseDouble(price.getText()));
            } catch (SQLException ex) {
            }
        }
            Stage stage = (Stage) cBtn.getScene().getWindow();
            stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        catalog.setItems(catalogs);

    }
}
