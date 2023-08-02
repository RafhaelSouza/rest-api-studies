create table restaurant_responsible_user (user_id int8 not null, restaurant_id int8 not null);
create index if not exists restaurant_responsible_user_idx on restaurant_responsible_user using btree (user_id, restaurant_id);

alter table if exists restaurant_responsible_user add constraint fk_user_restaurant foreign key (user_id) references tab_users;
alter table if exists restaurant_responsible_user add constraint fk_restaurant_user foreign key (restaurant_id) references tab_restaurants;