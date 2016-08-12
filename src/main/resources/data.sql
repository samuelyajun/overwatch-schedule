INSERT INTO attributetype (name) VALUES ('ROLE');
INSERT INTO attributetype (name) VALUES ('OFFICE');
INSERT INTO attributetype (name) VALUES ('CLIENT');
INSERT INTO attributetype (name) VALUES ('PROJECT');

INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (1, 'Engagement Manager');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (1, 'Tech Lead');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (1, 'Business Analyst');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (1, 'Developer');

INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (2, 'Beaverton');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (2, 'Baltimore');

INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (3, 'Cake Systems');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (3, 'Cockram');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (3, 'Catalyst DevWorks');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (3, 'Cambia');

INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (4, 'Cake');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (4, '3DS MAX and AutoCAD');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (4, 'Overwatch');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (4, 'Mobile');

INSERT INTO users (email, first_name, last_name) VALUES ('hmccardell@catalystdevworks.com','Hayes ','McCardell II');
INSERT INTO users (email, first_name, last_name) VALUES ('bpyl@catalystdevworks.com','Brenton','Pyl');
INSERT INTO users (email, first_name, last_name) VALUES ('elandes@catalystdevworks.com','Eric','Landes');
INSERT INTO users (email, first_name, last_name) VALUES ('tbenz@catalystdevworks.com','Theresa','Benz');
INSERT INTO users (email, first_name, last_name) VALUES ('sbyington@catalystdevworks.com','Steven','Byington');
INSERT INTO users (email, first_name, last_name) VALUES ('sannamalai@catalystdevworks.com','Sivagami','Annamalai');
INSERT INTO users (email, first_name, last_name) VALUES ('smoss@catalystdevworks.com','Steven','Moss');
INSERT INTO users (email, first_name, last_name) VALUES ('nduncan@catalystdevworks.com','Nick','Duncan');
INSERT INTO users (email, first_name, last_name) VALUES ('philario@catalystdevworks.com','Paolo','Hilario');
INSERT INTO users (email, first_name, last_name) VALUES ('slandesberg@catalystdevworks.com','Sam','Landesberg');
INSERT INTO users (email, first_name, last_name) VALUES ('gfisher@catalystdevworks.com','Greg','Fisher');
INSERT INTO users (email, first_name, last_name) VALUES ('ddelaney@catalystdevworks.com','Derek','Delaney');
INSERT INTO users (email, first_name, last_name) VALUES ('dgoldstein@catalystdevworks.com','Danny','Goldstein');
INSERT INTO users (email, first_name, last_name) VALUES ('jjacobson@catalystdevworks.com','Josh','Jacobson');
INSERT INTO users (email, first_name, last_name) VALUES ('dsloane@catalystdevworks.com','Dan','Sloane');
INSERT INTO users (email, first_name, last_name) VALUES ('thoffman@catalystdevworks.com','Travis','Hoffman');
INSERT INTO users (email, first_name, last_name) VALUES ('kslott@catalystdevworks.com','Kate','Slott');
INSERT INTO users (email, first_name, last_name) VALUES ('afields@catalystdevworks.com','Adam','Fields');
INSERT INTO users (email, first_name, last_name) VALUES ('kcoomes@catalystdevworks.com','Ken','Coomes');
INSERT INTO users (email, first_name, last_name) VALUES ('pseng@catalystdevworks.com','Pros','Seng');
INSERT INTO users (email, first_name, last_name) VALUES ('aedwards@catalystdevworks.com','Abby','Edwards');
INSERT INTO users (email, first_name, last_name) VALUES ('bfutral@catalystdevworks.com','Brett','Futral');
INSERT INTO users (email, first_name, last_name) VALUES ('blarsen@catalystdevworks.com','Brad','Larsen');
INSERT INTO users (email, first_name, last_name) VALUES ('jbarroga@catalystdevworks.com','Jordan','Barroga');
INSERT INTO users (email, first_name, last_name) VALUES ('jblair@catalystdevworks.com','James','Blair');
INSERT INTO users (email, first_name, last_name) VALUES ('klafrance@catalystdevworks.com','Katy','LaFrance');
INSERT INTO users (email, first_name, last_name) VALUES ('amoss@catalystdevworks.com','Alexandra','Moss');
INSERT INTO users (email, first_name, last_name) VALUES ('arajigah@catalystdevworks.com','Andrew','Rajigah');
INSERT INTO users (email, first_name, last_name) VALUES ('hmccardell@catalystdevworks.com','Hayes ','McCardell II');
INSERT INTO users (email, first_name, last_name) VALUES ('mderfler@catalystdevworks.com','Michael','Derfler');
INSERT INTO users (email, first_name, last_name) VALUES ('ppor@catalystdevworks.com','Peter','Por');
INSERT INTO users (email, first_name, last_name) VALUES ('ssmith@catalystdevworks.com','Steve','Smith');
INSERT INTO users (email, first_name, last_name) VALUES ('gstringfellow@catalystdevworks.com','Gavin','Stringfellow');
INSERT INTO users (email, first_name, last_name) VALUES ('agraham@catalystdevworks.com','Andrew','Graham');
INSERT INTO users (email, first_name, last_name) VALUES ('ejornales@catalystdevworks.com','Earl','Jornales');
INSERT INTO users (email, first_name, last_name) VALUES ('cfernandez@catalystdevworks.com','CJ','Fernandez');
INSERT INTO users (email, first_name, last_name) VALUES ('tgrimes@catalystdevworks.com','Tyler','Grimes');
INSERT INTO users (email, first_name, last_name) VALUES ('jdouma@catalystdevworks.com','Justin','Douma');
INSERT INTO users (email, first_name, last_name) VALUES ('jvanblokland@catalystdevworks.com','Jacques ','Van Blokland');
INSERT INTO users (email, first_name, last_name) VALUES ('showard@catalystdevworks.com','Skye','Howard');

