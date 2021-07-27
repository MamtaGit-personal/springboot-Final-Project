package com.mamta.food.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mamta.food.entity.Dish;
import com.mamta.food.entity.Order;
import com.mamta.food.entity.UpdateDish;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Update a dish name at a restaurant"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface UpdateDishController {
//@formatter:off
  @Operation(
      summary = "Summary - Update a dish name at a restaurant",
      description = "Description - Returns the details of an updated dish name at a restaurant",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Dish with new dish name is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = UpdateDish.class))),
          // whatever you return, that is the type it should be. Hence Order.Class
          @ApiResponse(
              responseCode = "400",
              description = "The Request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Dishes were not found with input criteria",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))
         
      },
          parameters = {
              @Parameter(
                  name = "dishId", 
                  required = false, 
                  description = "The restaurant ID"),
              @Parameter(
                  name = "newPrice", 
                  required = false, 
                  description = "The new dish name")
           }
     
  )
  
  @RequestMapping(value = "/dishes/{dishId},{newPrice}", method = RequestMethod.PUT)  // To tell spring
  UpdateDish updateDishName(@PathVariable Long dishId, @PathVariable double newPrice);

}
