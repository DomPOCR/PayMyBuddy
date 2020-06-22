CREATE database IF NOT EXISTS testP6;
USE TestP6;
-- -----------------------------------------------------
-- Table `testP6`.`user`
-- -----------------------------------------------------

DROP TABLE IF EXISTS user;
CREATE table IF NOT EXISTS testP6.user
(
    Id         int           NOT NULL auto_increment,
    firstname  varchar(50)   NOT NULL,
    lastname   varchar(50)   NOT NULL,
    email      varchar(50)   NOT NULL,
    password   varchar(255)  NOT NULL,
    balance    DECIMAL(9, 2) NOT NULL DEFAULT 0,
    createDate DATETIME      NOT NULL DEFAULT now(),
    isActive   TINYINT       NOT NULL,
    primary key (id),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
) ENGINE = InnoDB
  CHARACTER SET utf8;

-- -----------------------------------------------------
-- Table `testP6`.`bank_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testP6`.`bank_account`
(
    `iban`        VARCHAR(34)  NOT NULL,
    `bic`         VARCHAR(50)  NOT NULL,
    `bankName`    VARCHAR(255) NOT NULL,
    `accountName` VARCHAR(255) NOT NULL,
    `user_id`     INT          NOT NULL,
    PRIMARY KEY (`iban`, `user_id`),
    INDEX `fk_bank_account_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_bank_account_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `testP6`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testP6`.`tranfert`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testP6`.`tranfert`
(
    `id`              INT           NOT NULL AUTO_INCREMENT,
    `amount`          DECIMAL(9, 2) NOT NULL,
    `description`     VARCHAR(255)  NOT NULL,
    `transactionDate` DATETIME      NOT NULL DEFAULT now(),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testP6`.`relation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testP6`.`relation`
(
    `id`       INT NOT NULL AUTO_INCREMENT,
    `user_id1` INT NOT NULL,
    `user_id`  INT NOT NULL,
    PRIMARY KEY (`id`, `user_id1`, `user_id`),
    INDEX `fk_relation_user2_idx` (`user_id1` ASC) VISIBLE,
    INDEX `fk_relation_user1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_relation_user2`
        FOREIGN KEY (`user_id1`)
            REFERENCES `testP6`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_relation_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `testP6`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testP6`.`transfert_statut`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testP6`.`transfert_statut`
(
    `id`    INT         NOT NULL AUTO_INCREMENT,
    `label` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `label_UNIQUE` (`label` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testP6`.`internal_transfert`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testP6`.`internal_transfert`
(
    `id`            INT NOT NULL AUTO_INCREMENT,
    `own_user_id`   INT NOT NULL,
    `buddy_user_id` INT NOT NULL,
    PRIMARY KEY (`id`, `own_user_id`, `buddy_user_id`),
    INDEX `fk_internal_transfert_user1_idx` (`own_user_id` ASC) VISIBLE,
    INDEX `fk_internal_transfert_user2_idx` (`buddy_user_id` ASC) VISIBLE,
    CONSTRAINT `fk_internal_transfert_user1`
        FOREIGN KEY (`own_user_id`)
            REFERENCES `testP6`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_internal_transfert_user2`
        FOREIGN KEY (`buddy_user_id`)
            REFERENCES `testP6`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testP6`.`external_transfert`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `testP6`.`external_transfert`
(
    `id`                               INT NOT NULL AUTO_INCREMENT,
    `internal_transfert_id`            INT NOT NULL,
    `internal_transfert_own_user_id`   INT NOT NULL,
    `internal_transfert_buddy_user_id` INT NOT NULL,
    PRIMARY KEY (`id`, `internal_transfert_id`, `internal_transfert_own_user_id`, `internal_transfert_buddy_user_id`),
    INDEX `fk_external_transfert_internal_transfert1_idx` (`internal_transfert_id` ASC, `internal_transfert_own_user_id`
                                                           ASC, `internal_transfert_buddy_user_id` ASC) VISIBLE,
    CONSTRAINT `fk_external_transfert_internal_transfert1`
        FOREIGN KEY (`internal_transfert_id`, `internal_transfert_own_user_id`, `internal_transfert_buddy_user_id`)
            REFERENCES `testP6`.`internal_transfert` (`id`, `own_user_id`, `buddy_user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- INSERT
-- -----------------------------------------------------

INSERT INTO user(id, lastname, firstname, email, password, createDate, balance, isActive)
VALUES (1, 'Boyd', 'John', 'jaboyd@email.com', 'mdp1', now(), 0.00, 1),
       (2, 'Bauer', 'Jack', 'jb@email.com', 'mdp2', now(), 100.00, 1),
       (3, 'P', 'Dom', 'dp@email.com', 'mdp3', now(), 1000.00, 1),
       (4, 'Lefebvre', 'Paul', 'pl@email.com', 'mdp4', now(), 10.00, 0);
COMMIT;

INSERT INTO bank_account(iban, bic, bankName, accountName)
VALUES ('IBANBOYD', 'BICBOYD', 'CMN', 'Compte courant'),
       ('IBANBAUER', 'BICBAUER', 'CMN', 'Compte courant'),
       ('IBANP', 'BICP', 'CMN', 'Compte courant'),
       ('IBANLEFEVBRE', 'BICLEFEVBRE', 'CMN', 'Compte courant');
COMMIT;



