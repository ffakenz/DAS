CREATE TRIGGER insert_precio_cuotas ON cuotas
FOR INSERT
AS
BEGIN
    WITH precio_vehiculo_inserted AS (
        SELECT
            v.precio AS 'precio_vehiculo'
            , i.id_plan AS 'id_plan'
            , i.nro_cuota AS 'nro_cuota'
        FROM
            planes p
            INNER JOIN inserted i
                ON p.id = i.id_plan
            INNER JOIN vehiculos v
                ON p.vehiculo = v.id
    )
    UPDATE cuotas
    SET monto = q.precio_vehiculo / 60
    FROM precio_vehiculo_inserted q
    WHERE cuotas.nro_cuota = q.nro_cuota
        AND cuotas.id_plan = q.id_plan
END