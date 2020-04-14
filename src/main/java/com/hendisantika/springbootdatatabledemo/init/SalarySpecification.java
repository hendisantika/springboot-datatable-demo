package com.hendisantika.springbootdatatabledemo.init;

import com.hendisantika.springbootdatatabledemo.entity.Employee;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-datatable-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 15/04/20
 * Time: 06.29
 */
public class SalarySpecification implements Specification<Employee> {
    private final Integer minSalary;
    private final Integer maxSalary;

    public SalarySpecification(DataTablesInput input) {
        String salaryFilter = input.getColumn("salary").getSearch().getValue();
        if (!StringUtils.hasText(salaryFilter)) {
            minSalary = maxSalary = null;
            return;
        }
        String[] bounds = salaryFilter.split(";");
        minSalary = getValue(bounds, 0);
        maxSalary = getValue(bounds, 1);
    }

    public Integer getValue(String[] bounds, int index) {
        if (bounds.length > index && StringUtils.hasText(bounds[index])) {
            try {
                return Integer.valueOf(bounds[index]);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Expression<Integer> salary = root.get("salary").as(Integer.class);
        if (minSalary != null && maxSalary != null) {
            return criteriaBuilder.between(salary, minSalary, maxSalary);
        } else if (minSalary != null) {
            return criteriaBuilder.greaterThanOrEqualTo(salary, minSalary);
        } else if (maxSalary != null) {
            return criteriaBuilder.lessThanOrEqualTo(salary, maxSalary);
        } else {
            return criteriaBuilder.conjunction();
        }
    }

}