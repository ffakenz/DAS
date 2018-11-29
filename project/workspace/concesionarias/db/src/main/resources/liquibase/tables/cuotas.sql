CREATE TABLE cuotas (
	id_plan BIGINT FOREIGN KEY REFERENCES planes(id)
	, id_cuota BIGINT NOT NULL
	, fecha_pago DATETIME NULL
	, fecha_vencimiento DATETIME NOT NULL
	, PRIMARY KEY (id_plan, id_cuota)
);