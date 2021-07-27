package com.mamta.food.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.mamta.food.entity.DeletedOrder;
import com.mamta.food.entity.Dish;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "List of Dishes at a restaurant"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface DeleteOrderController {
//@formatter:off
  @Operation(
      summary = "Summary - Delete an Order of dish(es) at a restaurant",
      description = "Description - Returns the deleted Order at a restaurant",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Deleted Order Id is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = DeletedOrder.class))),
          // whatever you return, that is the type it should be. Hence Order.Class
          @ApiResponse(
              responseCode = "400",
              description = "The Request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "Delete order ID for dish was not found with input criteria",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))
         
      },
      parameters = {
         @Parameter(
             name = "OrderId", 
             required = false, 
             description = "The Order ID")
      }    
  )
  
  @RequestMapping(value = "/orders/{id}", method = RequestMethod.DELETE)
  DeletedOrder deleteOrder(@PathVariable Long id) ;
  
 }
