CREATE PROCEDURE get_last_login_by_username(@username BIGINT)  AS
SELECT TOP 1 *
FROM login
WHERE username = @username
ORDER BY log_in_time DESC;