CREATE TABLE frekwencja (
    idf     INTEGER NOT NULL,
    idp     INTEGER NOT NULL,
    iducz   INTEGER NOT NULL,
    data    DATE,
    godzina INTEGER,
    rodzaj  VARCHAR(20)
);

ALTER TABLE frekwencja ADD CONSTRAINT frekwencja_pk PRIMARY KEY ( idf );

CREATE TABLE klasy (
    idk             INTEGER NOT NULL,
    nazwa           VARCHAR(20) NOT NULL,
    datarozpoczecia DATE,
    nauczyciel_idn  INTEGER NOT NULL
);

ALTER TABLE klasy ADD CONSTRAINT idk PRIMARY KEY ( idk );

CREATE TABLE nauczyciele (
    idn INTEGER NOT NULL,
    idu INTEGER NOT NULL
);

ALTER TABLE nauczyciele ADD CONSTRAINT nauczyciel_pk PRIMARY KEY ( idn );

CREATE TABLE nauczycieleprzedmiotow (
    idnp INTEGER NOT NULL,
    idn  INTEGER NOT NULL,
    idp  INTEGER NOT NULL
);

ALTER TABLE nauczycieleprzedmiotow ADD CONSTRAINT nauczycieleprzedmiotow_pk PRIMARY KEY ( idnp );

CREATE TABLE oceny (
    ido   INTEGER NOT NULL,
    nazwa VARCHAR(20),
    ocena REAL NOT NULL,
    idu   INTEGER NOT NULL,
    idp   INTEGER NOT NULL
);

ALTER TABLE oceny ADD CONSTRAINT oceny_pk PRIMARY KEY ( ido );

CREATE TABLE przedmioty (
    idp   INTEGER NOT NULL,
    nazwa VARCHAR(20)
);

ALTER TABLE przedmioty ADD CONSTRAINT przedmiot_pk PRIMARY KEY ( idp );

CREATE TABLE przedmiotyklas (
    idpk INTEGER NOT NULL,
    idp  INTEGER NOT NULL,
    idk  INTEGER NOT NULL
);

ALTER TABLE przedmiotyklas ADD CONSTRAINT przedmiotyklas_pk PRIMARY KEY ( idpk );

CREATE TABLE uczniowie (
    iducz        INTEGER NOT NULL,
    nrwdzienniku INTEGER,
    idk          INTEGER NOT NULL,
    iduucznia    INTEGER NOT NULL,
    idurodzica   INTEGER NOT NULL
);

ALTER TABLE uczniowie ADD CONSTRAINT idu PRIMARY KEY ( iducz );

CREATE TABLE uzytkownicy (
    idu      INTEGER NOT NULL,
    login    VARCHAR(20),
    haslo    VARCHAR(20),
    imie     VARCHAR(20),
    nazwisko VARCHAR(20),
    rola     VARCHAR(20)
);

ALTER TABLE uzytkownicy ADD CONSTRAINT uzytkownicy_pk PRIMARY KEY ( idu );

ALTER TABLE frekwencja
    ADD CONSTRAINT frekwencja_p_fk FOREIGN KEY ( idp )
        REFERENCES przedmioty ( idp );

ALTER TABLE frekwencja
    ADD CONSTRAINT frekwencja_u_fk FOREIGN KEY ( iducz )
        REFERENCES uczniowie ( iducz );

ALTER TABLE uczniowie
    ADD CONSTRAINT idurodzica FOREIGN KEY ( idurodzica )
        REFERENCES uzytkownicy ( idu );

ALTER TABLE uczniowie
    ADD CONSTRAINT klasa FOREIGN KEY ( idk )
        REFERENCES klasy ( idk );

ALTER TABLE nauczyciele
    ADD CONSTRAINT nauczyciele_uzytkownicy_fk FOREIGN KEY ( idu )
        REFERENCES uzytkownicy ( idu );

ALTER TABLE nauczycieleprzedmiotow
    ADD CONSTRAINT nauczycieleprzedmiotow_n_fk FOREIGN KEY ( idn )
        REFERENCES nauczyciele ( idn );

ALTER TABLE nauczycieleprzedmiotow
    ADD CONSTRAINT nauczycieleprzedmiotow_p_fk FOREIGN KEY ( idp )
        REFERENCES przedmioty ( idp );

ALTER TABLE oceny
    ADD CONSTRAINT oceny_przedmioty_fk FOREIGN KEY ( idp )
        REFERENCES przedmioty ( idp );

ALTER TABLE oceny
    ADD CONSTRAINT oceny_uczen_fk FOREIGN KEY ( idu )
        REFERENCES uczniowie ( iducz );

ALTER TABLE przedmiotyklas
    ADD CONSTRAINT przedmiotyklas_k_fk FOREIGN KEY ( idk )
        REFERENCES klasy ( idk );

ALTER TABLE przedmiotyklas
    ADD CONSTRAINT przedmiotyklas_p_fk FOREIGN KEY ( idp )
        REFERENCES przedmioty ( idp );

ALTER TABLE uczniowie
    ADD CONSTRAINT uczniowie_uzytkownicy_fk FOREIGN KEY ( iduucznia )
        REFERENCES uzytkownicy ( idu );

ALTER TABLE klasy
    ADD CONSTRAINT wychowawca FOREIGN KEY ( nauczyciel_idn )
        REFERENCES nauczyciele ( idn );
