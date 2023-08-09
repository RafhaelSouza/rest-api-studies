create extension if not exists "uuid-ossp";

alter table tab_orders add column code uuid not null default uuid_generate_v4();
alter table tab_orders add constraint uk_order_code unique (code);


