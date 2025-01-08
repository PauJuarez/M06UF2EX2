package pau.m06.dam.IES.HivernatePauEx2;

import java.util.List;

public interface DAOInterface<T> {
    void add(T item);
    List<T> getAll();
}
