CREATE TABLE login (
	id BIGINT IDENTITY
	, username VARCHAR(100) NOT NULL
	, log_in_time DATETIME NOT NULL DEFAULT GETDATE()
	, log_out_time DATETIME DEFAULT NULL
	, PRIMARY KEY(id)
	, FOREIGN KEY(username) REFERENCES usuario(username)
	, INDEX I_login_username_log_out_time (username, log_out_time)
);