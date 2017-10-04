package com.models;

public class Dish extends EntityWithID {

  public Dish(double aPrice, String aName, long anId)
  {
    price = aPrice;
    name = aName;
    id = anId;
  }

  double price;
  String name;
}
