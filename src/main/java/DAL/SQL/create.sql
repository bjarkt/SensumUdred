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
  SSN VARCHAR(10) NOT NULL,
  firstName TEXT NOT NULL,
  lastName TEXT NOT NULL,
  phoneNumber VARCHAR(8),
  email TEXT NOT NULL ,
  PRIMARY KEY (SSN, email)
);

CREATE TABLE haslogin(
  users_SSN VARCHAR(10),
  users_email TEXT,
  accounts_ID BIGSERIAL,
  PRIMARY KEY (users_SSN, users_email, accounts_ID)
);

CREATE TABLE accounts(
  ID BIGSERIAL PRIMARY KEY,
  userName TEXT,
  password_hash TEXT,
  securityLevel INTEGER,
  isLoggedIn BOOLEAN,
  attempts INTEGER,
  dateLastLogin DATE,
  locked BOOLEAN
);

CREATE TABLE guardians(
  citizenSSN  VARCHAR(10),
  guardianSSN VARCHAR(10),
  representation TEXT,
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

INSERT INTO accounts VALUES(-1, 'admin', '$2a$15$uoVqNmySNPyqGB1jVLfnVu54R.JZz4E0I4WOI3fFHxvz3//q0mJw2', 1000, false, 0, null);