CREATE PROCEDURE get_sorteos_pendientes AS
SELECT *
FROM sorteos
  WHERE fecha_ejecucion <= GETDATE()
    AND estado NOT IN ('nuevo','fallado','completado')
ORDER BY fecha_ejecucion DESC;
