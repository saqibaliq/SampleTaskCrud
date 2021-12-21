package com.sample.task.repository;


import com.sample.task.model.DepartmentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long>, JpaSpecificationExecutor<DepartmentModel> {
    Page<DepartmentModel> findAll(Pageable pageable);
}