package se2203b.assignments.adminapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCatalogController implements Initializable {
    @FXML
    Button cancelBtn;

    @FXML
    Button saveBtn;

    @FXML
    TextField catalogName;

    private CatalogAdapter catalogAdapter;

    public void setModel(CatalogAdapter catalog) {
        catalogAdapter = catalog;
    }


    //closes stage
    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }


    //saves new catalog
    @FXML
    public void save() {
        try {

            //inserts new catalog with name from text field
            catalogAdapter.insertCatalog(catalogName.getText());
        } catch (SQLException ex) {
        }
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
