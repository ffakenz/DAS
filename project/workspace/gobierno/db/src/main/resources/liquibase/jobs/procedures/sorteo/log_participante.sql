CREATE PROCEDURE log_participante(
    @id_sorteo BIGINT
    , @id_plan BIGINT
        , @id_concesionaria BIGINT
        , @id_consumer BIGINT
        , @id_vehiculo BIGINT
) AS
INSERT INTO participantes(id_sorteo,id_plan,id_concesionaria,id_consumer,id_vehiculo)
VALUES (@id_sorteo,@id_plan,@id_concesionaria,@id_consumer,@id_vehiculo);