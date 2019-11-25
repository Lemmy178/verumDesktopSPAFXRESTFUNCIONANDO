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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class PanelEmployee implements Initializable {

    FXMLLoader fxml;
    Stage window;
    Scene scene;

    @FXML
    private BorderPane empBorderPane;
    @FXML
    private JFXButton btnEmpAdd;
    @FXML
    private JFXButton btnEmpAdm;
    @FXML
    private JFXButton btnEmpList;
    @FXML
    private VBox vBox;

    Parent root = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
        try {
            windowDefault();
        } catch (IOException ex) {
            Logger.getLogger(PanelEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addListeners() {
        for (Node node : vBox.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (node.getAccessibleText()) {
                        case "admEmployee":
                            try {
                                root = FXMLLoader.load(getClass().getResource("/com/verum/spa/gui/fxml/employee_Window_Modify.fxml"));
                                empBorderPane.setCenter(root);
                            } catch (IOException ex) {
                                Logger.getLogger(WindowMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                        case "listEmployee":
                            try {
                                root = FXMLLoader.load(getClass().getResource("/com/verum/spa/gui/fxml/employee_Window_List.fxml"));
                                empBorderPane.setCenter(root);
                            } catch (IOException ex) {
                                Logger.getLogger(WindowMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                    }
                });
            }
        }
    }

    public void windowDefault() throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/verum/spa/gui/fxml/product_Window_Modify.fxml"));
        empBorderPane.setCenter(root);
    }

}
