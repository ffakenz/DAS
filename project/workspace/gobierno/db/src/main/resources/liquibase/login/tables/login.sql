CREATE TABLE login (
	id BIGINT IDENTITY
	, documento BIGINT NOT NULL
	, log_in_time DATETIME NOT NULL DEFAULT SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time'
	, log_out_time DATETIME DEFAULT NULL
	, PRIMARY KEY(id)
	, FOREIGN KEY(documento) REFERENCES usuario(documento)
	, INDEX I_login_documento_log_out_time (documento, log_out_time)
);