package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class EmpControllerTest {

    private EmpController empController;

    @BeforeEach
    void setUp() {
        empController = new EmpController();
    }

    @Test
    void testGetEmployee() {
        Employee employee = empController.getEmployee();
        
        assertEquals("John Doe", employee.getName());
        assertEquals(30, employee.getAge());
    }

    @Test
    void testAddEmployeeValidData() {
        Employee validEmployee = new Employee("Alice", 25);
        ResponseEntity<String> response = empController.addEmployee(validEmployee);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User added", response.getBody());
    }
}
