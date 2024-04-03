package marat.DAO;

import marat.models.CommonEntity;

import java.util.Collection;
import java.util.List;

public interface CommonDAO<T extends CommonEntity<ID>, ID> {
    T getById(ID id);

    List<T> getAll();

    void save(T entity);

    void saveCollection(List<T> entities);

    void delete(T entity);

    void deleteById(ID id);

    void update(T entity);
}
