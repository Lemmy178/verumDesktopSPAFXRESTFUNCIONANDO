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
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class PanelTreatment implements Initializable {

    @FXML JFXTextField txtName;
    @FXML JFXTextField txtCost;
    @FXML JFXComboBox<String> cmbStatus;
    @FXML JFXTextArea txaDescription;
    
    @FXML JFXButton btnNew;
    @FXML JFXButton btnSave;
    @FXML JFXButton btnDelete;
    
    @FXML HBox hBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void addListeners() {
        for (Node node : hBox.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (node.getAccessibleText()) {
                        case "add":
                            break;
                        case "update":
                            break;
                        case "delete":
                            break;
                    }
                });
            }
        }
    }


}
