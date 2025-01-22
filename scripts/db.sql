create database castores;

use castores;

create table rol (
idrol int not null auto_increment primary key,
nombre varchar(45) not null);

create table usuario (
idusuario int not null auto_increment primary key,
nombre varchar(100) not null,
correo varchar(50) not null,
contraseña varchar(25) not null,
estatus int not null,
idrol int not null,
foreign key (idrol) references rol(idrol));

create table producto (
idproducto int not null auto_increment primary key,
nombre varchar(45) not null,
precio decimal(16, 2) not null,
cantidad int not null,
estatus int not null);

create table historial (
idhistorial int not null auto_increment primary key,
movimiento enum("Entrada", "Salida") not null,
fecha timestamp not null,
idusuario int not null,
foreign key (idusuario) references usuario(idusuario),
idproducto int not null,
foreign key (idproducto) references producto(idproducto));

insert into rol values (1, "Administrador");
insert into rol values (2, "Almacenista");

insert into usuario values (1, "Aaron", "ejemplo@gmail.com", "123", 1, 1);
insert into usuario values (2, "Nora", "example@gmail.com", "123", 1, 2);

insert into producto values (1, "Pc", "10000", "5", 1);

insert into historial values (1, 'Entrada', now(), 1, 1);

/*
SELECT * FROM historial h
INNER JOIN usuario u ON h.idusuario = u.idusuario
INNER JOIN producto p ON h.idproducto = p.idproducto;

SELECT h.idhistorial, h.movimiento, h.fecha, h.idusuario as usuarioid, h.idproducto as productoid,
u.idusuario, u.nombre as unombre, u.correo, u.contraseña, u.estatus as ustatus, u.idrol as rolid,
p.idproducto, p.nombre as pnombre, p.precio, p.cantidad, p.estatus as pstatus
FROM historial h JOIN usuario u ON h.idusuario = u.idusuario JOIN producto p ON h.idproducto = p.idproducto;

UPDATE usuario SET correo = "example@gmail.com" where idusuario = 2;
*/
