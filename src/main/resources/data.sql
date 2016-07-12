INSERT INTO schedule  (id, end_date, start_date, survey, frequency) VALUES (1, '2016-05-15', '2016-04-30', 'surveyURL1', 'ONE_WEEK'); 
INSERT INTO schedule  (id, end_date, start_date, survey, frequency) VALUES (2, '2016-07-31', '2016-07-01', 'surveyURL2', 'TWO_WEEKS');  

INSERT INTO days (schedule_id, days_of_week) VALUES (1, 'MONDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (1, 'FRIDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'WEDNESDAY');

INSERT INTO attributetype (id, name) VALUES (1, 'ROLE');
INSERT INTO attributetype (id, name) VALUES (2, 'OFFICE');
INSERT INTO attributetype (id, name) VALUES (3, 'CLIENT');
INSERT INTO attributetype (id, name) VALUES (4, 'PROJECT');

INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (1, 1, 'DEV');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (2, 1, 'ANALYST');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (3, 1, 'ENGAGEMENT MANAGER');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (4, 1, 'TECH LEAD');

INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (5, 2, 'BALTIMORE');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (6, 2, 'BEAVERTON');

INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (7, 3, 'CHOICE');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (8, 3, 'CAMBIA');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (9, 3, 'NIKE');

INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (10, 4, 'PMSI');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (11, 4, 'VACATION_RENTALS');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (12, 4, 'DB1');

INSERT INTO users (id, email, first_name, last_name) VALUES (1, 'user1@test.com', 'Test1', 'User1');
INSERT INTO users (id, email, first_name, last_name) VALUES (2, 'user2@test.com', 'Test2', 'User2');
INSERT INTO users (id, email, first_name, last_name) VALUES (3, 'user3@test.com', 'Test3', 'User3');

INSERT INTO respondent (id, schedule_id, user_id) VALUES (1, 1, 1);
INSERT INTO respondent (id, schedule_id, user_id) VALUES (2, 2, 2);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 1);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 5);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 7);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 10);
