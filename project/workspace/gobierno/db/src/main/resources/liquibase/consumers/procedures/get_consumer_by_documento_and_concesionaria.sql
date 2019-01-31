CREATE PROCEDURE get_consumer_by_documento_and_concesionaria (
      @documento BIGINT,
      @concesionaria BIGINT
) AS
SELECT cs.*
FROM consumers cs JOIN estado_cuentas cc ON cs.documento = cc.dni_consumer
WHERE cc.dni_consumer = @documento
  AND cc.concesionaria = @concesionaria;