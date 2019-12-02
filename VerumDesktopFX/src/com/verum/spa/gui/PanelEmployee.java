/*=============================================================================
 |       Author:  Edson M.
 |       Course:  Spa
 |     Due Date:  11/6/2019
 |  Description:  ProductWindowAdd
 |                
 | Deficiencies:  No detected.

                  
 *===========================================================================*/
package com.verum.spa.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.verum.spa.consume.controller.EmployeeController;
import com.verum.spa.model.Employee;
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

public class PanelEmployee implements Initializable {

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TableColumn<Employee, Integer> columnEmpID;

    @FXML
    private TableColumn<Employee, String> columnEmpName;

    @FXML
    private TableColumn<Employee, String> columnEmpLastName1;

    @FXML
    private TableColumn<Employee, String> columnEmpLastName2;

    @FXML
    private TableColumn<Employee, String> columnEmpStatus;

    @FXML
    private ImageView imgVUser;

    @FXML
    private JFXTextField txtEmpPos;

    @FXML
    private JFXTextField txtEmpName;

    @FXML
    private JFXTextField txtEmpLastN1;

    @FXML
    private JFXTextField txtEmpLastN2;

    @FXML
    private JFXTextField txtEmpRFC;

    @FXML
    private JFXTextField txtEmpPhone;

    @FXML
    private JFXTextField txtEmpAdd;

    @FXML
    private JFXTextField txtEmpCharge;

    @FXML
    private JFXTextField txtEmpConName;

    @FXML
    private JFXTextField txtEmpPass;

    @FXML
    private JFXComboBox<String> cmbEmpStatus;

    @FXML
    private JFXComboBox<String> cmbEmpGender;

    @FXML
    private Label lblEmpNumber;

    @FXML
    private JFXButton btnNewEmployee;

    @FXML
    private JFXButton btnSaveEmployee;

    @FXML
    private JFXButton btnDeleteEmployee;

    @FXML
    private JFXButton btnUpdate;

    private EmployeeController empCtrl = new EmployeeController();

    private ObservableList<Employee> masterData = FXCollections.observableArrayList();

    private ArrayList<Employee> employeeData = new ArrayList<>();
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public PanelEmployee() {
        addValues();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addingListeners();
        creatingTables();
    }

