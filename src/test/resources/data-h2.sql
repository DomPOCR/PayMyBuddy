-- -----------------------------------------------------
-- INSERT
-- -----------------------------------------------------


INSERT INTO user(id,lastname, firstname, email, password, createDate, balance)
VALUES (1,'Boyd', 'John', 'jboyd@email.com', 'mdp1', '2020-05-01', 2000.00),
       (2,'Bauer', 'Jack', 'jb@email.com', 'mdp2', '2019-05-01', 10000.00),
       (3,'P', 'Dom', 'dp@email.com', 'mdp3', '2018-05-01', 1000.00),
       (4,'Lefebvre', 'Paul', 'pl@email.com', 'mdp4', '2017-05-01', 10.00);
COMMIT;

INSERT INTO bank_account(iban, bic, bankName, accountName, user_id)
VALUES ('IBANBOYD', 'BICBOYD', 'CMN', 'Compte courant', 1),
       ('IBANBAUER', 'BICBAUER', 'AXA', 'Compte courant', 2),
       ('IBANP', 'BICP', 'LAPOSTE', 'Compte courant', 3),
       ('IBANLEFEVBRE', 'BICLEFEVBRE', 'BNP', 'Compte courant', 4);
COMMIT;

INSERT INTO transfert (amount, description, transactionDate)
VALUES ('1000.00', 'Mon 1er versement','2020-01-01'),
       ('99', 'Dette de DomP','2020-05-01');
COMMIT;

INSERT INTO external_transfert (transfert_id, bank_account_iban, fees)
VALUES (1, 'IBANP', '0.005');
COMMIT;

INSERT INTO internal_transfert (transfert_id, sender_id, receiver_id)
VALUES (2, 4, 2);

COMMIT;

INSERT INTO relation (owner, buddy)
VALUES  ('1', '2'),
        ('1', '4'),
        ('2', '1'),
        ('2', '3'),
        ('2', '4'),
        ('4', '1');



