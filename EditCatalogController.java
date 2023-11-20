package se2203b.assignments.adminapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Statement;


//controller used when editing a catalog
public class EditCatalogController {


    @FXML
    private Button cancelBu, saveB;

    //the current name of the catalog to be edited
    @FXML
    private Label oldName;

    @FXML
    private TextField newName;

    CatalogAdapter catalogAdapter;

    //sets the model
    public void setModel(CatalogAdapter catalogs, String oldN) {
        this.catalogAdapter = catalogs;
        oldName.setText(oldN);
    }

    //closes stage
    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBu.getScene().getWindow();
        stage.close();
    }

    //saves the new catalog name
    @FXML
    public void saveNewName() throws SQLException {
        String newN = newName.getText();

        //if the new name is not empty
        if(!newN.equals("")) {
            catalogAdapter.updateCatalog(oldName.getText(), newN);
        }

        //close stage
        Stage stage = (Stage) cancelBu.getScene().getWindow();
        stage.close();
    }


}
