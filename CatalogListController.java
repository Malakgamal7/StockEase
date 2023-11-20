package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.xml.catalog.Catalog;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CatalogListController implements Initializable {

    private CatalogAdapter catalogAdapter;

    @FXML
    private TableView<Catalogs> tableView;
    @FXML
    private TableColumn<Catalogs, String> catalogNameCol;
    @FXML
    private TableColumn<Catalogs, Integer> productCol;

    final ObservableList<Catalogs> data = FXCollections.observableArrayList();

    public void setModel(CatalogAdapter catalogs) {
        catalogAdapter =catalogs;
        buildData();
    }

    //gets all catalogs in table
    @FXML
    public void buildData() {
        try {
            data.addAll(catalogAdapter.getCatalogList());
        } catch (SQLException ex) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //puts the values in the right columns
        catalogNameCol.setCellValueFactory(cellData -> cellData.getValue().catalogNameProperty());
        productCol.setCellValueFactory(cellData -> cellData.getValue().productsProperty().asObject());
        tableView.setItems(data);

    }
}
