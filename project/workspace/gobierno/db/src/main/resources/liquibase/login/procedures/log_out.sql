CREATE PROCEDURE log_out(@id BIGINT) AS
UPDATE login
SET log_out_time = GETDATE()
WHERE id = @id;