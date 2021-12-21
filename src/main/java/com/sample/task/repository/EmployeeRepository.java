package com.sample.task.repository;



import com.sample.task.model.EmployeeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long>, JpaSpecificationExecutor<EmployeeModel> {
    Page<EmployeeModel> findAll(Pageable pageable);
}