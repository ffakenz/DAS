CREATE TABLE cuotas (
	id_plan             BIGINT      NOT NULL
	, nro_cuota         BIGINT      NOT NULL
	, fecha_vencimiento DATETIME    NOT NULL    -- TODO this should be calculated as +1 month until 23:59:59
	, monto             INT         NULL
	, fecha_pago        DATETIME    NULL
	, fecha_alta        DATETIME    NOT NULL    DEFAULT GETDATE() -- informado por la consecionaria
	, PRIMARY KEY (id_plan, nro_cuota)
	, FOREIGN KEY (id_plan) REFERENCES planes(id)
);