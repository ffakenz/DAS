USE das_final_gobierno;
GO
DROP VIEW compradores;
GO
CREATE VIEW compradores AS
SELECT 
	p.id AS planId
	, p.cant_cuotas_pagas
	, p.fecha_alta
	, p.fecha_ultima_actualizacion
	, v.nombre AS nombreVehiculo
	, c.nombre AS nombreCliente
	, u.documento
	, u.id AS clientId
FROM planes p
	INNER JOIN vehiculos v ON p.vehiculo = v.id
	INNER JOIN consecionarias c ON p.consecionaria = c.id
	INNER JOIN clientes u ON p.cliente = u.correlativo and p.consecionaria = u.id_consecionaria;
GO

UPDATE compradores 
SET cant_cuotas_pagas = 23, fecha_ultima_actualizacion = DATEADD(MONTH, cant_cuotas_pagas, fecha_alta) 
WHERE planId = 1;
UPDATE compradores
SET cant_cuotas_pagas = 24, fecha_ultima_actualizacion = DATEADD(MONTH, cant_cuotas_pagas, fecha_alta) 
WHERE planId = 2;
UPDATE compradores
SET cant_cuotas_pagas = 25, fecha_ultima_actualizacion = DATEADD(MONTH, cant_cuotas_pagas, fecha_alta) 
WHERE planId = 3;
UPDATE compradores
SET cant_cuotas_pagas = 36, fecha_ultima_actualizacion = DATEADD(MONTH, cant_cuotas_pagas, fecha_alta) 
WHERE planId = 4;
UPDATE compradores
SET cant_cuotas_pagas = 37, fecha_ultima_actualizacion = DATEADD(MONTH, cant_cuotas_pagas, fecha_alta) 
WHERE planId = 5;
UPDATE compradores -- adelanto cuota pero dentro del rango
SET cant_cuotas_pagas = 28, fecha_ultima_actualizacion = DATEADD(MONTH, cant_cuotas_pagas - 1, fecha_alta) 
WHERE planId = 6;
UPDATE compradores -- moroso cuota pero dentro del rango
SET cant_cuotas_pagas = 30, fecha_ultima_actualizacion = DATEADD(MONTH, cant_cuotas_pagas + 1, fecha_alta) 
WHERE planId = 7;
	