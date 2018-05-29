-- MUST FOR DATABASE JUNIT TESTING --

INSERT INTO users VALUES ('00000000', 'Admin', 'Admin', '00000000', 'admin@admin.com');
INSERT INTO haslogin VALUES ('0000000000', 10000);
INSERT INTO accounts VALUES(10000, 'admin', '$2a$10$0zQ5VoTQFH7uIY0SV6y.aOCgBM63idUYoLuyDRPdqk03.VQPS1otW', 1000, false, false, 0, null);

INSERT INTO users VALUES ('99999999', 'tester', 'tester', '99999999', 'tester@tester.com');
INSERT INTO haslogin VALUES ('99999999', 10001);
INSERT INTO accounts VALUES(10001, 'tester', '$2a$10$sXLHK59F9z.P5rRVycQ4N.FIwMYdo0pF2Rl10NYNToXD57s7cVBzu', 0, false, false, 0, null);

-- Users and Accounts for CASEWORKERS

INSERT INTO users VALUES ('1103971427', 'Lasse', 'Traberg-Larsen', '28499228', 'lasse@traberg-larsen.dk');
INSERT INTO haslogin VALUES ('1103971427', 10002);
INSERT INTO accounts VALUES(10002, 'lasse', '$2a$10$tfYqKXCgmHFyBtHisQLJ6uWEc8eLeF3mc8zDSchnhThDKpj/VHKxO', 1000, false, false, 0, null);

INSERT INTO users VALUES ('2705973047', 'Bjarke', 'Tobiesen', '', 'blarke@tobiesenet.com');
INSERT INTO haslogin VALUES ('2705973047', 10003);
INSERT INTO accounts VALUES(10003, 'bjarke', '$2a$10$dCZFD3vrEyDWierWbBW09.AhPaGNrKg9dkfS/UUOH3e1xZF0OjIEG', 1000, false, false, 0, null);

INSERT INTO users VALUES ('0112982851', 'Lavan', 'Sathiyaseelan', '', 'lavansathi@gmail.com');
INSERT INTO haslogin VALUES ('0112982851', 10004);
INSERT INTO accounts VALUES(10004, 'lavan', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 1000, false, false, 0, null);

INSERT INTO users VALUES ('0610970819', 'Dennis', 'Petersen', '', 'dennistp@hotmail.dk');
INSERT INTO haslogin VALUES ('0610970819', 10005);
INSERT INTO accounts VALUES(10005, 'dennis', '$2a$10$Jg3ovmsApjj8yWpdAUR3ruX1S8m/do07oNLYv6kAD7naksiW1AfHq', 1000, false, false, 0, null);

INSERT INTO users VALUES ('1501982132', 'Christian', 'Aaby', '', 'christian@christian.com');
INSERT INTO haslogin VALUES ('1501982132', 10006);
INSERT INTO accounts VALUES(10006, 'christian', '$2a$10$pUJoeK62U1mB5AoxU4NY9.hVvOYb.CMW/IGRD5Vc3T.u.rP8ILog6', 1000, false, false, 0, null);

INSERT INTO users VALUES ('2802972051', 'Adrian', 'Navntoft', '', 'adrian@adrian.com');
INSERT INTO haslogin VALUES ('2802972051', 10007);
INSERT INTO accounts VALUES(10007, 'adrian', '$2a$10$823Zj8H9Qd5syYxfEkxLjO6V6OLKyRIcIgRRqeQN6QlBE6U84Fu/2', 1000, false, false, 0, null);

-- Users and Accounts for CITIZENS

INSERT INTO users VALUES ('12345678', 'Frank', 'Poulsen', '', 'frank@poulsen.com');
INSERT INTO haslogin VALUES ('12345678', 10008);
INSERT INTO accounts VALUES(10008, 'frankpoulsen', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 0, null);

INSERT INTO users VALUES ('87654321', 'Michael', 'Lordison', '', 'michael@lordison.com');
INSERT INTO haslogin VALUES ('87654321', 10009);
INSERT INTO accounts VALUES(10009, 'michaellordison', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 500, null);

