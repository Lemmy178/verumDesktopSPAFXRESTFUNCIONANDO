/*=============================================================================
 |       Author:  Erick Ruben Ramos Vazquez
 |       Course:  Spa
 |     Due Date:  10/28/2019
 |  Description:  ProductWindowAdd
 |                
 | Deficiencies:  Idea, permitir manejar nulos, detectarlos en controlador
                  y no acualizar esos en BD para no generar NULLS

                  
 *===========================================================================*/
package com.verum.spa.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.verum.spa.consume.controller.ProductController;
import com.verum.spa.model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductWindowModify implements Initializable {

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private CheckBox checkUnable;
    @FXML
    private JFXTextField txtProdName;
    @FXML
    private JFXTextField txtProdPrice;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXComboBox<String> cmbBrand;
    @FXML
    private JFXComboBox<String> cmbEstatus;
    @FXML
    private TableView<Product> tblProduct;
    @FXML
    private TableColumn<Product, Integer> columnProductID;
    @FXML
    private TableColumn<Product, String> columnProductName;
    @FXML
    private TableColumn<Product, String> columnProductBrand;
    @FXML
    private TableColumn<Product, Double> columnProductPrice;
    @FXML
    private TableColumn<Product, Integer> columnProductStatus;

    private ProductController proCtrl = new ProductController();

    private ObservableList<Product> masterData = FXCollections.observableArrayList();

    private ArrayList<Product> productData = new ArrayList<>();
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public ProductWindowModify() {
        addValues();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        creatingTables();
        validations();
        addingListeners();

    }

    /* <------------------------LISTENERS---------------------------------------------------------------------->*/
    public void addingListeners() {
        //Accept Button 
        btnNew.setOnAction((event) -> {
            try {
                String prodName = txtProdName.getText().trim();
                String prodBrand = cmbBrand.getValue().toString();
                double proPrice = Double.parseDouble(txtProdPrice.getText());
                alert.setHeaderText("Alerta:");
                if (!ProductController.emptyFieldsValidation(prodName, prodBrand, proPrice)) {
                    System.out.println("Nulo");
                    alert.setContentText("Todos los campos deben estar completados");
                    alert.showAndWait();
                } else {
                    Platform.runLater(() -> {
                        String response = ProductController.addProductController(prodName, prodBrand, proPrice);
                        alert.setContentText(response);
                        alert.showAndWait();
                        txtProdName.setText("");
                        txtProdPrice.setText("");
                    });
                }
            } catch (Exception e) {
                alert.setContentText("Todos los campos deben estar completados");
                alert.showAndWait();
                e.printStackTrace();

            }
        });

        btnSave.setOnAction((event) -> {
            try {
                Product pro = (Product) tblProduct.getSelectionModel().selectedItemProperty().getValue();
                String prodName = txtProdName.getText().trim();
                String prodBrand = cmbBrand.getValue().toString();
                double proPrice = Double.parseDouble(txtProdPrice.getText());
                String prodStatus = cmbEstatus.getValue().toString();
                alert.setHeaderText("Alerta:");
                if (!ProductController.emptyFieldsValidation(prodName, prodBrand, proPrice)) {
                    System.out.println("Nulo");
                    alert.setContentText("Todos los campos deben estar completados");
                    alert.showAndWait();
                } else {
                    Platform.runLater(() -> {
                        if (prodStatus.equals("Activo")) {
                            pro.setProdStatus(1);
                        } else {
                            pro.setProdStatus(2);
                        }
                        String response = ProductController.modifyProductController(prodName, prodBrand, proPrice, pro.getProdStatus(), pro.getProdId());
                        alert.setContentText(response);
                        alert.showAndWait();
                        txtProdName.setText("");
                        txtProdPrice.setText("");
                    });
                }
            } catch (Exception e) {
                alert.setContentText("Todos los campos deben estar completados");
                alert.showAndWait();
                e.printStackTrace();

            }
        });
        btnDelete.setOnAction((event) -> {
            Product pro = (Product) tblProduct.getSelectionModel().selectedItemProperty().getValue();
            Platform.runLater(() -> {
                String response = ProductController.logicalDelteController(pro.getProdId());
                alert.setContentText(response);
                alert.showAndWait();
                txtProdName.setText("");
                txtProdPrice.setText("");

            });

        });

        btnUpdate.setOnAction((event) -> {
            addValues();
            creatingTables();
        });

//        Selected Item
        tblProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product product) {
                txtProdName.setText(product.getProdName());
                txtProdPrice.setText(String.valueOf(product.getUseCost()));
                cmbBrand.setValue(product.getBrand());
                if (product.getProdStatus() == 2) {
                    cmbEstatus.setValue("Inactivo");
                } else {
                    cmbEstatus.setValue("Activo");
                }
                btnNew.setDisable(true);
                btnSave.setDisable(false);
                btnDelete.setDisable(false);
            }
        });

    }

    /* <------------------------VALIDATIONS---------------------------------------------------------------------->*/
    public void validations() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Campo vacio");

        txtProdName.getValidators().add(validator);
        txtProdPrice.getValidators().add(validator);
        cmbBrand.getValidators().add(validator);

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

    /* <------------------------ADDING VALUES---------------------------------------------------------------------->*/
    public void addValues() {
        Platform.runLater(() -> {
            try {
                productData = proCtrl.productList();
                initializeLogic(productData);
                if (productData != null) {
                    for (Product product : productData) {
                        masterData.add(product);
                    }
                }
            } catch (IOException ex) {
                alert.setHeaderText("Error:");
                alert.setContentText("Lista productos no disponible");
                alert.showAndWait();
            }
        });

    }

    /* <------------------------ADDING CELL VALUES---------------------------------------------------------------------->*/
    public void creatingTables() {
        columnProductID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("ProdId"));
        columnProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("ProdName"));
        columnProductBrand.setCellValueFactory(new PropertyValueFactory<Product, String>("Brand"));
        columnProductStatus.setCellValueFactory(new PropertyValueFactory<Product, Integer>("ProdStatus"));
        columnProductPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("UseCost"));
        tblProduct.setItems(masterData);
    }

    public void initializeLogic(ArrayList<Product> productData) {
        btnNew.setDisable(false);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);
        LinkedHashSet<String> uniqueBrand = new LinkedHashSet<String>();
        productData.forEach((product) -> {
            uniqueBrand.add(product.getBrand());
        });
        uniqueBrand.forEach((brand) -> {
            cmbBrand.getItems().add(brand);
        });

        //Agregando Estatus
        cmbEstatus.getItems().add("Activo");
        cmbEstatus.getItems().add("Inactivo");

        //Evitando ediciones
        cmbBrand.setEditable(false);
        cmbEstatus.setEditable(false);
    }

}
