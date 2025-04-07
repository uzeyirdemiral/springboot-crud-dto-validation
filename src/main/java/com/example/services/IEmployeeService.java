package com.example.services;

import com.example.dto.DtoEmployee;
import com.example.dto.DtoEmployeeIU;
import com.example.entites.Employee;

import java.util.List;

public interface IEmployeeService {


    public DtoEmployee saveEmployee(DtoEmployeeIU dtoEmployeeIU);

    public List<DtoEmployee> findAllEmployees();

    public DtoEmployee findByEmployeeId(Long id);

    public void deleteEmployee(Long id);

    public  DtoEmployee updateEmployee(Long id ,DtoEmployeeIU dtoEmployeeIU);
}
