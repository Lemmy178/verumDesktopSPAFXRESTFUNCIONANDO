/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  11/03/2019
 |  Description:  Treatment Window
 |                
 | Deficiencies:  No por el momento
 *===========================================================================*/
package com.verum.spa.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PanelTreatment implements Initializable {

    @FXML JFXTextField txtName;
    @FXML JFXTextField txtCost;
    @FXML JFXComboBox<String> cmbStatus;
    @FXML JFXTextArea txaDescription;
    
    @FXML JFXButton btnNew;
    @FXML JFXButton btnSave;
    @FXML JFXButton btnDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void addListeners() {
        
    }


}
