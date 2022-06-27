#schema used in mysql
###(WIP)
``` sql 
CREATE TABLE IF NOT EXISTS tb_brand (
    id BIGINT UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    brand_name VARCHAR(255) UNIQUE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tb_product_pack_type(
    id BIGINT UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    pack_name VARCHAR(100) NOT NULL CHECK(pack_name <> ' ')
);

CREATE TABLE IF NOT EXISTS tb_product (
    id BIGINT UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL CHECK(product_name <> ' '),
    pack_type_id BIGINT UNSIGNED NOT NULL,
    product_description VARCHAR(200) NOT NULL CHECK(product_description <> ' '),
    price DECIMAL(15,2) NOT NULL CHECK(price >= 0), ###unsigned for decimal is deprecated
    unit VARCHAR(4) NOT NULL CHECK(unit <> ' ' AND unit IN ('MG', 'KG', 'ML', 'LT', 'TON', 'UNIT')),
    brand_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (brand_id) REFERENCES tb_brand(id),
    FOREIGN KEY (pack_type_id) REFERENCES tb_product_pack_type(id)
);

CREATE TABLE IF NOT EXISTS tb_purchase_requisition(
    id BIGINT UNSIGNED UNIQUE NOT NULL AUTO_INCREMENT,
    process_status VARCHAR(50) NOT NULL CHECK (process_status <> ' '),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tb_product_to_be_ordered(
    purchase_requisition_id BIGINT UNSIGNED NOT NULL,
    product_id BIGINT UNSIGNED NOT NULL,
    quantity BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (purchase_requisition_id, product_id),
    FOREIGN KEY (purchase_requisition_id) REFERENCES tb_purchase_requisition(id),
    FOREIGN KEY (product_id) REFERENCES tb_product(id)
);

CREATE TABLE IF NOT EXISTS tb_user(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tb_purchase_requisition_history(
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    purchase_requisition_id BIGINT UNSIGNED NOT NULL,
    status_update VARCHAR(50) NOT NULL CHECK(status_update <> ' '),
    history_datetime TIMESTAMP NOT NULL,
    user_id BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (purchase_requisition_id) REFERENCES tb_purchase_requisition(id),
    FOREIGN KEY (user_id) REFERENCES tb_user(id)
);

```