package se.ifmo.ru.db;

import java.util.List;

public interface DBInteraction<T> {

    void create();
    void drop();
    void remove(String query);
    void add(T element);
    List<T> get(String query);
}
