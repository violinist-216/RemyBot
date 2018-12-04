package com.example.remy.models;

public class Ingredient {
    private Long ingrId;
    private String ingrName;

    public Ingredient(Long id, String name)
    {
        ingrId = id;
        ingrName = name;
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
