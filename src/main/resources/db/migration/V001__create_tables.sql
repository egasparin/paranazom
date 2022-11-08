-- -----------------------------------------------------
-- Table `tb_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tb_category` (
  `category_id` CHAR(36) NOT NULL PRIMARY KEY,
  `description` VARCHAR(55) NOT NULL,
  `image_url` VARCHAR(250) NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `tb_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tb_role` (
  `role_id` CHAR(36) NOT NULL PRIMARY KEY,
  `description` VARCHAR(55) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;


-- -----------------------------------------------------
-- Table `tb_employee`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `tb_employee` (
  `employee_id` CHAR(36) NOT NULL PRIMARY KEY,
  `name` VARCHAR(120) NOT NULL,
  `is_active` BOOLEAN NOT NULL,
  `entry_date` DATE NOT NULL,
  `role_id` CHAR(36) NOT NULL,
    FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `tb_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tb_user` (
  `user_id` CHAR(36) NOT NULL PRIMARY KEY,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `employee_id` CHAR(36) NULL,
    FOREIGN KEY (`employee_id`) REFERENCES `tb_employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `tb_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tb_product` (
  `product_id` CHAR(36) NOT NULL PRIMARY KEY,
  `name` VARCHAR(200) NOT NULL,
  `description` LONGTEXT NULL,
  `tags` VARCHAR(100) NOT NULL,
  `category_id` CHAR(36) NOT NULL,
  `price` FLOAT NOT NULL,
  `amount` INT NOT NULL,
  `image_url` VARCHAR(250) NULL,
    FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `tb_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tb_client` (
  `client_id` CHAR(36) NOT NULL PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `RG` CHAR(14) NULL,
  `CPF` CHAR(11) NOT NULL UNIQUE,
  `email_address` VARCHAR(50) NULL,
  `phone_number` CHAR(11) NULL,
  `address` VARCHAR(255) NULL
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `tb_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tb_order` (
  `order_id` CHAR(36) NOT NULL,
  `draft` TINYINT NOT NULL DEFAULT 0,
  `paid` TINYINT NOT NULL DEFAULT 0,
  `withdrawn` TINYINT NOT NULL DEFAULT 0,
  `description` MEDIUMTEXT NOT NULL,
  `discount` FLOAT NULL DEFAULT 0.0,
  `client_id` CHAR(36) NOT NULL,
  `employee_id` CHAR(36) NOT NULL,
  PRIMARY KEY (`order_id`),
    FOREIGN KEY (`client_id`) REFERENCES `tb_client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`employee_id`) REFERENCES `tb_employee` (`employee_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- -----------------------------------------------------
-- Table `tb_product_has_tb_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tb_product_has_tb_order` (
  `product_id` CHAR(36) NOT NULL,
  `order_id` CHAR(36) NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`product_id`, `order_id`),
    FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8;
