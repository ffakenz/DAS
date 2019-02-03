CREATE PROCEDURE log_sorteo (
    @mes_sorteo INT
) AS
INSERT INTO sorteos(mes_sorteo)
VALUES(@mes_sorteo);