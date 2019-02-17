CREATE PROCEDURE log_out( @id BIGINT )
AS
UPDATE login
SET log_out_time = SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
WHERE id = @id
    AND log_out_time IS NULL;
