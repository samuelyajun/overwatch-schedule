/*INSERT INTO schedule  (id, end_date, start_date) VALUES (1, null, '2016-07-01');
INSERT INTO schedule  (id, end_date, start_date) VALUES (2, '2016-07-31', '2016-07-01');
INSERT INTO schedule  (id, end_date, start_date) VALUES (3, '2016-12-31', '2016-08-01');
INSERT INTO schedule  (id, end_date, start_date) VALUES (4, null, '2016-08-01');
INSERT INTO schedule  (id, end_date, start_date) VALUES (5, null, '2016-08-01');*/
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
/*INSERT INTO days (schedule_id, days_of_week) VALUES (3, 'FRIDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (4, 'MONDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (5, 'MONDAY');*/

/*INSERT INTO frequency (schedule_id, survey_frequency) VALUES (1, 'ONE_WEEK');
INSERT INTO frequency (schedule_id, survey_frequency) VALUES (2, 'TWO_WEEKS');*/

INSERT INTO attributetypes (attribute_type_id, name) VALUES (1, 'ROLE');
INSERT INTO attributetypes (attribute_type_id, name) VALUES (2, 'OFFICE');
INSERT INTO attributetypes (attribute_type_id, name) VALUES (3, 'CLIENT');
INSERT INTO attributetypes (attribute_type_id, name) VALUES (4, 'PROJECT');

INSERT INTO allowedattributes (id, attribute_value) VALUES (1, 'DEV');
INSERT INTO allowedattributes (id, attribute_value) VALUES (2, 'BEAVERTON');
INSERT INTO allowedattributes (id, attribute_value) VALUES (3, 'CHOICE');
INSERT INTO allowedattributes (id, attribute_value) VALUES (4, 'PMSI');

INSERT INTO users (id, email, first_name, last_name) VALUES (1, 'user1@test.com', 'Test', 'User1');
INSERT INTO users (id, email, first_name, last_name) VALUES (2, 'user2@test.com', 'Test', 'User2');

INSERT INTO occurrence (id, schedule_id, email, create_date, occurrence_number, time_to_live)
                        VALUES (1, 1, 'user1@test.com', '2016-06-30', 1, '2016-08-28T16:00:49.455');
INSERT INTO occurrence (id, schedule_id, email, create_date, occurrence_number, time_to_live)
                        VALUES (2, 2, 'user2@test.com', '2016-05-15', 2, '2016-09-18T16:00:49.455');
                        
/*INSERT INTO scheduleattributes(id, schedule_id, attribute_id, type_id)
                                VALUES (1, 1, 1, 1);
INSERT INTO scheduleattributes(id, schedule_id, attribute_id, type_id)
                                VALUES (2, 2, 2, 2);*/          

