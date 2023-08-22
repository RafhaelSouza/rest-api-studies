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
delete from restaurant_responsible_user;
delete from tab_orders;
delete from tab_order_items;

-- enable foreign key checks
set session_replication_role = 'origin';

alter sequence tab_states_seq restart with 1;
alter sequence tab_cities_seq restart with 1;
alter sequence tab_kitchens_seq restart with 1;
alter sequence tab_users_seq restart with 1;
alter sequence tab_payment_ways_seq restart with 1;
alter sequence tab_groups_seq restart with 1;
alter sequence tab_permissions_seq restart with 1;
alter sequence tab_products_seq restart with 1;
alter sequence tab_restaurants_seq restart with 1;
alter sequence tab_users_seq restart with 1;
alter sequence tab_orders_seq restart with 1;
alter sequence tab_order_items_seq restart with 1;

insert into tab_kitchens (name) values ('thai');
insert into tab_kitchens (name) values ('indian');
insert into tab_kitchens (name) values ('argentina');
insert into tab_kitchens (name) values ('brazilian');

insert into tab_states (name) values ('california');
insert into tab_states (name) values ('florida');
insert into tab_states (name) values ('texas');

insert into tab_cities (name, state_id) values ('san francisco', 1);
insert into tab_cities (name, state_id) values ('san jose', 1);
insert into tab_cities (name, state_id) values ('miami', 2);
insert into tab_cities (name, state_id) values ('pensacola', 2);
insert into tab_cities (name, state_id) values ('austin', 3);

