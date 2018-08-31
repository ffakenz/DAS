CREATE PROCEDURE get_concesionaria_by_codigo (
    @codigo VARCHAR(50)
)  AS
SELECT *
FROM concesionaria
WHERE codigo = @codigo;