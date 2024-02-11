INSERT INTO roles (`id`, `roles`)
VALUES (1, 'ADMIN'),
       (2, 'MODERATOR'),
       (3, 'USER');

INSERT INTO users (id, create_on, email, first_name, last_name, password, username)
VALUES (1, '2023-11-19', 'admin@admin', 'admin', 'adminov',
        'dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'admin'),
       (2, '2023-11-19', 'test@test', 'test', 'testov',
        'dd6b70ba154d881d01f35d22a1fdc91003c02debb45d92eec4f87f9898b9d6478555ba9ce0304487d0c814455470bc05', 'test'),
       (3, '2023-11-19', 'user@user', 'user', 'userov',
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
VALUES (1, 'LAPTOP', 1, 'fx506-tuf', '2023-11-19',
        'https://ardes.bg/uploads/original/asus-fx506-tuf-gaming-f15-315233.jpg', '2022'),
       (2, 'LAPTOP', 1, 'g531-rog', '2023-11-19',
        'https://ardes.bg/uploads/original/asus-g531-rog-strix-g-236255.jpg', '2022'),
       (3, 'COMPUTER', 1, 'ROG-PC', '2023-11-19',
        'https://www.asus.com/media/Odin/Websites/us/ProductLine/20231028011358.png', '2022'),
       (4, 'COMPUTER', 2, 'aorus-elite-2', '2023-11-19',
        'https://cdn.mdcomputers.in/image/cache/catalog/custom-desktop/gigabyte/aorus-for-elite-2.0/aorus-for-elite-2.0-image-main-600x600.jpg',
        '2022'),
       (5, 'LAPTOP', 2, 'aorus-15pvc', '2023-11-19',
        'https://www.gigabyte.com/Laptop/gamer/images/product/AORUS15PVC.png', '2022'),
       (6, 'LAPTOP', 3, 'MacBook-pro', '2023-11-19',
        'https://www.investor.bg/media/files/resized/article/1600x/11e/0bf4f0034def62df1a0afd07e7f3d11e-apple-macbook-pro-m2-pro-and-m2-max-hero-230117-full-bleed-imagejpglarge.jpg',
        '2022'),
       (7, 'COMPUTER', 3, 'iMac-PC-2m', '2023-11-19',
        'https://4.img-dpreview.com/files/p/E~TS590x0~articles/9294369869/DLCS5227-Edit.jpeg', '2022'),
       (8, 'LAPTOP', 4, 'Inspiron-laptop', '2023-11-19',
        'https://s13emagst.akamaized.net/products/44413/44412491/images/res_134ba9d019e16765b36cdc68da24dcf2.jpg',
        '2022'),
       (9, 'COMPUTER', 4, 'Vostro-PC', '2023-11-19',
        'https://technome.bg/image/cache/catalog/pc-import/0006301183441/0006301239018/00063038831951-446x446.jpg',
        '2022');

INSERT INTO `products` (id, created, description, computer_type, image_url, phone_number, price, type_to_use, year,
                        model_id, seller_id, traces_to_use, is_sold)
VALUES (1, '2023-11-22 20:23:12.209579', 'asus pc1 i5 14 400 16gb ram 1tb NVME', 'COMPUTER',
        'https://www.asus.com/media/Odin/Websites/us/ProductLine/20231028011358.png',
        '4444-333-22-11',
        999, 'GAMING', 2021, 3, 1, 'B', false),
       (2, '2023-11-22 20:23:12.209579', 'asus pc2 i7 13 700k 32gb ram 2tb NVME gtx 3080 Ti', 'COMPUTER',
        'https://ardes.bg/uploads/original/asus-rog-strix-g35ca-451273.jpg',
        '555-4444-6665',
        1200, 'GAMING', 2021, 3, 1, 'A', false),
       (3, '2023-11-22 20:23:12.209579', 'asus pc3 i3 10100 8gb ram 512gb NVME', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '09932121232',
        699, 'GAMING', 2021, 2, 2, 'B', false),
       (4, '2023-11-22 20:23:12.209579', 'asus pc4 i3 10100 8gb 254gb NVME intel HD', 'LAPTOP',
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4XUEjwz6h-QWoTYpTB44jq4TWOnCwuacaebdfOrz1Rm8NkRXpuT8Xb6y2cM0E2MI6O_4&usqp=CAU',
        '444422-222',
        499, 'GAMING', 2021, 2, 1, 'A', false),
       (5, '2023-11-22 20:23:12.209579', 'asus pc5 i3 10100 8gb 256gb NVME intel HD', 'LAPTOP',
        'https://s13emagst.akamaized.net/products/52377/52376151/images/res_743d2daa3f630089ef3369ead42ce37b.jpg',
        '22211-33399',
        501, 'GAMING', 2021, 1, 3, 'B', false),
       (6, '2023-11-22 20:23:12.209579', 'asus pc6 Intel celeron 4gb ram 1tb HDD ', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '333213-2264532',
        299, 'GAMING', 2021, 1, 1, 'B', false),
       (7, '2023-11-22 20:23:12.209579', 'asus pc7 Intel celeron 4gb ram 512gb HDD', 'LAPTOP',
        'https://cdn1.focus.bg/bazar/e2/original/e2f30a150aa16c47f606f83e401fb0d7.jpg',
        '22224123-22225',
        350, 'GAMING', 2021, 2, 2, 'B', false),
       (8, '2023-11-22 20:23:12.209579', 'asus pc8 i3 10100 8gb 512gb HDD intel HD', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/ProductLine/20200824120814.jpg',
        '123123-424312',
        300, 'GAMING', 2021, 1, 3, 'B', false),
       (9, '2023-11-22 20:23:12.209579', 'asus pc9 AMD Ryzen 7 5800H 32gb ram 1tb NVME Nvidia GeForce RTX 3070',
        'LAPTOP',
        'https://cdn.ozone.bg/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/g/e/24efc955030be470354b943a14d6c8ad/geyming-laptop-asus---rog-strix-g15--156---ryzen-7--300hz--siv-30.jpg',
        '342-3123',
        1900, 'GAMING', 2021, 2, 3, 'B', false),
       (10, '2023-11-22 20:23:12.209579', 'asus pc10 Core™ Ultra 9 processor with NPU 32gb ram 2tb NVME', 'LAPTOP',
        'https://www.asus.com/media/Odin/Websites/global/Series/11.png',
        '43241-21312',
        1399, 'GAMING', 2021, 2, 1, 'B', false),
       (11, '2023-11-22 20:23:12.209579', 'asus pc11 intel 16gb ram 1tb HDD', 'LAPTOP',
        'https://www.trustedreviews.com/wp-content/uploads/sites/54/2023/06/Asus-Zenbook-Pro-14-Duo-OLED-21-scaled.jpg',
        '213123-213123',
        999, 'GAMING', 2021, 2, 2, 'B', false),
       (12, '2024-02-06 21:47:21.180268', 'Apple M1 (3.2GHz, 4M) 8 GB LPDDR4X 4266 MHz256GB SSD13.3" (33.78cm) 2560x1600 IPS лъскав дисплей', 'LAPTOP',
        'https://img-cdn.heureka.group/v1/8f165a09-f89c-4040-b8bc-a058f326211d.jpg?height=600',
        '42133',
        799.00, 'OFFICE', 2022, 6, 1, 'A', false),
       (13, '2024-02-06 21:47:21.180268',
        'Модел процесор:	Apple M2 Оперативна памет:	16 GB SSD (GB):	512 Размер на екрана (inch):	15.3" (33.78cm) 2560x1600 IPS лъскав дисплей', 'LAPTOP',
        'https://cdn.ozone.bg/media/catalog/product/cache/1/image/a4e40ebdc3e371adff845072e1c73f37/l/a/3d627471dda73641c8e5b99a73a70c3f/laptop-apple---macbook-air-15--153---m2-8-10--16gb-512gb--sin-30.jpg',
        '4123414',
        2999.00, 'OFFICE', 2024, 6, 1, 'A', false),
       (14, '2024-02-06 21:47:21.180268',
        'Процесор, серия:  Intel Core i5 Процесор:  Intel Core i5-1235U 1.30 GHz, 12 MB cacheЧипсет:  Intel Alder LakeПамет, GB:  8GB 3200GHz (1x8GB)Памет, слотове:  up to 16GB, Two SODIMM Памет, тип:  DDR4', 'LAPTOP',
        'https://img-cdn.heureka.group/v1/0731770d-1312-4dd9-b46c-2ca874d45bfc.jpg?height=600',
        '0896450701',
        1099.00, 'OFFICE', 2023, 8, 1, 'A', false);

