INSERT INTO schedule  (end_date, start_date, survey, frequency) VALUES ('2016-05-15', '2016-04-30', 'SC', 'ONE_WEEK'); 
INSERT INTO schedule  (end_date, start_date, survey, frequency) VALUES ('2016-07-31', '2016-07-01', 'ST', 'TWO_WEEKS');

--some users since we're hardcoding for now
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

INSERT INTO respondent (schedule_id, user_id) VALUES (1, 1);
INSERT INTO respondent (schedule_id, user_id) VALUES (2, 2);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 1);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 5);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 10);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 3);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 6);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 11);
