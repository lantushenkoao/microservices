ALTER SYSTEM SET max_connections = 200;
ALTER SYSTEM RESET shared_buffers;
--Create DB
CREATE DATABASE calendar;
GRANT ALL PRIVILEGES ON DATABASE calendar TO postgres;