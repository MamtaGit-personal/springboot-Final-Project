package com.mamta.food.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.mamta.food.entity.Dish;
import com.mamta.food.entity.Order;
import com.mamta.food.entity.OrderRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/orders")  // To tell spring
@OpenAPIDefinition(info = @Info(title = "List of Dishes at a restaurant"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface OrderDishesController {
//@formatter:off
  @Operation(
      summary = "Summary - Order food online at a restaurant",
      description = "Description - Returns the list of dishes and quantity orderd at a restaurant",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A list of Dishes and quantity is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Dish.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The Request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Dishes were found with input criteria",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))
         
      },
      parameters = {
          @Parameter(
              name = "orderRequest",  
              required = true,
              description = "The dish order as JSON")
       }
     
  )
   
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  //Order createOrder(@RequestBody OrderRequest orderRequest);
  Order createOrder(@Valid @RequestBody OrderRequest orderRequest);
  //@formatter:on

}
