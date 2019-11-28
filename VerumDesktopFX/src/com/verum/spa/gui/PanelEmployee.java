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
import com.verum.spa.model.Employee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class PanelEmployee implements Initializable {

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TableColumn<Employee, Integer> columIdEmp;

    @FXML
    private TableColumn<Employee, String> columEmpName;

    @FXML
    private TableColumn<Employee, String> columEmpLastName1;

    @FXML
    private TableColumn<Employee, String> columEmpLastName2;

    @FXML
    private TableColumn<Employee, String> columEmpUserName;

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
    private JFXTextField txtRFC;

    @FXML
    private JFXTextField txtEmpPhone;

    @FXML
    private JFXTextField txtEmpAdd;

    @FXML
    private JFXComboBox<?> cmbGenre;

    @FXML
    private Label lblEmployeeNumber;

    @FXML
    private JFXButton btnNewEmployee;

    @FXML
    private JFXButton btnSaveEmployee;

    @FXML
    private JFXButton btnDeleteEmployee;

    public PanelEmployee() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
