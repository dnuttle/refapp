package net.nuttle.rest;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;

import net.nuttle.io.FileService;
import net.nuttle.json.JacksonUtil;
import net.nuttle.model.Customer;
import net.nuttle.model.json.Product;
import net.nuttle.service.DataService;
import net.nuttle.service.ESService;
import net.nuttle.service.SimpleService;

@Controller
public class MainController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
  private DataService dataService;
  private SimpleService simpleService;
  private JacksonUtil jackson;
  private FileService fileService;
  private ESService esService;
  
  @Autowired
  public MainController(DataService dataService, SimpleService simpleService, FileService fileService, JacksonUtil jackson,
      ESService esService) {
    this.dataService = dataService;
    this.jackson = jackson;
    this.simpleService = simpleService;
    this.fileService = fileService;
    this.esService = esService;
  }

  @GetMapping("/")
  public String home() {
    return "index";
  }

  //SQLITE
  
  @GetMapping("/customers/state/{state}/")
  @ResponseBody
  public List<Customer> getCustomersByState(@PathVariable String state) {
    List<Customer> customers = dataService.getCustomersByState(state);
    LOGGER.debug("FOUND " + customers.size() + " CUSTOMERS IN " + state);
    LOGGER.debug(customers.get(0).toString());
    return customers;
  }
  
  //JACKSON/JSON
  
  /*
   * Note the slash following the path variable; this ensures it will work if the variable contains a period
   */
  @GetMapping("/json/file/{file}/")
  @ResponseBody
  public JsonNode getJsonForFile(@PathVariable String file) {
    LOGGER.debug("Request for file {}", file);
    return jackson.get(file);
  }
  
  @GetMapping("/json/product/{id}")
  @ResponseBody
  public Product getProductById(@PathVariable int id) {
    return jackson.getProduct(id);
  }
  
  //SIMPLE SERVICE
  
  @GetMapping("/inventory/{id}")
  @ResponseBody
  public int getInventoryById(@PathVariable int id) {
    return simpleService.getInventory(id);
  }
  
  //FILE SERVICE
  
  @GetMapping("/file/{path}/")
  @ResponseBody
  public String readFile(@PathVariable String path) throws IOException {
    return fileService.readFile(path);
  }
  
  @GetMapping(value="/file/{path}/", params={"charset"})
  @ResponseBody
  public String readFile(@PathVariable String path, @RequestParam(required=true) String charset) throws IOException {
    return fileService.readFile(path, charset);
  }
  
  //ELASTICSEARCH
  
  @GetMapping(value="/es/shakespeare/all")
  @ResponseBody
  public SearchResponse matchAllShakespeare() throws IOException {
    return esService.matchAllShakespeare();
  }
}
