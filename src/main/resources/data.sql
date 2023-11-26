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