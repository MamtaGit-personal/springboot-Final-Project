package com.mamta.food.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.mamta.food.entity.Dish;
import com.mamta.food.entity.DishType;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultShowDishesDao implements ShowDishesDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<Dish> fetchDishes(Long restId) {
    
      //Week3 , video 1
      log.debug("Dao layer: restaurant ID = {}", restId);
      
      //@formatter:off
            
      String sql = ""
          + "Select d.*, r.name as restaurant "
          + "FROM dishes d "
          + "JOIN restaurants r "
          + "ON r.id = d.rest_id "
          + "WHERE d.rest_id = :restaurant_id";
      //@formatter:on
      
      Map<String, Object> params = new HashMap<>();
      params.put("restaurant_id", restId);
           
      /////////////////////////////////////////////////////////
     /* Set<Long> restIDs = params.keySet();
      System.out.println("6) The restaurant Map with keys and their respective values are:");
      for(Long restID : restIDs) {
          System.out.println("key: " + restID + ", value: " + params.get(modelID));
      }*/
      /////////////////////////////////////////////////////////
      
      return jdbcTemplate.query(sql, params, 
          new RowMapper<>() {

            @Override
            public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
              //@formatter:off
              return Dish.builder()
                  .restId(rs.getLong("rest_id"))
                  .restaurant(rs.getString("restaurant"))
                  .dishId(rs.getLong("id"))
                  .dishName(rs.getString("dishName"))
                  .price(rs.getDouble("price"))
                  .dishType(DishType.valueOf(rs.getString("typeOfDish")))
                  .description(rs.getString("description"))
                  .build();
                 
              //@formatter:off
            }
      });
    
    }
 }
