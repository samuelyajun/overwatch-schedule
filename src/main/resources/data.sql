INSERT INTO schedule  (end_date, start_date, template_name, template_uri, frequency, isActive) VALUES (null, '2016-08-08', 'Sprint Checkup', '/templates/1', 'ONE_WEEK', 'true');

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



INSERT INTO respondent (schedule_id, user_id) VALUES (1, 1);
INSERT INTO respondent (schedule_id, user_id) VALUES (1, 2);


--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 2);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 3);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 4);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 5);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 6);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 7);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 8);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 9);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 10);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 11);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 12);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 13);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 14);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 15);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 16);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 17);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 18);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 19);
--INSERT INTO respondent (schedule_id, user_id) VALUES (2, 20);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 1);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 5);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 9);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (1, 10);

INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 1);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 5);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 9);
INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 10);

--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (2, 10);
--

--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (3, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (4, 10);

--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (5, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (6, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (7, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (8, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (9, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (9, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (9, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (9, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (10, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (10, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (10, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (10, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (11, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (11, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (11, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (11, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (12, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (12, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (12, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (12, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (13, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (13, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (13, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (13, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (14, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (14, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (14, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (14, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (15, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (15, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (15, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (15, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (16, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (16, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (16, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (16, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (17, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (17, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (17, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (17, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (18, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (18, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (18, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (18, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (19, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (19, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (19, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (19, 10);
--
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (20, 3);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (20, 6);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (20, 9);
--INSERT INTO respondent_allowedattribute (respondent_id, allowedattributes_id) VALUES (20, 10);


INSERT INTO occurrence (generationdate, is_complete, respondent_id) VALUES ('2016-07-11', true, 1);
INSERT INTO occurrence (generationdate, is_complete, respondent_id) VALUES ('2016-07-18', true, 1);
INSERT INTO occurrence (generationdate, is_complete, respondent_id) VALUES ('2016-07-25', true, 1);
INSERT INTO occurrence (generationdate, is_complete, respondent_id) VALUES ('2016-08-01', false, 1);
INSERT INTO occurrence (generationdate, is_complete, respondent_id) VALUES ('2016-08-08', false, 1);
INSERT INTO occurrence (generationdate, is_complete, respondent_id) VALUES ('2016-08-08', false, 2);
