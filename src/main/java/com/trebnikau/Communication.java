package com.trebnikau;

import com.trebnikau.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// Объект для связи с REST сервисом
@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "http://localhost:8080/api/employees";

    public List<Employee> getAllEmployees() {
        ResponseEntity<List<Employee>> responseEntity = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Employee>>() {
                });
        List<Employee> employees = responseEntity.getBody();
        return employees;
    }

    public Employee getEmployee(int employeeId) throws RestClientException {
        Employee employee = null;
        employee = restTemplate.getForObject(URL + "/" + employeeId, Employee.class);
        return employee;
    }

    public void saveEmployee(Employee employee) {
        if (employee.getId() == 0) {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, employee, String.class);
            System.out.println("New employee was added to the database...");
            System.out.println(responseEntity.getBody());
        } else {
            restTemplate.put(URL, employee);
            System.out.println("Employee with id = " + employee.getId() + " was updated...");
        }
    }

    public void deleteEmployee(int employeeId) {

        try {
            restTemplate.delete(URL + "/" + employeeId);
            System.out.println("Employee with id = " + employeeId + " was deleted");
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }
    }


}
