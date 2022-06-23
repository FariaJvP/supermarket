INSERT INTO tb_brand (brand_name) VALUES ('Coca-Cola'), ('Pepsi'), ('Kellogg''s'),('General Mills'),('Splendid Spoon'),('Mosaic Foods'),('Nestlé'),('Danone'),('Persil'),('Tide'),('Smucker''s'), ('PB2'), ('Dave''s Killer'),('The Hagen Group'),('White Pearl');

INSERT INTO tb_product(product_name, product_description, price, unit, brand_id) VALUES ('Penne alla Vodka', 'Frozen meal, 286g, vegetarian and no added sugar.', 15.00, 'UNIT', 6);
INSERT INTO tb_product(product_name, product_description, price, unit, brand_id) VALUES ('Coca-Cola Traditional', '2 liter bottle', 1.85, 'UNIT', 1);
INSERT INTO tb_product(product_name, product_description, price, unit, brand_id) VALUES ('Coca-Cola Twist', '2 liter bottle', 1.89, 'UNIT', 1);
INSERT INTO tb_product(product_name, product_description, price, unit, brand_id) VALUES ('Coca-Cola Zero', '2 liter bottle', 2.00, 'UNIT', 1);
INSERT INTO tb_product(product_name, product_description, price, unit, brand_id) VALUES ('White Rice',  'Plastic bag rice with 5kg.', 5.00, 'KG', 15);
INSERT INTO tb_product(product_name, product_description, price, unit, brand_id) VALUES ('Cereal', 'Cereal box with 400g', 7.85, 'UNIT', 3);

INSERT INTO tb_purchase_requisition(process_status) VALUES ('WAITING_HISTORY_STATUS_UPDATE');
INSERT INTO tb_purchase_requisition(process_status) VALUES ('WAITING_HISTORY_STATUS_UPDATE');

INSERT INTO tb_product_to_be_ordered(purchase_requisition_id, product_id, quantity) VALUES (1, 1, 1000000);
INSERT INTO tb_product_to_be_ordered(purchase_requisition_id, product_id, quantity) VALUES (1, 2, 400);
INSERT INTO tb_product_to_be_ordered(purchase_requisition_id, product_id, quantity) VALUES (1, 3, 100);
INSERT INTO tb_product_to_be_ordered(purchase_requisition_id, product_id, quantity) VALUES (1, 4, 200);
INSERT INTO tb_product_to_be_ordered(purchase_requisition_id, product_id, quantity) VALUES (1, 5, 100);
INSERT INTO tb_product_to_be_ordered(purchase_requisition_id, product_id, quantity) VALUES (1, 6, 100);

INSERT INTO tb_product_to_be_ordered(purchase_requisition_id, product_id, quantity) VALUES (2, 1, 500000);
INSERT INTO tb_product_to_be_ordered(purchase_requisition_id, product_id, quantity) VALUES (2, 4, 200);

INSERT INTO tb_purchase_requisition_history(purchase_requisition_id, status_update, history_datetime) VALUES (1, 'PENDING', current_timestamp());