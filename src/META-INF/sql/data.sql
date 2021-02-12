
-- 99800B85D3383E3A2FB45EB7D0066A4879A9DAD0 hola password
-- CDC4E9F90112A90A27D8A6D267CFC5391BAE3C6B adios password

INSERT INTO PDS2_PhotoFilm4You.administrator(id, email, password) values (1, 'administrator1@local.com', '99800B85D3383E3A2FB45EB7D0066A4879A9DAD0');
INSERT INTO PDS2_PhotoFilm4You.administrator(id, email, password) values (2, 'administrator2@local.com', 'CDC4E9F90112A90A27D8A6D267CFC5391BAE3C6B');

-- contraseña b6e70e0ba0dd7b97d328d8f6ac353fbbdc618d07 hola
INSERT INTO PDS2_PhotoFilm4You.customer(id,email,password,nif, address, phone, name, surname) values (11,'raquelgc@uoc.edu','99800B85D3383E3A2FB45EB7D0066A4879A9DAD0','51680569Y','paseo marítimo 17','666555444', 'Raquel','Garrido');
INSERT INTO PDS2_PhotoFilm4You.customer(id,email,password,nif, address, phone, name, surname) values (12,'panyu1512@uoc.edu','99800B85D3383E3A2FB45EB7D0066A4879A9DAD0','51680569Y','avenida principal 3','666555444', 'Enrique','Ferrer');
INSERT INTO PDS2_PhotoFilm4You.customer(id,email,password,nif, address, phone, name, surname) values (13,'ggomezmarti@uoc.edu','99800B85D3383E3A2FB45EB7D0066A4879A9DAD0','51680569Y','paseo rosaleda 20','666555444', 'Gabriel','Gómez');
INSERT INTO PDS2_PhotoFilm4You.customer(id,email,password,nif, address, phone, name, surname) values (14,'jonzl@uoc.edu','99800B85D3383E3A2FB45EB7D0066A4879A9DAD0','51680569Y','alameda apodaca 18','666555444', 'Jon','González');
INSERT INTO PDS2_PhotoFilm4You.customer(id,email,password,nif, address, phone, name, surname) values (15,'chocolo@uoc.edu','99800B85D3383E3A2FB45EB7D0066A4879A9DAD0','51680569Y','rivera manzana 48','666555444', 'Jonathan','Díaz');
INSERT INTO PDS2_PhotoFilm4You.customer(id,email,password,nif, address, phone, name, surname) values (16,'test@test.com','99800B85D3383E3A2FB45EB7D0066A4879A9DAD0','51680569Y','rivera manzana 48','666555444', 'Test User','Díaz');

INSERT INTO PDS2_PhotoFilm4You.category(id,name) values (1,'Imagen');
INSERT INTO PDS2_PhotoFilm4You.category(id,name) values (2,'Sonido');
INSERT INTO PDS2_PhotoFilm4You.category(id,name,parent) values (3,'Fotografia',1);
INSERT INTO PDS2_PhotoFilm4You.category(id,name,parent) values (4,'Video',2);
INSERT INTO PDS2_PhotoFilm4You.category(id,name,parent) values (5,'Accesorios fotografia',1);
INSERT INTO PDS2_PhotoFilm4You.category(id,name,parent) values (6,'Accesorios audio',2);

INSERT INTO PDS2_PhotoFilm4You.brand(id,name) values(1,'Nikon');
INSERT INTO PDS2_PhotoFilm4You.brand(id,name) values(2,'Canon');
INSERT INTO PDS2_PhotoFilm4You.brand(id,name) values(3,'Leica');
INSERT INTO PDS2_PhotoFilm4You.brand(id,name) values(4,'Sony');

INSERT INTO PDS2_PhotoFilm4You.model(id,name) values(1,'Premium');
INSERT INTO PDS2_PhotoFilm4You.model(id,name) values(2,'Basic');
INSERT INTO PDS2_PhotoFilm4You.model(id,name) values(3,'Standard');
INSERT INTO PDS2_PhotoFilm4You.model(id,name) values(4,'H2B');

INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (1,'Nikon D60','Cámara reflex digital',50,3,1,1);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (2,'Canon EOS','Cámara reflex digital',40,3,3,2);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (3,'Kodak EEE','Camara reflex',30,3,3,2);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (4,'HP EPS','Proyector', 20, 2, 2, 2);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (5,'Nikon D90','Cámara reflex digital',50,3,1,1);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (6,'Nikon D40','Cámara reflex digital',50,3,1,1);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (7,'Nikon D500','Cámara reflex digital',50,3,1,1);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (8,'Nikon D750','Cámara reflex digital',50,3,1,1);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (9,'Nikon D850','Cámara reflex digital',50,3,1,1);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (10,'Nikon D7500','Cámara reflex digital',50,3,1,1);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (11,'Nikon D5600','Cámara reflex digital',50,3,1,1);
INSERT INTO PDS2_PhotoFilm4You.product(id, name, description, dailyprice, category, model, brand) values (12,'Nikon D6','Cámara reflex digital',50,3,1,1);


