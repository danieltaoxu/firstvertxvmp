package com.models;

import org.bson.types.ObjectId;

public class Dish extends EntityWithID {

  public Dish(double aPrice, String aName, String anId)
  {
    price = aPrice;
    name = aName;
    id = anId;
  }

  public double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(String id) {
    this.id = id;
  }

  double price;
  String name;
  String id;
}
