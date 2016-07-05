INSERT INTO schedule  (id, end_date, start_date) VALUES (1, null, '2016-07-01');
INSERT INTO schedule  (id, end_date, start_date) VALUES (2, '2016-07-31', '2016-07-01');
INSERT INTO schedule  (id, end_date, start_date) VALUES (3, '2016-12-31', '2016-08-01');
INSERT INTO schedule  (id, end_date, start_date) VALUES (4, null, '2016-08-01');
INSERT INTO schedule  (id, end_date, start_date) VALUES (5, null, '2016-08-01');

INSERT INTO days (schedule_id, days_of_week) VALUES (1, 'MONDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (1, 'FRIDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'MONDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'TUESDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'WEDNESDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'THURSDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'SATURDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (2, 'SUNDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (3, 'FRIDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (4, 'MONDAY');
INSERT INTO days (schedule_id, days_of_week) VALUES (5, 'MONDAY');