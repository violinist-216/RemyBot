package com.example.remy.dao;

import com.example.remy.entities.Object;

import java.sql.*;
import java.util.ArrayList;

public class ObjectDao extends Dao<Object> {

    private static Object extractObjectFromResultSet(ResultSet rs) throws SQLException {
        Object object = new Object();
        object.setObject_id( rs.getLong("object_id") );
        object.setName( rs.getString("name") );
        object.setObject_type_id( rs.getLong("object_type_id") );
        return object;
    }

    public Object getById(Long id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM objects WHERE object_id=" + id);
            if(rs.next())
            {
                return extractObjectFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Object> getAll() {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM objects");
            ArrayList<Object> objects = new ArrayList<>();
            while(rs.next())
            {
                Object object = extractObjectFromResultSet(rs);
                objects.add(object);
            }
            return objects;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void insert(Object object) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO objects VALUES (NULL, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getName());
            ps.setLong(2, object.getObject_type_id());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                object.setObject_id(rs.getLong(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Object object) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE objects SET name=?, object_type_id=? WHERE object_id=?");
            ps.setString(1, object.getName());
            ps.setLong(2, object.getObject_type_id());
            ps.setLong(3, object.getObject_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Object object) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM objects WHERE object_id=" + object.getObject_id());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}