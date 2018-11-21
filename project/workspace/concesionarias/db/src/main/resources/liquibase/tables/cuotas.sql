CREATE TABLE cuotas (
	id_plan INT FOREIGN KEY REFERENCES planes(id)
	, id_cuota INT NOT NULL
	, fecha_pago DATETIME NULL
	, fecha_vencimiento DATETIME NOT NULL
	, PRIMARY KEY (id_plan, id_cuota)
);