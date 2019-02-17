CREATE PROCEDURE log_out_by_documento( @documento BIGINT )
AS
UPDATE login
SET log_out_time = SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
WHERE documento = @documento
    AND log_out_time IS NULL;

-- log_out_by_username