package com.example.remy.models;

import com.example.remy.dao.ObjectDao;
import com.example.remy.dao.ParamDao;
import com.example.remy.entities.Object;
import com.example.remy.entities.Param;

import java.util.ArrayList;
import java.util.List;

import static com.example.remy.models.Constants.URL_OBJECTS;
import static com.example.remy.models.Constants.URL_PARAMS;

public class User {
    private Long userId;
    private String userName;
    private boolean isAdmin;


    private static ObjectDao objectDao = new ObjectDao();
    private static ParamDao paramDao = new ParamDao();
    private static List<Object> objects;
    private static List<Param> params;

    public User(Long id, String name, boolean admin)
    {
        userId = id;
        userName = name;
        isAdmin = admin;
    }

    public static List<User> getAllUsers()
    {
        objects = objectDao.getAll(URL_OBJECTS);
        params = paramDao.getAll(URL_PARAMS);
        List<User> users = new ArrayList<>();
        for (Object obj : objects)
        {
            if(obj.getObject_type_id() == 1)
            {
                User user = new User(obj.getObject_id(), obj.getName(), false);
                for (Param param : params)
                {
                    if(param.getAttr_id() == 1 && param.getObject_id().equals(user.getUserId()))
                    {
                        user.setAdmin(param.getBool_value() == 1);
                    }
                }
                users.add(user);
            }
        }
        return users;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return this.userName;
    }
}
