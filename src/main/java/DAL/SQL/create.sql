DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS haslogin CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS guardians CASCADE;
DROP TABLE IF EXISTS participates CASCADE;
DROP TABLE IF EXISTS meetings CASCADE;
DROP TABLE IF EXISTS worksin CASCADE;
DROP TABLE IF EXISTS applies CASCADE;
DROP TABLE IF EXISTS elucidations CASCADE;
DROP TABLE IF EXISTS elucidationsHasTasks CASCADE;
DROP TABLE IF EXISTS inquiries CASCADE;
DROP TABLE IF EXISTS cases CASCADE;
DROP TABLE IF EXISTS caseHasThirdPartyInformations CASCADE;
DROP TABLE IF EXISTS thirdpartyinformations CASCADE;
DROP TABLE IF EXISTS caseHasThemes CASCADE;
DROP TABLE IF EXISTS themes CASCADE;
DROP TABLE IF EXISTS offerings CASCADE;
DROP TABLE IF EXISTS grantings CASCADE;
DROP TABLE IF EXISTS logs CASCADE;
DROP TABLE IF EXISTS changelogs CASCADE;
DROP TABLE IF EXISTS eventlogs CASCADE;

CREATE TABLE users(
  SSN VARCHAR(10) NOT NULL PRIMARY KEY,
  firstName TEXT NOT NULL,
  lastName TEXT NOT NULL,
  phoneNumber VARCHAR(8) DEFAULT '',
  email TEXT DEFAULT ''
);

CREATE TABLE haslogin(
  users_SSN VARCHAR(10),
  accounts_ID BIGSERIAL,
  PRIMARY KEY (users_SSN, accounts_ID)
);

CREATE TABLE accounts(
  ID BIGSERIAL PRIMARY KEY,
  userName TEXT NOT NULL UNIQUE,
  password_hash TEXT NOT NULL,
  securityLevel INTEGER DEFAULT 0,
  isLoggedIn BOOLEAN DEFAULT false,
  islocked BOOLEAN DEFAULT false,
  attempts INTEGER DEFAULT 0,
  dateLastLogin DATE
);

CREATE TABLE guardians(
  citizenSSN  VARCHAR(10),
  guardianSSN VARCHAR(10),
  representation TEXT DEFAULT '',
  PRIMARY KEY(citizenSSN, guardianSSN)
);

CREATE TABLE participates(
  users_SSN VARCHAR(10),
  users_email TEXT,
  meetings_ID BIGSERIAL,
  PRIMARY KEY (users_email, users_SSN, meetings_ID)
);

CREATE TABLE meetings(
  ID BIGSERIAL PRIMARY KEY ,
  information TEXT,
  date DATE,
  creaton VARCHAR(10),
  isCancelled BOOLEAN
);

CREATE TABLE worksin(
  elucidations_ID BIGSERIAL,
  users_SSN VARCHAR(10),
  PRIMARY KEY (elucidations_ID, users_SSN)
);

CREATE TABLE applies(
  elucidations_ID BIGSERIAL,
  users_SSN VARCHAR(10),
  PRIMARY KEY (elucidations_ID, users_SSN)
);

CREATE TABLE elucidations(
  ID BIGSERIAL PRIMARY KEY ,
  creationDate DATE
);

CREATE TABLE elucidationsHasTasks(
  elucidations_ID BIGSERIAL,
  task_ID BIGSERIAL,
  state TEXT
);

CREATE TABLE inquiries(
  task_ID BIGSERIAL PRIMARY KEY,
  source TEXT,
  description TEXT
);

CREATE TABLE cases(
  task_ID BIGSERIAL PRIMARY KEY,
  inquries_description TEXT,
  guardianAuthority TEXT,
  citizensAgreement BOOLEAN,
  specialCircumstances TEXT,
  totalLevelOfFunction CHAR
);

CREATE TABLE caseHasThirdPartyInformations(
  thirdpartyinformations_ID BIGSERIAL,
  cases_ID BIGSERIAL,
  PRIMARY KEY (thirdpartyinformations_ID, cases_ID)
);

CREATE TABLE thirdpartyinformations(
  ID BIGSERIAL PRIMARY KEY,
  description TEXT,
  source TEXT,
  data BYTEA
);

CREATE TABLE caseHasThemes(
  cases_ID BIGSERIAL,
  theme TEXT,
  documentation TEXT,
  PRIMARY KEY (cases_ID, theme)
);

CREATE TABLE themes(
  theme TEXT PRIMARY KEY,
  subtheme TEXT
);

CREATE TABLE offerings(
  cases_ID BIGSERIAL,
  description TEXT,
  paragraph TEXT,
  PRIMARY KEY (cases_ID, description)
);

CREATE TABLE grantings(
  cases_ID BIGSERIAL,
  description TEXT,
  PRIMARY KEY (cases_ID, description)
);

CREATE TABLE logs(
  accounts_ID BIGSERIAL,
  sessionID BIGSERIAL,
  changelogs_ID BIGSERIAL,
  eventlogs_ID BIGSERIAL,
  PRIMARY KEY (accounts_ID, sessionID, changelogs_ID, eventlogs_ID)
);

CREATE TABLE changelogs(
  ID BIGSERIAL PRIMARY KEY ,
  now TEXT,
  previous TEXT,
  dateTime DATE,
  fieldCode TEXT
);

CREATE TABLE eventlogs(
  ID BIGSERIAL PRIMARY KEY,
  description TEXT,
  logLevel INTEGER,
  logAction INTEGER
);

INSERT INTO users VALUES ('00000000', 'Admin', 'Admin', '00000000', 'admin@admin.com');
INSERT INTO haslogin VALUES ('0000000000', 1);
INSERT INTO accounts VALUES(2, 'admin', '$2a$10$0zQ5VoTQFH7uIY0SV6y.aOCgBM63idUYoLuyDRPdqk03.VQPS1otW', 1000, false, false, 0, null);

INSERT INTO users VALUES ('99999999', 'tester', 'tester', '99999999', 'tester@tester.com');
INSERT INTO haslogin VALUES ('99999999', 2);
INSERT INTO accounts VALUES(2, 'tester', '$2a$10$sXLHK59F9z.P5rRVycQ4N.FIwMYdo0pF2Rl10NYNToXD57s7cVBzu', 0, false, false, 0, null);

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