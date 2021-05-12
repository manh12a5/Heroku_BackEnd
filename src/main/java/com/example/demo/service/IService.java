package com.example.demo.service;

import java.util.List;

public interface IService <C> {
    List<C> findAll();
    C findById (Long id);
    C save (C c);
    void delete (Long id);
}
