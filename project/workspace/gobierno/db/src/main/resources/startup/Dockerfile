FROM microsoft/mssql-server-linux:latest

# Create app db directory
RUN mkdir -p /usr/src/app/db
WORKDIR /usr/src/app/db

# Bundle app db source
COPY . /usr/src/app/db

# Grant permissions for the createdb script to be executable
RUN chmod +x /usr/src/app/db/createdb.sh

CMD /bin/bash /usr/src/app/db/entrypoint.sh
