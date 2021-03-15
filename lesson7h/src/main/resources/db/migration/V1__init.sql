create table category (
                         id bigserial primary key,
                         title varchar(255),
                         created_at timestamp default current_timestamp,
                         updated_at timestamp default current_timestamp,
                         deleted_at timestamp);

insert into category (id, title) values
(1, 'Drinks'),
(2, 'Food');

create table product (
    id bigserial primary key,
    title varchar(255),
    cost int,
    category_id bigserial,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    deleted_at timestamp);

insert into product (category_id, title, cost) values
(1, 'Tea', 80),
(1, 'Coffee', 100),
(1, 'Water', 50),
(2, 'Meat', 280),
(2, 'Fish', 300),
(2, 'Cheese', 250),
(1, 'Juice', 180),
(2, 'Bananas', 100),
(2, 'Carrot', 50),
(2, 'Onion',60);
