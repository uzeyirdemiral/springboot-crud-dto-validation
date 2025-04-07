package com.example.controller.impl;

import com.example.controller.IEmployeeController;
import com.example.dto.DtoEmployee;
import com.example.dto.DtoEmployeeIU;
import com.example.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/employee")
public class EmployeeControllerImpl implements IEmployeeController {

    @Autowired
    private IEmployeeService employeeService;


    @PostMapping("/save")
    @Override
    public DtoEmployee saveEmployee(@RequestBody DtoEmployeeIU dtoEmployeeIU) {
        return employeeService.saveEmployee(dtoEmployeeIU);
    }

    @GetMapping("/list")
    @Override
    public List<DtoEmployee> findAllEmployees() {
        return employeeService.findAllEmployees();
    }


    @GetMapping("list/{id}")
    @Override
    public DtoEmployee findByEmployeeId(@PathVariable(name = "id") Long id) {
        return employeeService.findByEmployeeId(id);
    }


    @DeleteMapping("/delete/{id}")
    @Override
    public void deleteEmployee(@PathVariable(name = "id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/update/{id}")
    @Override
    public DtoEmployee updateEmployee(@PathVariable(name = "id") Long id, @RequestBody DtoEmployeeIU dtoEmployeeIU) {
        return employeeService.updateEmployee(id, dtoEmployeeIU);
    }
}
