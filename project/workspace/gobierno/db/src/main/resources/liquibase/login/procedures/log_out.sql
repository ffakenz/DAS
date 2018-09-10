CREATE PROCEDURE log_out( @username VARCHAR(100) )
AS
UPDATE login
SET log_out_time = GETDATE()
WHERE username = @username
    AND log_out_time IS NULL;
