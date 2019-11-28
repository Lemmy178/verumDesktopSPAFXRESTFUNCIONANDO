package com.verum.spa.consume.controller;

import com.verum.spa.consumeREST.EmployeeConsumeREST;
import com.verum.spa.model.Consumer;
import com.verum.spa.model.Employee;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeController {

    private static Employee emp = new Employee();
    private static Consumer consumer = new Consumer();
    private static EmployeeConsumeREST empRest = new EmployeeConsumeREST();
    private static int code;

    public static boolean emptyFieldsValidation(String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String email, String puesto) {
        if (name.trim().length() > 0 && name.trim().length() <= 64
                || lastName1.trim().length() > 0 && lastName1.trim().length() <= 64
                || lastName2.trim().length() > 0 && lastName2.trim().length() <= 64
                || rfc.trim().length() > 0 && rfc.trim().length() <= 14
                || adress.trim().length() > 0 && adress.trim().length() <= 200
                || tel.trim().length() > 0 && tel.trim().length() <= 25
                || email.trim().length() > 0 && email.trim().length() <= 200
                || puesto.trim().length() > 0 && puesto.trim().length() <= 20
                || genre.trim().length() > 0 && genre.trim().length() <= 2) {
            return true;
        }
        return false;
    }

    public static String addEmployeeController(String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String empPosition) {
        emp.setFirstName(name);
        emp.setLastName1(lastName1);
        emp.setLastName2(lastName2);
        emp.setRfc(rfc);
        emp.setTelephone(tel);
        emp.setPerAddress(adress);
        emp.setGender(genre);
        emp.setEmpPosition(empPosition);
        empRest.addEmployee(emp);

        if (code != 200) {
            return "Error";
        } else {
            return "Producto agregado.";
        }
    }

    public static String modifyEmployeeController(
            String name, String lastName1, String lastName2, String rfc,
            String tel, String adress, String genre, String empPosition, int empStatus, String photo, String charge,
            String pass, int empId, int conId, int perId
    ) {
        emp.setFirstName(name);
        emp.setLastName1(lastName1);
        emp.setLastName2(lastName2);
        emp.setRfc(rfc);
        emp.setTelephone(tel);
        emp.setPerAddress(adress);
        emp.setGender(genre);
        emp.setEmpPosition(empPosition);
        emp.setEmpStatus(empStatus);
        emp.setPhoto(photo);
        emp.setEmpId(empId);

        consumer.setRole(charge);
        consumer.setConId(conId);
        emp.setConsumer(consumer);
        emp.setIdPer(perId);

        code = empRest.modifyEmployee(emp);
        if (code != 200) {
            return "Error";
        } else {
            return "Producto modificado.";
        }
    }

    public static String logicalDelteEmpoyee(int empId) {
        emp.setEmpId(empId);
        code = empRest.logicalDeleteEmployee(emp);
        if (code != 200) {
            return "Error";
        } else {
            return "Producto eliminado.";
        }
    }

    public ArrayList<Employee> EmployeeList() throws IOException {
        ArrayList<Employee> datosEmployee = new ArrayList<>();
        datosEmployee = empRest.listEmployee();
        return datosEmployee;
    }

}
