package com.mygitgor.restaurant.mapper;

import java.util.List;

public interface ConfigMapper <T, E>{
    E toEntity(T t);
    T toDomain(E e);
    List<E> toEntity(List<T> t);
    List<T> toDomain(List<E> e);
}