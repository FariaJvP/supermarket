CREATE TABLE tb_brand (
    brand_id BIGINT UNIQUE NOT NULL AUTO_INCREMENT PRIMARY KEY,
    brand_name VARCHAR(255) UNIQUE NOT NULL);

INSERT INTO tb_brand(brand_name)
values
	('Coca-Cola'),
    ('Pepsi'),
    ('Kellogg''s'),
    ('General Mills'),
    ('Splendid Spoon'),
    ('Mosaic Foods'),
    ('Nestlé'),
    ('Danone'),
    ('Persil'),
    ('Tide'),
    ('Smucker''s'),
    ('PB2'),
    ('Dave''s Killer'),
    ('The Hagen Group'),
    ('White Pearl')