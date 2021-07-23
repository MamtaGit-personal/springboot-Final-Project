package com.mamta.food.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.mamta.food.entity.Customer;
import com.mamta.food.entity.CustomerOrderList;
import com.mamta.food.entity.Dish;
import com.mamta.food.entity.DishType;
import com.mamta.food.entity.Order;
import com.mamta.food.entity.OrderRequest;
import com.mamta.food.entity.OrderedDishesIDsAndQuantity;
import com.mamta.food.entity.Restaurant;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultOrderDishesDao implements OrderDishesDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  /////////////////////////////////////////////////////////////////////////////
  @Override
  public Order saveCustomerOrder(CustomerOrderList customerOrderList, Customer customer,
  Restaurant restaurant, List<Dish> dish, List<OrderedDishesIDsAndQuantity> orderedDishesIDsAndQuantity) {
    
    LocalDateTime now = LocalDateTime.now();  
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    String date = dtf.format(now);
    
    dtf = DateTimeFormatter.ofPattern("HH:mm");
    String pickupOrDeliveryTime = dtf.format(now);
    
    SqlParams params = generateInsertSql(customer, restaurant, customerOrderList, 
        date, pickupOrDeliveryTime);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);  
    
    Long orderPK = keyHolder.getKey().longValue();
    
    saveDishOrderWithQuantity(orderedDishesIDsAndQuantity, orderPK);
    
    // @formatter:off
    return Order.builder()
        .restId(restaurant.getRestId())
        .restaurantName(restaurant.getName())
        .customerName(customer.getName())
        .customerPhone(customer.getPhone())
        .orderType(customerOrderList.getOrderType())
        .date(date)
        .pickupOrDeliveryTime(pickupOrDeliveryTime)
        .dishAndQuantity(customerOrderList.getDishAndQuantity())
        .build();
    // @formatter:on
  }
  
  ///////////////////////////////////////////////////////////////////////////
  /*
  * customer_id in the orders table is id in the customers table
  * rest_id in the orders table is id in the restaurants table
  * phone in the orders table is phone in the customers table
  * amountDue in the orders table is totalAmountDue in the customerOrderList table
  * orderType in the orders table is orderType in the customerOrderList table 
  */
  private SqlParams generateInsertSql(Customer customer, Restaurant restaurant,
      CustomerOrderList customerOrderList, String date, String time) {
    SqlParams params = new SqlParams();
    // @formatter:off
    String sql = ""
        + "INSERT INTO orders  ("
        + "rest_id, customer_id, phone, date, pickupOrDeliveryTime, orderType, amountDue"
        + ") VALUES ("
        + ":rest_id, :customer_id, :phone, :date, :pickupOrDeliveryTime, :orderType, :amountDue"
        + ")";
    // @formatter:on    
    params.sql = sql;
    
    params.source.addValue("rest_id", restaurant.getRestId());
    params.source.addValue("customer_id", customer.getCustomerId());
    params.source.addValue("phone", customer.getPhone());
    params.source.addValue("date", date);
    params.source.addValue("pickupOrDeliveryTime", time);
    params.source.addValue("orderType", customerOrderList.getOrderType());
    params.source.addValue("amountDue", customerOrderList.getTotalAmountDue());
    
    return params;
  }
  
  ////////////////////////////////////////////////////////////////////////////
  private void saveDishOrderWithQuantity( List<OrderedDishesIDsAndQuantity> dishes, Long orderPK) {
    
      for(OrderedDishesIDsAndQuantity dish: dishes) { 
        SqlParams params =
        generateDishOrderWithQuantityInsertSql(dish.getDishId(), dish.getQuantity(), orderPK);
        jdbcTemplate.update(params.sql, params.source);
      
      }
     
  
  }
  
  //////////////////////////////////////////////////////////////////////////////////////////
  private SqlParams generateDishOrderWithQuantityInsertSql(Long dishId, int quantity, Long orderId) {
    SqlParams params = new SqlParams();
    // @formatter:off
    params.sql = ""
        + "INSERT INTO ordered_dishes  ("
        + "order_id, dish_id, quantity"
        + ") VALUES ("
        + ":order_id, :dish_id, :quantity"
        + ")";
    // @formatter:on
    
    params.source.addValue("order_id", orderId); 
    params.source.addValue("dish_id", dishId);
    params.source.addValue("quantity", quantity);
    
    return params;
  }

  //////////////////////////////////////////////////////////////////////////
  @Override
  public List<Dish> getDishListWithPriceDetails(OrderRequest orderRequest) {
    Map <String, Object> params = new HashMap<>();
    if(orderRequest.getDishAndQuantity().size() == 0) {
      return new LinkedList<>();
    }
    log.debug("DAO layer, getRestaurant Id = {}", orderRequest.getRestId());
    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM dishes "
        + "WHERE dishName IN (";  
 
    for(int index = 0; index < orderRequest.getDishAndQuantity().size(); index ++) {
      String key = "dish_" + index;
      sql += ":" + key + ", ";
      params.put(key, orderRequest.getDishAndQuantity().get(index).getDishName());
      
    }
    System.out.println("SQL string is: " + sql);
    sql = sql.substring(0, sql.length() - 2);
    sql += ") "
        + "AND rest_id = :restaurant_id";
    params.put("restaurant_id", orderRequest.getRestId());
    
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      @Override
      public Dish mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        return Dish.builder()
        .dishId(rs.getLong("id"))
        .restId(rs.getLong("rest_id"))
        .dishName(rs.getString("dishName"))
        .price(rs.getDouble("price"))
        .dishType(DishType.valueOf(rs.getString("typeOfDish")))
        .description(rs.getString("description"))
        .build();
        // @formatter:off
      }
    });
  }
 
  //////////////////////////////////////////////////////////////////////////
  @Override
  public Optional<Restaurant> getRestaurant(Long restaurantId) {
    
    String sql = ""
        + "SELECT * "
        + "FROM restaurants "
        + "WHERE id = :restaurant_id";  
    
    log.debug("DAO layer, getRestaurant Id = {}", restaurantId);
    Map <String, Object> params = new HashMap<>();
    params.put("restaurant_id", restaurantId);
    
    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new RestaurantResultSetExtractor()));
  }
  
  //////////////////////////////////////////////////////////////////////////
  @Override
  public Optional<Customer> getCustomer(String phone) {
    String sql = ""
        + "SELECT * "
        + "FROM customers "
        + "WHERE phone = :customer_phone";  
    
    log.debug("DAO layer, fetchCustomer has phone = {}", phone);
    Map <String, Object> params = new HashMap<>();
    params.put("customer_phone", phone);
    
    return Optional.ofNullable(
        jdbcTemplate.query(sql, params, new CustomerResultSetExtractor()));
  }
  
  ///////////////////////////////////////////////////////////////////////////////
  class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {

    @Override
    public Customer extractData(ResultSet rs) 
        throws SQLException, DataAccessException {
      rs.next();
      //System.out.println("DAO layer, rs.next() with customerId: " + rs.getString("customer_id"));
      return Customer.builder()
          .customerId(rs.getLong("id"))
          .name(rs.getString("name"))
          .phone(rs.getString("phone"))
          .build();
      }
  }
  /////////////////////////////////////////////////////////////////////////////
  class RestaurantResultSetExtractor implements ResultSetExtractor<Restaurant> {

    @Override
    public Restaurant extractData(ResultSet rs) 
        throws SQLException, DataAccessException {
      rs.next();
      //System.out.println("DAO layer, rs.next() with customerId: " + rs.getString("customer_id"));
      return Restaurant.builder()
          .restId(rs.getLong("id"))
          .name(rs.getString("name"))
          .address(rs.getString("address"))
          .restPhone(rs.getString("restaurantPhone"))
          .build();
      }
  }
  ////////////////////////////////////////////////////////////////////////////
  
  class SqlParams{
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  
  ////////////////////////////////////////////////////////////////////////////
    
}
