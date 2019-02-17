CREATE PROCEDURE get_plan (
    @id     BIGINT
)
AS
SELECT TOP 1 *
FROM planes
WHERE id = @id