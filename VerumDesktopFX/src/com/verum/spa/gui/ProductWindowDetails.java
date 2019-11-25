/*=============================================================================
 |       Author:  Erick Ruben Ramos Vazquez
 |       Course:  Spa
 |     Due Date:  10/28/2019
 |  Description:  ProductWindowAdd
 |                
 | Deficiencies:  Falta validar la entrada del precio, llenar tabla
                  llenar comboBox con metodo de marcas
                  Ya consume desde Rest correctamente
                  
 *===========================================================================*/
package com.verum.spa.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.verum.spa.consume.controller.ProductController;
import com.verum.spa.model.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class ProductWindowDetails implements Initializable {

    @FXML
    private JFXTextField txtProdName;

    @FXML
    private JFXTextField txtProdPrice;

    @FXML
    private ComboBox<?> cmbProdBrand;

    @FXML
    private JFXButton btnAddProduct;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private TreeTableView<Product> table;

    ProductController proCtrl;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    RequiredFieldValidator validator = new RequiredFieldValidator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        addingListeners();
//        validations();
//        addTable();
//        proCtrl = new ProductController();
    }

    public void addingListeners() {
        alert.setTitle("Mensaje");

        btnAddProduct.setOnAction((event) -> {
            Platform.runLater(() -> {
                String prodName = txtProdName.getText().trim();
//            String prodBrand = cmbProdBrand.getValue().toString();
                String prodPrice = txtProdPrice.getText().trim();

                if (prodName == null || /*prodBrand == null ||*/ prodPrice == null) {
                    alert.setHeaderText("Resultado de operacion:");
                    alert.setContentText("Todos los campos deben estar llenos");
                    alert.showAndWait();
                } else {
                    String resutl = proCtrl.addProductController(prodName, "AquiVaElCombo",
                            Double.parseDouble(prodPrice));
                    alert.setHeaderText("Resultado de operacion:");
                    alert.setContentText(resutl);
                    alert.showAndWait();
                    txtProdName.setText("");
                    txtProdPrice.setText("");
                }
            });

        });

        btnCancel.setOnAction((event) -> {
            txtProdName.setText("");
            txtProdPrice.setText("");
        });

    }

    public void validations() {
        //Validations
        txtProdName.getValidators().add(validator);
        validator.setMessage("Es necesario agregar un nombre de producto");
        txtProdPrice.getValidators().add(validator);
        validator.setMessage("Es necesario agregar un precio de producto");

        txtProdName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    txtProdName.validate();
                }
            }
        });

        txtProdPrice.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    txtProdPrice.validate();
                }
            }
        });
    }

    public void addTable() {

        Product pro = new Product();
        pro.setProdId(1);
        pro.setProdName("Nombre");
        pro.setBrand("brand");
        pro.setUseCost(10.5);
        pro.setProdStatus(1);

        TreeTableColumn<Product, Integer> idPro = new TreeTableColumn<>("ID");
        TreeTableColumn<Product, String> nombre = new TreeTableColumn<>("Nombre");
        TreeTableColumn<Product, String> marca = new TreeTableColumn<>("Marca");
        TreeTableColumn<Product, Double> precio = new TreeTableColumn<>("Precio");
        TreeTableColumn<Product, Integer> estatus = new TreeTableColumn<>("Estatus");
        table.getColumns().addAll(idPro, nombre, marca, precio, estatus);

        TreeItem<Product> itm1 = new TreeItem<>(pro);
        TreeItem<Product> itm11 = new TreeItem<>(pro);
        TreeItem<Product> itm12 = new TreeItem<>(pro);
//        itm1.getChildren().addAll(itm11,itm12);
        table.setRoot(itm1);

    }

}
