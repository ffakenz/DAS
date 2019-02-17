-- pagar cuota
CREATE PROCEDURE update_cuota (
    @id_plan             BIGINT
    , @nro_cuota         BIGINT
    , @monto             INT
    , @fecha_pago        DATETIME
)
AS
UPDATE dbo.cuotas
   SET monto            = @monto
    ,  fecha_pago       = @fecha_pago
 WHERE  id_plan         = @id_plan
      AND nro_cuota     = @nro_cuota