package com.example.services.impl;

import com.example.dto.DtoDepartment;
import com.example.dto.DtoDepartmentIU;
import com.example.entites.Department;
import com.example.repository.DepartmentRepository;
import com.example.services.IDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    public DepartmentRepository departmentRepository;

    @Override
    public DtoDepartment saveDepartment(DtoDepartmentIU departmentIU) {
        DtoDepartment dtoDepartment = new DtoDepartment();
        Department department = new Department();
        BeanUtils.copyProperties(departmentIU, department);
        Department dbDepartment = departmentRepository.save(department);
        BeanUtils.copyProperties(dbDepartment, dtoDepartment);
        return dtoDepartment;
    }

    @Override
    public List<DtoDepartment> getAllDepartment() {
        List<DtoDepartment> dtoDepartmentList = new ArrayList<>();
        List<Department> department = departmentRepository.findAll();
        for (Department department1 : department) {
            DtoDepartment dtoDepartment = new DtoDepartment();
            BeanUtils.copyProperties(department1, dtoDepartment);
            dtoDepartmentList.add(dtoDepartment);
        }
        return dtoDepartmentList;
    }

    @Override
    public DtoDepartment getByDepartmentId(Long id) {
        DtoDepartment dtoDepartment = new DtoDepartment();
        Optional<Department> optional = departmentRepository.findById(id);

        Department department = optional.get();
        BeanUtils.copyProperties(department, dtoDepartment);

        return dtoDepartment;
    }

    @Override
    public void deleteDepartment(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isPresent()) {
            departmentRepository.delete(optional.get());
        }
    }

    @Override
    public DtoDepartment updateDepartment(Long id, DtoDepartmentIU dtoDepartmentIU) {
        DtoDepartment dtoDepartment = new DtoDepartment();
        Optional<Department> optional = departmentRepository.findById(id);
        if (optional.isPresent()) {
            Department department = optional.get();
            department.setDepartmentName(dtoDepartmentIU.getDepartmentName());

            Department dbDepartment = departmentRepository.save(department);
            BeanUtils.copyProperties(dbDepartment, dtoDepartment);
        }

        return dtoDepartment;
    }
}
