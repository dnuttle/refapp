package net.nuttle.json;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import net.nuttle.model.json.Product;

@Component
public class JacksonUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtil.class);
  private static final String DATA_PATH = System.getProperty("user.dir") + "/src/main/resources/";
  
  public static void main(String[] args) {
    JacksonUtil util = new JacksonUtil();
    LOGGER.debug(util.print("data1.json"));
  }
  
  public String print(String file, boolean prettyPrint) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      ArrayNode root = (ArrayNode) mapper.readTree(new File(DATA_PATH + file));
      if (prettyPrint) {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
      } else {
        return mapper.writeValueAsString(root);
      }
    } catch (IOException e) {
      LOGGER.error("Error printing JSON in {}", file, e);
      return null;
    }
  }
  
  public String print(String file) {
    return print(file, true);
  }
  
  public JsonNode get(String file) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readTree(new File(DATA_PATH + file));
    } catch (IOException e) {
      LOGGER.error("Error getting JSON in {}", file, e);
      return null;
    }
  }
  
  public Product getProduct(int id) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      //ArrayNode root = (ArrayNode) mapper.readTree(new File(DATA_PATH + "data1.json"));
      List<Product> products = mapper.readValue(new File(DATA_PATH + "data1.json"), new TypeReference<List<Product>>(){});
      for (Product product : products) {
        if (id == product.getId()) {
          return product;
        }
      }
      /*
      for (JsonNode node : root) {
        if (node.get("id").asInt() == id) {
          return mapper.treeToValue(node, Product.class);
        }
      }
      */
      return null;
    } catch (IOException e) {
      LOGGER.error("Error getting product from data1.json", e);
      return null;
    }
  }
}
