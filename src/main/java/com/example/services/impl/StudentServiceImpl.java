package com.example.services.impl;

import com.example.dto.DtoCourse;
import com.example.dto.DtoStudent;
import com.example.dto.DtoStudentIU;
import com.example.entites.Course;
import com.example.entites.Student;
import com.example.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.services.IStudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    public StudentRepository studentRepository;

    @Override
    public DtoStudent saveStudent(DtoStudentIU dtoStudentIU) {
        DtoStudent response = new DtoStudent();
        Student student = new Student();
        BeanUtils.copyProperties(dtoStudentIU, student);

        Student dbStudent = studentRepository.save(student);
        BeanUtils.copyProperties(dbStudent, response);


        return response;
    }

    @Override
    public List<DtoStudent> getAllStudents() {
        List<DtoStudent> dtoStudentList = new ArrayList<>();

        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            DtoStudent dtoStudent = new DtoStudent();
            BeanUtils.copyProperties(student, dtoStudent);
            dtoStudentList.add(dtoStudent);
        }
        return dtoStudentList;
    }

    @Override
    public DtoStudent getStudentById(Integer id) {
        DtoStudent dtoStudent = new DtoStudent();
        Optional<Student> optional = studentRepository.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        Student student = optional.get();
        BeanUtils.copyProperties(student, dtoStudent);

        if (student.getCourses() != null && !student.getCourses().isEmpty()) {
            for (Course course : student.getCourses()) {
                DtoCourse dtoCourse = new DtoCourse();
                BeanUtils.copyProperties(course, dtoCourse);
                dtoStudent.getCourses().add(dtoCourse);
            }
        }
        return dtoStudent;
    }

    @Override
    public void deleteStudent(Integer id) {
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            studentRepository.delete(optional.get());
        }

    }

    @Override
    public DtoStudent updateStudent(Integer id, DtoStudentIU dtoStudentIU) {
        DtoStudent dtoStudent = new DtoStudent();
        Optional<Student> optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            Student dbStudent = optional.get();

            dbStudent.setFirstName(dtoStudentIU.getFirstName());
            dbStudent.setLastName(dtoStudentIU.getLastName());
            dbStudent.setBirthOfDate(dtoStudentIU.getBirthOfDate());

            Student updateStudent = studentRepository.save(dbStudent);

            BeanUtils.copyProperties(updateStudent, dtoStudent);
            return dtoStudent;
        }

        return null;
    }
}
