package net.nuttle.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.nuttle.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByState(String state);
  
}
