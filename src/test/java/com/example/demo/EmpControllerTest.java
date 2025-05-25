package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest  // No need for (classes = ...)
@AutoConfigureMockMvc
public class EmpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetEmployee() throws Exception {
        mockMvc.perform(get("/getemployee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee API working"));
    }

    @Test
    void testAddEmployee_Valid() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new Employee("John", 30));
        mockMvc.perform(post("/addemployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("User added"));
    }

    @Test
    void testAddEmployee_Invalid() throws Exception {
        mockMvc.perform(post("/addemployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadGateway());
    }

    static class Employee {
        public String name;
        public int age;

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Employee() {}
    }
}
