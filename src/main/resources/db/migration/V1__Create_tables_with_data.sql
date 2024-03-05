SET SCHEMA ${flyway:defaultSchema};

CREATE TABLE IF NOT EXISTS brands (
    BRAND_ID integer primary key not null,
    BRAND_NAME varchar(20)
);

CREATE TABLE IF NOT EXISTS prices (
    ID integer auto_increment primary key,
    BRAND_ID integer not null,
    START_DATE timestamp not null,
    END_DATE timestamp not null,
    PRICE_LIST varchar(2) not null,
    PRODUCT_ID varchar(10) not null,
    PRIORITY boolean,
    PRICE numeric(20, 2) not null,
    CURR varchar(3) not null,

    constraint brand_ctx_fk foreign key (BRAND_ID)
    references brands (BRAND_ID)
);

INSERT INTO brands(BRAND_ID, BRAND_NAME) values (1, 'Zara');

INSERT INTO prices(BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PRODUCT_ID, PRIORITY, PRICE, CURR) VALUES
    (1, '2020-06-14T00:00:00.000Z', '2020-12-31T23:59:59.000Z', '1', '35455', 0, 35.50, 'EUR'),
    (1, '2020-06-14T15:00:00.000Z', '2020-06-14T18:30:00.000Z', '2', '35455', 1, 25.45, 'EUR'),
    (1, '2020-06-15T00:00:00.000Z', '2020-06-15T11:00:00.000Z', '3', '35455', 1, 30.50, 'EUR'),
    (1, '2020-06-15T16:00:00.000Z', '2020-12-31T23:59:59.000Z', '4', '35455', 1, 38.95, 'EUR');