CREATE TABLE "User" (ID SERIAL NOT NULL, Name varchar(255), Email varchar(255), Password varchar(255), PRIMARY KEY (ID));
CREATE TABLE Game (ID SERIAL NOT NULL, Name varchar(255), Year int4 NOT NULL, Price float8 NOT NULL, Description varchar(255), PRIMARY KEY (ID));
CREATE TABLE Platform (ID SERIAL NOT NULL, Name varchar(255), Year int4 NOT NULL, Description varchar(255), Manufacturer varchar(255), PRIMARY KEY (ID));
