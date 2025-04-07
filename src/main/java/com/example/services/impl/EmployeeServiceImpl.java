package com.example.services.impl;

import com.example.dto.DtoDepartment;
import com.example.dto.DtoEmployee;
import com.example.dto.DtoEmployeeIU;
import com.example.entites.Department;
import com.example.entites.Employee;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;
import com.example.services.IEmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DtoEmployee saveEmployee(DtoEmployeeIU dtoEmployeeIU) {
        Optional<Department> optional = departmentRepository.findById(dtoEmployeeIU.getDepartmentId());

        DtoEmployee dtoEmployee = new DtoEmployee();
        Employee employee = new Employee();
        BeanUtils.copyProperties(dtoEmployeeIU, employee);
        employee.setDepartment(optional.get());
        Employee dbEmployee = employeeRepository.save(employee);
        BeanUtils.copyProperties(dbEmployee, dtoEmployee);
        dtoEmployee.setDepartment(new DtoDepartment(dbEmployee.getDepartment().getId(), dbEmployee.getDepartment().getDepartmentName()));
        return dtoEmployee;
    }

    @Override
    public List<DtoEmployee> findAllEmployees() {
        List<DtoEmployee> dtoEmployees = new ArrayList<>();

        List<Employee> employees = employeeRepository.findAll();
        if (employees != null && !employees.isEmpty()) {
            for (Employee employee : employees) {
                DtoEmployee dtoEmployee = new DtoEmployee();
                BeanUtils.copyProperties(employee, dtoEmployee);

                dtoEmployee.setDepartment(new DtoDepartment(employee.getDepartment().getId(), employee.getDepartment().getDepartmentName()));
                dtoEmployees.add(dtoEmployee);
            }
        }

        return dtoEmployees;
    }

    @Override
    public DtoEmployee findByEmployeeId(Long id) {
        DtoEmployee dtoEmployee = new DtoEmployee();

        Optional<Employee> optional = employeeRepository.findById(id);

        Employee employee = optional.get();
        Department department = optional.get().getDepartment();
        DtoDepartment dtoDepartment = new DtoDepartment();
        BeanUtils.copyProperties(department, dtoDepartment);
        BeanUtils.copyProperties(employee, dtoEmployee);
        dtoEmployee.setDepartment(dtoDepartment);
        return dtoEmployee;
    }

    @Override
    public void deleteEmployee(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            employeeRepository.delete(optional.get());
        }
    }

    @Override
    public DtoEmployee updateEmployee(Long id, DtoEmployeeIU dtoEmployeeIU) {
        DtoEmployee dtoEmployee = new DtoEmployee();
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employee employee = optional.get();
            employee.setName(dtoEmployeeIU.getName());
            Optional<Department> optionalDepartment = departmentRepository.findById(dtoEmployeeIU.getDepartmentId());
            if (optionalDepartment.isPresent()) {
                Department department = optionalDepartment.get();
                employee.setDepartment(department);
            }

            Employee dbEmployee = employeeRepository.save(employee);
            BeanUtils.copyProperties(dbEmployee, dtoEmployee);
            dtoEmployee.setDepartment(new DtoDepartment(dbEmployee.getDepartment().getId(), dbEmployee.getDepartment().getDepartmentName()));
        }

        return dtoEmployee;
    }


}
