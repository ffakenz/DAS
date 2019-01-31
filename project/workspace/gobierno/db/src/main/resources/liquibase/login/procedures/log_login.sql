CREATE PROCEDURE log_login(@documento BIGINT) AS
INSERT INTO login(documento)
VALUES (@documento);
