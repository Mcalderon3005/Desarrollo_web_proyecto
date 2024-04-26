drop table if exists concesionaria.vehiculo;
drop schema if exists concesionaria;
drop user if exists 'usuario_prueba'@'%';
create database if not exists concesionaria;

create user 'usuario_prueba'@'%' identified by 'Usuar1o_Clave.';

grant all privileges on concesionaria.* to 'usuario_prueba'@'%';
flush privileges;

create table concesionaria.categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_categoria))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

create table concesionaria.vehiculo (
  id_vehiculo INT NOT NULL AUTO_INCREMENT,
  id_categoria INT NOT NULL,
  marca VARCHAR(45) NOT NULL,
  modelo VARCHAR(45) NOT NULL, 
  anio INT,
  color VARCHAR(45),  
  precio double,
  existencias int,  
  ruta_imagen varchar(1024),
  activo bool,
  ruta_informe varchar(1024),
  PRIMARY KEY (id_vehiculo),
  foreign key fk_vehiculo_categoria (id_categoria) references categoria(id_categoria)  
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE concesionaria.usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  username varchar(20) NOT NULL,
  password varchar(512) NOT NULL,
  nombre VARCHAR(20) NOT NULL,
  apellidos VARCHAR(30) NOT NULL,
  correo VARCHAR(25) NULL,
  telefono VARCHAR(15) NULL,
  ruta_imagen varchar(1024),
  activo boolean,
  PRIMARY KEY (`id_usuario`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

/*Se insertan 3 registros en la tabla cliente como ejemplo */
INSERT INTO concesionaria.usuario (id_usuario, username,password,nombre, apellidos, correo, telefono,ruta_imagen,activo) VALUES 
(1,'messi','$2a$10$P1.w58XvnaYQUQgZUCk4aO/RTRl8EValluCqB3S2VMLTbRt.tlre.','Lionel', 'Messi Cuccitini',    'jcastro@gmail.com',    '4556-8978', 'https://upload.wikimedia.org/wikipedia/commons/b/b4/Lionel-Messi-Argentina-2022-FIFA-World-Cup_%28cropped%29.jpg',true),
(2,'ronaldo','$2a$10$GkEj.ZzmQa/aEfDmtLIh3udIH5fMphx/35d0EYeqZL5uzgCJ0lQRi','Cristiano',  'Ronaldo Dos Santos', 'acontreras@gmail.com', '5456-8789','https://upload.wikimedia.org/wikipedia/commons/d/d7/Cristiano_Ronaldo_playing_for_Al_Nassr_FC_against_Persepolis%2C_September_2023_%28cropped%29.jpg',true),
(3,'neymar','$2a$10$koGR7eS22Pv5KdaVJKDcge04ZB53iMiw76.UjHPY.XyVYlYqXnPbO','Neymar', 'da Silva Santos Júnior',     'lmena@gmail.com',      '7898-8936','https://upload.wikimedia.org/wikipedia/commons/8/83/Bra-Cos_%281%29_%28cropped%29.jpg',true);

create table concesionaria.rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre varchar(20),
  id_usuario int,
  PRIMARY KEY (id_rol),
  foreign key fk_rol_usuario (id_usuario) references usuario(id_usuario)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

insert into concesionaria.rol (id_rol, nombre, id_usuario) values
 (1,'ROLE_ADMIN',1), (2,'ROLE_VENDEDOR',1), (3,'ROLE_USER',1),
 (4,'ROLE_VENDEDOR',2), (5,'ROLE_USER',2),
 (6,'ROLE_USER',3);
 
INSERT INTO concesionaria.categoria (id_categoria,descripcion,ruta_imagen,activo) VALUES
(1,'Manual','https://th.bing.com/th/id/OIP.k3eG1donlmElkCyvye_6bwAAAA?rs=1&pid=ImgDetMain',true),
(2,'Automatico','https://img.philhyundai.com/2020/08/25/CKnDvbvJ/top-five-automatic-cars-632b.jpg',true),
(3,'Adaptable','https://storage.googleapis.com/concesionaria-6e588.appspot.com/concesionaria/categoria/img0000000000000000003WhatsApp%20Image%202024-04-04%20at%2010.22.28%20PM.jpeg?GoogleAccessId=firebase-adminsdk-lp4yt@concesionaria-6e588.iam.gserviceaccount.com&Expires=2028526452&Signature=IJX9iQ0ADsPcZbBRUBCoCXZvLhmoYvSUtGnwKNcqAAo%2FM1bBT5NxoYeRnu9a5Eu%2BtA%2FyBsq3l2N6X2KG84Qggo7VFzm5yyP6K5OixhgVgYa56gCODn4SA42pm5dVtxQd5MH5xhpjcEN%2FQopepGYDwr8nxrUmeT8AP2ozoGecA3GjaXleb%2ButF0T3HGq04GwdDcJXHtwrv9%2FyDLdWlibTR7BSlN3XvEG1t6eoRyeoYHpN%2BzIxy8OhIlzU7yc69qy4seqijCKFg2deq8y%2BMP%2BA3CxZDIqhjjDAZkMqLJqfQocbx7lHBewUsYduemrbiF%2Br1Uy%2FNxhJLeEUEb2qKgFpSA%3D%3D',true),
(4,'Eléctrico','https://storage.googleapis.com/concesionaria-6e588.appspot.com/concesionaria/categoria/img0000000000000000004carroElectrico.jpg?GoogleAccessId=firebase-adminsdk-lp4yt@concesionaria-6e588.iam.gserviceaccount.com&Expires=2028526489&Signature=VFclH6ayqx%2B2dvFBdHUJZLi32zB43ZkXyrGQJpLJVYnK4nd0VLK7jwd3VnfYS%2F6IVW2%2FARXJSyLNM18wXQn75wQacyyyQQOCsBBeNQTPJkNUBaM6QG6UCui5LsgyTThukh2JtKKBTPL915kBjLnNA4Fa86xGuVjp3lejjBqyTNN0oH6JVE20XHR8X02uvYR%2BqwvI8VELnOgAdVY0hruLCp7KH%2BXbRe2PEpR8EJ5sZxhXjpYl7pklGCol8EQ6RFcKilmi723TYjcvEe0P8khlLDa1iw%2BAtDrUN5yTKdne4QnetgwzRolN0GvGG3qXf4d3pJSNBPJJReNAepQ4d8S4Yw%3D%3D',false),
(5,'Usado','https://th.bing.com/th/id/OIP.XlDvPL0ULxYaKk8wBPQoXgHaEH?rs=1&pid=ImgDetMain',false);

INSERT INTO concesionaria.vehiculo (id_vehiculo,id_categoria,marca,modelo,anio,color,precio,existencias,ruta_imagen,activo,ruta_informe) VALUES
(1,1,'Toyota','GR Supra',2024,'Rojo',45540,2,'https://www.toyota.com/imgix/content/dam/toyota/jellies/max/2024/grsupra/45thanniversaryedition/2376/d19/36/5.png?fmt=webp&wid=930&qlt=90',true,NULL);