package DAO.Intefaces;

import java.util.List;
import java.util.UUID;

public interface DAO<T> {
    List<T> getAll();

    T get(UUID id);

    void save(T t);

    void update(T t);

    void delete(T t);

}
