CREATE PROCEDURE add_username_consumers (
    @documento BIGINT
    , @username VARCHAR(100)
) AS
UPDATE usuario
SET username = @username
WHERE documento = @documento;