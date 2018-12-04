package com.example.remy.models;

import com.example.remy.dao.ObjectDao;
import com.example.remy.dao.ParamDao;
import com.example.remy.entities.Object;
import com.example.remy.entities.Param;

import java.util.ArrayList;
import java.util.List;

import static com.example.remy.models.Constants.URL_OBJECTS;
import static com.example.remy.models.Constants.URL_PARAMS;

public class Ingredient {
    private Long ingrId;
    private String ingrName;

    private static ObjectDao objectDao = new ObjectDao();
    private static ParamDao paramDao = new ParamDao();
    private static List<Object> objects;
    private static List<Param> params;

    public Ingredient(Long id, String name)
    {
        ingrId = id;
        ingrName = name;
    }

    public static List<Ingredient> getAllIngredients()
    {
        objects = objectDao.getAll(URL_OBJECTS);
        params = paramDao.getAll(URL_PARAMS);
        List<Ingredient> ingredients = new ArrayList<>();
        for (Object obj : objects)
        {
            if(obj.getObject_type_id() == 3)
            {
                Ingredient ingredient = new Ingredient(obj.getObject_id(), obj.getName());
                ingredients.add(ingredient);
            }
        }
        return ingredients;
    }

    public Long getIngrId() {
        return ingrId;
    }

    public String getIngrName() {
        return ingrName;
    }

    public void setIngrId(Long ingrId) {
        this.ingrId = ingrId;
    }

    public void setIngrName(String ingrName) {
        this.ingrName = ingrName;
    }
}
