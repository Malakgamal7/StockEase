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

public class ProductListController implements Initializable {

    private ProductAdapter productAdapter;

    @FXML
    private TableView<Products> tableView;

    //columns in the fxml
    @FXML
    private TableColumn<Products, String> productNameCol;
    @FXML
    private TableColumn<Products, String> catalogNameCol;
    @FXML
    private TableColumn<Products,String> brandName;
    @FXML
    private TableColumn<Products,String> size;
    @FXML
    private TableColumn<Products, Double> priceCol;

    final ObservableList<Products> data = FXCollections.observableArrayList();

    public void setModel(ProductAdapter products) {
        productAdapter = products;
        buildData();
    }

    //gets all products
    @FXML
    public void buildData() {
        try {
            data.addAll(productAdapter.getProductList());
        } catch (SQLException ex) {
        }
    }

    //sets the columns with the values from each product
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        productNameCol.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        catalogNameCol.setCellValueFactory(cellData -> cellData.getValue().catalogNameProperty());
        brandName.setCellValueFactory(cellData -> cellData.getValue().brandNameProperty());
        size.setCellValueFactory(cellData -> cellData.getValue().sizeProperty());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        tableView.setItems(data);

    }
}

