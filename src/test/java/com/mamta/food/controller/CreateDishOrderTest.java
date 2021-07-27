package com.mamta.food.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.mamta.food.OnlineFood;
import com.mamta.food.controller.support.CreateDishOrderTestSupport;
import com.mamta.food.entity.Order;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {OnlineFood.class})
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Restaurant_Schema.sql",
    "classpath:flyway/migrations/V1.1__Restaurant_Data.sql"}, config = @SqlConfig(encoding = "utf-8"))
public class CreateDishOrderTest extends CreateDishOrderTestSupport{

  @Autowired
  //private JdbcTemplate jdbcTemplate;
  
      
  @Test
  void testCreateDishOrderReturnsSuccess201() {
    
    // Given: an order as JSON
    String body = createOrderBody(); 
    String uri = getBaseUriForOrders(); 
    
    HttpHeaders headers = new HttpHeaders(); 
    headers.setContentType(MediaType.APPLICATION_JSON);
    
    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
    System.out.println("The uri is:" + uri + ", the body is: " + body + ", body entity is: " + bodyEntity);
    
    // When: the order is sent
    ResponseEntity<Order> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
         Order.class);
    
    // Then: a 201 status is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED); // getting 201 success
    
    // And: the returned order is correct
    assertThat(response.getBody()).isNotNull();
    System.out.println("assertThat response.getBody()=" +response.getBody());
   
    //Order order = response.getBody();
  }
}
