-- -----------------------------------------------------
-- Schema P6_DB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `P6_DB`;

-- -----------------------------------------------------
-- Schema P6_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `P6_DB` DEFAULT CHARACTER SET utf8;
USE `P6_DB`;

-- -----------------------------------------------------
-- Table `P6_DB`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `P6_DB`.`user`;

CREATE TABLE IF NOT EXISTS `P6_DB`.`user`
(
    `id`         INT           NOT NULL AUTO_INCREMENT,
    `lastname`   VARCHAR(50)   NOT NULL,
    `firstname`  VARCHAR(50)   NOT NULL,
    `email`      VARCHAR(50)   NOT NULL,
    `password`   VARCHAR(255)  NOT NULL,
    `balance`    DECIMAL(9, 2) NOT NULL DEFAULT 0,
    `createDate` DATETIME      NOT NULL DEFAULT now(),
    PRIMARY KEY (`id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC)
);


-- -----------------------------------------------------
-- Table `P6_DB`.`bank_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `P6_DB`.`bank_account`;

CREATE TABLE IF NOT EXISTS `P6_DB`.`bank_account`
(
    `iban`        VARCHAR(34) NOT NULL,
    `bic`         VARCHAR(50) NOT NULL,
    `bankName`    VARCHAR(50) NOT NULL,
    `accountName` VARCHAR(50) NOT NULL,
    `user_id`     INT         NOT NULL,
    PRIMARY KEY (`iban`),
    INDEX `fk_bank_account_user1_idx` (`user_id` ASC),
    CONSTRAINT `fk_bank_account_user1`
        FOREIGN KEY (`user_id`)
            REFERENCES `P6_DB`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);



-- -----------------------------------------------------
-- Table `P6_DB`.`transfert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `P6_DB`.`transfert`;

CREATE TABLE IF NOT EXISTS `P6_DB`.`transfert`
(
    `id`              INT           NOT NULL AUTO_INCREMENT,
    `amount`          DECIMAL(9, 2) NOT NULL,
    `description`     VARCHAR(255)  NULL,
    `transactionDate` DATETIME      NOT NULL DEFAULT now(),
    PRIMARY KEY (`id`)
);



-- -----------------------------------------------------
-- Table `P6_DB`.`relation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `P6_DB`.`relation`;

CREATE TABLE IF NOT EXISTS `P6_DB`.`relation`
(
    `id`    INT NOT NULL AUTO_INCREMENT,
    `owner` INT NOT NULL,
    `buddy` INT NOT NULL,
    -- PRIMARY KEY (`owner_id`, `buddy_id`),
    PRIMARY KEY (`id`),
    INDEX `fk_relation_user2_idx` (`buddy` ASC),
    CONSTRAINT `fk_relation_user1`
        FOREIGN KEY (`owner`)
            REFERENCES `P6_DB`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_relation_user2`
        FOREIGN KEY (`buddy`)
            REFERENCES `P6_DB`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);



-- -----------------------------------------------------
-- Table `P6_DB`.`internal_transfert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `P6_DB`.`internal_transfert`;

CREATE TABLE IF NOT EXISTS `P6_DB`.`internal_transfert`
(
    `transfert_id` INT NOT NULL AUTO_INCREMENT,
    `sender_id`    INT NOT NULL,
    `receiver_id`  INT NOT NULL,
    PRIMARY KEY (`transfert_id`),
    INDEX `fk_internal_transfert_user1_idx` (`sender_id` ASC),
    INDEX `fk_internal_transfert_user2_idx` (`receiver_id` ASC),
    CONSTRAINT `fk_internal_transfert_transfert1`
        FOREIGN KEY (`transfert_id`)
            REFERENCES `P6_DB`.`transfert` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_internal_transfert_user1`
        FOREIGN KEY (`sender_id`)
            REFERENCES `P6_DB`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_internal_transfert_user2`
        FOREIGN KEY (`receiver_id`)
            REFERENCES `P6_DB`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `P6_DB`.`external_transfert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `P6_DB`.`external_transfert`;

CREATE TABLE IF NOT EXISTS `P6_DB`.`external_transfert`
(
    `transfert_id`      INT           NOT NULL AUTO_INCREMENT,
    `bank_account_iban` VARCHAR(34)   NOT NULL,
    `fees`              DECIMAL(9, 2) NOT NULL DEFAULT 0,
    PRIMARY KEY (`transfert_id`),
    INDEX `fk_external_transfert_bank_account1_idx` (`bank_account_iban` ASC),
    CONSTRAINT `fk_external_transfert_transfert1`
        FOREIGN KEY (`transfert_id`)
            REFERENCES `P6_DB`.`transfert` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_external_transfert_bank_account1`
        FOREIGN KEY (`bank_account_iban`)
            REFERENCES `P6_DB`.`bank_account` (`iban`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

INSERT INTO p6_db.user(id,lastname, firstname, email, password, createDate, balance)
VALUES (1,'Boyd', 'John', 'jboyd@email.com', 'mdp1', now(), 2000.00),
       (2,'Bauer', 'Jack', 'jb@email.com', 'mdp2', now(), 10000.00),
       (3,'P', 'Dom', 'dp@email.com', 'mdp3', now(), 1000.00),
       (4,'Lefebvre', 'Paul', 'pl@email.com', 'mdp4', now(), 10.00);
COMMIT;

INSERT INTO p6_db.bank_account(iban, bic, bankName, accountName, user_id)
VALUES ('IBANBOYD', 'BICBOYD', 'CMN', 'Compte courant', 1),
       ('IBANBAUER', 'BICBAUER', 'AXA', 'Compte courant', 2),
       ('IBANP', 'BICP', 'LAPOSTE', 'Compte courant', 3),
       ('IBANLEFEVBRE', 'BICLEFEVBRE', 'BNP', 'Compte courant', 4);
COMMIT;

INSERT INTO p6_db.transfert (amount, description)
VALUES ('1000.00', 'Mon 1er versement'),
       ('99', 'Dette de DomP');
COMMIT;

INSERT INTO p6_db.external_transfert (transfert_id, bank_account_iban, fees)
VALUES (1, 'IBANP', '5');
COMMIT;

INSERT INTO p6_db.internal_transfert (transfert_id, sender_id, receiver_id)
VALUES (2, 4, 2);

COMMIT;

INSERT INTO p6_db.relation (owner, buddy)
VALUES  ('1', '2'),
        ('1', '4'),
        ('2', '1'),
        ('2', '3'),
        ('2', '4'),
        ('4', '1');
COMMIT;
