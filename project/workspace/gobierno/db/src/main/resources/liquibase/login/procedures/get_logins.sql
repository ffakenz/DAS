CREATE PROCEDURE get_logins(@documento BIGINT)  AS
SELECT *
FROM login
WHERE documento = @documento
ORDER BY log_in_time DESC;