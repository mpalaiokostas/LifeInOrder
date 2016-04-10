package com.food;

/**
 * Created by michail on 10/04/16.
 */
public class Recipe {
  private String title;

  public boolean hasTitle() {
    return (title!="")?true:false;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
