package org.art.playground.web;

import static org.assertj.core.api.Assertions.assertThat;
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

@Transactional
@SpringBootTest
class WebApplicationTests {

    @Autowired
    private EmployeeRepository repository;

    @Test
    void shouldFindEmployeesV1() {
        String query = "lastName==Brown";

        Node rootNode = new RSQLParser().parse(query);
        Specification<Employee> spec = rootNode.accept(new CustomRsqlVisitor<>());

        List<Employee> employees = repository.findAll(spec);

        assertThat(employees).hasSize(1)
            .anySatisfy(employee -> assertThat(employee.getLastName()).isEqualTo("Brown"));
    }

    @Test
    void shouldFindEmployeesV2() {
        String query = "firstName!=John;lastName!=Brown";
        Specification<Employee> spec = buildSpec(query);

        List<Employee> employees = repository.findAll(spec);

        assertThat(employees).hasSize(1)
            .anySatisfy(employee -> assertThat(employee.getFirstName()).isEqualTo("Larry"));
    }

    @Test
    void shouldFindEmployeesV3() {
        String query = "firstName==John,lastName==Smith";
        Specification<Employee> spec = buildSpec(query);

        List<Employee> employees = repository.findAll(spec);

        assertThat(employees).hasSize(2)
            .anySatisfy(employee -> assertThat(employee.getFirstName()).isEqualTo("John"))
            .anySatisfy(employee -> assertThat(employee.getLastName()).isEqualTo("Smith"));
    }

    @Test
    void shouldFindEmployeesV5() {
        String query = "firstName=in=(John,Harry)";
        Specification<Employee> spec = buildSpec(query);

        List<Employee> employees = repository.findAll(spec);

        assertThat(employees).hasSize(2)
            .anySatisfy(employee -> assertThat(employee.getFirstName()).isEqualTo("John"))
            .anySatisfy(employee -> assertThat(employee.getFirstName()).isEqualTo("Harry"));
    }

    @Test
    void shouldFindEmployeesV6() {
        String query = "company.name==Company_2";
        Specification<Employee> spec = buildSpec(query);

        List<Employee> employees = repository.findAll(spec);

        assertThat(employees).hasSize(2)
            .anySatisfy(employee -> assertThat(employee.getFirstName()).isEqualTo("Harry"))
            .anySatisfy(employee -> assertThat(employee.getFirstName()).isEqualTo("Larry"));
    }

    private Specification<Employee> buildSpec(String query) {
        Node rootNode = new RSQLParser().parse(query);
        return rootNode.accept(new CustomRsqlVisitor<>());
    }

}
