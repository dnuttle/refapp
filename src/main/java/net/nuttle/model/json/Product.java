package net.nuttle.model.json;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

  @JsonProperty
  private int id;
  @JsonProperty
  private int prodid;
  @JsonProperty
  private int sku;
  @JsonProperty
  private String name;
  @JsonProperty
  private List<Map<String, String>> properties;
  
  public Product() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProdid() {
    return prodid;
  }

  public void setProdid(int prodid) {
    this.prodid = prodid;
  }

  public int getSku() {
    return sku;
  }

  public void setSku(int sku) {
    this.sku = sku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Map<String, String>> getProperties() {
    return properties;
  }

  public void setProperties(List<Map<String, String>> properties) {
    this.properties = properties;
  }
}