-- START FIRST GROUP OF TEST DATA

INSERT INTO schedule  (end_date, start_date, template_name, template_uri, frequency, isActive) VALUES (null, '2016-07-21', 'Sprint Checkup', '/templates/1', 'ONE_WEEK', 'true');

INSERT INTO respondent (schedule_id, user_id) VALUES (1, 1);
INSERT INTO respondent (schedule_id, user_id) VALUES (1, 2);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 4);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 5);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 9);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 10);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 4);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 5);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 9);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 10);

INSERT INTO occurrence (generation_date, is_complete, respondent_id, schedule_id, flight_number) VALUES ('2016-07-21', true, 1, 1, 1);
INSERT INTO occurrence (generation_date, is_complete, respondent_id, schedule_id, flight_number) VALUES ('2016-07-21', true, 2, 1, 1);

INSERT INTO occurrence (generation_date, is_complete, respondent_id, schedule_id, flight_number) VALUES ('2016-07-28', true, 1, 1, 2);
INSERT INTO occurrence (generation_date, is_complete, respondent_id, schedule_id, flight_number) VALUES ('2016-07-28', true, 2, 1, 2);

INSERT INTO occurrence (generation_date, is_complete, respondent_id, schedule_id, flight_number) VALUES ('2016-08-04', true, 1, 1, 3);
INSERT INTO occurrence (generation_date, is_complete, respondent_id, schedule_id, flight_number) VALUES ('2016-08-04', false, 2, 1, 3);

INSERT INTO flight (schedule_id, schedule_is_active, flight_number, is_closed, occurrences_in_flight) VALUES (1, true, 1, true, 2);
INSERT INTO flight (schedule_id, schedule_is_active, flight_number, is_closed, occurrences_in_flight) VALUES (1, true, 2, true, 2);
INSERT INTO flight (schedule_id, schedule_is_active, flight_number, is_closed, occurrences_in_flight) VALUES (1, true, 3, false, 2);

-- END FIRST GROUP OF TEST DATA


-- START SECOND GROUP OF TEST DATA
INSERT INTO schedule  (end_date, start_date, template_name, template_uri, frequency, isActive) VALUES (null, '2016-08-04', 'Sprint Checkup', '/templates/1', 'ONE_TIME', 'true');

INSERT INTO respondent (schedule_id, user_id) VALUES (2, 3);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 4);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 5);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 6);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 7);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 8);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 4);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 9);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 10);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 4);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 9);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 10);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 4);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 9);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 10);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 4);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 9);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 10);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 4);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 9);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 10);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 9);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 10);

--INSERT INTO occurrence (generation_date, is_complete, respondent_id, schedule_id, flight_number) VALUES ('2016-08-11', true, 1, 1, 1);


-- END SECOND GROUP OF TEST DATA