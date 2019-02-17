INSERT INTO login(documento, log_out_time)
VALUES
	(111, SYSDATETIMEOFFSET() AT TIME ZONE 'Argentina Standard Time')
	, (222, default);