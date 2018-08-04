CREATE PROCEDURE get_logins(@username BIGINT)  AS
SELECT MAX(id)
FROM login
WHERE username = @username
    AND log_out_time IS NULL;