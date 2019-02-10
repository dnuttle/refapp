package net.nuttle.pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.nuttle.service.SimpleService;

/**
 * Exposes just the getInventory method of SimpleService,
 * but not the getPassword method.
 * @author Dan
 *
 */
@Service
public class Adapter {

  private SimpleService simpleService;
  
  @Autowired
  public Adapter(SimpleService simpleService) {
    this.simpleService = simpleService;
  }
  
  public int getInventory(int id) {
    return simpleService.getInventory(id);
  }
  
}
