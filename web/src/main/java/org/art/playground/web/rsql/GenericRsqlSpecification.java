package org.art.playground.web.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        From<?, ?> resolvedRoot = resovleRootEntity(root);
        String resolvedProperty = resolveProperty(property);

        List<Object> args = castArguments(resolvedRoot, resolvedProperty);

        return process(resolvedRoot, resolvedProperty, args, builder);
    }

    private From<?, ?> resovleRootEntity(Root<T> root) {
        From<?, ?> resolvedRoot = root;
        if (shouldBeJoined()) {
            List<String> properties = getProperties();
            for (int i = 0; i < properties.size() - 1; i++) {
                resolvedRoot = resolvedRoot.join(properties.get(i));
            }
        }
        return resolvedRoot;
    }

    private String resolveProperty(String property) {
        String resovledProperty = property;
        if (shouldBeJoined()) {
            List<String> propertyPath = getProperties();
            return propertyPath.getLast();
        }
        return resovledProperty;
    }

    private List<String> getProperties() {
        return Arrays.asList(property.split("\\."));
    }

    private boolean shouldBeJoined() {
        return property.contains(".");
    }

    private Predicate process(From<?, ?> root, String property, List<Object> args, CriteriaBuilder builder) {
        Object argument = args.getFirst();
        return switch (RsqlSearchOperation.getSimpleOperator(operator)) {
            case EQUAL: {
                if (argument instanceof String) {
                    yield builder.equal(root.get(property), argument.toString());
                } else if (argument == null) {
                    yield builder.isNull(root.get(property));
                } else {
                    yield builder.equal(root.get(property), argument);
                }
            }
            case NOT_EQUAL: {
                if (argument instanceof String) {
                    yield builder.notEqual(root.get(property), argument.toString());
                } else if (argument == null) {
                    yield builder.isNotNull(root.get(property));
                } else {
                    yield builder.notEqual(root.get(property), argument);
                }
            }
            case GREATER_THAN: {
                yield builder.greaterThan(root.get(property), argument.toString());
            }
            case GREATER_THAN_OR_EQUAL: {
                yield builder.greaterThanOrEqualTo(root.get(property), argument.toString());
            }
            case LESS_THAN: {
                yield builder.lessThan(root.get(property), argument.toString());
            }
            case LESS_THAN_OR_EQUAL: {
                yield builder.lessThanOrEqualTo(root.get(property), argument.toString());
            }
            case IN:
                yield root.get(property).in(args);
            case NOT_IN:
                yield builder.not(root.get(property).in(args));
        };
    }

    private List<Object> castArguments(From<?, ?> join, String property) {
        Class<? extends Object> type = join.get(property).getJavaType();
        return arguments.stream().map(arg -> {
            if (type.equals(Integer.class)) {
                return Integer.parseInt(arg);
            } else if (type.equals(Long.class)) {
                return Long.parseLong(arg);
            } else {
                return arg;
            }
        }).collect(Collectors.toList());
    }
}
