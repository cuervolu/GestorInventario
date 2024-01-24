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
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Jeringa Estándar', 'Jeringa para administración de medicamentos', (SELECT id FROM categories WHERE name = 'Jeringa'), 2.99, 1000, false, false, CURRENT_TIMESTAMP, 1);

-- Vacutainer
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Vacutainer Normal', 'Tubo para extracción de sangre normal', (SELECT id FROM categories WHERE name = 'Vacutainer'), 1.49, 800, false, false, CURRENT_TIMESTAMP, 1);

-- Adaptador
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Adaptador Universal', 'Adaptador para diversos usos médicos', (SELECT id FROM categories WHERE name = 'Adaptador'), 3.99, 300, false, false, CURRENT_TIMESTAMP, 1);

-- Alcohol Sachet
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Alcohol Sachet', 'Paquete individual de alcohol para limpieza', (SELECT id FROM categories WHERE name = 'Alcohol Sachet'), 0.99, 500, false, false, CURRENT_TIMESTAMP, 1);

-- Mariposas
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Mariposas', 'Dispositivo para extracción de sangre con mariposa', (SELECT id FROM categories WHERE name = 'Mariposas'), 4.49, 200, false, false, CURRENT_TIMESTAMP, 1);

-- Agujas
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Agujas Estándar', 'Agujas para diversas aplicaciones médicas', (SELECT id FROM categories WHERE name = 'Agujas'), 1.99, 1000, false, false, CURRENT_TIMESTAMP, 1);

-- Parche Venopunción
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Parche Venopunción', 'Parche adhesivo para sitios de punción venosa', (SELECT id FROM categories WHERE name = 'Parche Venopunción'), 0.79, 800, false, false, CURRENT_TIMESTAMP, 1);

-- Ligadura
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Ligadura', 'Material para ligar vasos sanguíneos', (SELECT id FROM categories WHERE name = 'Ligadura'), 1.29, 500, false, false, CURRENT_TIMESTAMP, 1);

-- Camisa Venopunción
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Camisa Venopunción', 'Prenda para facilitar la punción venosa', (SELECT id FROM categories WHERE name = 'Camisa Venopunción'), 5.99, 200, false, false, CURRENT_TIMESTAMP, 1);

-- Apositos
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Apositos Estériles', 'Apósitos para cubrir y proteger heridas', (SELECT id FROM categories WHERE name = 'Apositos'), 2.49, 600, true, false, CURRENT_TIMESTAMP, 1);

-- Gasas
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Gasas Estériles', 'Gasas para limpieza y cubrimiento de heridas', (SELECT id FROM categories WHERE name = 'Gasas'), 1.99, 800, true, false, CURRENT_TIMESTAMP, 1);

-- Alcohol
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Alcohol Líquido', 'Alcohol para limpieza y desinfección', (SELECT id FROM categories WHERE name = 'Alcohol'), 3.49, 400, false, false, CURRENT_TIMESTAMP, 1);

-- Agua Oxigenada
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Agua Oxigenada', 'Solución para limpieza de heridas y desinfección', (SELECT id FROM categories WHERE name = 'Agua Oxigenada'), 1.99, 300, false, false, CURRENT_TIMESTAMP, 1);

-- Catéter
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Catéter Intravenoso', 'Dispositivo para administración de fluidos', (SELECT id FROM categories WHERE name = 'Catéter'), 8.99, 150, false, false, CURRENT_TIMESTAMP, 1);

-- Telas
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Telas para Vendaje', 'Telas para realizar vendajes médicos', (SELECT id FROM categories WHERE name = 'Telas'), 2.29, 500, false, false, CURRENT_TIMESTAMP, 1);

-- Guantes
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Guantes de Nitrilo', 'Guantes médicos de nitrilo desechables', (SELECT id FROM categories WHERE name = 'Guantes'), 5.99, 1000, true, false, CURRENT_TIMESTAMP, 1);

-- Algodones
INSERT INTO products (name, description, category_id, price, stock, sterile, is_deleted, created_date, created_by)
VALUES ('Algodones', 'Bolas de algodón para limpieza y aplicaciones médicas', (SELECT id FROM categories WHERE name = 'Algodones'), 1.49, 800, false, false, CURRENT_TIMESTAMP, 1);



-- Roles
INSERT INTO roles (name)
VALUES ('ADMIN');
INSERT INTO roles (name)
VALUES ('USER');
