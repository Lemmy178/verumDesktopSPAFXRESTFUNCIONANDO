/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  11/03/2019
 |  Description:  Treatment Window
 |                
 | Deficiencies:  No por el momento
 *===========================================================================*/
package com.verum.spa.gui;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

public class PanelTreatment implements Initializable {

//    FXMLLoader fxml;
//    Stage window;
//    Scene scene;
//    
//    @FXML TextField txtName;
//    @FXML TextField txtCost;
//    @FXML ComboBox cmbStatus;
//    @FXML TextArea txaDescription;
//    
//    @FXML Button btnNew;
//    @FXML Button btnSave;
//    @FXML Button btnDelete;
//    
//    @FXML VBox vBox;
//    
//    Parent root = null;
    @FXML
    private JFXTextField txtName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prueba();
    }

//    public void addListeners() {
//        for (Node node : vBox.getChildren()) {
//            if (node.getAccessibleText() != null) {
//                node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
//                    switch (node.getAccessibleText()) {
//                        case "add":
//                            break;
//                        case "update":
//                            break;
//                        case "delete":
//                            break;
//                    }
//                });
//            }
//        }
//    }

    public void prueba() {
        txtName.setText("hola ricardo");
    }

}
