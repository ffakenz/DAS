CREATE PROCEDURE get_login_by_username(@username VARCHAR(100))  AS
SELECT *
FROM login
WHERE username = @username
    AND log_out_time IS NULL;