package com.verum.spa.consume.controller;

import com.verum.spa.consumeREST.EmployeeConsumeREST;
import com.verum.spa.model.Consumer;
import com.verum.spa.model.Employee;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeController {

    private static Employee emp = new Employee();
    //private static Consumer consumer = new Consumer();
    private static EmployeeConsumeREST empRest = new EmployeeConsumeREST();
    private static int code;

    public static boolean emptyFieldsValidation(String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String puesto, String conName, String charge, String pass) {
        if (name.trim().length() > 0 && name.trim().length() <= 64
                || lastName1.trim().length() > 0 && lastName1.trim().length() <= 64
                || lastName2.trim().length() > 0 && lastName2.trim().length() <= 64
                || rfc.trim().length() > 0 && rfc.trim().length() <= 14
                || adress.trim().length() > 0 && adress.trim().length() <= 200
                || tel.trim().length() > 0 && tel.trim().length() <= 25
                || puesto.trim().length() > 0 && puesto.trim().length() <= 20
                || pass.trim().length() > 0 && pass.trim().length() <= 48
                || genre.trim().length() > 0 && genre.trim().length() <= 2
                || charge.trim().length() > 0 && charge.trim().length() <= 24
                || conName.trim().length() > 0 && conName.trim().length() <= 48) {
            return true;
        }
        return false;
    }

    public static boolean emptyFieldsValidation(String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String puesto) {
        if (name.trim().length() > 0 && name.trim().length() <= 64
                || lastName1.trim().length() > 0 && lastName1.trim().length() <= 64
                || lastName2.trim().length() > 0 && lastName2.trim().length() <= 64
                || rfc.trim().length() > 0 && rfc.trim().length() <= 14
                || adress.trim().length() > 0 && adress.trim().length() <= 200
                || tel.trim().length() > 0 && tel.trim().length() <= 25
                || puesto.trim().length() > 0 && puesto.trim().length() <= 20
                || genre.trim().length() > 0 && genre.trim().length() <= 2) {
            return true;
        }
        return false;
    }

    public static String addEmployeeController(String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String empPosition, String status, String charge, String conName, String pass) {
        Consumer consumer = new Consumer();
        emp.setFirstName(name);
        emp.setLastName1(lastName1);
        emp.setLastName2(lastName2);
        emp.setRfc(rfc);
        emp.setEmpNumber(generateEmployeeNumber(rfc));
        emp.setTelephone(tel);
        emp.setPerAddress(adress);
        emp.setPhoto("photo");
        emp.setGender(genre);
        emp.setEmpPosition(empPosition);
        if (status.equals("Activo")) {
            emp.setEmpStatus(1);
        } else {
            emp.setEmpStatus(2);
        }
        consumer.setConName(conName);
        consumer.setPass(pass);
        consumer.setRole(charge);
        emp.setConsumer(consumer);
        code = empRest.addEmployee(emp);
        if (code != 200) {
            return "Error";
        } else {
            return "Empleado agregado.";
        }
    }

    public static String modifyEmployeeController(
            String name, String lastName1, String lastName2,
            String tel, String adress, String genre, String empPosition, String status, String charge,
            String pass, int empId, int conId, int perId
    )// String photo,
    {
        Consumer consumer = new Consumer();
        emp.setFirstName(name);
        emp.setLastName1(lastName1);
        emp.setLastName2(lastName2);
        emp.setTelephone(tel);
        emp.setPerAddress(adress);
        emp.setGender(genre);
        emp.setEmpPosition(empPosition);
        if (status.equals("Activo")) {
            emp.setEmpStatus(1);
        } else {
            emp.setEmpStatus(2);
        }
        emp.setPhoto("photo");
//        consumer.setConName(conName);
        consumer.setRole(charge);
        consumer.setConId(conId);
        consumer.setPass(pass);
        emp.setConsumer(consumer);
        emp.setEmpId(empId);
        consumer.setConId(conId);
        emp.setPerId(perId);

        code = empRest.modifyEmployee(emp);
        if (code != 200) {
            return "Error";
        } else {
            return "Empleado modificado.";
        }
    }

    public static String logicalDelteEmpoyee(int empId) {
        emp.setEmpId(empId);
        code = empRest.logicalDeleteEmployee(emp);
        if (code != 200) {
            return "Error";
        } else {
            return "Empleado eliminado.";
        }
    }

    public ArrayList<Employee> EmployeeList() throws IOException {
        ArrayList<Employee> datosEmployee = new ArrayList<>();
        datosEmployee = empRest.listEmployee();
        return datosEmployee;
    }

    public static String generateEmployeeNumber(String rfc) {
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        Date now = new Date();
        String unique = "E" + rfc.substring(0, 4) + sdf.format(now);
        return unique.toUpperCase();
    }
}
