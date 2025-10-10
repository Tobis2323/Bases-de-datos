-- =====================================================================
-- LEGO - Normalization of THEME, AGE and COUNTRY
-- Base: PUBLIC.LEGO_SETS (flattened dataset)
-- Engine: H2 2.x
-- =====================================================================

-- ---------------------------------------------------------------------
-- Cleanup (idempotent)
-- ---------------------------------------------------------------------
DROP TABLE IF EXISTS LEGO_SETS;
DROP TABLE IF EXISTS THEMES;
DROP TABLE IF EXISTS AGE_GROUPS;
DROP TABLE IF EXISTS COUNTRIES;

DROP SEQUENCE IF EXISTS SEQ_LEGO_SET_ID;
DROP SEQUENCE IF EXISTS SEQ_THEME_ID;
DROP SEQUENCE IF EXISTS SEQ_AGE_GROUP_ID;
DROP SEQUENCE IF EXISTS SEQ_COUNTRY_ID;

-- ---------------------------------------------------------------------
-- Sequences (auto-increment strategy)
-- ---------------------------------------------------------------------
CREATE SEQUENCE IF NOT EXISTS SEQ_LEGO_SET_ID   START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS SEQ_THEME_ID      START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS SEQ_AGE_GROUP_ID  START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS SEQ_COUNTRY_ID    START WITH 1 INCREMENT BY 1;

-- ---------------------------------------------------------------------
-- Table: COUNTRIES
-- ID_COUNTRY (PK), CODE (indexed + unique), NAME
-- ---------------------------------------------------------------------
CREATE TABLE COUNTRIES (
                           ID_COUNTRY  INTEGER      NOT NULL DEFAULT NEXT VALUE FOR SEQ_COUNTRY_ID,
                           CODE        VARCHAR(3)   NOT NULL,
                           NAME        VARCHAR(100) NOT NULL,
                           CONSTRAINT PK_COUNTRIES PRIMARY KEY (ID_COUNTRY),
                           CONSTRAINT UK_COUNTRIES_CODE UNIQUE (CODE)
);

-- Seed countries
INSERT INTO COUNTRIES (CODE, NAME) VALUES
                                       ('AT','Austria'),
                                       ('AU','Australia'),
                                       ('BE','Belgium'),
                                       ('CA','Canada'),
                                       ('CH','Switzerland'),
                                       ('CZ','Czech Republic'),
                                       ('DE','Germany'),
                                       ('DN','Denmark'),
                                       ('ES','Spain'),
                                       ('FI','Finland'),
                                       ('FR','France'),
                                       ('GB','United Kingdom'),
                                       ('IE','Ireland'),
                                       ('IT','Italy'),
                                       ('LU','Luxembourg'),
                                       ('NL','Netherlands'),
                                       ('NO','Norway'),
                                       ('NZ','New Zealand'),
                                       ('PL','Poland'),
                                       ('PT','Portugal'),
                                       ('US','United States');

-- Required index by CODE
CREATE INDEX IF NOT EXISTS IX_COUNTRIES_CODE ON COUNTRIES (CODE);

-- ---------------------------------------------------------------------
-- Table: THEMES (catalog of themes)
-- ---------------------------------------------------------------------
CREATE TABLE THEMES (
                        ID_THEME  INTEGER      NOT NULL DEFAULT NEXT VALUE FOR SEQ_THEME_ID,
                        NAME      VARCHAR(120) NOT NULL,
                        CONSTRAINT PK_THEMES PRIMARY KEY (ID_THEME),
                        CONSTRAINT UK_THEMES_NAME UNIQUE (NAME)
);

-- Seed themes
INSERT INTO THEMES (NAME) VALUES
                              ('Star Wars'),
                              ('City'),
                              ('Technic'),
                              ('Friends'),
                              ('Ninjago'),
                              ('Harry Potter');

