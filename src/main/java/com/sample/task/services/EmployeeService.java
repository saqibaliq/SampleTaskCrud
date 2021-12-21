package com.sample.task.services;


import com.sample.task.model.EmployeeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    EmployeeModel saveEmployee(EmployeeModel employeeBean);

    EmployeeModel updateEmployee(EmployeeModel employeeBean);

    EmployeeModel deleteEmployee(EmployeeModel employeeBean);

    List<EmployeeModel> listOfEmployees();

    EmployeeModel findById(Long id);

    Page<EmployeeModel> findAllByFilterWithPaging(Pageable pageable);
}
