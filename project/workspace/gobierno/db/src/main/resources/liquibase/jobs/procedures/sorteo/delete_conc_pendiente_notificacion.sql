CREATE PROCEDURE delete_conc_pendiente_notificacion(
  @id_sorteo          BIGINT
  , @id_concesionaria   BIGINT
) AS
DELETE FROM conc_pendiente_notificacion
WHERE id_sorteo = @id_sorteo AND id_concesionaria = @id_concesionaria;