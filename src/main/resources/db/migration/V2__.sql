ALTER TABLE product
    ADD quantity INT NULL;

ALTER TABLE product
    MODIFY quantity INT NOT NULL;

ALTER TABLE product
DROP
COLUMN qty;