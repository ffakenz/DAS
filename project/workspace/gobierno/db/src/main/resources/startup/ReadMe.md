Under gobierno/db/src/main/resources/startup
You need to build the container:
    `docker build -t mssql_das .`
Then, you need to run the container:
    `docker run -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Das12345' -p 1433:1433 --name mssql -d mssql_das`
Then you can connect to the SQL Server in the container by running a tool on your host or you can docker exec into the container and run sqlcmd from inside the container:
    `docker exec -it mssql /bin/bash /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P Das12345`