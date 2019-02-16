CREATE TABLE cuotas (
	id_plan             BIGINT      NOT NULL
	, nro_cuota         BIGINT      NOT NULL
	, fecha_vencimiento  AS CAST (
        DATEADD(MONTH, 1, fecha_alta) AS DATETIME
    )
	, monto             INT         NULL
	, fecha_pago        DATETIME    NULL
	, fecha_alta        DATETIME    NOT NULL    -- informado por la consecionaria
	, PRIMARY KEY (id_plan, nro_cuota)
	, FOREIGN KEY (id_plan) REFERENCES planes(id)
);