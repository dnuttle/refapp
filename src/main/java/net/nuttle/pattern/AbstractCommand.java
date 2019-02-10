package net.nuttle.pattern;

import net.nuttle.app.SpringContext;
import net.nuttle.service.SimpleService;

public abstract class AbstractCommand {

  private SimpleService simpleService;
  
  public AbstractCommand() {
    this.simpleService = SpringContext.getSimpleService();
  }
  
  public SimpleService getSimpleService() {
    return simpleService;
  }
}
