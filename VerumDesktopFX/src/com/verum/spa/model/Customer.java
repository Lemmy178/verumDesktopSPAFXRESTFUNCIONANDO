/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Customer Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/

package com.verum.spa.model;

public class Customer extends Person {

    private int cusId;
    private String uniqueNumber;
    private String email;
    private int cusStatus;
    private Consumer consumer;

    public Customer() {
        this.consumer = new Consumer();
    }
    //CONSTRUCTOR FOR LIST
    public Customer(int cusId, String uniqueNumber, String email, int cusStatus, int conId, String conName, String pass, String role, String firstName, String lastName1, String lastName2, String gender, String perAddress, String telephone, String rfc) {
        super(firstName, lastName1, lastName2, gender, perAddress, telephone, rfc);
        this.cusId = cusId;
        this.uniqueNumber = uniqueNumber;
        this.email = email;
        this.cusStatus = cusStatus;
        this.consumer = new Consumer(conId, conName, pass, role);//conName, String pass, String role
    }
    //CONSTRUCTOR FOR ADD
    public Customer(String uniqueNumber, String email, int cusStatus, String conName, String pass, String role, String firstName, String lastName1, String lastName2, String gender, String perAddress, String telephone, String rfc) {
        super(firstName, lastName1, lastName2, gender, perAddress, telephone, rfc);
        this.uniqueNumber = uniqueNumber;
        this.email = email;
        this.cusStatus = cusStatus;
        this.consumer = new Consumer(conName, pass, role);//conName, String pass, String role
    }

    public int getCusId() {
        return cusId;
    }

    public void setCusId(int cusId) {
        this.cusId = cusId;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCusStatus() {
        return cusStatus;
    }

    public void setCusStatus(int cusStatus) {
        this.cusStatus = cusStatus;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

}
