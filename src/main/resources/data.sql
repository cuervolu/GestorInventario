INSERT INTO categories (name)
VALUES ('Jeringa');
INSERT INTO categories (name)
VALUES ('Vacutainer');
INSERT INTO categories (name)
VALUES ('Adaptador');
INSERT INTO categories (name)
VALUES ('Alcohol Sachet');
INSERT INTO categories (name)
VALUES ('Mariposas');
INSERT INTO categories (name)
VALUES ('Agujas');
INSERT INTO categories (name)
VALUES ('Parche Venopunción');
INSERT INTO categories (name)
VALUES ('Ligadura');
INSERT INTO categories (name)
VALUES ('Camisa Venopunción');
INSERT INTO categories (name)
VALUES ('Apositos');
INSERT INTO categories (name)
VALUES ('Gasas');
INSERT INTO categories (name)
VALUES ('Alcohol');
INSERT INTO categories (name)
VALUES ('Agua Oxigenada');
INSERT INTO categories (name)
VALUES ('Catéter');
INSERT INTO categories (name)
VALUES ('Telas');
INSERT INTO categories (name)
VALUES ('Guantes');
INSERT INTO categories (name)
VALUES ('Algodones');
-- Jeringa
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Jeringa Estándar', 'Jeringa para administración de medicamentos',
        (SELECT id FROM categories WHERE name = 'Jeringa'), 2.99, 1000, false, false,
        CURRENT_TIMESTAMP);

-- Vacutainer
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Vacutainer Normal', 'Tubo para extracción de sangre normal',
        (SELECT id FROM categories WHERE name = 'Vacutainer'), 1.49, 800, false, false,
        CURRENT_TIMESTAMP);

-- Adaptador
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Adaptador Universal', 'Adaptador para diversos usos médicos',
        (SELECT id FROM categories WHERE name = 'Adaptador'), 3.99, 300, false, false,
        CURRENT_TIMESTAMP);

-- Alcohol Sachet
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Alcohol Sachet', 'Paquete individual de alcohol para limpieza',
        (SELECT id FROM categories WHERE name = 'Alcohol Sachet'), 0.99, 500, false, false,
        CURRENT_TIMESTAMP);

-- Mariposas
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Mariposas', 'Dispositivo para extracción de sangre con mariposa',
        (SELECT id FROM categories WHERE name = 'Mariposas'), 4.49, 200, false, false,
        CURRENT_TIMESTAMP);

-- Agujas
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Agujas Estándar', 'Agujas para diversas aplicaciones médicas',
        (SELECT id FROM categories WHERE name = 'Agujas'), 1.99, 1000, false, false,
        CURRENT_TIMESTAMP);

-- Parche Venopunción
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Parche Venopunción', 'Parche adhesivo para sitios de punción venosa',
        (SELECT id FROM categories WHERE name = 'Parche Venopunción'), 0.79, 800, false, false,
        CURRENT_TIMESTAMP);

-- Ligadura
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Ligadura', 'Material para ligar vasos sanguíneos',
        (SELECT id FROM categories WHERE name = 'Ligadura'), 1.29, 500, false, false,
        CURRENT_TIMESTAMP);

-- Camisa Venopunción
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Camisa Venopunción', 'Prenda para facilitar la punción venosa',
        (SELECT id FROM categories WHERE name = 'Camisa Venopunción'), 5.99, 200, false, false,
        CURRENT_TIMESTAMP);

-- Apositos
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Apositos Estériles', 'Apósitos para cubrir y proteger heridas',
        (SELECT id FROM categories WHERE name = 'Apositos'), 2.49, 600, true, false,
        CURRENT_TIMESTAMP);

-- Gasas
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Gasas Estériles', 'Gasas para limpieza y cubrimiento de heridas',
        (SELECT id FROM categories WHERE name = 'Gasas'), 1.99, 800, true, false,
        CURRENT_TIMESTAMP);

-- Alcohol
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Alcohol Líquido', 'Alcohol para limpieza y desinfección',
        (SELECT id FROM categories WHERE name = 'Alcohol'), 3.49, 400, false, false,
        CURRENT_TIMESTAMP);

-- Agua Oxigenada
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Agua Oxigenada', 'Solución para limpieza de heridas y desinfección',
        (SELECT id FROM categories WHERE name = 'Agua Oxigenada'), 1.99, 300, false, false,
        CURRENT_TIMESTAMP);

-- Catéter
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Catéter Intravenoso', 'Dispositivo para administración de fluidos',
        (SELECT id FROM categories WHERE name = 'Catéter'), 8.99, 150, false, false,
        CURRENT_TIMESTAMP);

-- Telas
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Telas para Vendaje', 'Telas para realizar vendajes médicos',
        (SELECT id FROM categories WHERE name = 'Telas'), 2.29, 500, false, false,
        CURRENT_TIMESTAMP);

-- Guantes
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Guantes de Nitrilo', 'Guantes médicos de nitrilo desechables',
        (SELECT id FROM categories WHERE name = 'Guantes'), 5.99, 1000, true, false,
        CURRENT_TIMESTAMP);

-- Algodones
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted,
                      created_date)
VALUES ('Algodones', 'Bolas de algodón para limpieza y aplicaciones médicas',
        (SELECT id FROM categories WHERE name = 'Algodones'), 1.49, 800, false, false,
        CURRENT_TIMESTAMP);


-- Roles
INSERT INTO roles (name)
VALUES ('ADMIN');
INSERT INTO roles (name)
VALUES ('USER');
