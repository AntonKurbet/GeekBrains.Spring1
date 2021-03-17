create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  score                 bigint,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (id, username, password, email, score)
values
(1, 'user', '$2y$12$KoBlGqSO5Efaci1TW4m3rOxQOtgz8A5XhWz8Jcnby5XkV7ENOJQTC', 'user@gmail.com', 0),
(2, 'user2', '$2y$12$KoBlGqSO5Efaci1TW4m3rOxQOtgz8A5XhWz8Jcnby5XkV7ENOJQTC', 'user2@gmail.com', 22),
(3, 'user3', '$2y$12$KoBlGqSO5Efaci1TW4m3rOxQOtgz8A5XhWz8Jcnby5XkV7ENOJQTC', 'user3@gmail.com', 33)
;

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(2, 1),
(3, 1);