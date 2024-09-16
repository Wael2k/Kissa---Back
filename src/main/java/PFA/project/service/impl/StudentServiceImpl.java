package PFA.project.service.impl;

import PFA.project.dao.StudentRepository;
import PFA.project.dao.entity.Student;
import PFA.project.persistence.service.AbstractServiceImpl;
import PFA.project.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl  extends AbstractServiceImpl<Student, StudentRepository> implements StudentService {
    public StudentServiceImpl(StudentRepository abstractRepository) {
        super(abstractRepository);
    }
    @Override
    public Student createOrUpdateStudent(Student student) {
        return super.createOrUpdate(student);
    }
}
