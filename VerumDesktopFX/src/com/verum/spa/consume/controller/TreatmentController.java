/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  11/27/2019
 |  Description:  Room Controller
 |                
 | Deficiencies:  Ninguna detectada. Se hace el control de entrada del usuario  
                  antes de mandar valores a BD
 *===========================================================================*/

package com.verum.spa.consume.controller;

import com.verum.spa.consumeREST.TreatmentConsumeREST;
import com.verum.spa.model.Treatment;
import java.io.IOException;
import java.util.ArrayList;

public class TreatmentController {
    
    private static TreatmentConsumeREST treatREST = new TreatmentConsumeREST();
    private static int code;
    
    public static boolean emptyFieldsValidation(String roomName, String roomDesc, String photo, int roomId, int branchId, int status) {
        if (roomName.trim().length() > 0 && roomName.trim().length() < 129 && roomDesc.trim().length() > 0 && photo.trim().length() > 0 && roomId > 0  && branchId > 0 && status > 0) {
            return true;
        }
        return false;
    }
    
    public static boolean addRoomController(String treatName, String treatDesc, double cost){
        Treatment treatment = new Treatment();
        treatment.setTreatName(treatName);
        treatment.setTreatDesc(treatDesc);
        treatment.setCost(cost);
        code = treatREST.addTreatment(treatment);
        return code == 200;
    }
    
    public static boolean modifyRoomController(String treatName, String treatDesc, double cost, int treatStatus, int treatId){
        Treatment treatment = new Treatment(treatId, treatName, treatDesc, cost, treatStatus);
        code = treatREST.modifyTreatment(treatment);
        return code == 200;
    }
    
    public static boolean logicalDeleteRoomController(int roomId){
        Treatment treatment = new Treatment();
        treatment.setTreatId(roomId);
        code = treatREST.logicalDeleteTreatment(treatment);
        return code == 200;
    }
    
    public static ArrayList<Treatment> roomList() throws IOException{
        ArrayList<Treatment> datosRoom;
        datosRoom = treatREST.listRoom();
        return datosRoom;
    }
}
