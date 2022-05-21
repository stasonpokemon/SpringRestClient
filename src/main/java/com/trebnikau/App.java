package com.trebnikau;

import com.trebnikau.comfiguration.MyConfig;
import com.trebnikau.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);
//        communication.getAllEmployees().forEach(System.out::println);

//        System.out.println(communication.getEmployee(4));

//        Employee employee = new Employee();
//        employee.setId(11);
//        employee.setName("Anton");
//        employee.setSurname("Kirillov");
//        employee.setDepartment("IT");
//        employee.setSalary(700);
//        communication.saveEmployee(employee);
        communication.deleteEmployee(111);
    }
}
