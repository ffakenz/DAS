CREATE PROCEDURE approve_concesionaria(
    @id BIGINT
    , @codigo VARCHAR(50)
) AS
BEGIN
  IF @codigo IS NULL
    BEGIN
      RAISERROR('The parameter @codigo is null', 15, 1)
    END
  ELSE
    BEGIN
      UPDATE concesionaria
      SET fecha_alta = GETDATE()
          , codigo = @codigo
      WHERE id = @id
          AND codigo IS NULL
    END
END;
