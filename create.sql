create table user
(
    id         int           not null auto_increment,
    firstname  varchar(50)   not null,
    lastname   varchar(50)   not null,
    email      varchar(50)   not null,
    password   varchar(255)  not null,
    balance    DECIMAL(9, 2) NOT NULL DEFAULT 0,
    createDate DATETIME      NOT NULL DEFAULT now(),
    isActive   TINYINT       NOT NULL,
    primary key (id),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
) ) engine = InnoDB