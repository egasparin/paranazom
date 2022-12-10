CREATE TABLE IF NOT EXISTS `tb_permission` (
	`permission_id` BIGINT(20) PRIMARY KEY,
	`description`  VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tb_user_permission` (
	`user_id` CHAR(36) NOT NULL,
	`permission_id` BIGINT(20) NOT NULL,
	PRIMARY KEY (`user_id`, `permission_id`),
	FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id` ),
	FOREIGN KEY (`permission_id`) REFERENCES `tb_permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- populando a tabela de permissoes

INSERT INTO `tb_permission` (`permission_id`, `description`) values (1, 'WRITE_CATEGORY');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (2, 'READ_CATEGORY');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (3, 'WRITE_USER');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (4, 'READ_USER');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (5, 'WRITE_PRODUCT');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (6, 'READ_PRODUCT');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (7, 'WRITE_ROLE');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (8, 'READ_ROLE');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (9, 'WRITE_OPTIONS');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (10, 'READ_OPTIONS');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (11, 'WRITE_OPTIONS_PRODUCT_RELATION');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (12, 'READ_OPTIONS_PRODUCT');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (13, 'WRITE_PRODUCT_ORDER_RELATION');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (14, 'READ_PRODUCT_ORDER_RELATION');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (15, 'WRITE_ORDER');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (16, 'READ_ORDER');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (17, 'WRITE_CLIENT');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (18, 'READ_CLIENT');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (19, 'WRITE_EMPLOYEE');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (20, 'READ_EMPLOYEE');

-- definindo as permissoes para os usuarios cadastrados
-- admin - pode realizar todas as operacoes
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 1);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 2);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 3);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 4);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 5);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 6);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 7);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 8);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 9);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 10);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 11);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 12);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 13);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 14);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 15);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 16);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 17);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 18);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 19);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('970978e3-5e02-11ed-9296-0242ac120002', 20);
