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
  PRIMARY KEY (id_vehiculo),
  foreign key fk_vehiculo_categoria (id_categoria) references categoria(id_categoria)  
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb4;