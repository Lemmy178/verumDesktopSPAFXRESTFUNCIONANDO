/*=============================================================================
 |       Author:  Edson Mesraim Santos Perez
 |       Course:  Spa
 |     Due Date:  11/06/2019
 |  Description:  Person Model
 |                
 | Deficiencies:  No detected.
 *===========================================================================*/

package com.verum.spa.model;

public abstract class Person {

    private String firstName;
    private String lastName1;
    private String lastName2;
    private String gender;
    private String perAddress;
    private String telephone;
    private String rfc;

    public Person() {
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

}
