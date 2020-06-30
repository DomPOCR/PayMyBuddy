-- -----------------------------------------------------
-- INSERT
-- -----------------------------------------------------
/*

INSERT INTO user(id,lastname, firstname, email, password, createDate, balance, isActive)
VALUES (1,'Boyd', 'John', 'jboyd@email.com', 'mdp1', now(), 0.00, 1),
       (2,'Bauer', 'Jack', 'jb@email.com', 'mdp2', now(), 10000.00, 1),
       (3,'P', 'Dom', 'dp@email.com', 'mdp3', now(), 1000.00, 1),
       (4,'Lefebvre', 'Paul', 'pl@email.com', 'mdp4', now(), 10.00, 1);
COMMIT;

INSERT INTO bank_account(iban, bic, bankName, accountName, user_id)
VALUES ('IBANBOYD', 'BICBOYD', 'CMN', 'Compte courant', 1),
       ('IBANBAUER', 'BICBAUER', 'AXA', 'Compte courant', 2),
       ('IBANP', 'BICP', 'LAPOSTE', 'Compte courant', 3),
       ('IBANLEFEVBRE', 'BICLEFEVBRE', 'BNP', 'Compte courant', 4);
COMMIT;

*/

