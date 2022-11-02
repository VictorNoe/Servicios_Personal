package mx.edu.utez.personal4b.model;

import mx.edu.utez.personal4b.utils.Response;

import java.util.List;

public interface Repository<T> { //La te es para tomar las referencias, atributos o metodos de esa clase.
    List<T> findAll();
    T findById(Long id);
    Response<T> save(T object);
    Response<T> update(T object);
    Response<T> remove(Long id);
}
