package com.example.services;

import com.example.dto.DtoEmployee;
import com.example.entites.Employee;

import java.util.List;

public interface IEmployeeService {

    public List<DtoEmployee> findAllEmployees();
}
