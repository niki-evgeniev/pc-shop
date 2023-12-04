INSERT INTO roles (`id`, `roles`)
VALUES (1, 'ADMIN'),
       (2, 'MODERATOR'),
       (3, 'USER');

INSERT INTO users (id, create_on, email, name, password, username)
VALUES
    (1, '2023-11-19', 'admin@admin', 'adminov','dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'admin'),
    (2, '2023-11-19', 'test@test', 'testov','dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'test'),
    (3, '2023-11-19', 'user@user', 'userov','dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'user');

INSERT INTO `brands` (`id`, `name`)
VALUES (1, 'Asus'),
       (2, 'Gigabyte '),
       (3, 'Apple'),
       (4, 'Dell');

INSERT INTO `ip_users` (`id`, `ip`)
VALUES (1, '0:0:0:0:0:0:0:1');

INSERT INTO `users_ip` (`user_id`, `ip_id`)
VALUES (1, 1),
       (2, 1),
       (3, 1);

INSERT INTO users_roles(`user_id`, `role_id`)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (2, 2),
    (2, 3),
    (3, 2),
    (3, 3);

INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
VALUES (1, 'LAPTOP', 1, 'TUF'),
       (2, 'LAPTOP', 1, 'ROG'),
       (3, 'COMPUTER', 1, 'ROG-PC'),
       (4, 'COMPUTER', 2, 'AORUS-PC'),
       (5, 'LAPTOP', 2, 'AORUS'),
       (6, 'LAPTOP', 3, 'MacBook'),
       (7, 'COMPUTER', 3, 'iMac-PC'),
       (8, 'LAPTOP', 4, 'Inspiron'),
       (9, 'COMPUTER', 4, 'Vostro-PC');

INSERT INTO `products` (id, created, description, computer_type, image_url, phone_number, price, type_to_use, year, model_id, seller_id)
VALUES (1, '2023-11-22 20:23:12.209579', 'asus pc1', 'COMPUTER',
        'https://desktop.bg/system/images/383033/normal/asus_rog_strix_g15cfwb7636.jpg',
        '4444',
        1500, 'GAMING', 2021, 2, 1),
       (2, '2023-11-22 20:23:12.209579', 'asus pc2', 'COMPUTER',
        'https://desktop.bg/system/images/383033/normal/asus_rog_strix_g15cfwb7636.jpg',
        '4444-444',
        1600, 'GAMING', 2021, 2, 1),
       (3, '2023-11-22 20:23:12.209579', 'asus pc3', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '4444-333',
        1900, 'GAMING', 2021, 2, 2),
       (4, '2023-11-22 20:23:12.209579', 'asus pc4', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '4444-222',
        1900, 'GAMING', 2021, 2, 1),
       (5, '2023-11-22 20:23:12.209579', 'asus pc5', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '222-333',
        1900, 'GAMING', 2021, 2, 3),
       (6, '2023-11-22 20:23:12.209579', 'asus pc6', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '333-22',
        1900, 'GAMING', 2021, 2, 1),
       (7, '2023-11-22 20:23:12.209579', 'asus pc7', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '2222-2222',
        1900, 'GAMING', 2021, 2, 2),
       (8, '2023-11-22 20:23:12.209579', 'asus pc8', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '123123-424312',
        1900, 'GAMING', 2021, 2, 3),
       (9, '2023-11-22 20:23:12.209579', 'asus pc9', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '342-3123',
        1900, 'GAMING', 2021, 2, 3),
       (10, '2023-11-22 20:23:12.209579', 'asus pc10', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '43241-21312',
        1900, 'GAMING', 2021, 2, 1),
       (11, '2023-11-22 20:23:12.209579', 'asus pc11', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '213123-213123',
        1900, 'GAMING', 2021, 2, 2);
