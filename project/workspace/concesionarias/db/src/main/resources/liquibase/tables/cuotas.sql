CREATE TABLE cuotas (
	id_plan             BIGINT      NOT NULL
	, nro_cuota         BIGINT      NOT NULL
	, fecha_vencimiento DATETIME    NOT NULL
	, monto             INT         NULL
	, fecha_pago        DATETIME    NULL
	, PRIMARY KEY (id, id_plan)
	, FOREIGN KEY (id_plan) REFERENCES planes(id)
);