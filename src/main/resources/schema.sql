drop table if exists CurrencyExchangeRate cascade;
drop table if exists PRODUCT cascade;
create table CurrencyExchangeRate
(
    id             bigserial not null,
    broj_tecajnice varchar(255),
    datum_primjene varchar(255),
    drzava         varchar(255),
    drzava_iso     varchar(255),
    kupovni_tecaj  varchar(255),
    prodajni_tecaj varchar(255),
    sifra_valute   varchar(255),
    srednji_tecaj  varchar(255),
    valuta         varchar(255),
    primary key (id)
);
create table PRODUCT
(
    IS_AVAILABLE boolean,
    PRICE_EUR    numeric(38, 2),
    PRICE_USD    numeric(38, 2),
    id           bigserial not null,
    CODE         varchar(255),
    DESCRIPTION  varchar(255),
    NAME         varchar(255),
    primary key (id)
);
