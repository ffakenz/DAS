USE db_gobierno;

INSERT INTO tipo_usuario(nombre)
VALUES('comprador'),('gobierno'); 

INSERT INTO usuario(tipo, username, password)
VALUES
	('gobierno', 'irocca', 'lam')
	, ('gobierno', 'ffakenz', '123');

INSERT INTO detalle_usuario(tipo, username, documento, nombre)
VALUES
	('gobierno', 'irocca', 37575567, 'Nacho')
	, ('gobierno', 'ffakenz', 93337511, 'Franco');


INSERT INTO login(tipo, username)
VALUES
	('gobierno', 'irocca')
	, ('gobierno', 'ffakenz');

