package org.art.playground.web.controller;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import java.util.List;
import org.art.playground.web.rsql.CustomRsqlVisitor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@SuppressWarnings("unchecked")
public abstract class SearchControllerMixin<T> {

    public abstract JpaSpecificationExecutor<T> getRepository();

    @GetMapping("/search")
    public List<T> search(@PathVariable String query) {
        Node rootNode = new RSQLParser().parse(query);
        Specification<Object> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return getRepository().findAll((Specification<T>) spec);
    }
}
