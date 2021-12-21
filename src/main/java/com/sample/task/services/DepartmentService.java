package com.sample.task.services;


import com.sample.task.model.DepartmentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface DepartmentService {
    DepartmentModel saveDepartment(DepartmentModel departmentBean);

    DepartmentModel updateDepartment(DepartmentModel departmentBean);

    DepartmentModel deleteDepartment(DepartmentModel departmentBean);

    List<DepartmentModel> listOfDepartments();

    DepartmentModel findById(Long id);

    Page<DepartmentModel> findAllByFilterWithPaging(Pageable pageable);
}
