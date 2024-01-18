insert into USUARIOS (id, username, password, role) values (100, 'ana@email.com', '$2a$12$0KnQDCJl2j1gtjKmGZCWjerb3L3j0p3pSnwxxDm560qRAMbiW7bii', 'ROLE_ADMIN');
insert into USUARIOS (id, username, password, role) values (101, 'bia@email.com', '$2a$12$0KnQDCJl2j1gtjKmGZCWjerb3L3j0p3pSnwxxDm560qRAMbiW7bii', 'ROLE_CLIENTE');
insert into USUARIOS (id, username, password, role) values (102, 'bob@email.com', '$2a$12$0KnQDCJl2j1gtjKmGZCWjerb3L3j0p3pSnwxxDm560qRAMbiW7bii', 'ROLE_CLIENTE');
insert into USUARIOS (id, username, password, role) values (103, 'toby@email.com', '$2a$12$0KnQDCJl2j1gtjKmGZCWjerb3L3j0p3pSnwxxDm560qRAMbiW7bii', 'ROLE_CLIENTE');
insert into CLIENTES (id, nome, cpf, id_usuario) values (10, 'Bianca Silva', '57673202093',101);
insert into CLIENTES (id, nome, cpf, id_usuario) values (20, 'Roberto Gomes', '88913528029',102);