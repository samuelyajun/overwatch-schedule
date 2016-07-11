INSERT INTO schedule  (id, end_date, start_date, survey, frequency) VALUES (1, '2016-05-15', '2016-04-30', 'surveyURL', 'ONE_WEEK'); 
INSERT INTO schedule  (id, end_date, start_date, survey, frequency) VALUES (2, '2016-07-31', '2016-07-01', 'surveyURL', 'TWO_WEEKS');  

INSERT INTO days (schedule_id, days_of_week) VALUES (1, 'MONDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (1, 'FRIDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'MONDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'TUESDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'WEDNESDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'THURSDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'SATURDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'SUNDAY');


INSERT INTO attributetype (id, name) VALUES (1, 'ROLE');
INSERT INTO attributetype (id, name) VALUES (2, 'OFFICE');
INSERT INTO attributetype (id, name) VALUES (3, 'CLIENT');
INSERT INTO attributetype (id, name) VALUES (4, 'PROJECT');

INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (1, 1, 'DEV');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (2, 2, 'BEAVERTON');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (3, 3, 'CHOICE');
INSERT INTO allowedattribute (id, attributetype_id, attribute_value) VALUES (4, 4, 'PMSI');

INSERT INTO users (id, email, first_name, last_name) VALUES (1, 'user1@test.com', 'Test', 'User1');
INSERT INTO users (id, email, first_name, last_name) VALUES (2, 'user2@test.com', 'Test', 'User2');

INSERT INTO respondent (id, schedule_id, user_id) VALUES (1, 1, 1);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 1);
