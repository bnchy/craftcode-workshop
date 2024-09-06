INSERT INTO BREWERY (name, location)
VALUES ('Brasserie d''Achouffe', 'Achouffe 32, 6666 Houffalize'),
       ('Microbrouwerij Grimbergen', ' Abdijstraat 20, 1850 Grimbergen'),
       ('Brouwerij Cornelissen', 'Itterplein 19, 3960 Bree');

INSERT INTO BEER (name, alcohol_percentage, beer_type, brewery_id)
VALUES
    ('chouffe', 6, 'ALE', 1),
    ('grimbergen', 6, 'FRUIT', 2),
    ('pils', 10, 'FRUIT', 3);

INSERT INTO CLASSIFICATION (country, fermentation_type, names_and_origins, used_grain_type)
VALUES
    ('BELGIUM', 'WARM', 'ABBEY_BEER', 'BARELY' ),
    ('GERMANY','COOL', 'TRAPPIST_BEER', 'OATS');

INSERT INTO CLASSIFICATION_BEER (beer_id, classification_id)
VALUES
    (1,1),
    (3,2)
