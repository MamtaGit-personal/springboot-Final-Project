package com.mamta.food.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mamta.food.dao.OrderDishesDao;
import com.mamta.food.entity.Customer;
import com.mamta.food.entity.Dish;
import com.mamta.food.entity.Order;
import com.mamta.food.entity.OrderRequest;
import com.mamta.food.entity.OrderRequestWithIdAndTotalAmountDue;
import com.mamta.food.entity.Restaurant;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultOrderDishesService implements OrderDishesService{
  
  @Autowired
  private OrderDishesDao orderDishesDao;
  
  private double totalAmount;
  
  @Transactional
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    log.debug("Service layer, Order = {} ", orderRequest);
    
    Customer customer = fetchCustomer(orderRequest);
    Restaurant restaurant = fetchRestaurant(orderRequest);
    List<Dish> dish = fetchDishListWithPriceDetails(orderRequest, restaurant.getName());
    
    System.out.println("Service layer, Customer Name is: " + customer.getName());
    System.out.println("Service layer, restaurant Name is: " + restaurant.getName());
    System.out.println("Service layer, dishes ordered are: " + dish);
    
    totalAmount = 0.0;
    OrderRequestWithIdAndTotalAmountDue orderRequestWithIdAndTotalAmountDue = 
        getOrderRequestWithIdAndTotalAmountDue(customer, restaurant, dish, orderRequest);
    
    System.out.println("Service layer, orderRequestWithIdAndTotalAmountDue: " + orderRequestWithIdAndTotalAmountDue);
    
    LocalDateTime now = LocalDateTime.now();  
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    String date = dtf.format(now);
    
    dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    String pickupOrDeliveryTime = dtf.format(now);
    
    Order order = orderDishesDao.saveCustomerOrder(orderRequestWithIdAndTotalAmountDue, date, pickupOrDeliveryTime);
    
    return order;
  }
  
 
  /////////////////////////////////////////////////////////////////////////////////////////
  private OrderRequestWithIdAndTotalAmountDue getOrderRequestWithIdAndTotalAmountDue(Customer customer,
      Restaurant restaurant, List<Dish> dish, OrderRequest orderRequest) {
    
    double [] price = new double[dish.size()];;
    int quantity = 0;
    String tempDishName = "";
    int counter = 0;
    Map<String, Integer> myDishNameAndQuantity = new HashMap<>();
    
    Set<String> dishNames = orderRequest.getDishNameAndQuantity().keySet();
    for(String dishName: dishNames) {
      myDishNameAndQuantity.put(dishName, orderRequest.getDishNameAndQuantity().get(dishName));
      System.out.println("Service layer: Dish Map is:" + myDishNameAndQuantity);
    }
    
    Map<Long, Integer> myDishIdAndQuantity = new HashMap<>();
    
    for(String dishName: dishNames) {
     
      for(int index =0; index < dish.size(); index++) {
        tempDishName = dish.get(index).getDishName();
        System.out.println(" Service layer, dish.size() is: " + dish.size());
        System.out.println(" Service layer, dish.get(index).getDishName(): " + tempDishName);
       
        if(dishName.equals(tempDishName)) {
          myDishIdAndQuantity.put(dish.get(index).getDishId(), orderRequest.getDishNameAndQuantity().get(dishName));
          quantity = orderRequest.getDishNameAndQuantity().get(dishName);
          price[counter] = dish.get(index).getPrice(); ///Failing here...
          price[counter] = price[counter]*quantity;
          break;
        }
      }
      counter++;
   }
    
   for(int i = 0; i < price.length; i++) {
   totalAmount = totalAmount + price[i];
   } 
     
   return OrderRequestWithIdAndTotalAmountDue.builder()
       .customer(customer)
       .restaurant(restaurant)
       .orderType(orderRequest.getOrderType())
       .totalAmoutDue(totalAmount)
       .dishIdAndQuantity(myDishIdAndQuantity)
       .dishNameAndQuantity(myDishNameAndQuantity)
       .build();
   
  }
 
  //////////////////////////////////////////////////////////////////////////
  protected List<Dish> fetchDishListWithPriceDetails(OrderRequest orderRequest, String restaurant) {
    return orderDishesDao.getDishListWithPriceDetails(orderRequest, restaurant);
  }

  //////////////////////////////////////////////////////////////////////////
  protected Restaurant fetchRestaurant(OrderRequest orderRequest) {
    
    return orderDishesDao.getRestaurant(orderRequest.getRestId())
        .orElseThrow(() -> new NoSuchElementException("Restaurant with ID = " 
        + orderRequest.getRestId()+ " was NOT found"));
  }
  
  /////////////////////////////////////////////////////////////////////////
  protected Customer fetchCustomer(OrderRequest orderRequest) {
    
    return orderDishesDao.getCustomer(orderRequest.getCustomerPhone())
        .orElseThrow(() -> new NoSuchElementException("Customer with ID = " 
        + orderRequest.getCustomerPhone()+ " was NOT found"));
  }
    
}
