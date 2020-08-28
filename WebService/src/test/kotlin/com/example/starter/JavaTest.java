package com.example.starter;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class JavaTest {
  @Test
  public void testFunction() throws IOException, InterruptedException {
    File file = new File("c://t!#@#arget");
    Thread.sleep(400);
    System.out.println(file.isAbsolute());
    System.out.println(file.getAbsolutePath());
    System.out.println(file.getCanonicalPath());
    System.out.println(file.getPath());
  }
}
