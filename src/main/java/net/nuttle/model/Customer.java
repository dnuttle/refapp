package net.nuttle.model;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  @Column(name="name")
  private String name;
  @Column(name="city")
  private String city;
  @Column(name="state")
  private String state;
  @Column(name="postal_code")
  private String postalCode;
  
  public Customer() {}
  
  public Customer(int id, String name, String city, String state, String postalCode) {
    this.id = id;
    this.name = name;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
  }
  
  public Customer(String name, String city, String state, String postalCode) {
    this.name = name;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
  }

  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public String getCity() {
    return city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }
  
  public void setState(String state) {
    this.state = state;
  }

  public String getPostalCode() {
    return postalCode;
  }
  
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  
  @Override
  public String toString() {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.writeValueAsString(this);
    } catch (IOException e) {
      return "ERROR";
    }
  }
  
}
