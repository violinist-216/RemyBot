package com.example.remy.services;

import com.example.remy.dao.ParamDao;
import com.example.remy.entities.Param;
import com.example.remy.exceptions.Error;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ParamService implements IService<Param> {

    private ParamDao dao;

    @Context
    private HttpHeaders requestHeaders;

    private String getHeaderVersion(){
        return requestHeaders.getRequestHeader("version").get(0);
    }

    @GET
    @Path("/{param_id}")
    @Override
    public Response getObject(@PathParam("param_id") Long id) throws SQLException {
        Param object = dao.getById(id);
        if(object != null)
            return ResponseCreator.
                    success(getHeaderVersion(), object);
        else
            return ResponseCreator.
                    error(404, Error.NOT_FOUND.getCode(), getHeaderVersion());
    }

    @DELETE
    @Path("/{param_id}")
    @Override
    public Response removeObject(@PathParam("param_id") Long id) throws SQLException {
        Param attribute = dao.getById(id);
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
    public Response createObject(Param obj) throws IOException, SQLException {
        dao.insert(obj);
        Param createdType = dao.getById(obj.getParam_id());
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
    public Response updateObject(Param obj) throws SQLException {
        dao.update(obj);
        Param updatedType = dao.getById(obj.getParam_id());
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
        List<Param> types = dao.getAll();
        if(types != null)
        {
            GenericEntity<List<Param>> entity = new GenericEntity<List<Param>>(types){};
            return ResponseCreator.success(getHeaderVersion(), entity);
        }
        else return ResponseCreator.
                error(404, Error.NOT_FOUND.getCode(), getHeaderVersion());
    }
}