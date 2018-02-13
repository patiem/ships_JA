package persistence.database.query.dao;

import java.util.List;

/**
 * generic interface which will provide methods to implement for queries like save,update,delete,read
 */
public interface GenericDao<T> {
    void save(T obj);

    void update(T obj);

    T findById(long id);

    void delete(T obj);

    List<T> getAllData();

    void clearAllData();
}
