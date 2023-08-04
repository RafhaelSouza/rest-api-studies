alter table tab_restaurants add active boolean not null;
update tab_restaurants set active = true;