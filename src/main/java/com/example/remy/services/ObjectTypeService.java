package com.example.remy.services;

import com.example.remy.dao.ObjectTypeDao;
import com.example.remy.entities.ObjectType;
import com.example.remy.exceptions.Error;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ObjectTypeService implements IService<ObjectType> {

    private ObjectTypeDao dao;

    @Context
    private HttpHeaders requestHeaders;

    private String getHeaderVersion(){
        return requestHeaders.getRequestHeader("version").get(0);
    }

    @GET
    @Path("/{object_type_id}")
    @Override
    public Response getObject(@PathParam("object_type_id") Long id) throws SQLException {
        ObjectType object = dao.getById(id);
        if(object != null)
            return ResponseCreator.
                    success(getHeaderVersion(), object);
        else
            return ResponseCreator.
                    error(404, Error.NOT_FOUND.getCode(), getHeaderVersion());
    }

    @DELETE
    @Path("/{object_type_id}")
    @Override
    public Response removeObject(@PathParam("object_type_id") Long id) throws SQLException {
        ObjectType attribute = dao.getById(id);
        if(attribute != null){
            dao.delete(attribute);
            return ResponseCreator.
                    success(getHeaderVersion(), attribute.toString() + " is removed");
        }
        return ResponseCreator.
                error(500, Error.SERVER_ERROR.getCode(), getHeaderVersion());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response createObject(ObjectType obj) throws IOException, SQLException {
        dao.insert(obj);
        ObjectType createdType = dao.getById(obj.getObject_type_id());
        if(createdType != null)
            return ResponseCreator.
                    success(getHeaderVersion(), createdType);
        else
            return ResponseCreator.
                    error(500, Error.SERVER_ERROR.getCode(), getHeaderVersion());
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response updateObject(ObjectType obj) throws SQLException {
        dao.update(obj);
        ObjectType updatedType = dao.getById(obj.getObject_type_id());
        if(updatedType != null)
            return ResponseCreator.
                    success(getHeaderVersion(), updatedType);
        else
            return ResponseCreator.
                    error(404, Error.NOT_FOUND.getCode(), getHeaderVersion());
    }

    //TODO: do this
    @GET
    @Override
    public Response getAll() throws SQLException {
        List<ObjectType> types = dao.getAll();
        if(types != null)
        {
            GenericEntity<List<ObjectType>> entity = new GenericEntity<List<ObjectType>>(types){};
            return ResponseCreator.success(getHeaderVersion(), entity);
        }
        else return ResponseCreator.
                error(404, Error.NOT_FOUND.getCode(), getHeaderVersion());
    }
}