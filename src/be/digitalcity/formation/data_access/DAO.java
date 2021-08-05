package be.digitalcity.formation.data_access;

import java.util.List;

public interface DAO<T, ID> {

    // CREATE
    boolean insert(T toInsert);

    // READ
    T getOne(ID id);
    List<T> getAll();

    // UPDATE
    boolean update(T element);

    // DELETE
    boolean delete(ID id);

}
