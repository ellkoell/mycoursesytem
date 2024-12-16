package dataaccess_student;

interface BaseRepository<T> {
    void save(T entity);
    T findById(int id);
    void delete(int id);
}