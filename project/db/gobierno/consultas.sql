USE db_gobierno;
GO
-- consultar de clientes para el usuario que se loguea con su documento
	DECLARE @documento INT = 400
	SELECT * FROM clientes WHERE documento = @documento;


-- clientes que estan en mas de una consecionaria ( A )
	SELECT documento, COUNT(distinct id_consecionaria) AS cnt
	FROM clientes
	GROUP BY documento
	HAVING COUNT(distinct id_consecionaria) > 1;

-- clientes que estan mas de una vez en la misma consecionaria ( B )
	SELECT documento, id_consecionaria, COUNT(id_consecionaria) AS cnt
	FROM clientes
	GROUP BY documento, id_consecionaria
	HAVING COUNT(id_consecionaria) > 1;

-- cantidad de clientes por consecionaria
	SELECT id_consecionaria, COUNT(documento) AS cnt
	FROM clientes
	GROUP BY id_consecionaria
	order by cnt asc;

-- A && B
	WITH common AS (
		SELECT documento, id_consecionaria
		FROM clientes
	)
	, grupoA AS (
		-- clientes que estan en mas de una consecionaria ( A )
		SELECT documento, COUNT(distinct id_consecionaria) AS cnt
		FROM common
		GROUP BY documento
		HAVING COUNT(distinct id_consecionaria) > 1
	), grupoB AS (
		-- clientes que estan mas de una vez en la misma consecionaria ( B )
		SELECT documento, id_consecionaria, COUNT(id_consecionaria) AS cnt
		FROM clientes
		GROUP BY documento, id_consecionaria
		HAVING COUNT(id_consecionaria) > 1
	)
	SELECT * 
	FROM grupoA a 
		INNER JOIN grupoB  b 
			ON b.documento = a.documento;



-- Consultar calendario sorteos
	SELECT DISTINCT fecha
	FROM sorteos
	ORDER BY fecha ASC;

-- consultar ultimo ganador
	SElECT TOP 1 plan_id FROM ganadores ORDER BY id DESC;

-- verifica estado ultimo ganador: consultar si el ultimo ganador tiene premio
	-- Actualizar estado ganador
		
-- hay sorteo pendiente
	DECLARE @sorteo_id INT; 
	DECLARE @mes INT;

	SELECT  @sorteo_id = id FROM sorteos WHERE estado = 'pendiente' AND fecha <= GETDATE();
	SELECT  @mes = mes_sorteo FROM sorteos WHERE estado = 'pendiente' AND fecha <= GETDATE();

-- hay sorteo para el dia de la fecha
	SELECT  @sorteo_id = id FROM sorteos WHERE fecha = CAST(GETDATE() AS DATE);
	SELECT  @mes = mes_sorteo FROM sorteos WHERE fecha = CAST(GETDATE() AS DATE);

-- hay planes desactualizados para el sorteo
	SELECT * FROM planes WHERE DATEPART(MONTH, fecha_ultima_actualizacion) < @mes

-- actualiza los planes

-- obitiene los participantes
	SELECT *, DATEDIFF(MONTH, fecha_alta, fecha_ultima_actualizacion) AS CNT_CUOTAS
	FROM compradores 
	WHERE cant_cuotas_pagas >= DATEDIFF(MONTH, fecha_alta, fecha_ultima_actualizacion) -- al dia
		AND cant_cuotas_pagas BETWEEN 24 AND 36 -- dentro del rango

-- selecciona el ganador

-- consultar consecionarias a notificar (no auditadas)
	SELECT * 
	FROM consecionarias 
	WHERE id NOT IN (
		SELECT consecionaria
		FROM consecionarias_notificadas
		WHERE sorteo = @sorteo_id
	);
	-- por cada una
	-- DECLARE @consecionaria INT;
		-- notifica las consecionarias
			-- audita la consecionaria notificada		
			-- INSERT INTO consecionarias_notificadas(sorteo, consecionaria) VALUES (@sorteo_id, @consecionaria);

-- enviar mail al cliente