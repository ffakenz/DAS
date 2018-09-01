CREATE PROCEDURE get_aprobadas  AS
SELECT *
FROM concesionaria
WHERE fecha_alta IS NOT NULL
  AND codigo IS NOT NULL;