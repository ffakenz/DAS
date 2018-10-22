CREATE PROCEDURE insert_participante(
    @id_sorteo BIGINT
    , @id_plan BIGINT
) AS
INSERT INTO participantes(id_sorteo, id_plan)
VALUES (@id_sorteo,@id_plan);