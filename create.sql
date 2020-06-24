create table bank_account
(
    iban        varchar(34) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (iban)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null DEFAULT 0,
    createDate datetime      not null DEFAULT now(),
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    iban        varchar(34) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (iban)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
create table bank_account
(
    user_id     varchar(255) not null,
    accountName varchar(50),
    bankName    varchar(50),
    bic         varchar(50),
    primary key (user_id)
) engine = InnoDB
create table external_transfert
(
    transfert_id bigint        not null,
    fees         Decimal(9, 2) not null,
    primary key (transfert_id)
) engine = InnoDB
create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
insert into hibernate_sequence
values (1)
create table internal_transfert
(
    transfert_id bigint not null,
    primary key (transfert_id)
) engine = InnoDB
create table relation
(
    owner_id bigint not null,
    primary key (owner_id)
) engine = InnoDB
create table transfert
(
    id          bigint        not null,
    accountName varchar(50)   not null,
    amount      Decimal(9, 2) not null,
    bankName    varchar(50)   not null,
    primary key (id)
) engine = InnoDB
create table user
(
    id         bigint        not null,
    balance    Decimal(9, 2) not null,
    createDate datetime      not null,
    email      varchar(255)  not null,
    firstname  varchar(50)   not null,
    isActive   TINYINT       not null,
    lastname   varchar(50)   not null,
    password   varchar(255)  not null,
    primary key (id)
) engine = InnoDB
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email)
alter table bank_account
    add constraint fk_bank_account_user1 foreign key (user_id) references user (id)
