BEGIN TRANSACTION;

DROP TABLE IF EXISTS recipecards;

CREATE TABLE recipecards (
  id serial PRIMARY KEY,
  title varchar (256),
  image varchar(256) NOT NULL,   -- image URL
  imageType varchar(256) NOT NULL,       -- image type

);

COMMIT TRANSACTION;
