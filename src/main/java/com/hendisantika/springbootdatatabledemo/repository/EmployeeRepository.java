package com.hendisantika.springbootdatatabledemo.repository;

import com.hendisantika.springbootdatatabledemo.entity.Employee;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-datatable-demo
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 15/04/20
 * Time: 06.23
 */
public interface EmployeeRepository extends DataTablesRepository<Employee, Integer> {
}
