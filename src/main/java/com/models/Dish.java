package com.models;

import org.bson.types.ObjectId;

public class Dish extends EntityWithID {

  public Dish(double aPrice, String aName, String anId)
  {
    price = aPrice;
    name = aName;
    id = anId;
  }

  double price;
  String name;
}
