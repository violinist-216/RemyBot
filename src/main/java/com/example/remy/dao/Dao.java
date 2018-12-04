package com.example.remy.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public abstract class Dao<T> {
    abstract void insert(T entity) throws SQLException, IOException;
    abstract T getById(Long id) throws SQLException;
    abstract void update(T entity) throws SQLException;
    abstract void delete(T entity) throws SQLException;
    abstract List<T> getAll() throws SQLException;
}