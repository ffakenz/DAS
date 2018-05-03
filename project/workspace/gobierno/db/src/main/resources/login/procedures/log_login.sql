CREATE PROCEDURE log_login(@username VARCHAR(100)) AS
INSERT INTO login(username)
VALUES (@username);