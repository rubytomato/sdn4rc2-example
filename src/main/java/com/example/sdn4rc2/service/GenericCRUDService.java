package com.example.sdn4rc2.service;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.neo4j.repository.GraphRepository;

public abstract class GenericCRUDService<T,F> implements Pagination {

  private static final int DEPTH_LIST = 0;
  private static final int DEPTH_ENTITY = 1;

  public long count() {
    return getRepository().count();
  }

  public Iterable<T> findAll(int page, int size, String s) {
    Pageable pager = new PageRequest(currentPage(page), size, Direction.ASC, s);
    return getRepository().findAll(pager, DEPTH_LIST);
  }

  public T findOne(final Long id) {
    return getRepository().findOne(id, DEPTH_ENTITY);
  }

  public void findOneToForm(final Long id, F form) {
    T t = getRepository().findOne(id, DEPTH_ENTITY);
    convertToForm(t, form);
  }

  public T save(final F form, final int depth) {
    T t = convertToEntity(form);
    return getRepository().save(t, depth);
  }

  public void delete(final Long id) {
    getRepository().delete(id);
  }

  public abstract GraphRepository<T> getRepository();

  public abstract void convertToForm(T t, F form);

  public abstract T convertToEntity(F form);

  public abstract Iterable<Map<String, Object>> entityIDs();

  public abstract Integer maxEntityID();

}

