package com.hendisantika.springbootdatatabledemo.controller;

import com.hendisantika.springbootdatatabledemo.entity.Employee;
import com.hendisantika.springbootdatatabledemo.repository.EmployeeRepository;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = "/employees")
    public DataTablesOutput<Employee> list(@Valid DataTablesInput input) {
        return employeeRepository.findAll(input);
    }

    @PostMapping(value = "/employees")
    public DataTablesOutput<Employee> listPOST(@Valid @RequestBody DataTablesInput input) {
        return employeeRepository.findAll(input);
    }

    @GetMapping(value = "/employees-advanced")
    public DataTablesOutput<Employee> listAdvanced(@Valid DataTablesInput input) {
        return employeeRepository.findAll(input, new SalarySpecification(input), new ExcludeAnalystsSpecification());
    }

    private class SalarySpecification implements Specification<Employee> {
        private final Integer minSalary;
        private final Integer maxSalary;

        SalarySpecification(DataTablesInput input) {
            String salaryFilter = input.getColumn("salary").getSearch().getValue();
            if (!StringUtils.hasText(salaryFilter)) {
                minSalary = maxSalary = null;
                return;
            }
            String[] bounds = salaryFilter.split(";");
            minSalary = getValue(bounds, 0);
            maxSalary = getValue(bounds, 1);
        }

        private Integer getValue(String[] bounds, int index) {
            if (bounds.length > index && StringUtils.hasText(bounds[index])) {
                try {
                    return Integer.valueOf(bounds[index]);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        }
    }
