INSERT INTO schedule  (end_date, start_date, template_uri, frequency, isActive) VALUES ('2016-05-15', '2016-04-30', '/surveys?suid=SKKE-2778954', 'ONE_WEEK', 'true');
INSERT INTO schedule  (end_date, start_date, template_uri, frequency, isActive) VALUES ('2016-09-01', '2016-07-28', '/surveys?suid=SKKE-2778954', 'TWO_WEEKS', 'true');
INSERT INTO schedule  (end_date, start_date, template_uri, frequency, isActive) VALUES ('2016-10-31', '2016-07-27', '/surveys?suid=SKKE-2778954', 'THREE_WEEKS', 'true');
INSERT INTO schedule  (end_date, start_date, template_uri, frequency, isActive) VALUES ('2017-12-31', '2016-07-27', '/surveys?suid=SKKE-2778954', 'TWO_WEEKS', 'true');
INSERT INTO schedule  (end_date, start_date, template_uri, frequency, isActive) VALUES ('2015-01-01', '2016-07-27', '/surveys?suid=SKKE-2778954', 'TWO_WEEKS', 'false');
INSERT INTO schedule  (end_date, start_date, template_uri, frequency, isActive) VALUES (null, '2016-07-06', '/surveys?suid=SKKE-2778954', 'THREE_WEEKS', 'true');
INSERT INTO schedule  (end_date, start_date, template_uri, frequency, isActive) VALUES (null, '2016-07-13', '/surveys?suid=SKKE-2778954', 'TWO_WEEKS', 'true');
INSERT INTO schedule  (end_date, start_date, template_uri, frequency, isActive) VALUES (null, '2016-07-20', '/surveys?suid=SKKE-2778954', 'ONE_WEEK', 'true');
INSERT INTO schedule  (end_date, start_date, template_uri, frequency, isActive) VALUES (null, '2017-07-27', '/surveys?suid=SKKE-2778954', 'ONE_WEEK', 'true');
INSERT INTO schedule  (end_date, start_date, template_uri, frequency, isActive) VALUES (null, '2017-07-27', '/surveys?suid=SKKE-2778954', 'ONE_TIME', 'true');

INSERT INTO users (first_name, last_name, email) VALUES ('Brenton', 'Pyl', 'bpyl@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Travis', 'Hoffman', 'thoffman@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Brett', 'Futral', 'bfutral@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Steven', 'Smith', 'ssmith@catalystdevworks.com');

INSERT INTO users (first_name, last_name, email) VALUES ('Abigail', 'Edwards', 'aedwards@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Joshua', 'Jacobson', 'jjacobson@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Sivagami', 'Annamalai', 'sannamalai@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Hayes', 'McCardell II', 'hmccardell@catalystdevworks.com');

INSERT INTO users (first_name, last_name, email) VALUES ('Pros', 'Seng', 'pseng@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Kathryn', 'LaFrance', 'klafrance@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Andrew', 'Rajigah', 'arajigah@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Alexandra', 'Moss', 'amoss@catalystdevworks.com');

INSERT INTO users (first_name, last_name, email) VALUES ('Thomas', 'Getzoyan', 'tgetzoyan@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('James', 'Blair', 'jblair@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Bradley', 'Larsen', 'blarsen@catalystdevworks.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Jordan', 'Barroga', 'jbarroga@catalystdevworks.com');


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

INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (3, 'Choice');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (3, 'Cambia');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (3, 'Nike');

INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (4, 'PMSI');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (4, 'Vacation Rentals');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (4, 'DB1');

INSERT INTO users (email, first_name, last_name) VALUES ('aedwards@catalystdevworks.com', 'Abby', 'Edwards');
INSERT INTO users (email, first_name, last_name) VALUES ('sannamalai@catalystdevworks.com', 'Shivi', 'Annamalai');
INSERT INTO users (email, first_name, last_name) VALUES ('jjacobson@catalystdevworks.com', 'Josh', 'Jacobson');
INSERT INTO users (email, first_name, last_name) VALUES ('mderfler@catalystdevworks.com', 'Michael', 'Derfler');
INSERT INTO users (email, first_name, last_name) VALUES ('pseng@catalystdevworks.com', 'Pros', 'Seng');
INSERT INTO users (email, first_name, last_name) VALUES ('arajigah@catalystdevworks.com', 'Andrew', 'Rajigah');
INSERT INTO users (email, first_name, last_name) VALUES ('amoss@catalystdevworks.com', 'Alex', 'Moss');
INSERT INTO users (email, first_name, last_name) VALUES ('klafrance@catalystdevworks.com', 'Katy', 'LaFrance');
INSERT INTO users (email, first_name, last_name) VALUES ('jblair@catalystdevworks.com', 'Jimmy', 'Blair');
INSERT INTO users (email, first_name, last_name) VALUES ('blarsen@catalystdevworks.com', 'Brad', 'Larsen');
INSERT INTO users (email, first_name, last_name) VALUES ('jbarroga@catalystdevworks.com', 'Jordan', 'Barroga');
INSERT INTO users (email, first_name, last_name) VALUES ('bfutral@catalystdevworks.com', 'Brett', 'Futral');
INSERT INTO users (email, first_name, last_name) VALUES ('thoffman@catalystdevworks.com', 'Travis', 'Hoffman');
INSERT INTO users (email, first_name, last_name) VALUES ('ppor@catalystdevworks.com', 'Peter', 'Por');
INSERT INTO users (email, first_name, last_name) VALUES ('ssmith@catalystdevworks.com', 'Steve', 'Smith');
INSERT INTO users (email, first_name, last_name) VALUES ('afields@catalystdevworks.com', 'Adam', 'Fields');
INSERT INTO users (email, first_name, last_name) VALUES ('kcoomes@catalystdevworks.com', 'Ken', 'Coomes');
INSERT INTO users (email, first_name, last_name) VALUES ('kslott@catalystdevworks.com', 'Kate', 'Slott');
INSERT INTO users (email, first_name, last_name) VALUES ('bpyl@catalystdevworks.com', 'Brenton', 'Pyl');
INSERT INTO users (email, first_name, last_name) VALUES ('hmccardell@catalystdevworks.com', 'Hayes', 'McCardell II');

INSERT INTO respondent (schedule_id, user_id) VALUES (1, 1);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 1);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 2);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 3);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 4);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 5);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 6);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 7);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 8);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 9);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 10);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 11);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 12);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 13);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 14);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 15);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 16);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 17);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 18);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 19);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 20);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 1);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 5);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 10);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (9, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (9, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (9, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (9, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (10, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (10, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (10, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (10, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (11, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (11, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (11, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (11, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (12, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (12, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (12, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (12, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (13, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (13, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (13, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (13, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (14, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (14, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (14, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (14, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (15, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (15, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (15, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (15, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (16, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (16, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (16, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (16, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (17, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (17, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (17, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (17, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (18, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (18, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (18, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (18, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (19, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (19, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (19, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (19, 11);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (20, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (20, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (20, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (20, 11);
