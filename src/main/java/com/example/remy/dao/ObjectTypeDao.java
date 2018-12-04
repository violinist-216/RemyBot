package com.example.remy.dao;

import com.example.remy.entities.ObjectType;

import java.sql.*;
import java.util.ArrayList;

public class ObjectTypeDao extends Dao<ObjectType> {

    private static ObjectType extractObjectFromResultSet(ResultSet rs) throws SQLException {
        ObjectType object = new ObjectType();
        object.setObject_type_id( rs.getLong("object_type_id") );
        object.setName( rs.getString("name") );
        return object;
    }

    public ObjectType getById(Long id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM object_types WHERE object_type_id=" + id);
            if(rs.next())
            {
                return extractObjectFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<ObjectType> getAll() {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM object_types");
            ArrayList<ObjectType> objects = new ArrayList<>();
            while(rs.next())
            {
                ObjectType object = extractObjectFromResultSet(rs);
                objects.add(object);
            }
            return objects;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void insert(ObjectType object) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO object_types VALUES (NULL, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                object.setObject_type_id(rs.getLong(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(ObjectType object) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE object_types SET name=? WHERE object_type_id=?");
            ps.setString(1, object.getName());
            ps.setLong(2, object.getObject_type_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(ObjectType object) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM object_types WHERE object_type_id=" + object.getObject_type_id());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}