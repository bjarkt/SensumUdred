-- MUST FOR DATABASE JUNIT TESTING --

INSERT INTO users VALUES ('00000000', 'Admin', 'Admin', '00000000', 'admin@admin.com');
INSERT INTO haslogin VALUES ('0000000000', 1);
INSERT INTO accounts VALUES(1, 'admin', '$2a$10$0zQ5VoTQFH7uIY0SV6y.aOCgBM63idUYoLuyDRPdqk03.VQPS1otW', 1000, false, false, 0, null);

INSERT INTO users VALUES ('99999999', 'tester', 'tester', '99999999', 'tester@tester.com');
INSERT INTO haslogin VALUES ('99999999', 2);
INSERT INTO accounts VALUES(2, 'tester', '$2a$10$sXLHK59F9z.P5rRVycQ4N.FIwMYdo0pF2Rl10NYNToXD57s7cVBzu', 0, false, false, 0, null);

-- Accounts for each member

INSERT INTO users VALUES ('90909090', 'Lasse', 'Bubbibjørn', '80808080', 'lasse@lasse.com');
INSERT INTO haslogin VALUES ('90909090', 3);
INSERT INTO accounts VALUES(3, 'lasse', '$2a$10$tfYqKXCgmHFyBtHisQLJ6uWEc8eLeF3mc8zDSchnhThDKpj/VHKxO', 1000, false, false, 0, null);

INSERT INTO users VALUES ('80808080', 'Bjarke', 'Pingvinbjørn', '', 'bjarke@bjarke.com');
INSERT INTO haslogin VALUES ('80808080', 4);
INSERT INTO accounts VALUES(4, 'bjarke', '$2a$10$dCZFD3vrEyDWierWbBW09.AhPaGNrKg9dkfS/UUOH3e1xZF0OjIEG', 1000, false, false, 0, null);

INSERT INTO users VALUES ('70707070', 'Lavan', 'Højbjørn', '', 'lavan@lavan.com');
INSERT INTO haslogin VALUES ('70707070', 5);
INSERT INTO accounts VALUES(5, 'lavan', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 1000, false, false, 0, null);

INSERT INTO users VALUES ('60606060', 'Dennis', 'Isbjørn', '', 'dennis@dennis.com');
INSERT INTO haslogin VALUES ('60606060', 6);
INSERT INTO accounts VALUES(6, 'dennis', '$2a$10$Jg3ovmsApjj8yWpdAUR3ruX1S8m/do07oNLYv6kAD7naksiW1AfHq', 1000, false, false, 0, null);

INSERT INTO users VALUES ('50505050', 'Christian', 'Spisebjørn', '', 'christian@christian.com');
INSERT INTO haslogin VALUES ('50505050', 7);
INSERT INTO accounts VALUES(7, 'christian', '$2a$10$pUJoeK62U1mB5AoxU4NY9.hVvOYb.CMW/IGRD5Vc3T.u.rP8ILog6', 1000, false, false, 0, null);

INSERT INTO users VALUES ('40404040', 'Adrian', 'Cykelbjørn', '', 'adrian@adrian.com');
INSERT INTO haslogin VALUES ('40404040', 8);
INSERT INTO accounts VALUES(8, 'adrian', '$2a$10$823Zj8H9Qd5syYxfEkxLjO6V6OLKyRIcIgRRqeQN6QlBE6U84Fu/2', 1000, false, false, 0, null);

-- TEST USERS

INSERT INTO users VALUES ('12345678', 'Frank', 'Poulsen', '', 'frank@poulsen.com');
INSERT INTO haslogin VALUES ('12345678', 9);
INSERT INTO accounts VALUES(9, 'frankpoulsen', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 0, null);

INSERT INTO users VALUES ('87654321', 'Michael', 'Lordison', '', 'michael@lordison.com');
INSERT INTO haslogin VALUES ('87654321', 10);
INSERT INTO accounts VALUES(10, 'michaellordison', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 500, null);

INSERT INTO users VALUES ('11223344', 'Jacob', 'Erikson', '', 'jacob@erikson.com');
INSERT INTO haslogin VALUES ('12345678', 11);
INSERT INTO accounts VALUES(11, 'jacoberikson', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 0, null);

INSERT INTO users VALUES ('44332211', 'Katrine', 'Havgaard', '', 'katrine@havgaard.com');
INSERT INTO haslogin VALUES ('12345678', 12);
INSERT INTO accounts VALUES(12, 'katrinehavgaard', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 0, null);

INSERT INTO users VALUES ('55667788', 'Frederikke', 'Hous', '', 'frederikke@hous.com');
INSERT INTO haslogin VALUES ('12345678', 13);
INSERT INTO accounts VALUES(13, 'frederikkehous', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 0, null);

INSERT INTO users VALUES ('88776655', 'Kim', 'Ingeman', '', 'fim@ingeman.com');
INSERT INTO haslogin VALUES ('12345678', 14);
INSERT INTO accounts VALUES(14, 'kimingeman', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 500, null);

-- TEST DATA

-- 1

INSERT INTO elucidations(id, applies_ssn, creationdate, isclosed) VALUES (1, '60606060', '2018-05-24 09:00:00', false);
INSERT INTO inquiries(source, description) VALUES ('En mand ved navn Dennis fortalte om ham selv og at han mangler hjælp.', 'Dennis er en mand med mange mentale problemer. Personen tanker er i et andet univers, han lever i en verden med magi og drager.');
INSERT INTO elucidationshastasks(elucidations_id, task_id, state) VALUES (1, 1, 'INQUIRY');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (1, '90909090');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (1, '80808080');

INSERT INTO meetings(elucidation_id, number, information, date, creator, iscancelled) VALUES (1, 1, 'Meeting information', 'today', '90909090', false);
INSERT INTO meetings(elucidation_id, number, information, date, creator, iscancelled) VALUES (1, 2, 'Meeting information', 'tomorrow', '80808080', false);

INSERT INTO participates(cases_id, meetings_number, users_ssn) VALUES (1, 1, '60606060');
INSERT INTO participates(cases_id, meetings_number, users_ssn) VALUES (1, 1, '90909090');
INSERT INTO participates(cases_id, meetings_number, users_ssn) VALUES (1, 2, '60606060');
INSERT INTO participates(cases_id, meetings_number, users_ssn) VALUES (1, 2, '80808080');

 -- 2

INSERT INTO elucidations(id, applies_ssn, creationdate, isclosed) VALUES (2, '55667788', '2018-05-24 09:00:00', false);
INSERT INTO inquiries(source, description) VALUES ('source ...', 'description');
INSERT INTO elucidationshastasks(elucidations_id, task_id, state) VALUES (2, 2, 'INQUIRY');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (2, '87654321');

-- 3

INSERT INTO elucidations(id, applies_ssn, creationdate, isclosed) VALUES (3, '44332211', '2018-05-24 11:00:00', false);
INSERT INTO inquiries(source, description) VALUES ('source ...', 'description');
INSERT INTO elucidationshastasks(elucidations_id, task_id, state) VALUES (3, 3, 'INQUIRY');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (3, '88776655');

-- 4

INSERT INTO elucidations(id, applies_ssn, creationdate, isclosed) VALUES (4, '12345678', '2018-05-24 12:00:00', false);
INSERT INTO inquiries(source, description) VALUES ('source ...', 'description');
INSERT INTO elucidationshastasks(elucidations_id, task_id, state) VALUES (4, 4, 'INQUIRY');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (4, '88776655');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (4, '87654321');