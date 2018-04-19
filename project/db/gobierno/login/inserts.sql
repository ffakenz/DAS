USE db_gobierno;

INSERT INTO tipo_usuario(nombre)
VALUES('comprador'),('gobierno'); 

INSERT INTO usuario(username, password, rol)
VALUES
	('irocca', 'lam', 'gobierno')
	, ('ffakenz', '123', 'gobierno');

INSERT INTO detalle_usuario(username, documento, nombre)
VALUES
	('irocca', 37575567, 'Nacho')
	, ('ffakenz', 93337511, 'Franco');


INSERT INTO login(username)
VALUES
	('irocca')
	, ('ffakenz');

