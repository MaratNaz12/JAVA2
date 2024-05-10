package marat.DAO;

import marat.models.CommonEntity;

import java.util.List;

public interface CommonDAO<T extends CommonEntity> {
    T getById(Long id);

    List<T> getAll();

    void save(T entity);

    void saveCollection(List<T> entities);

    void delete(T entity);

    void deleteById(Long id);

    void update(T entity);
}
