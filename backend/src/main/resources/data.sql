INSERT INTO BREWERY (name, location)
VALUES
    ('Brasserie d''Achouffe', 'Achouffe 32, 6666 Houffalize'),
    ('Microbrouwerij Grimbergen', 'Abdijstraat 20, 1850 Grimbergen'),
    ('Brouwerij Cornelissen', 'Itterplein 19, 3960 Bree'),
    ('Brouwerij Het Anker', 'Guido Gezellelaan 49, 2800 Mechelen'),
    ('Brouwerij Van Honsebrouck', 'Ingelmunstersestraat 46, 8770 Ingelmunster'),
    ('Brouwerij Duvel Moortgat', 'Breendonk-Dorp 58, 2870 Puurs'),
    ('Brouwerij Huyghe', 'Geraardsbergsesteenweg 14, 9070 Melle'),
    ('Brouwerij Rodenbach', 'Spanjestraat 133, 8800 Roeselare'),
    ('Brouwerij Bosteels', 'Kerkstraat 96, 9255 Buggenhout'),
    ('Brouwerij De Halve Maan', 'Walplein 26, 8000 Brugge');

INSERT INTO CLASSIFICATION (country, fermentation_type, names_and_origins, used_grain_type)
VALUES
    ('BELGIUM', 'WARM', 'ABBEY_BEER', 'BARLEY'),
    ('GERMANY', 'COOL', 'TRAPPIST_BEER', 'OATS'),
    ('FRANCE', 'WARM', 'SAISON', 'WHEAT'),
    ('USA', 'WARM', 'PALE_ALE', 'BARLEY'),
    ('CZECH', 'COOL', 'LAGER', 'BARLEY'),
    ('UK', 'WARM', 'BITTER', 'BARLEY'),
    ('IRELAND', 'COOL', 'STOUT', 'BARLEY'),
    ('BELGIUM', 'COOL', 'LAMBIC', 'WHEAT'),
    ('AUSTRIA', 'COOL', 'PILSNER', 'BARLEY'),
    ('NETHERLANDS', 'WARM', 'TRIPLE', 'BARLEY');

INSERT INTO BEER (name, alcohol_percentage, beer_type, brewery_id, classification_id)
VALUES
    ('chouffe', 8, 'ALE', 1, 1),
    ('grimbergen', 6, 'FRUIT', 2, 2),
    ('pils', 10, 'FRUIT', 3, 2),
    ('chouffe blond', 8, 'ALE', 1, 1),
    ('gouden carolus', 9, 'STRONG_ALE', 4, 1),
    ('kasteel rouge', 8, 'FRUIT', 5, 2),
    ('duvel', 8.5, 'STRONG_ALE', 6, 1),
    ('delirium tremens', 8.5, 'PALE_ALE', 7, 4),
    ('rodenbach grand cru', 6, 'FLEMISH_RED', 8, 8),
    ('kwak', 8.4, 'AMBER', 9, 1),
    ('brugse zot', 6, 'PALE_ALE', 10, 4),
    ('tripel karmeliet', 8.4, 'TRIPLE', 9, 10),
    ('westmalle dubbel', 7, 'TRAPPIST', 2, 2),
    ('orval', 6.2, 'TRAPPIST', 2, 2),
    ('chimay blue', 9, 'TRAPPIST', 2, 2),
    ('leffe blonde', 6.6, 'BELGIAN_BLONDE', 2, 1),
    ('hoegaarden', 4.9, 'WITBIER', 6, 8),
    ('guinness', 4.2, 'STOUT', 7, 7),
    ('pilsner urquell', 4.4, 'LAGER', 8, 5),
    ('schneider weisse', 5.4, 'WEISSBIER', 8, 3);