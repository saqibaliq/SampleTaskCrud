package com.sample.task.servicesImpl;


import com.sample.task.model.DepartmentModel;
import com.sample.task.repository.DepartmentRepository;
import com.sample.task.services.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentModel saveDepartment(DepartmentModel departmentBean) {
        return departmentRepository.save(departmentBean);
    }

    @Override
    public DepartmentModel updateDepartment(DepartmentModel departmentBean) {
        if (departmentBean.getId() != null) {
            Optional<DepartmentModel> persisted = departmentRepository.findById(departmentBean.getId());
            if (persisted == null) {
                return null;
            }
            DepartmentModel updated = departmentRepository.save(departmentBean);
            return updated;
        }
        return null;
    }

    @Override
    public DepartmentModel deleteDepartment(DepartmentModel departmentBean) {
        if (departmentBean.getId() != null) {
            departmentRepository.delete(departmentBean);
            return departmentBean;
        }
        return null;

    }

    @Override
    public List<DepartmentModel> listOfDepartments() {
        List<DepartmentModel> departmentBeans = new ArrayList<>();
        departmentRepository.findAll().forEach(departmentBean -> departmentBeans.add(departmentBean));
        return departmentBeans;
    }

    @Override
    public DepartmentModel findById(Long id) {
        Optional<DepartmentModel> optional = departmentRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<DepartmentModel> findAllByFilterWithPaging(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }
}
