/*=============================================================================
 |       Author:  Ricardo Iván Ramírez Bello
 |       Course:  Spa
 |     Due Date:  11/26/2019
 |  Description:  Room Controller
 |                
 | Deficiencies:  Ninguna detectada. Se hace el control de entrada del usuario  
                  antes de mandar valores a BD
 *===========================================================================*/
package com.verum.spa.consume.controller;

import com.verum.spa.consumeREST.RoomConsumeREST;
import com.verum.spa.model.Room;
import java.io.IOException;
import java.util.ArrayList;

public class RoomController {
    
    private static RoomConsumeREST roomREST = new RoomConsumeREST();
    private static int code;
    
    public static boolean emptyFieldsValidation(String roomName, String roomDesc, String photo, int roomId, int branchId, int status) {
        if (roomName.trim().length() > 0 && roomName.trim().length() < 129 && roomDesc.trim().length() > 0 && photo.trim().length() > 0 && roomId > 0  && branchId > 0 && status > 0) {
            return true;
        }
        return false;
    }
    
    public static boolean addRoomController(String roomName, String roomDesc, String photo, int branchId){
        Room room = new Room();
        room.setRoomName(roomName);
        room.setRoomDesc(roomDesc);
        room.setPhoto(photo);
        room.getBranch().setBranchId(branchId);
        code = roomREST.addRoom(room);
        return code == 200;
    }
    
    public static boolean modifyRoomController(String roomName, String roomDesc, String photo, int branchId, int roomStatus, int roomId){
        Room room = new Room();
        room.setRoomName(roomName);
        room.setRoomDesc(roomDesc);
        room.setPhoto(photo);
        room.getBranch().setBranchId(branchId);
        room.setRoomStatus(roomStatus);
        room.setRoomId(roomId);
        code = roomREST.modifyRoom(room);
        return code == 200;
    }
    
    public static boolean logicalDeleteRoomController(int roomId){
        Room room = new Room();
        room.setRoomId(roomId);
        code = roomREST.logicalDeleteRoom(room);
        return code == 200;
    }
    
    public static ArrayList<Room> roomList() throws IOException{
        ArrayList<Room> datosRoom;
        datosRoom = roomREST.listRoom();
        return datosRoom;
    }
}
