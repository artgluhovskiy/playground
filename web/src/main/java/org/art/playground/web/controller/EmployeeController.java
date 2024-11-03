package org.art.playground.web.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.art.playground.web.entity.Employee;
import org.art.playground.web.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
@RequiredArgsConstructor
public class EmployeeController extends SearchControllerMixin<Employee> {

    private final EmployeeRepository repository;

    @GetMapping("/employee/{id}")
    public Employee findById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

}
