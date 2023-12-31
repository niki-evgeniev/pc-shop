INSERT INTO roles (`id`, `roles`)
VALUES (1, 'ADMIN'),
       (2, 'MODERATOR'),
       (3, 'USER');

INSERT INTO users (id, create_on, email, name, password, username)
VALUES (1, '2023-11-19', 'admin@admin', 'adminov',
        'dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'admin'),
       (2, '2023-11-19', 'test@test', 'testov',
        'dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'test'),
       (3, '2023-11-19', 'user@user', 'userov',
        'dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'user');

INSERT INTO `brands` (`id`, `name`, created)
VALUES (1, 'Asus', '2023-11-19'),
       (2, 'Gigabyte ', '2023-11-19'),
       (3, 'Apple', '2023-11-19'),
       (4, 'Dell', '2023-11-19');

INSERT INTO `ip_users` (`id`, `ip`, is_banned)
VALUES (1, '0:0:0:0:0:0:0:1', false),
       (2, '212.50.79.137', true);

INSERT INTO `users_ip` (`user_id`, `ip_id`)
VALUES (1, 1),
       (2, 1),
       (3, 1);

INSERT INTO users_roles(`user_id`, `role_id`)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 2),
       (2, 3),
       (3, 2),
       (3, 3);

INSERT INTO `models` (`id`, `category`, `brand_id`, `name`, created, image_url, start_year)
VALUES (1, 'LAPTOP', 1, 'TUF-laptop', '2023-11-19',
        'https://ardes.bg/uploads/original/asus-fx506-tuf-gaming-f15-315233.jpg', '2022'),
       (2, 'LAPTOP', 1, 'ROG-laptop', '2023-11-19',
        'https://ardes.bg/uploads/original/asus-g531-rog-strix-g-236255.jpg', '2022'),
       (3, 'COMPUTER', 1, 'ROG-PC', '2023-11-19',
        'https://desktop.bg/system/images/383033/normal/asus_rog_strix_g15cfwb7636.jpg', '2022'),
       (4, 'COMPUTER', 2, 'AORUS-PC', '2023-11-19',
        'https://cdn.mdcomputers.in/image/cache/catalog/custom-desktop/gigabyte/aorus-for-elite-2.0/aorus-for-elite-2.0-image-main-600x600.jpg', '2022'),
       (5, 'LAPTOP', 2, 'AORUS-laptop', '2023-11-19',
        'https://www.gigabyte.com/Laptop/gamer/images/product/AORUS15PVC.png', '2022'),
       (6, 'LAPTOP', 3, 'MacBook-laptop', '2023-11-19',
        'https://www.investor.bg/media/files/resized/article/1600x/11e/0bf4f0034def62df1a0afd07e7f3d11e-apple-macbook-pro-m2-pro-and-m2-max-hero-230117-full-bleed-imagejpglarge.jpg', '2022'),
       (7, 'COMPUTER', 3, 'iMac-PC', '2023-11-19',
        'https://4.img-dpreview.com/files/p/E~TS590x0~articles/9294369869/DLCS5227-Edit.jpeg', '2022'),
       (8, 'LAPTOP', 4, 'Inspiron-laptop', '2023-11-19',
        'https://s13emagst.akamaized.net/products/44413/44412491/images/res_134ba9d019e16765b36cdc68da24dcf2.jpg', '2022'),
       (9, 'COMPUTER', 4, 'Vostro-PC', '2023-11-19',
        'https://technome.bg/image/cache/catalog/pc-import/0006301183441/0006301239018/00063038831951-446x446.jpg', '2022');

INSERT INTO `products` (id, created, description, computer_type, image_url, phone_number, price, type_to_use, year,
                        model_id, seller_id, traces_to_use)
VALUES (1, '2023-11-22 20:23:12.209579', 'asus pc1', 'COMPUTER',
        'https://desktop.bg/system/images/383033/normal/asus_rog_strix_g15cfwb7636.jpg',
        '4444',
        1500, 'GAMING', 2021, 2, 1, 'B'),
       (2, '2023-11-22 20:23:12.209579', 'asus pc2', 'COMPUTER',
        'https://desktop.bg/system/images/383033/normal/asus_rog_strix_g15cfwb7636.jpg',
        '4444-444',
        1600, 'GAMING', 2021, 2, 1, 'B'),
       (3, '2023-11-22 20:23:12.209579', 'asus pc3', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '4444-333',
        1900, 'GAMING', 2021, 2, 2, 'B'),
       (4, '2023-11-22 20:23:12.209579', 'asus pc4', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '4444-222',
        1900, 'GAMING', 2021, 2, 1, 'B'),
       (5, '2023-11-22 20:23:12.209579', 'asus pc5', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '222-333',
        1900, 'GAMING', 2021, 2, 3, 'B'),
       (6, '2023-11-22 20:23:12.209579', 'asus pc6', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '333-22',
        1900, 'GAMING', 2021, 2, 1, 'B'),
       (7, '2023-11-22 20:23:12.209579', 'asus pc7', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '2222-2222',
        1900, 'GAMING', 2021, 2, 2, 'B'),
       (8, '2023-11-22 20:23:12.209579', 'asus pc8', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '123123-424312',
        1900, 'GAMING', 2021, 2, 3, 'B'),
       (9, '2023-11-22 20:23:12.209579', 'asus pc9', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '342-3123',
        1900, 'GAMING', 2021, 2, 3, 'B'),
       (10, '2023-11-22 20:23:12.209579', 'asus pc10', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '43241-21312',
        1900, 'GAMING', 2021, 2, 1, 'B'),
       (11, '2023-11-22 20:23:12.209579', 'asus pc11', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '213123-213123',
        1900, 'GAMING', 2021, 2, 2, 'B');
