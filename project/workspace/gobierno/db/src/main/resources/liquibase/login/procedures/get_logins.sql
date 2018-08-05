CREATE PROCEDURE get_logins(@username BIGINT)  AS
SELECT *
FROM login
WHERE username = @username
ORDER BY log_in_time DESC;