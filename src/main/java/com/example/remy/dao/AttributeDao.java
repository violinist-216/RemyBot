package com.example.remy.dao;

import com.example.remy.entities.Attribute;

import java.sql.*;
import java.util.ArrayList;

public class AttributeDao extends Dao<Attribute> {

    private static Attribute extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Attribute attribute = new Attribute();
        attribute.setAttr_id( rs.getLong("attr_id") );
        attribute.setName( rs.getString("name") );
        attribute.setObject_type_id( rs.getLong("object_type_id") );
        return attribute;
    }

    public Attribute getById(Long id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM attributes WHERE attr_id=" + id);
            if(rs.next())
            {
                return extractOrderFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Attribute> getAll() {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM attributes");
            ArrayList<Attribute> attributes = new ArrayList<>();
            while(rs.next())
            {
                Attribute attribute = extractOrderFromResultSet(rs);
                attributes.add(attribute);
            }
            return attributes;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void insert(Attribute attribute) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO attributes VALUES (NULL, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, attribute.getName());
            ps.setLong(2, attribute.getObject_type_id());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                attribute.setAttr_id(rs.getLong(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Attribute attribute) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE attributes SET name=?, object_type_id=? WHERE attr_id=?");
            ps.setString(1, attribute.getName());
            ps.setLong(2, attribute.getObject_type_id());
            ps.setLong(3, attribute.getAttr_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Attribute attribute) {
        ConnectionFactory connector = new ConnectionFactory();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM attributes WHERE attr_id=" + attribute.getAttr_id());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}