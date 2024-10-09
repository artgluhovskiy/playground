package org.art.playground.web;

import java.util.List;
import org.art.playground.web.entity.Employee;
import org.art.playground.web.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WebApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldCreateEmployees() {
        List<Employee> all = employeeRepository.findAll();
        System.out.println(all);
    }

}
