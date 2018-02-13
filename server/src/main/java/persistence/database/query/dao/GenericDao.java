package persistence.database.query.dao;

/**
 * generic interface which will provide methods to implement for queries like save,update,delete,read
 */
public interface GenericDao<T> {
    void save(T obj);

    void update(T obj);

    T findById(long id);

    void delete(T obj);

    void getAllData();

    void clearTable();
}