    public void addingListeners() {
        btnNewEmployee.setOnAction((event) -> {
            try {
                String name = txtEmpName.getText().trim();
                String lastName1 = txtEmpLastN1.getText().trim();
                String lastName2 = txtEmpLastN2.getText().trim();
                String RFC = txtEmpRFC.getText().trim();
                String telephone = txtEmpPhone.getText().trim();
                String perAddress = txtEmpAdd.getText().trim();
                String empPosition = txtEmpPos.getText().trim();
                String gender = cmbEmpGender.getValue().toString();
                String status = cmbEmpStatus.getValue().toString();
                String conName = RFC.substring(0, 4).toLowerCase();
                String charge = txtEmpCharge.getText().trim();
                String pass = txtEmpPass.getText().trim();
                alert.setHeaderText("Alert:");
                if (!EmployeeController.emptyFieldsValidation(name, lastName1, lastName2, RFC, telephone, perAddress, gender, empPosition, conName)) {
                    System.out.println("Nulo");
                    alert.setContentText("Todos los campos deben estar completos");
                    alert.showAndWait();
                } else {
                    Platform.runLater(() -> {
                        String response = EmployeeController.addEmployeeController(name, lastName1, lastName2, RFC, telephone, perAddress, gender, empPosition, status, charge, conName, pass);
                        alert.setContentText(response);
                        alert.showAndWait();
                        txtEmpName.setText("");
                        txtEmpLastN1.setText("");
                        txtEmpLastN2.setText("");
                        txtEmpRFC.setText("");
                        txtEmpPhone.setText("");
                        txtEmpPos.setText("");
                        txtEmpAdd.setText("");
                        txtEmpCharge.setText("");
                        txtEmpConName.setVisible(false);
                        txtEmpConName.setText("");
                        txtEmpPass.setText("");
                    });
                }
            } catch (Exception e) {
                alert.setContentText("Todos los campos deben estar completos");
                alert.showAndWait();
                e.printStackTrace();
            }
        });
        btnSaveEmployee.setOnAction((event) -> {
            try {
                Employee emp = (Employee) tblEmployee.getSelectionModel().selectedItemProperty().getValue();
                String name = txtEmpName.getText().trim();
                String lastName1 = txtEmpLastN1.getText().trim();
                String lastName2 = txtEmpLastN2.getText().trim();
                String RFC = txtEmpRFC.getText().trim();
                String telephone = txtEmpPhone.getText().trim();
                String perAddress = txtEmpAdd.getText().trim();
                String empPosition = txtEmpPos.getText().trim();
                String gender = cmbEmpGender.getValue().toString();
                String status = cmbEmpStatus.getValue().toString();
                String conName = txtEmpConName.getText().trim();
                String charge = txtEmpCharge.getText().trim();
                String pass = txtEmpPass.getText().trim();
                alert.setHeaderText("Alert:");
                if (!EmployeeController.emptyFieldsValidation(name, lastName1, lastName2, RFC, telephone, perAddress, gender, empPosition)) {
                    System.out.println("Nulo");
                    alert.setContentText("Todos los campos deben estar completos");
                    alert.showAndWait();
                } else {
                    Platform.runLater(() -> {
                        //modify
                        String response = EmployeeController.modifyEmployeeController(name, lastName1, lastName2, telephone, perAddress,
                                gender, empPosition, status, charge, pass, emp.getEmpId(), emp.getConsumer().getConId(), emp.getPerId());
                        alert.setContentText(response);
                        alert.showAndWait();
                        txtEmpName.setText("");
                        txtEmpLastN1.setText("");
                        txtEmpLastN2.setText("");
                        txtEmpRFC.setText("");
                        txtEmpPhone.setText("");
                        txtEmpPos.setText("");
                        txtEmpAdd.setText("");
                        txtEmpCharge.setText("");
                        txtEmpConName.setVisible(false);
                        txtEmpConName.setText("");
                        txtEmpPass.setText("");
                    });
                }
            } catch (Exception e) {
                alert.setContentText("Todos los campos deben estar completos");
                alert.showAndWait();
                e.printStackTrace();
            }
        });
        btnDeleteEmployee.setOnAction((event) -> {
            Employee emp = (Employee) tblEmployee.getSelectionModel().selectedItemProperty().getValue();
            Platform.runLater(() -> {
                String response = EmployeeController.logicalDelteEmpoyee(emp.getEmpId());
                alert.setContentText(response);
                alert.showAndWait();
                txtEmpName.setText("");
                txtEmpLastN1.setText("");
                txtEmpLastN2.setText("");
                txtEmpRFC.setText("");
                txtEmpPhone.setText("");
                txtEmpPos.setText("");
                txtEmpAdd.setText("");
                txtEmpCharge.setText("");
                txtEmpConName.setVisible(false);
                txtEmpConName.setText("");
                txtEmpPass.setText("");
            });
        });
        btnUpdate.setOnAction((event) -> {
            updateTable();
        });

        tblEmployee.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Employee>() {
            @Override
            public void changed(ObservableValue<? extends Employee> observable, Employee oldValue, Employee emp) {
                txtEmpName.setText(emp.getFirstName());
                txtEmpLastN1.setText(emp.getLastName1());
                txtEmpLastN2.setText(emp.getLastName2());
                txtEmpPos.setText(emp.getEmpPosition());
                txtEmpAdd.setText(emp.getPerAddress());
                lblEmpNumber.setText(emp.getEmpNumber());
                txtEmpPhone.setText(emp.getTelephone());
                txtEmpRFC.setText(emp.getRfc());
                txtEmpRFC.setEditable(false);
                cmbEmpGender.setValue(emp.getGender());
                if (emp.getEmpStatus() == 1) {
                    cmbEmpStatus.setValue("Activo");
                } else {
                    cmbEmpStatus.setValue("Inactivo");
                }
                txtEmpCharge.setText(emp.getConsumer().getRole());
                txtEmpConName.setVisible(true);
                txtEmpConName.setText(emp.getConsumer().getConName());
                txtEmpPass.setText(emp.getConsumer().getPass());
                btnSaveEmployee.setDisable(false);
                btnDeleteEmployee.setDisable(false);
                btnNewEmployee.setDisable(true);
            }
        });
    }

    public void addValues() {
        Platform.runLater(() -> {
            try {
                employeeData = empCtrl.EmployeeList();
                initializeLogic();
                if (employeeData != null) {
                    for (Employee employee : employeeData) {
                        masterData.add(employee);
                    }
                }
                creatingTables();
            } catch (IOException ioe) {
                alert.setHeaderText("Error: ");
                alert.setContentText("Lista de clientes no disponible");
                alert.showAndWait();
            }
        });
    }

    public void creatingTables() {
        columnEmpID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("EmpId"));
        columnEmpName.setCellValueFactory(new PropertyValueFactory<Employee, String>("FirstName"));
        columnEmpLastName1.setCellValueFactory(new PropertyValueFactory<Employee, String>("LastName1"));
        columnEmpLastName2.setCellValueFactory(new PropertyValueFactory<Employee, String>("LastName2"));
        columnEmpStatus.setCellValueFactory(new PropertyValueFactory<Employee, String>("EmpStatus"));
        tblEmployee.setItems(masterData);
    }

    public void initializeLogic() {
        btnNewEmployee.setDisable(false);
        btnDeleteEmployee.setDisable(true);
        btnSaveEmployee.setDisable(true);

        cmbEmpGender.getItems().add("M");
        cmbEmpGender.getItems().add("F");
        cmbEmpGender.getItems().add("O");

        cmbEmpStatus.getItems().add("Activo");
        cmbEmpStatus.getItems().add("Inactivo");

        txtEmpConName.setVisible(false);
    }

    public void updateTable() {
        tblEmployee.getItems().clear();
        addValues();
        creatingTables();
    }
}
