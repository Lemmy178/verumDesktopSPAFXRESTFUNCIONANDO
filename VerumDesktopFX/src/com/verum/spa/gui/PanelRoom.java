/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  11/03/2019
 |  Description:  RoomWindow
 |                
 | Deficiencies:  No por el momento
 *===========================================================================*/
package com.verum.spa.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelRoom implements Initializable {

    FXMLLoader fxml;
    Stage window;
    Scene scene;
    
    @FXML TextField txtName;
    @FXML TextArea txaDescription;
    @FXML ComboBox cmbStatus;
    
    @FXML ImageView imgvPhoto;
    
    @FXML Button btnNew;
    @FXML Button btnSave;
    @FXML Button btnDelete;
    
    @FXML VBox vBox;
    
    Parent root = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void addListeners() {
        for (Node node : vBox.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (node.getAccessibleText()) {
                        case "NEW":
                            break;
                        case "SAVE":
                            break;
                        case "DELETE":
                            break;
                    }
                });
            }
        }
    }
    

    

}
