package com.example.remy.bot;

import java.util.*;
import com.example.remy.dao.*;
import com.example.remy.entities.*;
import com.example.remy.entities.Object;
import com.example.remy.models.*;

public class BotMethods {

    public static void main(String[] args)
    {
        BotMethods methods = new BotMethods();

        List<User> users = User.getAllUsers();
        for(User user:users)
        {
            System.out.println(user.toString());
        }

        List<Recipe> recipes = Recipe.getAllPecipes();
        for(Recipe recipe : recipes)
        {
            System.out.println(recipe.toString());
        }
    }

    private AttributeDao attributeDao = new AttributeDao();
    private ObjectDao objectDao = new ObjectDao();
    private ObjectTypeDao typeDao = new ObjectTypeDao();
    private ParamDao paramDao = new ParamDao();

    private List<Attribute> attributes = attributeDao.getAll();
    private List<Object> objects = objectDao.getAll();
    private List<ObjectType> types = typeDao.getAll();
    private List<Param> params = paramDao.getAll();


    public static List<Recipe> getRecipesByUser(User user)
    {
        List<Recipe> recipes = new ArrayList<>();
        List<UsersRecipes> urs = UsersRecipes.getAllUsersRecipes();
        for(UsersRecipes ur : urs)
        {
            if(ur.getUserId() == user.getUserId())
                recipes.add(ur.getRecipe());
        }
        return recipes;
    }
}
