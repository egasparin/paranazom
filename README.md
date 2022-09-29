# PROJETO PARANAZOM

**Autores:** Eduardo Gasparin e Guilherme Feier Huff

## Database Schema

Na imagem é apresentado o esquema do banco de dados utilizado na aplicação desenvolvida

![MER](database_schema/MER.png)

## Implementação do Banco de Dados

O banco de dados utilizado foi implementado via container, sendo a definição deste realizada em um arquivo .yalm, com o seguinte conteúdo

```
	version: '3.1'

	services:
	  mysql:
		image: mysql:5.7.32
		command: --default-authentication-plugin=mysql_native_password
		environment:
		  MYSQL_ROOT_PASSWORD: "root"
		  MYSQL_DATABASE: "db_mysql"
		ports:
		  - "3306:3306"
		volumes:
		  - ./MySql:/var/lib/mysql
```