INSERT INTO PDS2_PhotoFilm4You.product_rating(id, comment, rating, product, customer) values (1, 'Muy bueno',9, 1,11);
INSERT INTO PDS2_PhotoFilm4You.product_rating(id, comment, rating, product, customer) values (2, 'Malo',2, 2,12);
INSERT INTO PDS2_PhotoFilm4You.product_rating(id, comment, rating, product, customer) values (3, 'Nada fuera de lo común',5, 2,13);
INSERT INTO PDS2_PhotoFilm4You.product_rating(id, comment, rating, product, customer) values (4, 'Se me rompió a los dos días',1, 2,14);
INSERT INTO PDS2_PhotoFilm4You.product_rating(id, comment, rating, product, customer) values (5, 'Excelente',10, 1,11);


INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329837','Operational',1);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329838','Operational',2);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329839','Operational',3);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329840','Operational',4);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329841','Operational',5);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329842','Operational',6);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329843','Operational',7);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329844','Operational',8);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329845','Operational',8);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329846','Operational',8);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329847','Operational',8);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329848','Operational',8);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA5968329987','Broken',1);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('XXFRNA596832A987','Operational',1);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('NNHRNA596832A987','Operational',2);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('YYFRNA596832A987','Operational',2);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('ZZFRNA596832A987','Operational',2);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('ZZFRNA5968444443','Broken',3);
INSERT INTO PDS2_PhotoFilm4You.item(serialNumber,status,idProduct) values('ZZFRNA5968224443','Operational',4);



INSERT INTO PDS2_PhotoFilm4You.cancellation(id, creationdate, penalization, penalizationstatus) VALUES (10000,'2020-12-09', 40.50, 'Pending');
INSERT INTO PDS2_PhotoFilm4You.cancellation(id, creationdate, penalization, penalizationstatus) VALUES (20000,'2020-12-09', 200.30, 'Paid');
INSERT INTO PDS2_PhotoFilm4You.cancellation(id, creationdate, penalization, penalizationstatus) VALUES (30000,'2020-12-10', 30.50, 'Pending');
INSERT INTO PDS2_PhotoFilm4You.cancellation(id, creationdate, penalization, penalizationstatus) VALUES (40000,'2020-12-11', 10.50, 'Pending');

INSERT INTO PDS2_PhotoFilm4You.rent(id, fromdate, status, todate, cancellationid, customerid) VALUES (1000000, '2020-12-09', 'Confirmed', '2020-12-19', null, 11);
INSERT INTO PDS2_PhotoFilm4You.rent(id, fromdate, status, todate, cancellationid, customerid) VALUES (2000000, '2020-12-13', 'Cancelled', '2020-12-30', 20000, 12);
INSERT INTO PDS2_PhotoFilm4You.rent(id, fromdate, status, todate, cancellationid, customerid) VALUES (3000000, '2020-12-11', 'NotConfirmed', '2020-12-20', null, 11);
INSERT INTO PDS2_PhotoFilm4You.rent(id, fromdate, status, todate, cancellationid, customerid) VALUES (4000000, '2020-12-13', 'NotConfirmed', '2020-12-30', null, 13);
INSERT INTO PDS2_PhotoFilm4You.rent(id, fromdate, status, todate, cancellationid, customerid) VALUES (5000000, '2020-12-13', 'Cancelled', '2021-01-22', 10000, 14);
INSERT INTO PDS2_PhotoFilm4You.rent(id, fromdate, status, todate, cancellationid, customerid) VALUES (6000000, '2020-12-13', 'Confirmed', '2020-12-29', null, 14);
INSERT INTO PDS2_PhotoFilm4You.rent(id, fromdate, status, todate, cancellationid, customerid) VALUES (7000000, '2020-12-13', 'Confirmed', '2020-12-27', null, 13);
INSERT INTO PDS2_PhotoFilm4You.rent(id, fromdate, status, todate, cancellationid, customerid) VALUES (8000000, '2020-12-13', 'NotConfirmed', '2020-12-27', null, 16);
INSERT INTO PDS2_PhotoFilm4You.rent(id, fromdate, status, todate, cancellationid, customerid) VALUES (9000000, '2020-12-20', 'Confirmed', '2021-01-14', null, 16);
INSERT INTO PDS2_PhotoFilm4You.rent(id, fromdate, status, todate, cancellationid, customerid) VALUES (1100000, '2020-12-21', 'Confirmed', '2021-01-20', null, 16);

INSERT INTO PDS2_PhotoFilm4You.rent_item(rentjpa_id, items_serialnumber) VALUES (1000000, 'XXFRNA5968329837');
INSERT INTO PDS2_PhotoFilm4You.rent_item(rentjpa_id, items_serialnumber) VALUES (2000000, 'XXFRNA5968329837');
INSERT INTO PDS2_PhotoFilm4You.rent_item(rentjpa_id, items_serialnumber) VALUES (3000000, 'ZZFRNA596832A987');
INSERT INTO PDS2_PhotoFilm4You.rent_item(rentjpa_id, items_serialnumber) VALUES (3000000, 'YYFRNA596832A987');
INSERT INTO PDS2_PhotoFilm4You.rent_item(rentjpa_id, items_serialnumber) VALUES (9000000, 'YYFRNA596832A987');
INSERT INTO PDS2_PhotoFilm4You.rent_item(rentjpa_id, items_serialnumber) VALUES (1100000, 'ZZFRNA596832A987');