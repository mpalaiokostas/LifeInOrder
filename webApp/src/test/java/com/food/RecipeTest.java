package com.food;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by michail on 10/04/16.
 */
public class RecipeTest {

  Recipe myRecipe;

  @BeforeMethod
  public void setUp() throws Exception {
    myRecipe = new Recipe();
    myRecipe.setTitle("Fakes");
  }

  @Test
  public void testRecipeHasName() throws Exception {
    Assert.assertTrue(myRecipe.hasTitle());
  }
}
