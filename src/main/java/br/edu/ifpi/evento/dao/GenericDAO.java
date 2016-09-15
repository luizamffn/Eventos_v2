package br.edu.ifpi.evento.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T> {
	T save(T objeto);

    void delete(T objeto);

    T findByID(Serializable id);

    List<T> findAll();
}
