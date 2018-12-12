CREATE PROCEDURE get_login_by_documento(@documento BIGINT)  AS
SELECT *
FROM login
WHERE documento = @documento
    AND log_out_time IS NULL;

--get_login_by_username