CREATE PROCEDURE log_out(@id BIGINT, @log_out_time DATETIME) AS
UPDATE login
SET log_out_time = @log_out_time
WHERE id = @id;