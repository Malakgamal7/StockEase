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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EditDeleteCatalogController implements Initializable {

    private Connection conn;



    @FXML
    private ComboBox catalogCombo;

    private CatalogAdapter catalogs;

    @FXML
    private Button cancelButton, saveB, editB;

    @FXML
    private Label error;

    //data variable to populate ComboBox
    final ObservableList<String> data = FXCollections.observableArrayList();

    private CatalogAdapter catalogAdapter;

    public void setModel(CatalogAdapter catalogAdapter){
        this.catalogAdapter = catalogAdapter;
        buildCombo();
    }

    //closes stage
    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void buildCombo() {
        try { //getting all the catalog names for the comboBox
            data.addAll(catalogAdapter.getCatalogNames());
        } catch (SQLException ex) {
        }
    }

    @FXML
    public void delete() throws SQLException {
        //getting the catalog that is to be deleted
        String s = catalogCombo.getValue().toString();

        //getting the number of products in the catalog that is to be deleted
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT Products FROM Catalogs WHERE CatalogName = '"+s+"'");
        int num = 0;
        if(rs.next()){
            num = rs.getInt(1);
        }

        //deletes only if there are no products in the catalog
        if(num == 0) {
            catalogAdapter.delete(s);
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        } else{
            error.setText("Cannot delete catalog");
        }

    }


    //loads a new fxml file to edit the catalog
    @FXML
    public void openEdit() throws IOException, SQLException {

        catalogs = new CatalogAdapter(MainApplicationController.conn);
        String s = catalogCombo.getValue().toString();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editCatalog.fxml"));
        Parent addCatalog = (Parent) fxmlLoader.load();
        EditCatalogController editCatalogController = (EditCatalogController) fxmlLoader.getController();
        editCatalogController.setModel(catalogs,s);
        Scene scene = new Scene(addCatalog);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.show();
        Stage st = (Stage) cancelButton.getScene().getWindow();
        st.close();

    }


    //sets connection and builds combobox
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        catalogCombo.setItems(data);
        conn = MainApplicationController.conn;
    }
}