INSERT INTO users VALUES ('11223344', 'Jacob', 'Erikson', '', 'jacob@erikson.com');
INSERT INTO haslogin VALUES ('12345678', 100010);
INSERT INTO accounts VALUES(100010, 'jacoberikson', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 0, null);

INSERT INTO users VALUES ('44332211', 'Katrine', 'Havgaard', '', 'katrine@havgaard.com');
INSERT INTO haslogin VALUES ('12345678', 10011);
INSERT INTO accounts VALUES(10011, 'katrinehavgaard', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 0, null);

INSERT INTO users VALUES ('55667788', 'Frederikke', 'Hous', '', 'frederikke@hous.com');
INSERT INTO haslogin VALUES ('12345678', 10012);
INSERT INTO accounts VALUES(10012, 'frederikkehous', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 0, null);

INSERT INTO users VALUES ('88776655', 'Kim', 'Ingeman', '', 'fim@ingeman.com');
INSERT INTO haslogin VALUES ('12345678', 10013);
INSERT INTO accounts VALUES(10013, 'kimingeman', '$2a$10$IcK4B09228.mbfqJBwlUq.SSxaRkmk405yqcKpETSkHboPa1qNZmS', 0, false, false, 500, null);

-- TEST DATA

-- 1

INSERT INTO elucidations(id, applies_ssn, creationdate, isclosed) VALUES (100000, '12345678', '2018-05-24 09:00:00', false);
INSERT INTO inquiries(task_id, source, description) VALUES (100000, 'En mand ved navn Dennis fortalte om ham selv og at han mangler hjælp.', 'Dennis er en mand med mange mentale problemer. Personen tanker er i et andet univers, han lever i en verden med magi og drager.');
INSERT INTO elucidationshastasks(elucidations_id, task_id, state) VALUES (100000, 100000, 'CASE');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (100000, '1103971427');
INSERT into casehasthemes(cases_id, theme, documentation) VALUES (100000, 'Testtema', 'Dette er en dokumentation');
INSERT INTO themes(cases_id, theme, subtheme, leveloffunction) VALUES (100000, 'Testtema', 'Testsubtema', 4);

INSERT INTO meetings(elucidation_id, number, information, date, creator, iscancelled) VALUES (100000, 1, 'Meeting information', 'today', '90909090', false);
INSERT INTO meetings(elucidation_id, number, information, date, creator, iscancelled) VALUES (100000, 2, 'Meeting information', 'tomorrow', '80808080', false);

INSERT INTO participates(cases_id, meetings_number, users_ssn) VALUES (100000, 1, '60606060');
INSERT INTO participates(cases_id, meetings_number, users_ssn) VALUES (100000, 1, '90909090');
INSERT INTO participates(cases_id, meetings_number, users_ssn) VALUES (100000, 2, '60606060');
INSERT INTO participates(cases_id, meetings_number, users_ssn) VALUES (100000, 2, '80808080');

 -- 2

INSERT INTO elucidations(id, applies_ssn, creationdate, isclosed) VALUES (200000, '55667788', '2018-05-24 09:00:00', false);
INSERT INTO inquiries(source, description) VALUES ('source ...', 'description');
INSERT INTO elucidationshastasks(elucidations_id, task_id, state) VALUES (200000, 200000, 'INQUIRY');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (200000, '87654321');

-- 3

INSERT INTO elucidations(id, applies_ssn, creationdate, isclosed) VALUES (300000, '44332211', '2018-05-24 11:00:00', false);
INSERT INTO inquiries(source, description) VALUES ('source ...', 'description');
INSERT INTO elucidationshastasks(elucidations_id, task_id, state) VALUES (300000, 300000, 'INQUIRY');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (300000, '88776655');

-- 4

INSERT INTO elucidations(id, applies_ssn, creationdate, isclosed) VALUES (400000, '12345678', '2018-05-24 12:00:00', false);
INSERT INTO inquiries(source, description) VALUES ('source ...', 'description');
INSERT INTO elucidationshastasks(elucidations_id, task_id, state) VALUES (400000, 400000, 'INQUIRY');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (400000, '88776655');
INSERT INTO worksin(elucidations_id, users_ssn) VALUES (400000, '87654321');