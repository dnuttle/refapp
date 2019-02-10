package net.nuttle;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import net.nuttle.model.Customer;
import net.nuttle.service.DataService;

@SpringBootApplication
@ComponentScan(basePackages={"net.nuttle"})
public class RefApplication implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(RefApplication.class);
  private DataService dataService;
  
  @Autowired
  public RefApplication(DataService dataService) {
    this.dataService = dataService;
  }
  
  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(RefApplication.class);
    Properties properties = new Properties();
    //properties.setProperty("spring.datasource.url", "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/db.sqlite");
    //This adds to but apparently does not override existing env properties
    app.setDefaultProperties(properties);
    app.run(args);
  }
  
  @Override
  public void run(String... args) throws Exception {
    /*
     * TODO: This will have to wait; the data service returns the correct number of elements,
     * but each of them is null. There is something basic about Hibernate I don't grasp.
     * I believe it has to do with the identity field definition, but I can't find the difference
     * between mine and the Baeldung example.
     */
    dataService.addCustomer("Acme", "West Dundee", "IL", "60118");
    dataService.addCustomer("Earl", "Elgin", "IL", "60121");
    List<Customer> il = dataService.getCustomersByState("IL");
    LOGGER.debug("Found " + il.size() + " customers in IL");
    LOGGER.debug("First: " + il.get(1));
  }
  
}
