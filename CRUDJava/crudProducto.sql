SELECT * FROM PRODUCTO;

SELECT * FROM PRODUCTO WHERE ID=1;

INSERT INTO PRODUCTO(ID, NOM_PRO, PRECIOU_PRO) values(102,'TECLADO', 50.0);

UPDATE PRODUCTO SET NOM_PRO='TECLADO GAMER', PRECIOU_PRO= 60.0 WHERE ID=102;

DELETE FROM PRODUCTO WHERE ID=102;