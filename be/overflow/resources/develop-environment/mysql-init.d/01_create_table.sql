create table auth_info_tb
(
    auth_info_id    BIGINT       not null auto_increment,
    user_id         BIGINT       not null,
    auth_info_type  varchar(255) not null,
    auth_info_token varchar(255) not null,
    created_date    datetime     not null,
    deleted         bit          not null,
    updated_date    datetime     not null,
    primary key (auth_info_id)
) ENGINE = InnoDB;

create table user_tb
(
    user_id       BIGINT       not null auto_increment,
    user_email    varchar(255) not null,
    user_nickname varchar(255) not null,
    user_password varchar(255) not null,
    created_date  datetime     not null,
    deleted       bit          not null,
    updated_date  datetime     not null,
    primary key (user_id)
) ENGINE = InnoDB;