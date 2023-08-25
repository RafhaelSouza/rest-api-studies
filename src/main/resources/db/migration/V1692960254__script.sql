create table tab_product_photo (
    product_id int8 not null,
    file_name varchar(150) not null,
    description varchar(150) not null,
    content_type varchar(80) not null,
    file_size int4 not null,
    primary key(product_id)
);

alter table if exists tab_product_photo add constraint fk_product_photo foreign key (product_id) references tab_product_photo;
