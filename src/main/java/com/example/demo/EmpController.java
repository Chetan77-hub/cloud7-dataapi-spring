package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmpController {

    // Endpoint to return simple employee data
    @GetMapping("/getemployee")
    public Employee getEmployee() {
        return new Employee("John Doe", 30);
    }

    // Endpoint to add employee, expects JSON body with username and age
    @PostMapping("/addemployee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        if (employee.getName() != null && employee.getAge() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body("User added");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Invalid data");
        }
    }
}

class Employee {
    private String name;
    private int age;

    // Constructor, Getters, and Setters
    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
