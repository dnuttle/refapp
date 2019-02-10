package net.nuttle.service;

import java.util.List;

import net.nuttle.model.Customer;

public interface DataService {

  public List<Customer> getAllCustomers();
  public List<Customer> getCustomersByState(String state);
  public void addCustomer(String name, String city, String state, String postalCode);
}
