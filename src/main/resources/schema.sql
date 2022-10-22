/*
 * Author:  mehul jain
 * Created: Oct 22, 2022
 */

DROP TABLE IF EXISTS users;  
CREATE TABLE users (
userid INT AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
emailId VARCHAR(50) NOT NULL,
phoneNumber VARCHAR(10) NOT NULL
);  

DROP TABLE IF EXISTS email;  
CREATE TABLE email (
emailKey INT AUTO_INCREMENT PRIMARY KEY,
fromemail VARCHAR(50) NOT NULL,
toemail VARCHAR(50) NOT NULL,
subject VARCHAR(255) NOT NULL,
body VARCHAR(1024) NOT NULL
);  