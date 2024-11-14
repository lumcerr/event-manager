/*Drop user first if they exist*/
DROP USER IF EXISTS "mysqluser"@"%";

/*Now create user with prop privileges*/
CREATE USER "mysqluser"@"%" IDENTIFIED BY "mysqluser";

GRANT ALL PRIVILEGES ON KasvAcademy . * TO "mysqluser"@"%";

FLUSH PRIVILEGES;