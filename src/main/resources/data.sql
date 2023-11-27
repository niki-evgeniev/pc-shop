INSERT INTO roles (`id`, `roles`)
VALUES
    (1, 'ADMIN'),
    (2, 'MODERATOR'),
    (3, 'USER');

INSERT INTO users (id, create_on, email, name, password, username, role_id)
VALUES
    (1, '2023-11-19', 'admin@admin', 'adminov','dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'admin', 1),
    (2, '2023-11-19', 'test@test', 'testov','dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'test', 3),
    (3, '2023-11-19', 'user@user', 'userov','dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'user', 3);

INSERT INTO `brands` (`id`, `name`)
VALUES (1, 'Asus'),
       (2, 'Gigabyte '),
       (3, 'Apple');

INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
VALUES (1, 'LAPTOP', 1, 'model1'),
       (2, 'COMPUTER', 1, 'ModelPc'),
       (3, 'COMPUTER', 2, 'ModelPcGiga'),
       (4, 'LAPTOP', 2, 'ModelLPGiga'),
       (5, 'LAPTOP', 3, 'AppleLapTop');