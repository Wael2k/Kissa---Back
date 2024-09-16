package PFA.project.service.impl;

import PFA.project.dao.TeacherRepository;
import PFA.project.dao.entity.Teacher;
import PFA.project.persistence.service.AbstractServiceImpl;
import PFA.project.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends AbstractServiceImpl<Teacher, TeacherRepository> implements TeacherService {
    public TeacherServiceImpl(TeacherRepository abstractRepository) {
        super(abstractRepository);
    }
    @Override
    public Teacher createOrUpdateTeacher(Teacher teacher) {
        return super.createOrUpdate(teacher);
    }
}