-- ---------------------------------------------------------------------
-- Table: AGE_GROUPS (catalog of age ranges)
-- CODE = dataset literal (e.g., '6-12', '12')
-- ---------------------------------------------------------------------
CREATE TABLE AGE_GROUPS (
                            ID_AGE_GROUP  INTEGER      NOT NULL DEFAULT NEXT VALUE FOR SEQ_AGE_GROUP_ID,
                            CODE          VARCHAR(16)  NOT NULL,
                            CONSTRAINT PK_AGE_GROUP PRIMARY KEY (ID_AGE_GROUP),
                            CONSTRAINT UK_AGE_GROUP_CODE UNIQUE (CODE)
);

-- Seed age groups
INSERT INTO AGE_GROUPS (CODE) VALUES
                                  ('6-12'),
                                  ('12+'),
                                  ('5-8'),
                                  ('8-14');

-- ---------------------------------------------------------------------
-- Table: LEGO_SETS (normalized destination)
-- * Surrogate PK ID_SET
-- * FKs to THEMES, AGE_GROUPS, COUNTRIES
-- ---------------------------------------------------------------------
CREATE TABLE LEGO_SETS (
                           ID_SET            INTEGER       NOT NULL DEFAULT NEXT VALUE FOR SEQ_LEGO_SET_ID,
                           PROD_ID           INTEGER       NOT NULL,
                           SET_NAME          VARCHAR(200)  NOT NULL,
                           PROD_DESC         VARCHAR(2048),
                           REVIEW_DIFFICULTY VARCHAR(32),
                           PIECE_COUNT       INTEGER,
                           STAR_RATING       DECIMAL(3,1),
                           LIST_PRICE        DECIMAL(10,2),
                           THEME_ID          INTEGER       NOT NULL,
                           AGE_GROUP_ID      INTEGER       NOT NULL,
                           COUNTRY_ID        INTEGER       NOT NULL,
                           CONSTRAINT PK_LEGO_SET PRIMARY KEY (ID_SET),
                           CONSTRAINT CK_STAR_RATING CHECK (STAR_RATING IS NULL OR (STAR_RATING >= 0 AND STAR_RATING <= 5)),
                           CONSTRAINT FK_LEGOSET_THEME     FOREIGN KEY (THEME_ID)     REFERENCES THEMES(ID_THEME),
                           CONSTRAINT FK_LEGOSET_AGEGROUP  FOREIGN KEY (AGE_GROUP_ID) REFERENCES AGE_GROUPS(ID_AGE_GROUP),
                           CONSTRAINT FK_LEGOSET_COUNTRY   FOREIGN KEY (COUNTRY_ID)   REFERENCES COUNTRIES(ID_COUNTRY)
);

-- Suggested indexes for queries
CREATE INDEX IF NOT EXISTS IX_LEGOSET_THEME    ON LEGO_SETS (THEME_ID);
CREATE INDEX IF NOT EXISTS IX_LEGOSET_AGEGROUP ON LEGO_SETS (AGE_GROUP_ID);
CREATE INDEX IF NOT EXISTS IX_LEGOSET_COUNTRY  ON LEGO_SETS (COUNTRY_ID);
CREATE INDEX IF NOT EXISTS IX_LEGOSET_PRICE    ON LEGO_SETS (LIST_PRICE);
CREATE INDEX IF NOT EXISTS IX_LEGOSET_PIECES   ON LEGO_SETS (PIECE_COUNT);

-- Ejemplo: seed data para LEGO_SETS
INSERT INTO LEGO_SETS (
    PROD_ID, SET_NAME, PROD_DESC, REVIEW_DIFFICULTY, PIECE_COUNT, STAR_RATING, LIST_PRICE, THEME_ID, AGE_GROUP_ID, COUNTRY_ID
) VALUES
      (10001, 'Millennium Falcon', 'Star Wars spaceship', 'Medium', 1345, 4.8, 149.99, 1, 1, 1),
      (10002, 'City Police Station', 'City theme police station', 'Easy', 742, 4.2, 79.99, 2, 3, 2),
      (10003, 'Technic Bugatti', 'Technic supercar', 'Hard', 3599, 4.9, 349.99, 3, 2, 3);

-- =====================================================================
-- End of DDL
-- =====================================================================