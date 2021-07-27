drop table if exists ordered_dishes;
drop table if exists orders;
drop table if exists dishes;
drop table if exists customers;
drop table if exists restaurants;

CREATE TABLE restaurants(
	id int(11) not null auto_increment,
  	name varchar(25) not null,
	address varchar(75),
  	restaurantPhone varchar(16) not null unique,
	primary key(id)
);

CREATE TABLE customers(
    id int(11) not null auto_increment,
    name varchar(25) not null,
	phone varchar(16) not null unique,
	primary key(id)
);

CREATE TABLE dishes(
	id int(11) not null auto_increment,
    rest_id int(11) not null,
	dishName varchar(25) not null,
    price double,
    typeOfDish varchar(20) not null,  -- vegetarian or non-vegetarian
    description varchar(150) not null,
    primary key(id),
    UNIQUE KEY (rest_id, dishName),
    foreign key(rest_id) references restaurants(id)
);

CREATE TABLE orders(
    id int(11) not null auto_increment,
    rest_id int(11) not null,
    customer_id int(11) not null,   -- list of dish Ids
    phone varchar(16) not null,
    date Date,
	pickupOrDeliveryTime varchar(16),
    orderType varchar(20) not null default 'Pickup',  -- Pickup or Delivery
    amountDue double,
    primary key(id),
    foreign key(rest_id) references restaurants(id),
    foreign key(customer_id) references customers(id)
);

CREATE TABLE ordered_dishes(
    order_id int(11) not null,
    dish_id int(11) not null,   
    quantity int,
    foreign key(order_id) references orders(id),
    foreign key(dish_id) references dishes(id)
);