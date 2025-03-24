package com.example.controller;


import com.example.dto.DtoStudent;
import com.example.dto.DtoStudentIU;

import java.util.List;

public interface IStudentController {

    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU);

    public List<DtoStudent> getAllStudent();

    public DtoStudent getStudentById(Integer id);

    public void deleteStudent(Integer id);

    public DtoStudent updateStudent(Integer id ,DtoStudentIU dtoStudentIU);

}
