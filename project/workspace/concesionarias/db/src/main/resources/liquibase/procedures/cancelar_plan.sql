CREATE PROCEDURE cancelar_plan (
    @tipo_de_plan   VARCHAR(100)
    , @plan_id      BIGINT
    , @documento    BIGINT
) AS
UPDATE pl
SET pl.estado = 'cancelado'
    , pl.fecha_ultima_actualizacion = SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
FROM planes AS pl INNER JOIN clientes AS cl ON pl.cliente = cl.id_cliente
WHERE pl.id = @plan_id
    AND pl.tipo_de_plan = @tipo_de_plan
    AND cl.documento = @documento;