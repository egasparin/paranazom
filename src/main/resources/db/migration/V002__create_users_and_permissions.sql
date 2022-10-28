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

-- populando a tabela de usuarios
INSERT INTO `tb_user`(`user_id`, `email`, `password`) values ('6fb1d2ed-066a-44cb-92fb-a13b93a585ed', 'admin@paranazom.com', 'MOjb.ujgjpX0na');
INSERT INTO `tb_user`(`user_id`, `email`, `password`) values ('c0a873c2-b8f8-446f-ae18-ffb9ef57426e', 'eduardo@paranazom.com', '$2a$10$Zc3w6HyuPOPXamaMhh');

-- populando a tabela de permissoes
INSERT INTO `tb_permission` (`permission_id`, `description`) values (1, 'CADASTRAR_CATEGORIA');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (2, 'PESQUISAR_CATEGORIA');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (3, 'CADASTRAR_USUARIO');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (4, 'REMOVER_USUARIO');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (5, 'PESQUISAR_USUARIO');

INSERT INTO `tb_permission` (`permission_id`, `description`) values (6, 'CADASTRAR_PRODUTO');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (7, 'REMOVER_PRODUTO');
INSERT INTO `tb_permission` (`permission_id`, `description`) values (8, 'PESQUISAR_PRODUTO');

-- definindo as permissoes para os usuarios cadastrados
-- admin - pode realizar todas as operacoes
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('6fb1d2ed-066a-44cb-92fb-a13b93a585ed', 1);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('6fb1d2ed-066a-44cb-92fb-a13b93a585ed', 2);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('6fb1d2ed-066a-44cb-92fb-a13b93a585ed', 3);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('6fb1d2ed-066a-44cb-92fb-a13b93a585ed', 4);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('6fb1d2ed-066a-44cb-92fb-a13b93a585ed', 5);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('6fb1d2ed-066a-44cb-92fb-a13b93a585ed', 6);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('6fb1d2ed-066a-44cb-92fb-a13b93a585ed', 7);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('6fb1d2ed-066a-44cb-92fb-a13b93a585ed', 8);

-- eduardo - pode realizar somente algumas operacoes de consulta
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('c0a873c2-b8f8-446f-ae18-ffb9ef57426e', 2);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('c0a873c2-b8f8-446f-ae18-ffb9ef57426e', 5);
INSERT INTO `tb_user_permission` (`user_id`, `permission_id`) values ('c0a873c2-b8f8-446f-ae18-ffb9ef57426e', 8);





