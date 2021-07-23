----------------------------------------------------------------------------
Insert into restaurants(name, address, restaurantPhone)
Values('TrueIndianFood', '1 Tulane, Tulane, AZ', '111-111-1111');

Insert into restaurants(name, address, restaurantPhone)
Values('NumeroUnoFajitas', '2 Julane, FosterCity, CA', '222-111-1111');

Insert into restaurants(name, address, restaurantPhone)
Values('PFChangs', '3 Dulane, FosterCity, PA', '333-111-1111');

-----------------------------------------------------------------------------

Insert into customers(name, phone)
Values('Sam', '111-222-1111');

Insert into customers(name, phone)
Values('Pam', '111-333-1111');

Insert into customers(name, phone)
Values('Ram', '111-444-1111');

-----------------------------------------------------------------------------

Insert into dishes(rest_id, dishName, price, typeOfDish, description)
Values(1, 'Lentil', 6.00, 'Vegetarian', 'sumptuous savory of Indian spices in soup');

Insert into dishes(rest_id, dishName, price, typeOfDish, description)
Values(1, 'Chicken Curry', 7.00, 'NonVegetarian', 'mouth watering curry made with yogurt, ginger and Indian spices');

Insert into dishes(rest_id, dishName, price, typeOfDish, description)
Values(1, 'Rice', 4.00, 'Vegetarian', 'Indian Basmati rice');

Insert into dishes(rest_id, dishName, price, typeOfDish, description)
Values(1, 'Fish', 5.00, 'NonVegetarian', 'Tasty fish cooked in Indian style');


Insert into dishes(rest_id, dishName, price, typeOfDish, description)
Values(2, 'Taco', 6.00, 'NonVegetarian', 'Yummy taco made with organic ingredients');

Insert into dishes(rest_id, dishName, price, typeOfDish, description)
Values(2, 'Chicken Fajita', 7.00, 'NonVegetarian', 'Spicy and flavorful authentic mexican fajita');

Insert into dishes(rest_id, dishName, price, typeOfDish, description)
Values(2, 'Rice', 4.00, 'Vegetarian', 'brown organic delicious rice');


Insert into dishes(rest_id, dishName, price, typeOfDish, description)
Values(3, 'Chicken', 9.00, 'NonVegetarian', 'chicken made with garlic, fresh vegetables in soy sauce');

Insert into dishes(rest_id, dishName, price, typeOfDish, description)
Values(3, 'ChaoMin', 6.00, 'Vegetarian', 'delicious rice noodle cooked with soy and fresh vegetables');

Insert into dishes(rest_id, dishName, price, typeOfDish, description)
Values(3, 'Eggroll', 7.00, 'NonVegetarian', 'egg rolled in crispy thin flat dough of rice');

-----------------------------------------------------------------------------

Insert into orders(rest_id, customer_id, phone, date, pickupOrDeliveryTime, orderType, amountDue)
Values(1, 2, '111-222-1111', '2021-07-20', '10:45', 'Pickup', 24.00);

Insert into orders(rest_id, customer_id, phone, date, pickupOrDeliveryTime, orderType, amountDue)
Values(2, 3, '111-333-1111', '2021-07-20', '18:30', 'Delivery', 26.00);

-- Insert into orders(rest_id, customer_id, phone, date, pickupOrDeliveryTime, orderType, amountDue)
-- Values(3, 1, '111-444-1111', '2021-07-20',  '19:15', 'Delivery', 18.00);
------------------------------------------------------------------------------

Insert into ordered_dishes(order_id, dish_id, quantity)
Values(1, 1, 1);

Insert into ordered_dishes(order_id, dish_id, quantity)
Values(1, 2, 2);

Insert into ordered_dishes(order_id, dish_id, quantity)
Values(1, 3, 1);

Insert into ordered_dishes(order_id, dish_id, quantity)
Values(2, 5, 2);

Insert into ordered_dishes(order_id, dish_id, quantity)
Values(2, 6, 2);




