INSERT INTO schedule  (end_date, start_date, survey, frequency) VALUES ('2016-05-15', '2016-04-30', 'surveyURL1', 'ONE_WEEK'); 
INSERT INTO schedule  (end_date, start_date, survey, frequency) VALUES ('2016-07-31', '2016-07-01', 'surveyURL2', 'TWO_WEEKS');  

INSERT INTO days (schedule_id, days_of_week) VALUES (1, 'MONDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (1, 'FRIDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'WEDNESDAY');

INSERT INTO attributetype (name) VALUES ('ROLE');
INSERT INTO attributetype (name) VALUES ('OFFICE');
INSERT INTO attributetype (name) VALUES ('CLIENT');
INSERT INTO attributetype (name) VALUES ('PROJECT');

INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES ( 1, 'DEV');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES ( 1, 'ANALYST');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES ( 1, 'ENGAGEMENT MANAGER');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES ( 1, 'TECH LEAD');

INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES ( 2, 'BALTIMORE');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES ( 2, 'BEAVERTON');

INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (3, 'CHOICE');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (3, 'CAMBIA');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (3, 'NIKE');

INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (4, 'PMSI');
INSERT INTO allowedattribute (attributetype_id, attribute_value) VALUES (4, 'VACATION_RENTALS');
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
