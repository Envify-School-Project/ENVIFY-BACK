create database IF NOT EXISTS envify_database;

create user IF NOT EXISTS 'envify-dev'@'%.%.%.%' identified by 'envify-password';
grant select, update, insert, delete on envify_database.* to 'envify-dev'@'%.%.%.%';

flush privileges;

use envify_database;