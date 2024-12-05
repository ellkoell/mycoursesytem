package dataaccess;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T,I> {


    Optional<T> insert(T entity);
    Optional<T> getbyId(I id);
    List<T> getAll();
    Optional<T> update(T entity);
    void deletebyId(I id);

}