insert into tab_restaurants (name, shipping_costs, kitchen_id, created_at, updated_at, active, opened, address_city_id, address_postalcode, address_street, address_number, address_district)
values ('thai gourmet', 10, 1, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc', true, true, 1, '11111-222', 'fifth Avenue', '1000', 'downtown');
insert into tab_restaurants (name, shipping_costs, kitchen_id, created_at, updated_at, active, opened)
values ('thai delivery', 9.50, 1, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc', true, true);
insert into tab_restaurants (name, shipping_costs, kitchen_id, created_at, updated_at, active, opened)
values ('tuk tuk indian food', 15, 2, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc', true, true);
insert into tab_restaurants (name, shipping_costs, kitchen_id, created_at, updated_at, active, opened)
values ('java steakhouse', 12, 3, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc', true, true);
insert into tab_restaurants (name, shipping_costs, kitchen_id, created_at, updated_at, active, opened)
values ('Uncle Sams Diner', 11, 4, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc', true, true);
insert into tab_restaurants (name, shipping_costs, kitchen_id, created_at, updated_at, active, opened)
values ('Marys Bar', 6, 4, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc', true, true);

insert into tab_payment_ways (description) values ('Credit Card');
insert into tab_payment_ways (description) values ('Debit Card');
insert into tab_payment_ways (description) values ('Cash');

insert into tab_permissions (name, description) values ('SEARCH_KITCHEN', 'Allow to search kitchens');
insert into tab_permissions (name, description) values ('UPDATE_KITCHEN', 'Allow to update kitchens');

insert into restaurant_payment_way (restaurant_id, paymentway_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Pork with sweet and sour sauce', 'Delicious pork with special sauce', 78.90, false, 1, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Thai shrimp', '16 large prawns in spicy sauce', 110, true, 1, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Spicy salad with grilled meat', 'Leaf salad with thinly sliced grilled beef and our special red pepper sauce', 87.20, true, 2, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Garlic Naan', 'Indian traditional bread with garlic topping', 21, true, 3, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Murg Curry', 'Cubes of chicken prepared with curry sauce and spices', 43, true, 3, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('Ancho steak', 'Tender and juicy cut, two fingers thick, taken from the front of the sirloin steak', 79, true, 4, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('T-Bone', 'Very tasty cut, with a T-shaped bone, with the sirloin on one side and the filet mignon on the other', 89, true, 4, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('X-All sandwich', 'Big sandwich with lots of cheese, beef burger, bacon, egg, salad and mayonnaise', 19, true, 5, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_products (name, description, price, active, restaurant_id, created_at, updated_at) values ('termite skewer', 'Comes with flour, cassava and vinaigrette', 8, true, 6, current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');

insert into tab_groups (name, created_at, updated_at) values ('Manager', current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_groups (name, created_at, updated_at) values ('Seller', current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_groups (name, created_at, updated_at) values ('Secretary', current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_groups (name, created_at, updated_at) values ('Register', current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');

insert into group_permission (group_id, permission_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1);

insert into tab_users (name, email, password, created_at, updated_at) values ('User One', 'name1@domain.com', '123', current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_users (name, email, password, created_at, updated_at) values ('User Two', 'name2@domain.com', '123', current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_users (name, email, password, created_at, updated_at) values ('User Three', 'name3@domain.com', '123', current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_users (name, email, password, created_at, updated_at) values ('User Four', 'name4@domain.com', '123', current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');
insert into tab_users (name, email, password, created_at, updated_at) values ('User Five', 'name5@domain.com', '123', current_timestamp at time zone 'utc', current_timestamp at time zone 'utc');

insert into user_group (user_id, group_id) values (1, 1), (1, 2), (2, 2);

insert into restaurant_responsible_user (user_id, restaurant_id) values (1, 5), (3, 5);

insert into tab_orders (code, restaurant_id, client_user_id, paymentway_id, address_city_id, address_postalcode,
                    address_street, address_number, address_complement, address_district,
	                status, created_at, partial_price, shipping_costs, total_price)
values ('d4aed75c-44bd-4ec5-a303-ee20a0f88e32', 1, 1, 1, 1, '11111-111', 'First Avenue', '100', 'Apt 101', 'Downtown',
        'CREATED', current_timestamp at time zone 'utc', 298.90, 10, 308.90);

insert into tab_order_items (order_id, product_id, amount, unit_price, total_price, observations, created_at)
values (1, 1, 1, 78.9, 78.9, null, current_timestamp at time zone 'utc');

insert into tab_order_items (order_id, product_id, amount, unit_price, total_price, observations, created_at)
values (1, 2, 2, 110, 220, 'More spicy, please', current_timestamp at time zone 'utc');

insert into tab_orders (code, restaurant_id, client_user_id, paymentway_id, address_city_id, address_postalcode,
                            address_street, address_number, address_complement, address_district,
                            status, created_at, partial_price, shipping_costs, total_price)
values ('cee92b85-1456-43d7-842f-93be0d57b954', 4, 1, 2, 1, '22222-111', 'Second Avenue', '500', 'First block', 'Downtown',
        'CREATED', current_timestamp at time zone 'utc', 79, 0, 79);

insert into tab_order_items (order_id, product_id, amount, unit_price, total_price, observations, created_at)
values (2, 6, 1, 79, 79, 'Well done steak', current_timestamp at time zone 'utc');

insert into tab_orders (code, restaurant_id, client_user_id, paymentway_id, address_city_id, address_postalcode,
                            address_street, address_number, address_complement, address_district,
                            status, created_at, partial_price, shipping_costs, total_price)
values ('12a0af35-759d-43d2-bdfa-6cd85e3354bf', 4, 1, 2, 1, '33333-222', 'Third Avenue', '505', 'Second block', 'Downtown',
        'DELIVERED', current_timestamp at time zone 'utc', 120, 30, 150);

insert into tab_order_items (order_id, product_id, amount, unit_price, total_price, observations, created_at)
values (3, 2, 1, 120, 120, null, current_timestamp at time zone 'utc');

insert into tab_orders (code, restaurant_id, client_user_id, paymentway_id, address_city_id, address_postalcode,
                            address_street, address_number, address_complement, address_district,
                            status, created_at, partial_price, shipping_costs, total_price)
values ('960bc0ee-d04a-4729-a0f2-df01bd488f1c', 4, 1, 2, 1, '44444-333', 'Fourth Avenue', '510', 'Third block', 'Downtown',
        'DELIVERED', current_timestamp at time zone 'utc', 174.4, 5, 179.4);

insert into tab_order_items (order_id, product_id, amount, unit_price, total_price, observations, created_at)
values (4, 3, 2, 87.2, 174.4, null, current_timestamp at time zone 'utc');

insert into tab_orders (code, restaurant_id, client_user_id, paymentway_id, address_city_id, address_postalcode,
                            address_street, address_number, address_complement, address_district,
                            status, created_at, partial_price, shipping_costs, total_price)
values ('3b686e44-b6dc-4a9f-ab43-f8b872d8e2bd', 4, 1, 2, 1, '55555-444', 'Fifth Avenue', '510', 'Fourth block', 'Downtown',
        'DELIVERED', current_timestamp at time zone 'utc', 87.2, 10, 97.2);

insert into tab_order_items (order_id, product_id, amount, unit_price, total_price, observations, created_at)
values (5, 3, 1, 87.2, 87.2, null, current_timestamp at time zone 'utc');