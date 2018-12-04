package com.example.remy.models;

import com.example.remy.dao.ObjectDao;
import com.example.remy.dao.ParamDao;
import com.example.remy.entities.Object;
import com.example.remy.entities.Param;
import com.example.remy.models.*;

import java.util.ArrayList;
import java.util.List;

public class UsersRecipes {
    private Long urId;
    private Long userId;
    private Long recipeId;
    public User user;
    public Recipe recipe;

    private static ObjectDao objectDao = new ObjectDao();
    private static ParamDao paramDao = new ParamDao();
    private static List<Object> objects = objectDao.getAll();
    private static List<Param> params = paramDao.getAll();

    public UsersRecipes(Long urId, Long userId, Long recipeId)
    {
        this.urId = urId;
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public static List<UsersRecipes> getAllUsersRecipes()
    {
        List<UsersRecipes> urs = new ArrayList<>();
        for(Object obj : objects)
        {
            if(obj.getObject_type_id() == 5)
            {
                UsersRecipes ur = new UsersRecipes(obj.getObject_id(), null, null);
                for(Param param : params)
                {
                    if(param.getAttr_id()==8 && param.getObject_id()==ur.getUrId())
                        ur.setUserId(param.getNumber_value());

                    if(param.getAttr_id()==9 && param.getObject_id()== ur.getUrId())
                        ur.setRecipeId(param.getNumber_value());
                }
                urs.add(ur);
            }
        }
        return urs;
    }

    public Long getUrId() {
        return urId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setUrId(Long urId) {
        this.urId = urId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public User getUser() {
        for(User user : User.getAllUsers())
        {
            if(user.getUserId() == this.getUserId())
                return user;
        }
        return null;
    }

    public Recipe getRecipe()
    {
        for(Recipe recipe : Recipe.getAllPecipes())
        {
            if(recipe.getRecipeId() == this.getRecipeId())
                return recipe;
        }
        return null;
    }
}
