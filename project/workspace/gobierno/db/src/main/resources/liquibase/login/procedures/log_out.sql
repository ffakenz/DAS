CREATE PROCEDURE log_out(
    @id BIGINT
    , @username VARCHAR(100)) AS
UPDATE login
SET log_out_time = GETDATE()
WHERE id = @id
    AND username = @username
    AND log_out_time IS NULL;
