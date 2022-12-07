-- -----------------------------------------------------
-- Table `tb_category`
-- -----------------------------------------------------
INSERT tb_category (category_id, description) VALUES ('a5f09909-e09c-48fa-a2d6-2537f882c2d6', 'Celular'),
(uuid(), 'Televisor');

-- -----------------------------------------------------
-- Table `tb_role`
-- -----------------------------------------------------
INSERT tb_role (role_id, description) VALUES ('e75e70c7-5dfe-11ed-9296-0242ac120002', 'Vendedor'), 
(uuid(), 'Caixa'), ('b017d561-94fb-4871-9d0c-14ba8db4d1ed', 'admin'),
(uuid(), 'Gerente'); 

-- -----------------------------------------------------
-- Table `tb_employee`
-- -----------------------------------------------------
INSERT tb_employee (employee_id, `name`, is_active, entry_date, role_id) VALUES ('668b9f17-5dff-11ed-9296-0242ac120002', 'Eduardo Gasparin', 1, now(), 'e75e70c7-5dfe-11ed-9296-0242ac120002'),
('27820266-f797-4e72-a4d3-bae95c646ebb', 'admin', 1, now(), 'b017d561-94fb-4871-9d0c-14ba8db4d1ed');

-- -----------------------------------------------------
-- Table `tb_user`
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `tb_product`
-- -----------------------------------------------------
INSERT tb_product (product_id, `name`, tags, price, amount, category_id) VALUES ('a5f09929-e09c-48f2-22d6-2537f882c2d6', 'Iphone 14', 'apple', 12000.00, 4, 'a5f09909-e09c-48fa-a2d6-2537f882c2d6');

-- -----------------------------------------------------
-- Table `tb_client`
-- -----------------------------------------------------
INSERT INTO `tb_client` (`client_id`,`name`,`CPF`,`email_address`,`phone_number`)
VALUES
  ("c25473ad-5e04-11ed-9296-0242ac120002","Thor Wood","68866177648","proin.ultrices.duis@hotmail.edu","26976782629"),
  (uuid(),"Cyrus Hoffman","37730601763","nec.cursus@outlook.org","38934647484"),
  (uuid(),"Colorado Salazar","00161814473","nunc.in@icloud.ca","21967233358"),
  (uuid(),"Tarik Battle","26546977587","nulla.dignissim@icloud.org","23928229541"),
  (uuid(),"Noel Watts","94079090621","justo@hotmail.org","63937271640");
  
-- -----------------------------------------------------
-- Table `tb_order`
-- -----------------------------------------------------
INSERT INTO tb_order (order_id, description, client_id, employee_id) VALUES ('6957d8fe-5e05-11ed-9296-0242ac120002', 'Pedido tal e tal', 'c25473ad-5e04-11ed-9296-0242ac120002', '668b9f17-5dff-11ed-9296-0242ac120002');

-- -----------------------------------------------------
-- Table `tb_product_has_tb_order`
-- -----------------------------------------------------
INSERT INTO tb_product_has_tb_order (product_id, order_id, amount) VALUES ('a5f09929-e09c-48f2-22d6-2537f882c2d6', '6957d8fe-5e05-11ed-9296-0242ac120002', 1);