FROM postgres:11.14-bullseye
RUN mkdir -p "$PGDATA" && chmod 700 "$PGDATA"
ADD initdb.sql /docker-entrypoint-initdb.d/