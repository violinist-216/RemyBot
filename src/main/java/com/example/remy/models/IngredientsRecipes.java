package com.example.remy.models;

public class IngredientsRecipes {
    private Long irId;
    private Long ingrId;
    private Long recipeId;

    public IngredientsRecipes(Long irId, Long ingrId, Long recipeId)
    {
        this.irId = irId;
        this.ingrId = ingrId;
        this.recipeId = recipeId;
    }

    public Long getIrId() {
        return irId;
    }

    public Long getIngrId() {
        return ingrId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setIrId(Long irId) {
        this.irId = irId;
    }

    public void setIngrId(Long ingrId) {
        this.ingrId = ingrId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }
}
