package org.art.playground.web;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import java.util.List;
import org.art.playground.web.entity.Employee;
import org.art.playground.web.repository.EmployeeRepository;
import org.art.playground.web.rsql.CustomRsqlVisitor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class WebApplicationTests {

    @Autowired
    private EmployeeRepository repository;

    @Test
    @Transactional
    void shouldFindEmployees() {
        String query = "((firstName!=John;lastName!=Brown),(lastName==Brown))";
        Node rootNode = new RSQLParser().parse(query);
        Specification<Employee> spec = rootNode.accept(new CustomRsqlVisitor<>());
        List<Employee> all = repository.findAll(spec);

//        List<Employee> all = repository.findAll();
        System.out.println(all);
    }

}
