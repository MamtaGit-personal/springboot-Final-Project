package com.mamta.food.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.mamta.food.entity.DeletedOrder;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultDeleteOrderDao implements DeleteOrderDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;


  @Override
  public DeletedOrder getDeletedOrderDetailsBeforeDeletion(Long orderId) {
  //@formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM orders "
        + "WHERE id = :order_id";
    //@formatter:on
    Map<String, Object> params = new HashMap<>();
    params.put("order_id", orderId);
    return jdbcTemplate.query(sql, params, new DeleteOrderResultSetExtractor());
  }

  ///////////////////////////////////////////////////////////////////////////
  class DeleteOrderResultSetExtractor implements ResultSetExtractor<DeletedOrder> {

    @Override
    public DeletedOrder extractData(ResultSet rs) throws SQLException {
      rs.next();
      //@formatter:off
      return DeletedOrder.builder()
          .orderId(rs.getLong("id"))
          .restaurantId(rs.getLong("rest_id"))
          .customerPhone(rs.getString("phone"))
          .build();
      //@formatter:off
     }
  }
  
  /////////////////////////////////////////////////////////////////////////////
  @Override
  public Boolean deleteFromOrderedDishesTableForAGivenOrderId(Long orderId) {
    
      log.debug("Dao layer: order ID = {}",orderId);
      
      String Sql = "" 
      + "DELETE FROM ordered_dishes " 
      + "WHERE order_id = :order_id";
      
      Map<String, Object> params = new HashMap<>();
      params.put("order_id", orderId); 
      int numOfRows = jdbcTemplate.update(Sql, params);
      
      Boolean deleteSuccess = false;
      if(numOfRows == 1) {
        deleteSuccess = true;
      }
      else deleteSuccess = false;
    
      return deleteSuccess;
   }

  //////////////////////////////////////////////////////////////////////////////
  @Override
  public Boolean deleteFromOrdersTableForAGivenOrderId(Long orderId) {
    log.debug("Dao layer: order ID = {}", orderId);
    
    String Sql = "" 
    + "DELETE FROM orders " 
    + "WHERE id = :order_id";
    
    Map<String, Object> params = new HashMap<>();
    params.put("order_id", orderId); 
    int numOfRows = jdbcTemplate.update(Sql, params);
    
    Boolean deleteSuccess = false;
    if(numOfRows == 1) {
      deleteSuccess = true;
    }
    else {
      deleteSuccess = false;
    }
  
    return deleteSuccess;
  }
  
  ///////////////////////////////////////////////////////////////////////////////
}
