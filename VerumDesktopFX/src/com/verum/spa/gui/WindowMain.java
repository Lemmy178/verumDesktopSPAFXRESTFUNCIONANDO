/*=============================================================================
 |       Author:  Erick Ruben Ramos Vazquez
                  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  10/18/2019
 |  Description:  WindowMain
 |                
 | Deficiencies:  Falta completar la barra superior
 *===========================================================================*/
package com.verum.spa.gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowMain extends Application {

    @FXML
    private BorderPane pnlMain;
    @FXML
    private VBox vBoxSide;
    @FXML
    private JFXButton btnCli;
    @FXML
    private JFXButton btnEmp;
    @FXML
    private JFXButton btnSer;
    @FXML
    private JFXButton btnTre;
    @FXML
    private JFXButton btnRes;
    @FXML
    private JFXButton btnSal;
    @FXML
    private ImageView btnSale;
    @FXML
    private JFXButton btnSto;
    @FXML
    private ImageView btnSal1;
    @FXML
    private JFXButton btnPro;
    @FXML
    private ImageView btnSale1;
    @FXML
    private HBox hBoxTop;

    FXMLLoader fxml;
    Stage window;
    Scene scene;
    Parent root = null;
//    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public WindowMain() throws IOException {
        fxml = new FXMLLoader(System.class.getResource("/com/verum/spa/gui/fxml/window_main.fxml"));
        fxml.setController(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        fxml.load();
        scene = new Scene(fxml.getRoot());
        window = primaryStage;
        window.setScene(scene);
        window.setMinHeight(600);
        window.setMinWidth(800);
        window.show();
        options();
    }

    public void options() throws IOException {
        for (Node node : vBoxSide.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
                    switch (node.getAccessibleText()) {
                        case "EMPLOYEE": {
                            try {
                                root = FXMLLoader.load(getClass().getResource("/com/verum/spa/gui/fxml/panel_employee.fxml"));
                                pnlMain.setCenter(root);

                            } catch (IOException ex) {
                                Logger.getLogger(WindowMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case "CUSTOMER": {
                            try {
                                root = FXMLLoader.load(getClass().getResource("/com/verum/spa/gui/fxml/panel_customer.fxml"));
                                pnlMain.setCenter(root);
                            } catch (IOException ex) {
                                Logger.getLogger(WindowMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case "SERVICE":
                            if (pnlMain.getCenter() != null) {
                                pnlMain.getCenter().setVisible(false);
                            }
                            break;
                        case "TREATMENT": {
                            try {
                                root = FXMLLoader.load(getClass().getResource("/com/verum/spa/gui/fxml/panel_treatment.fxml"));
                                pnlMain.setCenter(root);

                            } catch (IOException ex) {
                                Logger.getLogger(WindowMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case "ROOM": {
                            try {
                                root = FXMLLoader.load(getClass().getResource("/com/verum/spa/gui/fxml/panel_room.fxml"));
                                pnlMain.setCenter(root);
                            } catch (IOException ex) {
                                Logger.getLogger(WindowMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case "RESERVATION":
                            if (pnlMain.getCenter() != null) {
                                pnlMain.getCenter().setVisible(false);
                            }
                            break;
                        case "BRANCH": {
                            try {
                                root = FXMLLoader.load(getClass().getResource("/com/verum/spa/gui/fxml/panel_branch.fxml"));
                                pnlMain.setCenter(root);
                            } catch (IOException ex) {
                                Logger.getLogger(WindowMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case "PRODUCT": {
                            try {
                                root = FXMLLoader.load(getClass().getResource("/com/verum/spa/gui/fxml/panel_product.fxml"));
                                pnlMain.setCenter(root);
                            } catch (IOException ex) {
                                Logger.getLogger(WindowMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case "ROOM_CONTROL": {
                            try {
                                root = FXMLLoader.load(getClass().getResource("/com/verum/spa/gui/fxml/panel_room_control.fxml"));
                                pnlMain.setCenter(root);
                                if (pnlMain.getRight() != null) {
                                    pnlMain.getRight().setVisible(false);
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(WindowMain.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                    }
                });
            }
        }
    }


 

}
