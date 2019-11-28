package com.verum.spa.consume.controller;

import com.verum.spa.consumeREST.CustomerConsumeREST;
import com.verum.spa.model.Customer;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerController {

    private static Customer cus = new Customer();
    private static CustomerConsumeREST cusRest = new CustomerConsumeREST();
    private static int code;

    public static boolean emptyFieldsValidation(String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String email, String pass) {
        if (name.trim().length() > 0 && name.trim().length() <= 64
                || lastName1.trim().length() > 0 && lastName1.trim().length() <= 64
                || lastName2.trim().length() > 0 && lastName2.trim().length() <= 64
                || rfc.trim().length() > 0 && rfc.trim().length() <= 14
                || adress.trim().length() > 0 && adress.trim().length() <= 200
                || tel.trim().length() > 0 && tel.trim().length() <= 25
                || email.trim().length() > 0 && email.trim().length() <= 200
                || pass.trim().length() > 0 && pass.trim().length() <= 48
                || genre.trim().length() > 0 && genre.trim().length() <= 2) {
            return true;
        }
        return false;
    }

    public static String addCustomerController(String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String email, String pass) {
        cus.setFirstName(name);
        cus.setLastName1(lastName1);
        cus.setLastName2(lastName2);
        cus.setRfc(rfc);
        cus.setTelephone(tel);
        cus.setPerAddress(adress);
        cus.setGender(genre);
        cus.setEmail(email);
        cus.getConsumer().setPass(pass);
        code = cusRest.addCustomer(cus);
        if (code != 200) {
            return "Error";
        } else {
            return "Producto agregado.";
        }
    }

    public static String modifyCustomerController(
            String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String email,
            int cusStatus, String charge,
            int cusId, int conId, int perId) {
        cus.setFirstName(name);
        cus.setLastName1(lastName1);
        cus.setLastName2(lastName2);
        cus.setRfc(rfc);
        cus.setTelephone(tel);
        cus.setPerAddress(adress);
        cus.setGender(genre);
        cus.setEmail(email);
        cus.setCusStatus(cusStatus);
        cus.getConsumer().setRole(charge);
        cus.setCusId(cusId);
        cus.getConsumer().setConId(conId);
        cus.setIdPer(perId);
        code = cusRest.modifyCustomer(cus);
        if (code != 200) {
            return "Error";
        } else {
            return "Producto modificado.";
        }
    }

    public static String logicalDelteCustomer(int cusId) {
        cus.setIdPer(code);
        code = cusRest.logicalDeleteCustomer(cus);
        if (code != 200) {
            return "Error";
        } else {
            return "Producto eliminado.";
        }
    }

    public ArrayList<Customer> CustomerList() throws IOException {
        ArrayList<Customer> datosCustomer = new ArrayList<>();
        datosCustomer = cusRest.listCustomer();
        return datosCustomer;
    }
}
