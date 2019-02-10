package net.nuttle.service;

import org.springframework.stereotype.Service;

/**
 * Represents some kind of "live" service
 * @author Dan
 *
 */
@Service
public class SimpleService {

  public int getInventory(int id) {
    if (id >= 1000) {
      return 1;
    }
    return 0;
  }
  
  /**
   * Example of a method that should have restricted access.
   * @param id
   * @return
   */
  public String getPassword(int id) {
    return "TOPSECRET";
  }
}
