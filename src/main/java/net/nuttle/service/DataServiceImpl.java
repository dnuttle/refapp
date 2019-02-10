package net.nuttle.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nuttle.model.Customer;
import net.nuttle.repository.CustomerRepository;

@Service
public class DataServiceImpl implements DataService {

  @Autowired
  private CustomerRepository custRepo;
  
  public DataServiceImpl() {
  }
  
  @Override
  public List<Customer> getCustomersByState(String state) {
    return custRepo.findByState(state);
  }
  
  @Override
  public void addCustomer(String name, String city, String state, String postalCode) {
    Customer cust = new Customer(name, city, state, postalCode);
    custRepo.save(cust);
  }
  
  @Override
  public List<Customer> getAllCustomers() {
    List<Customer> customers = new ArrayList<>();
    for (Customer customer : custRepo.findAll()) {
      customers.add(customer);
    }
    return customers;
  }
}
