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
import com.verum.spa.model.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class PanelCustomer implements Initializable {

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TableColumn<Customer, Integer> columIdCustomer;

    @FXML
    private TableColumn<Customer, String> columNameCustomer;

    @FXML
    private TableColumn<Customer, String> columCustomerLastName1;

    @FXML
    private TableColumn<Customer, String> columLastName2;

    @FXML
    private ImageView imgVUser;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtLastName1;

    @FXML
    private JFXTextField txtLastName2;

    @FXML
    private JFXTextField txtRFC;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXComboBox<?> cmbGenre;

    @FXML
    private Label lblClientNumber;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXButton btnNewCustomer;

    @FXML
    private JFXButton btnSaveCustomer;

    @FXML
    private JFXButton btnDeleteCustomer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
