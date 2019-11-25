/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Employee Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/

package com.verum.spa.model;

public class Employee extends Person {

    private int empId;
    private String empNumber;
    private String empPosition;
    private int empStatus;
    private String photo;
    private Consumer consumer;

    public Employee() {
        this.consumer = new Consumer();
    }
    //CONSTRUCTOR FOR LIST
    public Employee(int empId, String empNumber, String empPosition, int empStatus, String photo, int conId,
            String conName, String pass, String role, String firstName, String lastName1, String lastName2,
            String gender, String perAddress, String telephone, String rfc) {
        super(firstName, lastName1, lastName2, gender, perAddress, telephone, rfc);
        this.empId = empId;
        this.empNumber = empNumber;
        this.empPosition = empPosition;
        this.empStatus = empStatus;
        this.photo = photo;
        this.consumer = new Consumer(conId, conName, pass, role);//conName, pass, role
    }
    
    //CONSTRUCTOR FOR ADD
    public Employee(String empNumber, String empPosition, int empStatus, String photo,
            String conName, String pass, String role, String firstName, String lastName1, String lastName2,
            String gender, String perAddress, String telephone, String rfc) {
        super(firstName, lastName1, lastName2, gender, perAddress, telephone, rfc);
        this.empNumber = empNumber;
        this.empPosition = empPosition;
        this.empStatus = empStatus;
        this.photo = photo;
        this.consumer = new Consumer(conName, pass, role);//conName, pass, role
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpNumber() {
        return empNumber;
    }

    public void setEmpNumber(String empNumber) {
        this.empNumber = empNumber;
    }

    public String getEmpPosition() {
        return empPosition;
    }

    public void setEmpPosition(String empPosition) {
        this.empPosition = empPosition;
    }

    public int getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(int empStatus) {
        this.empStatus = empStatus;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

}
