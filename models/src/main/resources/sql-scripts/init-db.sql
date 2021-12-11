INSERT INTO item (id,title, description, category) VALUES (1,'Basketball ball','Ball for playing basketball.','Sports');
INSERT INTO item (id,title, description, category) VALUES (2,'Poker cards','Cards for playing poker.','Board game');
INSERT INTO item (id,title, description, category) VALUES (3,'Remi cards','Cards for remi.','Board game');
INSERT INTO item (id,title, description, category) VALUES (4,'Golf gear','Gear for playing golf.','Sports');
INSERT INTO item (id,title, description, category) VALUES (5,'Fishing boots','Boots for fishing.','Water sports');
INSERT INTO item (id,title, description, category) VALUES (6,'Tent','Tent for 2 persons.','Nature');
INSERT INTO item (id,title, description, category) VALUES (7,'Sup','Sup for lake, sea.','Water sports');
INSERT INTO item (id,title, description, category) VALUES (8,'Climbing gear','Gear for climbing.','Sports');
INSERT INTO item (id,title, description, category) VALUES (9,'Parachute','Perfect for sky-diving.','Sports');
INSERT INTO item (id,title, description, category) VALUES (10,'Chess','Inside board game.','Board game');

INSERT INTO person (id,username, email, role) VALUES (1,'crazyChica','crazychica@gmail.com','user');
INSERT INTO person (id,username, email, role) VALUES (2,'crazyChichito','crazychico@gmail.com','user');
INSERT INTO person (id,username, email, role) VALUES (3,'chessLover','chess123@gmail.com','user');

INSERT INTO borrow (from_date, to_date, id_person,id_item,returned,reserved) VALUES ('2021-10-29','2021-10-31',1,9, false, true);
INSERT INTO borrow (from_date, to_date, id_person,id_item,returned,reserved) VALUES ('2021-10-29','2021-10-31',1,8, true, true);
INSERT INTO borrow (from_date, to_date, id_person,id_item,returned,reserved) VALUES ('2021-10-29','2021-10-31',2,6, false, false);
INSERT INTO borrow (from_date, to_date, id_person,id_item,returned,reserved) VALUES ('2021-10-29','2021-10-31',3,10, false, false);

INSERT INTO favourites (id_person,id_item) VALUES (1,4);
INSERT INTO favourites (id_person,id_item) VALUES (1,8);
INSERT INTO favourites (id_person,id_item) VALUES (1,3);
