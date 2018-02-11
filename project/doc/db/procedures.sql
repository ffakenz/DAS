-- Evaluar necesidad de tener inicio o fin o una tabla secundaria con el historial de cambios por sorteo
-- Falta validar las transiciones de ser necesario
-- Actualizar fecha sorteo
		GO
		DROP PROCEDURE definir_fecha_sorteo;
		GO
		CREATE PROCEDURE definir_fecha_sorteo(@id_sorteo INT, @fecha DATETIME) AS
			UPDATE sorteos
			SET fecha = @fecha
			WHERE id = @id_sorteo;

			
	/*
		DECLARE @id_sorteo INT = 1
		DECLARE @fecha DATETIME = '2018-02-08'
		EXEC definir_fecha_sorteo @id_sorteo, @fecha;
	*/

-- Actualizar estado sorteo
		GO
		DROP PROCEDURE actualizar_estado_sorteo;
		GO
		CREATE PROCEDURE actualizar_estado_sorteo(@id_sorteo INT, @estadoFinal VARCHAR(100)) AS
			UPDATE 
				sorteos
			SET estado = @estadoFinal
			WHERE id = @id_sorteo;
	/*
		DECLARE @id_sorteo INT = 1
		DECLARE @estadoFinal VARCHAR(100) = 'pendiente'
		EXEC actualizar_estado_sorteo @id_sorteo, @estadoFinal;
	*/


-- actualiza los planes
	GO
	DROP PROCEDURE actualizar_plan;
	GO
	CREATE PROCEDURE actualizar_plan(@id_plan INT, @cant_cuotas_pagas INT ) AS
		UPDATE planes
		SET fecha_ultima_actualizacion = GETDATE()
			, cant_cuotas_pagas = @cant_cuotas_pagas
		WHERE id = @id_plan;
