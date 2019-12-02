/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  11/03/2019
 |  Description:  RoomWindow
 |                
 | Deficiencies:  No por el momento
 *===========================================================================*/
package com.verum.spa.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.verum.spa.consume.controller.BranchController;
import com.verum.spa.consume.controller.RoomController;
import com.verum.spa.model.Branch;
import com.verum.spa.model.Room;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class PanelRoom implements Initializable {
    
    @FXML JFXTextField txtName;
    @FXML JFXTextArea txaDescription;
    @FXML JFXComboBox<String> cmbStatus;
    @FXML JFXComboBox<String> cmbBranchName;
    @FXML JFXCheckBox chkListUnavailables;
    
    @FXML ImageView imgvPhoto;
    
    @FXML JFXButton btnNew;
    @FXML JFXButton btnSave;
    @FXML JFXButton btnDelete;
    @FXML JFXButton btnUpdate;
    
    @FXML TableView<Room> tblvRoomTable;
    
    @FXML TableColumn<Room, Integer> tblcRoomId;
    @FXML TableColumn<Room, String> tblcRoomName;
    @FXML TableColumn<Room, String> tblcRoomDesc;
    @FXML TableColumn<Room, String> tblcBranchName;
    @FXML TableColumn<Room, Byte> tblcRoomStatus;
    
    
    private ObservableList<Room> masterData = FXCollections.observableArrayList();
    
    private ArrayList<Room> roomData = new ArrayList<>();
    
    private Alert alert = new Alert(Alert.AlertType.NONE);
    
    public PanelRoom(){
        addValues();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeLogic();
        createTable();
        addListeners();
    }
    
    public void addListeners() {
        tblvRoomTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Room> observable, Room oldValue, Room room) -> {
            if(room != null){
                txtName.setText(room.getRoomName());
                txaDescription.setText(room.getRoomDesc());
                cmbBranchName.setValue(room.getBranch().getBranchName());
                if(room.getRoomStatus() == 1){
                    cmbStatus.setValue("Activo");
                } else {
                    cmbStatus.setValue("Inactivo");
                }
            } else {
                txtName.setText("");
                txaDescription.setText("");
                cmbBranchName.setValue("");
                cmbStatus.setValue("");
            }
        });
        
        btnDelete.setOnAction(ev -> {
            Room aux = tblvRoomTable.getSelectionModel().selectedItemProperty().get();
            if(aux != null){
                boolean resp = RoomController.logicalDeleteRoomController(aux.getRoomId());
                if(resp) showAlert("Eliminación lógica exitosa", "Los datos se han eliminado correctamente", Alert.AlertType.INFORMATION);
                else showAlert("Eliminación lógica no exitosa", "Ha ocurrido un error al intentar eliminar los datos", Alert.AlertType.ERROR);
            } else {
                showAlert("Selección invalida", "No se seleccionó una fila o la selección es inválida", Alert.AlertType.ERROR);
            }
        });
        
        btnNew.setOnAction(ev -> {
            tblvRoomTable.getSelectionModel().clearSelection();
        });
        
        btnSave.setOnAction(ev -> {
            Room aux = tblvRoomTable.getSelectionModel().selectedItemProperty().get();
            String name, desc, photo;
            int roomId, status, branchId;
            if(aux == null){
                name = txtName.getText();
                desc = txaDescription.getText();
                photo = imgvPhoto.toString();
                branchId = cmbBranchName.getSelectionModel().selectedIndexProperty().get();
                branchId += 1;
                if(RoomController.emptyFieldsValidation(name, desc, photo, branchId, branchId, branchId)){
                    boolean resp = RoomController.addRoomController(name, desc, photo, branchId);
                    if(resp) showAlert("Inserción exitosa", "Los datos se han guardado correctamente", Alert.AlertType.INFORMATION);
                    else showAlert("Inserción no exitosa", "Ha ocurrido un error al intentar guardar los datos", Alert.AlertType.ERROR);
                    
                } else {
                    showAlert("Campos invalidos", "Los campos ingresados no son correctos.", Alert.AlertType.ERROR);
                }
            } else {
                name = txtName.getText();
                desc = txaDescription.getText();
                photo = imgvPhoto.toString();
                roomId = aux.getRoomId();
                status = cmbStatus.getSelectionModel().selectedIndexProperty().get();
                status += 1;
                branchId = cmbBranchName.getSelectionModel().selectedIndexProperty().get();
                branchId += 1;
                if(RoomController.emptyFieldsValidation(name, desc, photo, roomId, branchId, status)){
                    boolean resp = RoomController.modifyRoomController(name, desc, photo, branchId,status, roomId);
                    if(resp){
                        showAlert("Actualización exitosa", "Los datos se han actualizado correctamente", Alert.AlertType.INFORMATION);
                    } else {
                        showAlert("Actualización no exitosa", "Ha ocurrido un error al intentar actualizar los datos", Alert.AlertType.ERROR);
                    }
                } else {
                    showAlert("Campos invalidos", "Los campos ingresados no son correctos.", Alert.AlertType.ERROR);
                }
            }
        });
        
        btnUpdate.setOnAction(ev -> {
            updateTable();
        });
    } 
    
    public void addValues(){
        Platform.runLater(() -> {
            try{
                roomData = RoomController.roomList();
                if(roomData != null){
                    roomData.forEach((room) -> {
                        if(chkListUnavailables.selectedProperty().get()){
                            if(room.getRoomStatus()!= 1){
                                masterData.add(room);
                            }
                        } else {
                            if(room.getRoomStatus()== 1){
                                masterData.add(room);
                            }
                        }
                    });
                }
            } catch(IOException e){
                showAlert("Datos no encontrados", "No se han encontrado valores en la base de datos.", Alert.AlertType.ERROR);
            } catch (Exception ex) {
                Logger.getLogger(PanelRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    public void createTable(){
        tblcRoomId.setCellValueFactory(new PropertyValueFactory<>("RoomId"));
        tblcRoomName.setCellValueFactory(new PropertyValueFactory<>("RoomName"));
        tblcRoomDesc.setCellValueFactory(new PropertyValueFactory<>("RoomDesc"));
        tblcBranchName.setCellValueFactory((TableColumn.CellDataFeatures<Room, String> param) -> new SimpleStringProperty(param.getValue().getBranch().getBranchName()));
        tblcRoomStatus.setCellValueFactory(new PropertyValueFactory<>("RoomStatus"));
        tblvRoomTable.setItems(masterData);
    }
    
    public void initializeLogic() {
        btnNew.setDisable(false);
        btnDelete.setDisable(false);
        btnSave.setDisable(false);
        
        BranchController branchCtrl = new BranchController();
        ArrayList<Branch> branches;
        try {
            branches = branchCtrl.branchList();
            LinkedHashSet<String> uniqueBranch = new LinkedHashSet<>();
            branches.forEach((branch) -> {
                uniqueBranch.add(branch.getBranchName());
            });
            uniqueBranch.forEach((name) -> {
                cmbBranchName.getItems().add(name);
            });
        } catch (Exception ex) {
            showAlert("Error en cargar lista", "La lista de las sucursales no se cargó adecuadamente", Alert.AlertType.ERROR);
        }
        
        cmbStatus.getItems().add("Activo");
        cmbStatus.getItems().add("Inactivo");

        cmbStatus.setEditable(false);
        cmbBranchName.setEditable(false);
    }
    
    public void showAlert(String title, String content, Alert.AlertType alertType){
        alert.setAlertType(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public void updateTable(){
        tblvRoomTable.getItems().clear();
        addValues();
        createTable();
    }
    
}