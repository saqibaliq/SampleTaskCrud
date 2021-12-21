package com.sample.task.transformer;


import com.sample.task.dto.DepartmentDTO;
import com.sample.task.model.DepartmentModel;

import java.util.ArrayList;
import java.util.List;

public class DepartmentTransformer {

    public static DepartmentModel getDepartmentBeanFromDTO(DepartmentDTO departmentDTO) {
        DepartmentModel departmentBean = new DepartmentModel();
        if (departmentDTO.getId() != null && !departmentDTO.getId().equals("")) {
            departmentBean.setId(Long.parseLong(departmentDTO.getId()));
        }
        if (departmentDTO.getDepartmentName() != null && !departmentDTO.getDepartmentName().equals("")) {
            departmentBean.setDepartmentName(departmentDTO.getDepartmentName());
        }
        if (departmentDTO.getManagerId() != null && !departmentDTO.getManagerId().equals("")) {
            departmentBean.setManagerId(Integer.parseInt(departmentDTO.getManagerId()));
        }

        return departmentBean;
    }

    public static DepartmentDTO getDepartmentDTOFromBean(DepartmentModel departmentBean) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        if (departmentBean.getId() != null && !departmentBean.getId().equals("")) {
            departmentDTO.setId(departmentBean.getId().toString());
        }
        if (departmentBean.getDepartmentName() != null && !departmentBean.getDepartmentName().equals("")) {
            departmentDTO.setDepartmentName(departmentBean.getDepartmentName());
        }
        if (departmentBean.getManagerId() != null && !departmentBean.getManagerId().equals("")) {
            departmentDTO.setManagerId(departmentBean.getManagerId().toString());
        }

        return departmentDTO;
    }

    public static List<DepartmentDTO> listOfDepartmentDTOFromBean(List<DepartmentModel> departmentBeans) {
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        for (DepartmentModel departmentBean : departmentBeans) {
            DepartmentDTO departmentDTO = DepartmentTransformer.getDepartmentDTOFromBean(departmentBean);
            departmentDTOS.add(departmentDTO);
        }
        return departmentDTOS;
    }

}
