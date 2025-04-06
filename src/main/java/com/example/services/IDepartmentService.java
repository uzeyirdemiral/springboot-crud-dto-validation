package com.example.services;

import com.example.dto.DtoDepartment;
import com.example.dto.DtoDepartmentIU;

import java.util.List;

public interface IDepartmentService {

    public DtoDepartment saveDepartment(DtoDepartmentIU departmentIU);

    public List<DtoDepartment> getAllDepartment();

    public DtoDepartment getByDepartmentId(Long id);

    public void deleteDepartment(Long id);

    public  DtoDepartment updateDepartment(Long id,DtoDepartmentIU dtoDepartmentIU);
}
