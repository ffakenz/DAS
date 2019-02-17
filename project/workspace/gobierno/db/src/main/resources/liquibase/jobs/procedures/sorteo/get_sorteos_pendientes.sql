CREATE PROCEDURE get_sorteos_pendientes AS
SELECT *
FROM sorteos
  WHERE fecha_ejecucion <= SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
    AND estado NOT IN ('nuevo','fallado','completado')
ORDER BY fecha_ejecucion DESC;
