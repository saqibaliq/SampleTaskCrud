package com.sample.task.controllers;



import com.sample.task.dto.DepartmentDTO;
import com.sample.task.dto.PageDTO;
import com.sample.task.dto.StatusDTO;
import com.sample.task.model.DepartmentModel;
import com.sample.task.services.DepartmentService;
import com.sample.task.transformer.DepartmentTransformer;
import com.sample.task.util.PaginationUtil;
import com.sample.task.util.Utility;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    //creating Post mapping that get the list of departments from the database
    @PostMapping("/getAllDepartments")
    public PageDTO getAll(@ModelAttribute PaginationUtil paginationUtil) {
        Map<String, String> params = new HashMap<>();
        System.out.println(paginationUtil.toString());
        params.put("currentPage", paginationUtil.getCurrentPage().toString());
        params.put("itemsPerPage", paginationUtil.getItemsPerPage().toString());
        params.put("sortBy", paginationUtil.getSortBy());
        params.put("direction", paginationUtil.getDirection());
        Page<DepartmentModel> page = departmentService.findAllByFilterWithPaging(Utility.createPageRequest(params));
        return new PageDTO(DepartmentTransformer.listOfDepartmentDTOFromBean(page.getContent()), page.getTotalElements(), page.getTotalPages());
    }

    //creating post mapping that save the department detail in the database
    @PostMapping("/createDepartment")
    private ResponseEntity<StatusDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            DepartmentModel departmentBean = DepartmentTransformer.getDepartmentBeanFromDTO(departmentDTO);
            DepartmentModel department = departmentService.saveDepartment(departmentBean);
            return new ResponseEntity<>(new StatusDTO(1, "Department Added Successfully ", DepartmentTransformer.getDepartmentDTOFromBean(department)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + e), HttpStatus.OK);
        }
    }

    //creating post mapping that update the department in the database
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Department Id ", readOnly = true, dataType = "string", paramType = "query", required = true),
    })
    @PostMapping("/updateDepartment")
    private ResponseEntity<StatusDTO> updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {

            DepartmentModel departmentBean = departmentService.findById(Long.parseLong(departmentDTO.getId()));
            if (departmentBean == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Department Id Not Found "), HttpStatus.NOT_FOUND);
            }
            DepartmentModel department = DepartmentTransformer.getDepartmentBeanFromDTO(departmentDTO);
            DepartmentModel updateDepartment = departmentService.updateDepartment(department);
            return new ResponseEntity<>(new StatusDTO(1, "Department Update Successfully ", DepartmentTransformer.getDepartmentDTOFromBean(updateDepartment)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + e), HttpStatus.OK);
        }
    }

    //creating get mapping that delete the department from the database
    @GetMapping(value = "/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable Long id) {

        DepartmentModel departmentBean = departmentService.findById(id);
        try {
            if (departmentBean == null) {
                return new ResponseEntity<>(new StatusDTO(0, "Department Id Not Found "), HttpStatus.NOT_FOUND);

            } else {
                DepartmentModel department = departmentService.deleteDepartment(departmentBean);
                return new ResponseEntity<>(new StatusDTO(1, "Department Delete Successfully ", DepartmentTransformer.getDepartmentDTOFromBean(department)), HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + e), HttpStatus.OK);

        }
    }

    //creating get mapping that get the department from the database
    @GetMapping(value = "/view/{id}")
    public ResponseEntity<StatusDTO> findById(@PathVariable Long id) {
        DepartmentModel departmentBean;
        DepartmentDTO departmentDTO = null;
        try {
            departmentBean = departmentService.findById(id);
            if (departmentBean != null) {
                departmentDTO = DepartmentTransformer.getDepartmentDTOFromBean(departmentBean);
                return new ResponseEntity<>(new StatusDTO(1, "Department Found Successfully ", departmentDTO), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new StatusDTO(0, "Department Id Not Found "), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + e), HttpStatus.OK);
        }
    }
}
