CREATE PROCEDURE get_gobierno_users_by_documento (
      @documento BIGINT
) AS
SELECT *
FROM gobierno_users
WHERE documento = @documento;