package net.nuttle.app;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import net.nuttle.service.SimpleService;

public class SpringContext implements ApplicationContextAware {

  private static ApplicationContext ctx;
  
  @Override
  public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    this.ctx = ctx;
  }
  
  public static ApplicationContext getApplicationContext() {
    return ctx;
  }
  
  public static SimpleService getSimpleService() {
    return ctx.getBean(SimpleService.class);
  }
}
