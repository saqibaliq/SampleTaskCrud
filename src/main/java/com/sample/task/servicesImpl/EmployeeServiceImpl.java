package com.sample.task.servicesImpl;


import com.sample.task.model.EmployeeModel;
import com.sample.task.repository.EmployeeRepository;
import com.sample.task.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeModel saveEmployee(EmployeeModel employeeBean) {
        return employeeRepository.save(employeeBean);
    }

    @Override
    public EmployeeModel updateEmployee(EmployeeModel employeeBean) {
        if (employeeBean.getEmployeeId() != null) {
            Optional<EmployeeModel> persisted = employeeRepository.findById(employeeBean.getEmployeeId());
            if (persisted == null) {
                return null;
            }
            EmployeeModel updated = employeeRepository.save(employeeBean);
            return updated;
        }
        return null;
    }

    @Override
    public EmployeeModel deleteEmployee(EmployeeModel employeeBean) {
        if (employeeBean.getEmployeeId() != null) {
            employeeRepository.delete(employeeBean);
            return employeeBean;
        }
        return null;
    }

    @Override
    public List<EmployeeModel> listOfEmployees() {
        List<EmployeeModel> employeeBeans = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeBean -> employeeBeans.add(employeeBean));
        return employeeBeans;
    }

    @Override
    public EmployeeModel findById(Long id) {
        Optional<EmployeeModel> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<EmployeeModel> findAllByFilterWithPaging(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
