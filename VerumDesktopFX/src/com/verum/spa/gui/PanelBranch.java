
package com.verum.spa.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.verum.spa.consume.controller.BranchController;
import com.verum.spa.model.Branch;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author moi_3
 */
public class PanelBranch implements Initializable{  
    
    @FXML TableView<Branch> tblList;    
    @FXML JFXTextField txtAddress;
    @FXML JFXTextField txtLatitude;
    @FXML JFXTextField txtLongitude;
    @FXML JFXTextField txtName;
    @FXML JFXComboBox cmbStatus;        
    
    @FXML JFXButton btnAddBranch;
    @FXML JFXButton btnSaveBranch;
    @FXML JFXButton btnDeleteBranch;                

    TableColumn<Branch,Integer> branchIdColumn = new TableColumn<>("Clave"); 
    TableColumn<Branch,String> branchNameColumn = new TableColumn<>("Nombre"); 
    TableColumn<Branch,String> branchAddressColumn = new TableColumn<>("Dirección"); 
    TableColumn<Branch,Double> latitudeColumn = new TableColumn<>("Latitud"); 
    TableColumn<Branch,Double> longitudeColumn = new TableColumn<>("Longitud"); 
    TableColumn<Branch,String> branchStatusColumn = new TableColumn<>("Estatus");                
    
    FXMLLoader fxmll;
    Stage window;
    Scene scene;
    
    BranchController branchCtrl;

    private Branch contextBranch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
        fillComboBoxes();        
        initializeColumns();
        fillTableView();        
    }
    
    private void addListeners(){                    
        
        btnAddBranch.setOnAction(evt -> {
            cleanFields();
            if(contextBranch!=null){
                if(contextBranch.getBranchId()!=-1){
                    contextBranch = new Branch();
                    contextBranch.setBranchId(-1);            
                }            
            }
            
            
            
        });
        
        btnSaveBranch.setOnAction(evt ->{
            boolean active = false;
            if(cmbStatus.getSelectionModel().getSelectedIndex()>-1){
                active = cmbStatus.getSelectionModel().getSelectedIndex()==0;            
            }            
            if(contextBranch!=null){
                if(contextBranch.getBranchId()!=-1){
                    contextBranch.setBranchName(txtName.getText());
                    contextBranch.setBranchAddress(txtAddress.getText());
                    contextBranch.setLatitude(Double.parseDouble(txtLatitude.getText()));
                    contextBranch.setLongitude(Double.parseDouble(txtLongitude.getText()));
                    contextBranch.setBranchStatus(active);                                                       

                    branchCtrl.addBranch(contextBranch.getBranchName(),
                            contextBranch.getBranchAddress(),
                            contextBranch.getLatitude(),
                            contextBranch.getLongitude(),
                            contextBranch.isBranchStatus());
            
                }            
            }
            fillTableView();
            tblList.getSelectionModel().selectLast();
            
        });        
        
        btnDeleteBranch.setOnAction(evt -> {
            if(contextBranch!=null)
                if(contextBranch.getBranchId()!=-1){
                    branchCtrl.logicalDelte(contextBranch.getBranchId());                
                }
            fillTableView();           
        });                       
        
        tblList.getSelectionModel().selectedItemProperty().addListener((obs,selection,newSelection) ->{
            if(newSelection!=null){
                contextBranch = newSelection;
                txtAddress.setText(contextBranch.getBranchAddress());
                txtLatitude.setText(String.valueOf(contextBranch.getLatitude()));
                txtLongitude.setText(String.valueOf(contextBranch.getLongitude()));
                txtName.setText(contextBranch.getBranchName());            
                int status = 0;
                if(!contextBranch.isBranchStatus())
                    status = 1;
                cmbStatus.getSelectionModel().select(status);                                    
            }        
        });
        
        
    }
    
    private void fillComboBoxes(){
        cmbStatus.getItems().addAll("Activo","Inactivo");    
    }
    
    /**
     * 
     *This method should be called after {@link #initializeColumns()} is called
     * 
    */    
    private void fillTableView(){                               
        
        tblList.setPlaceholder(new Label("No hay sucursales que mostrar"));
        tblList.getColumns().addAll(branchIdColumn,branchNameColumn,branchAddressColumn,latitudeColumn,longitudeColumn,branchStatusColumn);        
        
        ArrayList<Branch> branchList = branchCtrl.branchList();  
        
        if(branchList!=null){
            branchList.stream().forEach((branch) -> {
                tblList.getItems().add(branch);
            });                            
        }

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
    
    private boolean checkFields(){
        if(txtAddress.getText().equals(""))
            if(txtName.getText().equals(""))
                if(!txtLatitude.getText().equals(""))
                    if(!txtLongitude.getText().equals(""))
                        if(cmbStatus.getSelectionModel().getSelectedIndex()>0)
                            return true;               
        return false;
    }    
    
    private void initializeColumns(){    
        branchIdColumn.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        branchNameColumn.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        branchAddressColumn.setCellValueFactory(new PropertyValueFactory<>("branchAddress"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));
        branchStatusColumn.setCellValueFactory(new PropertyValueFactory<>("latitude"));
    }
    
}
