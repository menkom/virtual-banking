package info.mastera.repository.api;

import info.mastera.model.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface IBaseRepository<T extends BaseEntity> {

    Collection<T> getAll();

    void importData();

    void saveData();

    Optional<T> findById(String id);

    boolean save(T entity);

    boolean delete(String id);
}
