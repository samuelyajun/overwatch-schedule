INSERT INTO schedule  (end_date, start_date, survey, frequency) VALUES ('2016-05-15', '2016-04-30', 'SC', 'ONE_WEEK'); 
INSERT INTO schedule  (end_date, start_date, survey, frequency) VALUES ('2016-07-31', '2016-07-01', 'ST', 'TWO_WEEKS');  

INSERT INTO attributetype (name) VALUES ('Role');
INSERT INTO attributetype (name) VALUES ('Office');
INSERT INTO attributetype (name) VALUES ('Client');
INSERT INTO attributetype (name) VALUES ('Project');

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

INSERT INTO users (email, first_name, last_name) VALUES ('user1@test.com', 'Test1', 'User1');
INSERT INTO users (email, first_name, last_name) VALUES ('user2@test.com', 'Test2', 'User2');
INSERT INTO users (email, first_name, last_name) VALUES ('user3@test.com', 'Test3', 'User3');

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
