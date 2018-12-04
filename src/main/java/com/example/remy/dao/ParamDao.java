package com.example.remy.dao;

import com.example.remy.entities.Param;

import java.sql.*;
import java.util.ArrayList;

public class ParamDao extends Dao<Param> {

    private static Param extractObjectFromResultSet(ResultSet rs) throws SQLException {
        Param object = new Param();
        object.setParam_id( rs.getLong("param_id") );
        object.setText_value(rs.getString("text_value"));
        object.setNumber_value( rs.getLong("number_value") );
        object.setBool_value(rs.getByte("bool_value"));
        object.setAttr_id(rs.getLong("attr_id"));
        object.setObject_id(rs.getLong("object_id"));
        return object;
    }

    public Param getById(Long id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM params WHERE param_id=" + id);
            if(rs.next())
            {
                return extractObjectFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Param> getAll() {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM params");
            ArrayList<Param> objects = new ArrayList<>();
            while(rs.next())
            {
                Param object = extractObjectFromResultSet(rs);
                objects.add(object);
            }
            return objects;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void insert(Param object) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO params VALUES (?, ?, ?, ?, ?, NULL)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, object.getText_value());
            ps.setLong(2, object.getNumber_value());
            ps.setByte(3, object.getBool_value());
            ps.setLong(4, object.getAttr_id());
            ps.setLong(5, object.getObject_id());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                object.setParam_id(rs.getLong(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Param object) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE params SET text_value=?, number_value=?, bool_value=?, attr_id=?, object_id=? WHERE param_id=?");
            ps.setString(1, object.getText_value());
            ps.setLong(2, object.getNumber_value());
            ps.setByte(3, object.getBool_value());
            ps.setLong(4, object.getAttr_id());
            ps.setLong(5, object.getObject_id());
            ps.setLong(6, object.getParam_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Param object) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM params WHERE param_id=" + object.getParam_id());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}