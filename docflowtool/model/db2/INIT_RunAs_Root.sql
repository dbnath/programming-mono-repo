/*Run this script with root user previlege */
CREATE DATABASE IF NOT EXISTS TEST CHARACTER SET utf8 COLLATE utf8_bin;
USE TEST;
CREATE USER 'docworkflowuser'@'%' IDENTIFIED BY 'Test1234';
GRANT ALL PRIVILEGES ON *.* TO 'docworkflowuser'@'%';