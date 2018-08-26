-- TODO fix an issue in which bulking several rows with same estado_cuenta_id the one with bigger identity gets the lowest nro_cta
CREATE TRIGGER insert_cuotas ON cuotas
FOR INSERT
AS
BEGIN
    WITH fst_q AS (
        SELECT *,
            ROW_NUMBER() OVER (PARTITION BY estado_cuenta_id ORDER BY estado_cuenta_id DESC) AS incr
        FROM inserted
    ), snd_q AS (
        -- el maximo por cada estado_cuenta_id
        SELECT estado_cuenta_id, MAX(nro_cuota) AS max_id
        FROM cuotas
        GROUP BY estado_cuenta_id
    ), resultado AS (
        SELECT f.*, COALESCE(s.max_id, 0) + COALESCE(f.incr, 0) AS new_id
        FROM snd_q s
            FULL JOIN fst_q f
                ON s.estado_cuenta_id = f.estado_cuenta_id
    )
    UPDATE cuotas
    SET cuotas.nro_cuota = r.new_id
    FROM resultado r
    WHERE cuotas.id = r.id
END