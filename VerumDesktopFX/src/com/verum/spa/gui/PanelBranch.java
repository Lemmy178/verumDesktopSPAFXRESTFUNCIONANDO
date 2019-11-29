
package com.verum.spa.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.verum.spa.consume.controller.BranchController;
import com.verum.spa.model.Branch;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author moi_3
 */
public class PanelBranch implements Initializable{  
    
    @FXML private TableView<Branch> tblList;    
    @FXML private JFXTextField txtAddress;
    @FXML private JFXTextField txtLatitude;
    @FXML private JFXTextField txtLongitude;
    @FXML private JFXTextField txtName;
    @FXML private JFXComboBox cmbStatus;        
    
    @FXML private JFXButton btnAddBranch;
    @FXML private JFXButton btnSaveBranch;
    @FXML private JFXButton btnDeleteBranch;                
    @FXML private JFXButton btnUpdate;                
    @FXML private CheckBox checkUnable;
    

    @FXML private TableColumn<Branch, Integer> branchIdColumn;
    @FXML private TableColumn<Branch, String> branchNameColumn;
    @FXML private TableColumn<Branch, String> branchAddressColumn;
    @FXML private TableColumn<Branch, Double> latitudeColumn;
    @FXML private TableColumn<Branch, Double> longitudeColumn;
    @FXML private TableColumn<Branch, String> branchStatusColumn;
    
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);    
    private BranchController branchCtrl;
    private Branch contextBranch;
    FXMLLoader fxmll;
    ObservableList<Branch> branchList = FXCollections.observableArrayList();        
    ObservableList<Branch> branchListDisabledOnly = FXCollections.observableArrayList();        
    
    public PanelBranch() throws Exception{
        addValues();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        branchCtrl = new BranchController();
        addListeners();
        fillComboBoxes();
        initializeColumns();
        fillTableView(true);
    }
    
    private void addListeners(){                    
        
        btnAddBranch.setOnAction(evt -> {
            cleanFields();
            contextBranch = null;            
            btnAddBranch.setDisable(true);
            tblList.setDisable(true);
            btnDeleteBranch.setText("Cancelar");
        });
        
        btnSaveBranch.setOnAction((ActionEvent evt) ->{                        
            boolean accept = false;
            try{
                if(cmbStatus.getSelectionModel().getSelectedIndex()>-1)                                            
                    accept = checkFields();                        
                        if(contextBranch!=null){                                   
                            if(accept)
                                modifyBranch();                                                       
                        }else{  
                            if(accept)
                                addBranch();                                                            
                }                                    
                        
                if(accept)
                    refreshList();                                            
                else{
                    alert.setHeaderText("Error:");
                    alert.setContentText("Todos los campos deben estar completados");
                    alert.showAndWait();
                }
            }catch(NumberFormatException nfe){
                alert.setHeaderText("Error:");
                alert.setContentText("Latitud y longitud incorrectos");                
                alert.showAndWait();
            }
        });        
        
        btnDeleteBranch.setOnAction(evt -> {
            if(btnDeleteBranch.getText().equals("Cancelar")){
                cleanFields();
                tblList.setDisable(false);
                btnAddBranch.setDisable(false);
                btnDeleteBranch.setText("Eliminar");
            }else{
                if(contextBranch!=null)
                    if(contextBranch.getBranchId()!=-1){
                        branchCtrl.logicalDelte(contextBranch.getBranchId());                
                    }
                refreshList();            
            }
        });                       
        
        tblList.getSelectionModel().selectedItemProperty().addListener((obs,selection,newSelection) ->{
            if(newSelection!=null){
                contextBranch = newSelection;
                txtAddress.setText(contextBranch.getBranchAddress());
                txtLatitude.setText(String.valueOf(contextBranch.getLatitude()));
                txtLongitude.setText(String.valueOf(contextBranch.getLongitude()));
                txtName.setText(contextBranch.getBranchName());            
                int status = 0;
                if(contextBranch.getBranchStatus()!=1)
                    status = 1;
                cmbStatus.getSelectionModel().select(status);                                    
            }        
        });
        
        btnUpdate.setOnAction(evt ->{            
            refreshList();                    
        });
        
        
    }
    
    private void fillComboBoxes(){
        cmbStatus.getItems().addAll("Activo","Inactivo");    
    }
        
    private void fillTableView(boolean enabled){                               
        if(branchList!=null)
            if(enabled)
                tblList.setItems(branchList);                        
            else
                tblList.setItems(branchListDisabledOnly);
    }
    
    private void addValues(){
        Platform.runLater(() -> {
            try {
                ArrayList<Branch> dataBranch = branchCtrl.branchList();                                
                branchList.clear();
                if (dataBranch != null) {
                    dataBranch.stream().forEach((branch) -> {
                        if(branch.getBranchStatus()==1)
                            branchList.add(branch);
                        else
                            branchListDisabledOnly.add(branch);
                    });
                }
            } catch (IOException ex) {
                alert.setHeaderText("Error:");
                alert.setContentText("Lista sucursales no disponible");
                alert.showAndWait();
            } catch (Exception ex) {
                Logger.getLogger(PanelBranch.class.getName()).log(Level.SEVERE, null, ex);
            }
        });   
    }
    
    
    @Deprecated
    private void enableFields(){        
        txtAddress.setDisable(false);
        txtLatitude.setDisable(false);
        txtLongitude.setDisable(false);
        txtName.setDisable(false);
        cmbStatus.setDisable(false);
    }
    @Deprecated
    private void disableFields(){        
        txtAddress.setDisable(true);
        txtLatitude.setDisable(true);
        txtLongitude.setDisable(true);
        txtName.setDisable(true);
        cmbStatus.setDisable(true);                
    }
    
    private void cleanFields(){        
        txtAddress.setText("");
        txtLatitude.setText("");
        txtLongitude.setText("");
        txtName.setText("");
        cmbStatus.getSelectionModel().clearSelection();
    }
    
    private boolean checkFields()throws NumberFormatException{
        if(!txtAddress.getText().equals(""))
            if(!txtName.getText().equals(""))
                if(!txtLatitude.getText().equals("")){
                    Double.parseDouble(txtLatitude.getText());
                    if(!txtLongitude.getText().equals("")){
                        Double.parseDouble(txtLongitude.getText());
                        if(cmbStatus.getSelectionModel().getSelectedIndex()>-1)
                            return true;              
                    }
                }
        return false;
    }    
    
    private void initializeColumns(){    
        branchIdColumn.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        branchNameColumn.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        branchAddressColumn.setCellValueFactory(new PropertyValueFactory<>("branchAddress"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("longitude"));
        branchStatusColumn.setCellValueFactory(new PropertyValueFactory<>("branchStatus"));
    }
    
    private void refreshList(){        
            addValues();            
            if(checkUnable.isSelected())
                fillTableView(false);                                                        
            else
                fillTableView(true);
    }        
    
    private void modifyBranch(){
        branchCtrl.modifyBranch(contextBranch.getBranchId(),
                                txtName.getText(),
                                txtAddress.getText(),
                                Double.parseDouble(txtLatitude.getText()),
                                Double.parseDouble(txtLongitude.getText()),
                                cmbStatus.getSelectionModel().getSelectedItem().equals("Activo")?1:0);                                            
        tblList.setDisable(false);
        btnAddBranch.setDisable(false);    
    }
 
    private void addBranch(){
        contextBranch = new Branch(-1,txtName.getText(),
                                txtAddress.getText(),
                                Double.parseDouble(txtLatitude.getText()),
                                Double.parseDouble(txtLongitude.getText()),
                                cmbStatus.getSelectionModel().getSelectedItem().equals("Activo")?1:0);

        branchCtrl.addBranch(contextBranch.getBranchName(),
            contextBranch.getBranchAddress(),
            contextBranch.getLatitude(),
            contextBranch.getLongitude(),
            contextBranch.getBranchStatus()); 
    
    }
    
    
    
}