create table post
(
    id            bigint       not null auto_increment,
    reg_dt  datetime,
    upd_dt datetime,
    post_dt timestamp(6),
    author        varchar(255),
    content       TEXT         not null,
    title         varchar(500) not null,
    primary key (id)
);

create table user
(
    id            bigint       not null auto_increment,
    reg_dt  datetime,
    upd_dt datetime,
    email         varchar(255) not null,
    name          varchar(255) not null,
    picture       varchar(255),
    role          varchar(255) not null,
    primary key (id)
);

create table global_time_test
(
    id                     bigint not null auto_increment,
    title                  varchar(255),
    local_datetime         datetime,
    local_timestamp        timestamp,
    zone_datetime          datetime,
    zone_timestamp         timestamp,
    utc_zone_date_string   varchar(255),
    utc_offset_date_string varchar(255),
    utc_local_date_string  varchar(255),
    primary key (id)
);