CREATE PROCEDURE get_last_login_by_documento(@documento BIGINT)  AS
SELECT TOP 1 *
FROM login
WHERE documento = @documento
ORDER BY log_in_time DESC;

-- get_last_login_by_username