INSERT INTO BREWERY (name, location)
VALUES ('Brasserie d''Achouffe', 'Achouffe 32, 6666 Houffalize'),
       ('Microbrouwerij Grimbergen', ' Abdijstraat 20, 1850 Grimbergen'),
       ('Brouwerij Cornelissen', 'Itterplein 19, 3960 Bree');

INSERT INTO BEER (name, alcohol_percentage, beer_type, brewery_id)
VALUES
    ('chouffe', 6, 'ALE', 1),
    ('grimbergen', 6, 'FRUIT', 2),
    ('pils', 10, 'FRUIT', 3);

