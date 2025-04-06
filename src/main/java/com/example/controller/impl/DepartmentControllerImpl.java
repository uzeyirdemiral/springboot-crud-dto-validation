package com.example.controller.impl;

import com.example.controller.IDepartmentController;
import com.example.dto.DtoDepartment;
import com.example.dto.DtoDepartmentIU;
import com.example.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/department")
public class DepartmentControllerImpl implements IDepartmentController {


    @Autowired
    public IDepartmentService departmentService;

    @PostMapping("/save")
    @Override
    public DtoDepartment saveDepartment(@RequestBody DtoDepartmentIU departmentIU) {
        return departmentService.saveDepartment(departmentIU);
    }

    @GetMapping("/list")
    @Override
    public List<DtoDepartment> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("/list/{id}")
    @Override
    public DtoDepartment getByDepartmentId(@PathVariable(name = "id") Long id) {
        return departmentService.getByDepartmentId(id);
    }

    @DeleteMapping("list/{id}")
    @Override
    public void deleteDepartment(@PathVariable(name = "id") Long id) {
        departmentService.deleteDepartment(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public DtoDepartment updateDepartment(@PathVariable(name = "id") Long id, @RequestBody DtoDepartmentIU dtoDepartmentIU) {
        return departmentService.updateDepartment(id, dtoDepartmentIU);
    }
}
