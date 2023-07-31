-- disable foreign key checks
set session_replication_role = 'replica';

delete from tab_cities;
delete from tab_kitchens;
delete from tab_states;
delete from tab_payment_ways;
delete from tab_groups;
delete from group_permission;
delete from tab_permissions;
delete from tab_products;
delete from tab_restaurants;
delete from restaurant_payment_way;
delete from tab_users;
delete from user_group;

-- enable foreign key checks
set session_replication_role = 'origin';

alter sequence tab_cities_seq restart with 1;
alter sequence tab_kitchens_seq restart with 1;
alter sequence tab_users_seq restart with 1;
alter sequence tab_payment_ways_seq restart with 1;
alter sequence tab_groups_seq restart with 1;
alter sequence tab_permissions_seq restart with 1;
alter sequence tab_products_seq restart with 1;
alter sequence tab_restaurants_seq restart with 1;
alter sequence tab_users_seq restart with 1;

insert into tab_kitchens (id, name) values (1, 'thai');
insert into tab_kitchens (id, name) values (2, 'indian');
insert into tab_kitchens (id, name) values (3, 'argentina');
insert into tab_kitchens (id, name) values (4, 'brazilian');

insert into tab_states (id, name) values (1, 'california');
insert into tab_states (id, name) values (2, 'florida');
insert into tab_states (id, name) values (3, 'texas');

insert into tab_cities (id, name, state_id) values (1, 'san francisco', 1);
insert into tab_cities (id, name, state_id) values (2, 'san jose', 1);
insert into tab_cities (id, name, state_id) values (3, 'miami', 2);
insert into tab_cities (id, name, state_id) values (4, 'pensacola', 2);
insert into tab_cities (id, name, state_id) values (5, 'austin', 3);

insert into tab_restaurants (id, name, shipping_costs, kitchen_id, created_at, updated_at, active, opened, address_city_id, address_postalcode, address_street, address_number, address_district)
values (1, 'thai gourmet', 10, 1, current_timestamp, current_timestamp, true, true, 1, '11111-222', 'fifth Avenue', '1000', 'downtown');
insert into tab_restaurants (id, name, shipping_costs, kitchen_id, created_at, updated_at, active, opened)
values (2, 'thai delivery', 9.50, 1, current_timestamp, current_timestamp, true, true);
insert into tab_restaurants (id, name, shipping_costs, kitchen_id, created_at, updated_at, active, opened)
values (3, 'tuk tuk indian food', 15, 2, current_timestamp, current_timestamp, true, true);
insert into tab_restaurants (id, name, shipping_costs, kitchen_id, created_at, updated_at, active, opened)
values (4, 'java steakhouse', 12, 3, current_timestamp, current_timestamp, true, true);
insert into tab_restaurants (id, name, shipping_costs, kitchen_id, created_at, updated_at, active, opened)
values (5, 'Uncle Sams Diner', 11, 4, current_timestamp, current_timestamp, true, true);
insert into tab_restaurants (id, name, shipping_costs, kitchen_id, created_at, updated_at, active, opened)
values (6, 'Marys Bar', 6, 4, current_timestamp, current_timestamp, true, true);

insert into tab_payment_ways (id, description) values (1, 'Credit Card');
insert into tab_payment_ways (id, description) values (2, 'Debit Card');
insert into tab_payment_ways (id, description) values (3, 'Cash');

insert into tab_permissions (id, name, description) values (1, 'SEARCH_KITCHEN', 'Allow to search kitchens');
insert into tab_permissions (id, name, description) values (2, 'UPDATE_KITCHEN', 'Allow to update kitchens');

insert into restaurant_payment_way (restaurant_id, paymentway_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Pork with sweet and sour sauce', 'Delicious pork with special sauce', 78.90, true, 1, current_timestamp, current_timestamp);
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Thai shrimp', '16 large prawns in spicy sauce', 110, true, 1, current_timestamp, current_timestamp);
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Spicy salad with grilled meat', 'Leaf salad with thinly sliced grilled beef and our special red pepper sauce', 87.20, true, 2, current_timestamp, current_timestamp);
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Garlic Naan', 'Indian traditional bread with garlic topping', 21, true, 3, current_timestamp, current_timestamp);
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Murg Curry', 'Cubes of chicken prepared with curry sauce and spices', 43, true, 3, current_timestamp, current_timestamp);
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Ancho steak', 'Tender and juicy cut, two fingers thick, taken from the front of the sirloin steak', 79, true, 4, current_timestamp, current_timestamp);
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('T-Bone', 'Very tasty cut, with a T-shaped bone, with the sirloin on one side and the filet mignon on the other', 89, true, 4, current_timestamp, current_timestamp);
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('X-All sandwich', 'Big sandwich with lots of cheese, beef burger, bacon, egg, salad and mayonnaise', 19, true, 5, current_timestamp, current_timestamp);
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('termite skewer', 'Comes with flour, cassava and vinaigrette', 8, true, 6, current_timestamp, current_timestamp);

insert into tab_groups (name, created_at, updated_at) values ('Manager', current_timestamp, current_timestamp);
insert into tab_groups (name, created_at, updated_at) values ('Seller', current_timestamp, current_timestamp);
insert into tab_groups (name, created_at, updated_at) values ('Secretary', current_timestamp, current_timestamp);
insert into tab_groups (name, created_at, updated_at) values ('Register', current_timestamp, current_timestamp);

insert into group_permission (group_id, permission_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

insert into tab_users (id, name, email, password, created_at, updated_at) values (1, 'User One', 'name1@domain.com', '123', current_timestamp, current_timestamp);
insert into tab_users (id, name, email, password, created_at, updated_at) values (2, 'User Two', 'name2@domain.com', '123', current_timestamp, current_timestamp);
insert into tab_users (id, name, email, password, created_at, updated_at) values (3, 'User Three', 'name3@domain.com', '123', current_timestamp, current_timestamp);
insert into tab_users (id, name, email, password, created_at, updated_at) values (4, 'User Four', 'name4@domain.com', '123', current_timestamp, current_timestamp);