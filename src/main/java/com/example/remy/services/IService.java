package com.example.remy.services;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;

public interface IService<T> {
    Response getObject(Long id) throws SQLException;
    Response removeObject(Long id) throws SQLException;
    Response createObject(T obj) throws IOException, SQLException;
    Response updateObject(T obj) throws SQLException;
    Response getAll() throws SQLException;
}