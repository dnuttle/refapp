package net.nuttle.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.springframework.stereotype.Service;

@Service
public class FileService {

  private static final String DATA_PATH = System.getProperty("user.dir") + "/src/main/resources/";
  private static final String NEWLINE = "\n";

  public static void main(String[] args) throws IOException {
    StringBuilder content = new StringBuilder();
    content.append("Line one").append(NEWLINE);
    content.append("Line two").append(NEWLINE);
    content.append("Line three").append(NEWLINE);
    content.append("Line four").append(NEWLINE);
    FileService fs = new FileService();
    fs.writeFile(content.toString(), "test.txt");
  }
  
  /**
   * Naive file reader assumes that default encoding is OK.
   * Also loads entire file into memory.
   * @param file
   * @return
   * @throws IOException
   */
  public String readFile(String file) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File(DATA_PATH + file)))) {
      return readFile(reader);
    }
  }

  /**
   * Better file reader specifies charset, still loads file into memory
   * @param file
   * @param charset
   * @return
   * @throws IOException
   */
  public String readFile(String file, String charset) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(DATA_PATH + file)), charset))) {
      return readFile(reader);
    }
  }
  
  private String readFile(BufferedReader reader) throws IOException {
    String line = null;
    StringBuilder builder = new StringBuilder();
    while ((line = reader.readLine()) != null) {
      builder.append(line);
    }
    return builder.toString();
  }
  
  public void writeFile(String contents, String file) throws IOException {
    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(DATA_PATH + file)), "utf8")) {
      writer.write(contents);
      writer.flush();
    }
  }
}
