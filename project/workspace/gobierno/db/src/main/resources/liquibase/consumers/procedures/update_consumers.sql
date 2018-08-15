CREATE PROCEDURE update_consumers (
    @id BIGINT
    , @nro_telefono VARCHAR(20)
    , @email VARCHAR(50)
) AS
UPDATE consumers
SET nro_telefono = @nro_telefono
    , email = @email
WHERE id = @id