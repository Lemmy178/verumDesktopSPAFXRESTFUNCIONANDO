package com.verum.spa.consume.controller;

import com.verum.spa.consumeREST.CustomerConsumeREST;
import com.verum.spa.model.Consumer;
import com.verum.spa.model.Customer;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomerController {

    private static Customer cus = new Customer();
    private static CustomerConsumeREST cusRest = new CustomerConsumeREST();
    private static int code;

    public static boolean emptyFieldsValidation(String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String email, String pass, String conName, String charge) {
        if (name.trim().length() > 0 && name.trim().length() <= 64
                || lastName1.trim().length() > 0 && lastName1.trim().length() <= 64
                || lastName2.trim().length() > 0 && lastName2.trim().length() <= 64
                || rfc.trim().length() > 0 && rfc.trim().length() <= 14
                || adress.trim().length() > 0 && adress.trim().length() <= 200
                || tel.trim().length() > 0 && tel.trim().length() <= 25
                || email.trim().length() > 0 && email.trim().length() <= 200
                || pass.trim().length() > 0 && pass.trim().length() <= 48
                || genre.trim().length() > 0 && genre.trim().length() <= 2
                || charge.trim().length() > 0 && charge.trim().length() <= 24
                || conName.trim().length() > 0 && conName.trim().length() <= 48) {
            return true;
        }
        return false;
    }

    public static String addCustomerController(String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String gender, String email, String passw, String conName, String charge) {
        Consumer consumer = new Consumer();
        cus.setFirstName(name);
        cus.setLastName1(lastName1);
        cus.setLastName2(lastName2);
        cus.setRfc(rfc);
        cus.setUniqueNumber(generateUniqueNumber(rfc));
        cus.setTelephone(tel);
        cus.setPerAddress(adress);
        cus.setGender(gender);
        cus.setEmail(email);
        cus.setCusStatus(1);
        consumer.setConName(conName);
        consumer.setRole(charge);
        consumer.setPass(passw);
        cus.setConsumer(consumer);
        code = cusRest.addCustomer(cus);
        if (code != 200) {
            return "Error";
        } else {
            return "Cliente agregado.";
        }
    }

    public static String modifyCustomerController(
            String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String email, String pass, String charge,
            int cusStatus, int cusId, int conId, int perId) {
        Consumer consumer = new Consumer();
        cus.setFirstName(name);
        cus.setLastName1(lastName1);
        cus.setLastName2(lastName2);
        cus.setRfc(rfc);
        cus.setTelephone(tel);
        cus.setPerAddress(adress);
        cus.setGender(genre);
        cus.setEmail(email);
        cus.setCusStatus(cusStatus);
        consumer.setConId(conId);
        consumer.setPass(pass);
        consumer.setRole(charge);
        cus.setConsumer(consumer);
        cus.setCusId(cusId);
        cus.setPerId(perId);
        code = cusRest.modifyCustomer(cus);
        if (code != 200) {
            return "Error";
        } else {
            return "Cliente modificado.";
        }
    }

    public static String logicalDelteCustomer(int cusId) {
        cus.setPerId(code);
        code = cusRest.logicalDeleteCustomer(cus);
        if (code != 200) {
            return "Error";
        } else {
            return "Cliente eliminado.";
        }
    }

    public ArrayList<Customer> customerList() throws IOException {
        ArrayList<Customer> datosCustomer = new ArrayList<>();
        datosCustomer = cusRest.listCustomer();
        return datosCustomer;
    }

    public static String generateUniqueNumber(String rfc) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        Date now = new Date();
        String unique = rfc.substring(0, 4) + sdf.format(now);
        return unique.toUpperCase();
    }
}
