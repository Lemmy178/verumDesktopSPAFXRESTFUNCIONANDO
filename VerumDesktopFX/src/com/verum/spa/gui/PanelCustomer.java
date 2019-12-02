/*=============================================================================
 |       Author:  Edson M.
 |       Course:  Spa
 |     Due Date:  11/6/2019
 |  Description:  CustomerWindowAdd
 |                
 | Deficiencies:  No detected.

                  
 *===========================================================================*/
package com.verum.spa.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.verum.spa.consume.controller.CustomerController;
import com.verum.spa.model.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class PanelCustomer implements Initializable {

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TableColumn<Customer, Integer> columnCustomerID;

    @FXML
    private TableColumn<Customer, Integer> columnCustomerStatus;

    @FXML
    private TableColumn<Customer, String> columnCustomerName;

    @FXML
    private TableColumn<Customer, String> columnCustomerLastName1;

    @FXML
    private TableColumn<Customer, String> columnCustomerLastName2;

    @FXML
    private ImageView imgVUser;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtCusName;

    @FXML
    private JFXTextField txtCusLastName1;

    @FXML
    private JFXTextField txtCusLastName2;

    @FXML
    private JFXTextField txtCusRFC;

    @FXML
    private JFXTextField txtCusPhone;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<String> cmbGenre;

    @FXML
    private Label lblClientNumber;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtCharge;

    @FXML
    private JFXTextField txtCusConName;

    @FXML
    private JFXButton btnNewCustomer;

    @FXML
    private JFXButton btnSaveCustomer;

    @FXML
    private JFXButton btnDeleteCustomer;

    @FXML
    private JFXButton btnUpdate;

    private CustomerController cusCtrl = new CustomerController();

    private ObservableList<Customer> masterData = FXCollections.observableArrayList();

    private ArrayList<Customer> customerData = new ArrayList<>();
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public PanelCustomer() {
        addValues();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validations();
        addingListeners();
        creatingTables();
    }

    public void addingListeners() {
        btnNewCustomer.setOnAction((event) -> {
            try {
                String name = txtCusName.getText().trim();
                String lastName1 = txtCusLastName1.getText().trim();
                String lastName2 = txtCusLastName2.getText().trim();
                String RFC = txtCusRFC.getText().trim();
                String telephone = txtCusPhone.getText().trim();
                String perAddress = txtAddress.getText().trim();
                String email = txtEmail.getText().trim();
                String passw = txtPassword.getText().trim();
                String gender = cmbGenre.getValue().toString();
                String conName = RFC.substring(0, 4).toLowerCase();
                String charge = txtCharge.getText().trim();
                alert.setHeaderText("Alert:");
                if (!CustomerController.emptyFieldsValidation(name, lastName1, lastName2, RFC, telephone, perAddress, gender, email, passw)) {
                    System.out.println("Nulo");
                    alert.setContentText("Todos los campos deben estar completos");
                    alert.showAndWait();
                } else {
                    Platform.runLater(() -> {
                        String response = CustomerController.addCustomerController(name, lastName1, lastName2, RFC, telephone, perAddress, gender, email, passw, conName, charge);
                        alert.setContentText(response);
                        alert.showAndWait();
                        txtCusName.setText("");
                        txtCusLastName1.setText("");
                        txtCusLastName2.setText("");
                        txtCusRFC.setText("");
                        txtCusPhone.setText("");
                        txtAddress.setText("");
                        txtEmail.setText("");
                        txtPassword.setText("");
                        btnNewCustomer.setDisable(false);
                        btnDeleteCustomer.setDisable(true);
                        btnSaveCustomer.setDisable(true);
                        cmbGenre.setValue("");
                    });
                }
            } catch (Exception e) {
                alert.setContentText("Todos los campos deben estar completos");
                alert.showAndWait();
                e.printStackTrace();
            }
        });
        btnSaveCustomer.setOnAction((event) -> {
            try {
                Customer cus = (Customer) tblCustomer.getSelectionModel().selectedItemProperty().getValue();
                String name = txtCusName.getText().trim();
                String lastName1 = txtCusLastName1.getText().trim();
                String lastName2 = txtCusLastName2.getText().trim();
                String RFC = txtCusRFC.getText().trim();
                String telephone = txtCusPhone.getText().trim();
                String perAddress = txtAddress.getText().trim();
                String email = txtEmail.getText().trim();
                String pass = txtPassword.getText().trim();
                String gender = cmbGenre.getValue().toString();
                String charge = txtCharge.getText().trim();
                alert.setHeaderText("Alert:");
                if (!CustomerController.emptyFieldsValidation(name, lastName1, lastName2, RFC, telephone, perAddress, gender, email, pass)) {
                    System.out.println("Nulo");
                    alert.setContentText("Todos los campos deben estar completos");
                    alert.showAndWait();
                } else {
                    Platform.runLater(() -> {
                        //modify
                        String response = CustomerController.modifyCustomerController(name, lastName1, lastName2, RFC, telephone, perAddress, gender, email, pass,
                                charge, cus.getCusStatus(), cus.getCusId(), cus.getConsumer().getConId(), cus.getPerId());
                        alert.setContentText(response);
                        alert.showAndWait();
                        txtCusName.setText("");
                        txtCusLastName1.setText("");
                        txtCusLastName2.setText("");
                        txtCusRFC.setText("");
                        txtCusPhone.setText("");
                        txtAddress.setText("");
                        txtEmail.setText("");
                        txtPassword.setText("");
                        btnNewCustomer.setDisable(false);
                        btnDeleteCustomer.setDisable(true);
                        btnSaveCustomer.setDisable(true);
                        cmbGenre.setValue("");
                    });
                }
            } catch (Exception e) {
                alert.setContentText("Todos los campos deben estar completos");
                alert.showAndWait();
                e.printStackTrace();
            }
        });
        btnDeleteCustomer.setOnAction((event) -> {
            Customer cus = (Customer) tblCustomer.getSelectionModel().selectedItemProperty().getValue();
            Platform.runLater(() -> {
                String response = CustomerController.logicalDelteCustomer(cus.getCusId());
                alert.setContentText(response);
                alert.showAndWait();
                txtCusName.setText("");
                txtCusLastName1.setText("");
                txtCusLastName2.setText("");
                txtCusRFC.setText("");
                txtCusPhone.setText("");
                txtAddress.setText("");
                txtEmail.setText("");
                txtPassword.setText("");
                txtCharge.setText("");
                btnNewCustomer.setDisable(false);
                btnDeleteCustomer.setDisable(true);
                btnSaveCustomer.setDisable(true);
                btnSaveCustomer.setDisable(true);
                cmbGenre.setValue("");
            });
        });
        btnUpdate.setOnAction((event) -> {
            updateTable();
        });
        tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer customer) {

                txtCusName.setText(customer.getFirstName());
                txtCusLastName1.setText(customer.getLastName1());
                txtCusLastName2.setText(customer.getLastName2());
                txtEmail.setText(customer.getEmail());
                txtAddress.setText(customer.getPerAddress());
                lblClientNumber.setText(customer.getUniqueNumber());
                txtCusPhone.setText(customer.getTelephone());
                txtPassword.setText(customer.getConsumer().getPass());
                txtCusRFC.setText(customer.getRfc());
                txtCusRFC.setEditable(false);
                txtCharge.setText(customer.getConsumer().getRole());
                cmbGenre.setValue(customer.getGender());
                btnSaveCustomer.setDisable(false);
                btnDeleteCustomer.setDisable(false);
                btnNewCustomer.setDisable(true);
            }
        });
    }

    public void validations() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Campo Vacío");
        txtCusName.getValidators().add(validator);
        txtCusLastName1.getValidators().add(validator);
        txtCusLastName2.getValidators().add(validator);
        txtCusRFC.getValidators().add(validator);
        txtCusPhone.getValidators().add(validator);
        txtAddress.getValidators().add(validator);
        txtEmail.getValidators().add(validator);
        txtPassword.getValidators().add(validator);
        txtCusName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    txtCusName.validate();
                }
            }
        });
        txtCusLastName1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    txtCusLastName1.validate();
                }
            }
        });
        txtCusLastName2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    txtCusLastName2.validate();
                }
            }
        });
        txtCusRFC.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    txtCusRFC.validate();
                }
            }
        });
        txtCusPhone.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    txtCusPhone.validate();
                }
            }
        });
        txtAddress.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    txtAddress.validate();
                }
            }
        });
        txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    txtEmail.validate();
                }
            }
        });
        txtPassword.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    txtPassword.validate();
                }
            }
        });
    }

    public void addValues() {
        Platform.runLater(() -> {
            try {
                customerData = cusCtrl.customerList();
                initializeLogic();
                if (customerData != null) {
                    for (Customer customer : customerData) {
                        masterData.add(customer);
                    }
                }
            } catch (IOException ioe) {
                alert.setHeaderText("Error: ");
                alert.setContentText("Lista de clientes no disponible");
                alert.showAndWait();
            }
        });
    }

    public void creatingTables() {
        columnCustomerID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CusId"));
        columnCustomerName.setCellValueFactory(new PropertyValueFactory<Customer, String>("FirstName"));
        columnCustomerLastName1.setCellValueFactory(new PropertyValueFactory<Customer, String>("LastName1"));
        columnCustomerLastName2.setCellValueFactory(new PropertyValueFactory<Customer, String>("LastName2"));
        columnCustomerStatus.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CusStatus"));
        tblCustomer.setItems(masterData);
    }

    public void initializeLogic() {
        btnNewCustomer.setDisable(false);
        btnDeleteCustomer.setDisable(true);
        btnSaveCustomer.setDisable(true);

        cmbGenre.getItems().add("M");
        cmbGenre.getItems().add("F");
        cmbGenre.getItems().add("O");
        txtCusConName.setVisible(false);
    }

    public void updateTable() {
        tblCustomer.getItems().clear();
        addValues();
        creatingTables();
    }
}
