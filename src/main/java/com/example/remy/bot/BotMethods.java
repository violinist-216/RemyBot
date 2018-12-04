package com.example.remy.bot;

import java.util.*;
import com.example.remy.dao.*;
import com.example.remy.entities.*;
import com.example.remy.entities.Object;
import com.example.remy.models.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BotMethods {

    public static void main(String[] args)
    {
        String url = "http://localhost:8080/botapi/attributes";
        BotMethods methods = new BotMethods();

        ArrayList<Attribute> attrs = attributeDao.getAll(url);

        Attribute a = attributeDao.getById(url + "/1", Attribute.class);
        for(Attribute attr : attrs) {
            //attr.cast(new Attribute());
            System.out.println(attr.getName());
        }
        System.out.println(a.getName());
    }

    private static AttributeDao attributeDao = new AttributeDao();
    private ObjectDao objectDao = new ObjectDao();
    private ObjectTypeDao typeDao = new ObjectTypeDao();
    private ParamDao paramDao = new ParamDao();

    public static List<Recipe> getRecipesByUser(User user)
    {
        List<Recipe> recipes = new ArrayList<>();
        List<UsersRecipes> urs = UsersRecipes.getAllUsersRecipes();
        for(UsersRecipes ur : urs)
        {
            if(ur.getUserId().equals(user.getUserId()))
                recipes.add(ur.getRecipe());
        }
        return recipes;
    }
}
