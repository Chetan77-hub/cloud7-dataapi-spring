package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = EmpController.class)
@WebMvcTest(controllers = EmpController.class)
public class EmpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetEmployee() throws Exception {
        mockMvc.perform(get("/employee/getemployee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(30));
    }

    @Test
    void testAddEmployeeValid() throws Exception {
        Employee employee = new Employee("Alice", 28);
        mockMvc.perform(post("/employee/addemployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(content().string("User added"));
    }

    @Test
    void testAddEmployeeInvalid() throws Exception {
        Employee employee = new Employee("", 0);
        mockMvc.perform(post("/employee/addemployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isBadGateway())
                .andExpect(content().string("Invalid data"));
    }
}
