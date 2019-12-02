/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Person Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/
package com.verum.spa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class Person {

    @SerializedName("perId")
    @Expose
    private int perId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName1")
    @Expose
    private String lastName1;
    @SerializedName("lastName2")
    @Expose
    private String lastName2;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("perAddress")
    @Expose
    private String perAddress;
    @SerializedName("telephone")
    @Expose
    private String telephone;
    @SerializedName("rfc")
    @Expose
    private String rfc;

    public Person() {
    }

    public Person(int perId, String firstName, String lastName1, String lastName2, String gender, String perAddress, String telepnohe, String rfc) {
        this.perId = perId;
        this.firstName = firstName;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.gender = gender;
        this.perAddress = perAddress;
        this.telephone = telepnohe;
        this.rfc = rfc;
    }

    public Person(String firstName, String lastName1, String lastName2, String gender, String perAddress, String telepnohe, String rfc) {
        this.firstName = firstName;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.gender = gender;
        this.perAddress = perAddress;
        this.telephone = telepnohe;
        this.rfc = rfc;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPerAddress() {
        return perAddress;
    }

    public void setPerAddress(String perAddress) {
        this.perAddress = perAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telepnohe) {
        this.telephone = telepnohe;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

}
