CREATE TABLE IF NOT EXISTS recipe (
id INTEGER IDENTITY PRIMARY KEY,
description VARCHAR(150) not null,
name VARCHAR(100) not null);

INSERT INTO recipe  (description, name) VALUES ('Delicious baked chicken with pepper and onion', 'Baked chicken');
INSERT INTO recipe  (description, name) VALUES ('Cod with tomato seasoned with spices', 'Cod with tomato');
INSERT INTO recipe  (description, name) VALUES ('Anchovies in vinegar with fine herbs', 'Anchovies in vinegar');
INSERT INTO recipe  (description, name) VALUES ('Pasta salad with minced meat', 'Pasta salad');
INSERT INTO recipe  (description, name) VALUES ('Delicious fish soup with good grass', 'Fish soup');


