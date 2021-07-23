package com.mamta.food.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mamta.food.dao.OrderDishesDao;
import com.mamta.food.entity.Customer;
import com.mamta.food.entity.CustomerOrderList;
import com.mamta.food.entity.Dish;
import com.mamta.food.entity.Order;
import com.mamta.food.entity.OrderRequest;
import com.mamta.food.entity.OrderType;
import com.mamta.food.entity.OrderedDishesIDsAndQuantity;
import com.mamta.food.entity.OrderedDishesQuantiytAndPrice;
import com.mamta.food.entity.Restaurant;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultOrderDishesService implements OrderDishesService{
  
  @Autowired
  private OrderDishesDao orderDishesDao;
  
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    log.debug("Service layer, Order = {} ", orderRequest);
    
    Customer customer = fetchCustomer(orderRequest);
    Restaurant restaurant = fetchRestaurant(orderRequest);
    List<Dish> dish = fetchDishListWithPriceDetails(orderRequest);
    
    List<OrderedDishesQuantiytAndPrice> orderedDishesQuantiytAndPrice =
        getDishListQuantityAndPrice(orderRequest, dish);
    
    List<OrderedDishesIDsAndQuantity> orderedDishesIDsAndQuantity =
        fetchDishIdAndQuantity(orderRequest, dish);
    
    CustomerOrderList customerOrderList = 
        fetchDishOrderListForCustomer(orderedDishesQuantiytAndPrice, customer, 
            restaurant, orderRequest.getOrderType());
    
    String customerName = customer.getName();
    System.out.println("Dao layer, Customer Name is: " + customerName);
     
    return orderDishesDao.saveCustomerOrder(customerOrderList, customer, restaurant, dish, orderedDishesIDsAndQuantity);
  }
  
  ////////////////////////////////////////////////////////////////////////////
  private List<OrderedDishesIDsAndQuantity> fetchDishIdAndQuantity(OrderRequest orderRequest,
      List<Dish> dish) {
    
    List<OrderedDishesIDsAndQuantity> orderedDishesIDsAndQuantity = new ArrayList<>();
    
    for(int index = 0; index < dish.size(); index ++) {
        
      orderedDishesIDsAndQuantity.get(index).setDishId(dish.get(index).getDishId());
      
      for(int i = 0; i < orderRequest.getDishAndQuantity().size(); i ++) {
        
        if( orderRequest.getDishAndQuantity().get(i).getDishName() == dish.get(index).getDishName()) {
          orderedDishesIDsAndQuantity.get(index).setQuantity(
            orderRequest.getDishAndQuantity().get(i).getQuantity());
        }
      }
    }
    return orderedDishesIDsAndQuantity;
  }

  //////////////////////////////////////////////////////////////////////////
  private List<OrderedDishesQuantiytAndPrice> getDishListQuantityAndPrice(
      OrderRequest orderRequest, List<Dish> dish) {
    
    double price;
    int quantity = 0;
        
    List<OrderedDishesQuantiytAndPrice> dishQuantityAndPrice = new ArrayList<>();
    
    for(int index = 0; index < orderRequest.getDishAndQuantity().size(); index ++) {
      
      dishQuantityAndPrice.get(index).setDishName(
          orderRequest.getDishAndQuantity().get(index).getDishName());
      
      dishQuantityAndPrice.get(index).setQuantity(
          orderRequest.getDishAndQuantity().get(index).getQuantity());
      
      quantity = orderRequest.getDishAndQuantity().get(index).getQuantity();
      
      for(int i = 0; i < dish.size(); i ++) {
        if(dish.get(i).getDishName() == orderRequest.getDishAndQuantity().get(index).getDishName())
        {
          price = quantity *dish.get(i).getPrice();
          dishQuantityAndPrice.get(index).setPrice(price);
          
          dishQuantityAndPrice.get(index).setDishId(dish.get(index).getDishId());
        }
      }// for( int i.... ) loop
      
    }// for(int index .... ) loop
    return dishQuantityAndPrice;
  }

  //////////////////////////////////////////////////////////////////////////
  protected CustomerOrderList fetchDishOrderListForCustomer(
      List<OrderedDishesQuantiytAndPrice> orderedDishesQuantiytAndPrice,
      Customer customer, Restaurant restaurant, OrderType orderType) {
    
    CustomerOrderList customerFinalDishOrderList = new CustomerOrderList();
    
    customerFinalDishOrderList.setRestId(restaurant.getRestId());
    customerFinalDishOrderList.setRestaurantName(restaurant.getName());
    customerFinalDishOrderList.setCustomerName(customer.getName());
    customerFinalDishOrderList.setCustomerPhone(customer.getPhone());
    customerFinalDishOrderList.setOrderType(orderType);    
    //customerFinalDishOrderList.setDishAndQuantity(orderedDishes);
    
    double totalPrice = 0.0;
    double [] price = new double[orderedDishesQuantiytAndPrice.size() - 1];
    
    for(int index = 0; index < orderedDishesQuantiytAndPrice.size(); index ++) {
      
      customerFinalDishOrderList.getDishAndQuantity().get(index)
      .setDishName(orderedDishesQuantiytAndPrice.get(index).getDishName());
      
      customerFinalDishOrderList.getDishAndQuantity().get(index)
      .setQuantity(orderedDishesQuantiytAndPrice.get(index).getQuantity());
      
      price[index] = orderedDishesQuantiytAndPrice.get(index).getPrice();       
    }
   
    for(int i = 0; i < orderedDishesQuantiytAndPrice.size(); i++) {
      totalPrice += price[i];
    }
    
    customerFinalDishOrderList.setTotalAmountDue(totalPrice);
    
    return customerFinalDishOrderList;
  }
  
  //////////////////////////////////////////////////////////////////////////
  private List<Dish> fetchDishListWithPriceDetails(OrderRequest orderRequest) {
    return orderDishesDao.getDishListWithPriceDetails(orderRequest);
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
