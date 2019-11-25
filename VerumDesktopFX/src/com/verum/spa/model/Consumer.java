/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Customer Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.model;

public class Consumer {

    private int conId;
    private String conName;
    private String pass;
    private String role;

    public Consumer() {
    }

    public Consumer(int conId, String conName, String pass, String role) {
        this.conId = conId;
        this.conName = conName;
        this.pass = pass;
        this.role = role;
    }

    public Consumer(String conName, String pass, String role) {
        this.conName = conName;
        this.pass = pass;
        this.role = role;
    }

    public int getConId() {
        return conId;
    }

    public void setConId(int conId) {
        this.conId = conId;
    }

    public String getConName() {
        return conName;
    }

    public void setConName(String conName) {
        this.conName = conName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
