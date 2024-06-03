create table reservation
(
    id         int auto_increment
        primary key,
    room_id    int       not null,
    username   char(128) not null,
    start_time date      not null,
    end_time   date      not null,
    type       char(16)  not null
);

create table room
(
    id        int auto_increment
        primary key,
    room_type char(128) null,
    location  char(128) null
);

create table user
(
    username char(128) not null
        primary key,
    password char(128) not null,
    role     char(128) not null
);

create table user_message
(
    id       int auto_increment
        primary key,
    username char(120) not null,
    message  char(255) not null
);


