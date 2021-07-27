package com.mamta.food.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.mamta.food.entity.Dish;
import com.mamta.food.entity.DishType;
import com.mamta.food.entity.UpdateDish;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultUpdateDishDao implements UpdateDishDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcShowDishesTemplate;
  
  @Override
  public Boolean updateDishName(UpdateDish updateDish) {
    
    log.debug("DAO layer starts here..... restaurantId = {}, newDishName = {}", 
        updateDish.getDishId(), updateDish.getNewPrice());
    //@formatter:off
    String sql = ""
        + "UPDATE dishes "
        + "SET price = :newPrice "
        + "WHERE id = :dish_id";
     //@formatter:on
    
    //Map <String, Object> params = new HashMap<>();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("newPrice", updateDish.getNewPrice());
    params.addValue("dish_id", updateDish.getDishId());
    
    int numOfRows = jdbcTemplate.update(sql, params);
    
    if(numOfRows == 1) {
      return true;
    }
    return false;
  }
  
  //////////////////////////////////////////////////////////////
  public Dish getDishDetailsWithTheNewDishPrice(Long dishId, double newPrice) {
  
    MapSqlParameterSource params = new MapSqlParameterSource();
    
    //@formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM dishes "
        + "WHERE id = :dish_id";  
    //@formatter:on
    
    params.addValue("dish_id", dishId);
          
    log.debug("DAO layer: After the Dish has been added, newPrice = {}", newPrice);
    return jdbcShowDishesTemplate.query(sql, params, new UpdatedDishPriceResultSetExtractor());
        
  }     
   
  class UpdatedDishPriceResultSetExtractor implements ResultSetExtractor<Dish> {

    @Override
    public Dish extractData(ResultSet rs) throws SQLException {
    //@formatter:off
          return Dish.builder()
            .dishId(rs.getLong("id"))
            .restId(rs.getLong("rest_id"))
            .dishName(rs.getString("dishName"))
            .price(rs.getDouble("price"))
            .dishType(DishType.valueOf(rs.getString("typeOfDish")))
            .description(rs.getString("description"))
            .build();
        //@formatter:off
      }
    }
 }
