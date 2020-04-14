package com.hendisantika.springbootdatatabledemo.controller;

import com.hendisantika.springbootdatatabledemo.entity.Employee;
import com.hendisantika.springbootdatatabledemo.init.SalarySpecification;
import com.hendisantika.springbootdatatabledemo.repository.EmployeeRepository;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-datatable-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 15/04/20
 * Time: 06.25
 */
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public DataTablesOutput<Employee> list(@Valid DataTablesInput input) {
        return employeeRepository.findAll(input);
    }

    @PostMapping
    public DataTablesOutput<Employee> listPOST(@Valid @RequestBody DataTablesInput input) {
        return employeeRepository.findAll(input);
    }

    @GetMapping(value = "advanced")
    public DataTablesOutput<Employee> listAdvanced(@Valid DataTablesInput input) {
        return employeeRepository.findAll(input, new SalarySpecification(input), new ExcludeAnalystsSpecification());
    }

    private class ExcludeAnalystsSpecification implements Specification<Employee> {
        @Override
        public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            return criteriaBuilder.notEqual(root.get("position"), "Analyst");
        }
    }


}
