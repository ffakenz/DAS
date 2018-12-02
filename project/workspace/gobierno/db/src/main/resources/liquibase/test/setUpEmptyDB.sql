CREATE PROCEDURE setUpEmptyDB AS
BEGIN

    INSERT INTO tipos_estado_cuentas (tipo)
        VALUES ('inicial')
            ,('en_proceso')
            ,('pagado')
            ,('cancelado')
END
GO