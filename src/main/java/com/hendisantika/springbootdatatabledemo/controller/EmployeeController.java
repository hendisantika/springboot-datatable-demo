package com.hendisantika.springbootdatatabledemo.controller;

import com.hendisantika.springbootdatatabledemo.entity.Employee;
import com.hendisantika.springbootdatatabledemo.repository.EmployeeRepository;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
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
}
