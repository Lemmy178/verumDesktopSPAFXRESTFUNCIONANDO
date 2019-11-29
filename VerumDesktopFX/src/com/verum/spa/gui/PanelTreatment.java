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
import com.verum.spa.consume.controller.TreatmentController;
import com.verum.spa.model.Treatment;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PanelTreatment implements Initializable {

    @FXML JFXTextField txtName;
    @FXML JFXTextField txtCost;
    @FXML JFXComboBox<String> cmbStatus;
    @FXML JFXTextArea txaDescription;
    
    @FXML JFXButton btnNew;
    @FXML JFXButton btnSave;
    @FXML JFXButton btnDelete;
    @FXML JFXButton btnUpdate;
    
    @FXML TableView<Treatment> tblvTreatmentTable;
    
    @FXML TableColumn<Treatment, Integer> tblcTreatmentId;
    @FXML TableColumn<Treatment, String> tblcTreatmentName;
    @FXML TableColumn<Treatment, String> tblcTreatmentDesc;
    @FXML TableColumn<Treatment, String> tblcTreatmentCost;
    @FXML TableColumn<Treatment, Byte> tblcTreatmentStatus;
    
    private ObservableList<Treatment> masterData = FXCollections.observableArrayList();
    
    private ArrayList<Treatment> treatmentData = new ArrayList<>();
    
    private Alert alert = new Alert(Alert.AlertType.NONE);
    
    public PanelTreatment(){
        addValues();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeLogic();
        createTable();
        addListeners();
    }

    public void addListeners() {
        tblvTreatmentTable.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Treatment> observable, Treatment oldValue, Treatment treatment) -> {
            if(treatment != null){
                txtName.setText(treatment.getTreatName());
                txaDescription.setText(treatment.getTreatDesc());
                txtCost.setText(String.valueOf(treatment.getCost()));
                if(treatment.getTreatStatus() == 1){
                    cmbStatus.setValue("Activo");
                } else {
                    cmbStatus.setValue("Inactivo");
                }
            } else {
                txtName.setText("");
                txaDescription.setText("");
                txtCost.setText("");
                cmbStatus.setValue("");
            }
        });
        
        
        btnDelete.setOnAction(ev -> {
            Treatment aux = tblvTreatmentTable.getSelectionModel().selectedItemProperty().get();
            if(aux != null){
                boolean resp = TreatmentController.logicalDeleteTreatmentController(aux.getTreatId());
                if(resp) showAlert("Eliminación lógica exitosa", "Los datos se han eliminado correctamente", Alert.AlertType.INFORMATION);
                else showAlert("Eliminación lógica no exitosa", "Ha ocurrido un error al intentar eliminar los datos", Alert.AlertType.ERROR);
            } else {
                showAlert("Selección invalida", "No se seleccionó una fila o la selección es inválida", Alert.AlertType.ERROR);
            }
        });
        
        btnNew.setOnAction(ev -> {
            tblvTreatmentTable.getSelectionModel().clearSelection();
        });
        
        
        btnSave.setOnAction(ev -> {
            Treatment aux = tblvTreatmentTable.getSelectionModel().selectedItemProperty().get();
            String name, desc;
            int treatId, status;
            double cost;
            try{
                if(aux == null){
                    name = txtName.getText();
                    desc = txaDescription.getText();
                    cost = Double.parseDouble(txtCost.getText());
                    if(TreatmentController.emptyFieldsValidation(name, desc, cost, 1, 1)){
                        boolean resp = TreatmentController.addTreatmentController(name, desc, cost);
                        if(resp) showAlert("Inserción exitosa", "Los datos se han guardado correctamente", Alert.AlertType.INFORMATION);
                        else showAlert("Inserción no exitosa", "Ha ocurrido un error al intentar guardar los datos", Alert.AlertType.ERROR);

                    } else {
                        showAlert("Campos invalidos", "Los campos ingresados no son correctos.", Alert.AlertType.ERROR);
                    }
                } else {
                    name = txtName.getText();
                    desc = txaDescription.getText();
                    cost = Double.parseDouble(txtCost.getText());
                    treatId = aux.getTreatId();
                    status = cmbStatus.getSelectionModel().selectedIndexProperty().get();
                    status += 1;
                    if(TreatmentController.emptyFieldsValidation(name, desc, cost, status, treatId)){
                        boolean resp = TreatmentController.modifyTreatmentController(name, desc, cost, status, treatId);
                        if(resp){
                            showAlert("Actualización exitosa", "Los datos se han actualizado correctamente", Alert.AlertType.INFORMATION);
                        } else {
                            showAlert("Actualización no exitosa", "Ha ocurrido un error al intentar actualizar los datos", Alert.AlertType.ERROR);
                        }
                    } else {
                        showAlert("Campos invalidos", "Los campos ingresados no son correctos.", Alert.AlertType.ERROR);
                    }
                }
            } catch(NumberFormatException e){
                showAlert("Campo de costo inválido", "El campo del costo solo puede poseer números", Alert.AlertType.ERROR);
            }
        });
        
        btnUpdate.setOnAction(ev -> {
            updateTable();
        });
    }

    public void addValues(){
        Platform.runLater(() -> {
            try{
                treatmentData = TreatmentController.treatmentList();
                if(treatmentData != null){
                    treatmentData.forEach((treatment) -> {
                        masterData.add(treatment);
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
        tblcTreatmentId.setCellValueFactory(new PropertyValueFactory<>("TreatId"));
        tblcTreatmentName.setCellValueFactory(new PropertyValueFactory<>("TreatName"));
        tblcTreatmentDesc.setCellValueFactory(new PropertyValueFactory<>("TreatDesc"));
        tblcTreatmentCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        tblcTreatmentStatus.setCellValueFactory(new PropertyValueFactory<>("TreatStatus"));
        tblvTreatmentTable.setItems(masterData);
    }
    
    public void initializeLogic(){
        btnNew.setDisable(false);
        btnDelete.setDisable(false);
        btnSave.setDisable(false);
        cmbStatus.getItems().add("Activo");
        cmbStatus.getItems().add("Inactivo");
        cmbStatus.setEditable(false);
    }
    
    public void showAlert(String title, String content, Alert.AlertType alertType){
        alert.setAlertType(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void updateTable(){
        tblvTreatmentTable.getItems().clear();
        addValues();
        createTable();
    }
}
