package com.mamta.food.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.mamta.food.OnlineFood;
import com.mamta.food.controller.support.FetchDishTestSupport;
import com.mamta.food.entity.Dish;
import lombok.Getter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {OnlineFood.class})
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/V1.0__Restaurant_Schema.sql",
    "classpath:flyway/migrations/V1.1__Restaurant_Data.sql"}, config = @SqlConfig(encoding = "utf-8"))
public class FetchDishTest extends FetchDishTestSupport {
  
  @Test
  void testThatDishesAreReturnedWhenAValidRestaurantIdIsSupplied() {
    
    
    //Given: a valid restaurant ID
    int RestaurantID = 1;
    String uri = 
        String.format("%s?restaurantId=%s", getBaseUriForDishes(), RestaurantID);
    System.out.println("My uri is: " + uri);
    
    // When: a connection is made to the URI
    ResponseEntity<List<Dish>> response =getRestTemplate().exchange(uri, HttpMethod.GET, 
        null, new ParameterizedTypeReference<>() {});
    System.out.println(response.getBody());   
    //Then: a success (Ok - 200) status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

 }
