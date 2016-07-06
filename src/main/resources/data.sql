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



--some users since we're hardcoding for now
INSERT INTO users (id, first_name, last_name, email) VALUES (1, 'Brenton', 'Pyl', 'bpyl@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (2, 'Travis', 'Hoffman', 'thoffman@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (3, 'Brett', 'Futral', 'bfutral@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (4, 'Steven', 'Smith', 'ssmith@catalystdevworks.com');

INSERT INTO users (id, first_name, last_name, email) VALUES (5, 'Abigail', 'Edwards', 'aedwards@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (6, 'Joshua', 'Jacobson', 'jjacobson@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (7, 'Sivagami', 'Annamalai', 'sannamalai@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (8, 'Hayes', 'McCardell II', 'hmccardell@catalystdevworks.com');

INSERT INTO users (id, first_name, last_name, email) VALUES (9, 'Pros', 'Seng', 'pseng@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (10, 'Kathryn', 'LaFrance', 'klafrance@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (11, 'Andrew', 'Rajigah', 'arajigah@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (12, 'Alexandra', 'Moss', 'amoss@catalystdevworks.com');

INSERT INTO users (id, first_name, last_name, email) VALUES (13, 'Thomas', 'Getzoyan', 'tgetzoyan@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (14, 'James', 'Blair', 'jblair@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (15, 'Bradley', 'Larsen', 'blarsen@catalystdevworks.com');
INSERT INTO users (id, first_name, last_name, email) VALUES (16, 'Jordan', 'Barroga', 'jbarroga@catalystdevworks.com');

