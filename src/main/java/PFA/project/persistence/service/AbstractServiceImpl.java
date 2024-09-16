package PFA.project.persistence.service;

import PFA.project.persistence.dao.entity.AbstractBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractServiceImpl<T extends AbstractBaseEntity, R extends JpaRepository<T, Long>> {

    protected R abstractRepository;

    public AbstractServiceImpl(R abstractRepository) {
        this.abstractRepository = abstractRepository;
    }

    public T createOrUpdate(T subject) {
        return abstractRepository.saveAndFlush(subject);
    }

    public Optional<T> getById(Long id) {
        return abstractRepository.findById(id);
    }

    public List<T> getAll() {
        return abstractRepository.findAll();
    }

    public void delete(T subject) {
        abstractRepository.delete(subject);
    }
}
