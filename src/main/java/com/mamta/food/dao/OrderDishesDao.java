package com.mamta.food.dao;

import java.util.List;
import java.util.Optional;
import com.mamta.food.entity.Customer;
import com.mamta.food.entity.Dish;
import com.mamta.food.entity.Order;
import com.mamta.food.entity.OrderRequest;
import com.mamta.food.entity.OrderRequestWithIdAndTotalAmountDue;
import com.mamta.food.entity.Restaurant;

public interface OrderDishesDao {

  Optional<Customer> getCustomer(String phone);

  Optional<Restaurant> getRestaurant(Long restId);

  List<Dish> getDishListWithPriceDetails(OrderRequest orderRequest, String restaurant);
  
  Order saveCustomerOrder(OrderRequestWithIdAndTotalAmountDue orderRequestWithIdAndTotalAmountDue);
  
}
