package com.mamta.food.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.mamta.food.entity.Dish;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;




@RequestMapping("/dishes")  // To tell spring
@OpenAPIDefinition(info = @Info(title = "List of Dishes at a restaurant"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface GetDishesController {

  //@formatter:off
  @Operation(
      summary = "Summary - Returns a list of Dishes at a restaurant",
      description = "Description - Returns a list of Dishes given a restaurant Id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of Dishes is returned",
              content = @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = Dish.class))),
          @ApiResponse(
              responseCode = "400",
              description = "The Request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404",
              description = "No Dishes were found with input criteria",
                  content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500",
              description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))
         
      },
      parameters = {
          @Parameter(
              name = "restaurantId",  // restuarantId shows on the web
              allowEmptyValue = false,
              required = true,
              description = "The restaurant ID (i.e, 1)")
       }
     
  )
   
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Dish> fetchDishes(
      @RequestParam(required = true)  
        Long restaurantId);
  //@formatter:on
}
