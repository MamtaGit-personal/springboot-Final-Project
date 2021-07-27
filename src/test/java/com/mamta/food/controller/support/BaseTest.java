package com.mamta.food.controller.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import lombok.Getter;

public class BaseTest {
  @LocalServerPort
  private int serverPort;
  
  @Autowired
  @Getter
  private TestRestTemplate restTemplate;
  
  protected String getBaseUriForDishes() {
    return String.format("http://localhost:%d/dishes", serverPort);
  }
  
  protected String getBaseUriForOrders() {
    return String.format("http://localhost:%d/dishes", serverPort);
  }
}
