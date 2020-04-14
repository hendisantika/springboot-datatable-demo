package com.hendisantika.springbootdatatabledemo.init;

import com.hendisantika.springbootdatatabledemo.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-datatable-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 15/04/20
 * Time: 06.24
 */
@Service
@Slf4j
class EmployeeInitializer {
    private static final int NUMBER_TO_GENERATE = 5_000;
    private final EmployeeRepository employeeRepository;

    public EmployeeInitializer(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
