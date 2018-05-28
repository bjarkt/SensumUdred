DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS haslogin CASCADE;
DROP TABLE IF EXISTS accounts CASCADE;
DROP TABLE IF EXISTS guardians CASCADE;
DROP TABLE IF EXISTS participates CASCADE;
DROP TABLE IF EXISTS meetings CASCADE;
DROP TABLE IF EXISTS worksin CASCADE;
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
  SSN VARCHAR(10) PRIMARY KEY,
  firstName TEXT NOT NULL,
  lastName TEXT NOT NULL,
  phoneNumber VARCHAR(8) DEFAULT '',
  email TEXT DEFAULT ''
);

CREATE TABLE haslogin(
  users_SSN VARCHAR(10),
  accounts_ID BIGINT,
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
  cases_id BIGINT,
  meetings_number INT,
  users_SSN VARCHAR(10),
  PRIMARY KEY (cases_id, meetings_number, users_SSN)
);

CREATE TABLE meetings(
  elucidation_id BIGINT,
  number INT,
  information TEXT,
  date TIMESTAMP,
  creator VARCHAR(10),
  isCancelled BOOLEAN,
  PRIMARY KEY (elucidation_id, number)
);

CREATE TABLE worksin(
  elucidations_ID BIGINT,
  users_SSN VARCHAR(10),
  PRIMARY KEY (elucidations_ID, users_SSN)
);

CREATE TABLE elucidations(
  ID BIGSERIAL PRIMARY KEY ,
  applies_ssn TEXT NOT NULL,
  creationDate TIMESTAMP,
  isclosed BOOLEAN DEFAULT FALSE
);

CREATE TABLE elucidationsHasTasks(
  elucidations_ID BIGINT,
  task_ID BIGINT,
  state TEXT,
  PRIMARY KEY (elucidations_ID, task_ID)
);

CREATE TABLE inquiries(
  task_ID BIGSERIAL PRIMARY KEY,
  source TEXT,
  description TEXT
);

CREATE TABLE cases(
  task_ID BIGSERIAL PRIMARY KEY,
  inquries_source TEXT DEFAULT  '',
  inquries_description TEXT DEFAULT '',
  guardianAuthority TEXT DEFAULT '',
  citizensConsent BOOLEAN DEFAULT false,
  actingMunicipality TEXT DEFAULT '',
  specialCircumstances TEXT DEFAULT '',
  totalLevelOfFunction CHAR DEFAULT ''
);

CREATE TABLE caseHasThirdPartyInformations(
  thirdpartyinformations_number INT,
  cases_ID BIGINT,
  PRIMARY KEY (thirdpartyinformations_number, cases_ID)
);

CREATE TABLE thirdpartyinformations(
  cases_id BIGINT,
  number INT,
  description TEXT,
  source TEXT,
  data BYTEA,
  PRIMARY KEY(cases_id, number)
);

CREATE TABLE caseHasThemes(
  cases_ID BIGSERIAL,
  theme TEXT,
  documentation TEXT,
  PRIMARY KEY (cases_ID, theme)
);

CREATE TABLE themes(
  cases_id BIGINT,
  theme TEXT,
  subtheme TEXT,
  levelOfFunction INT,
  PRIMARY KEY(cases_id, theme)
);

CREATE TABLE offerings(
  cases_id BIGINT,
  description TEXT,
  paragraph INT,
  PRIMARY KEY (cases_id, description)
);

CREATE TABLE grantings(
  cases_ID BIGINT,
  description TEXT,
  PRIMARY KEY (cases_ID, description)
);

CREATE TABLE logs(
  accounts_ID BIGINT,
  sessionID BIGINT,
  changelogs_ID BIGINT,
  eventlogs_ID BIGINT,
  PRIMARY KEY (accounts_ID, sessionID, changelogs_ID, eventlogs_ID)
);

CREATE TABLE changelogs(
  ID BIGSERIAL PRIMARY KEY ,
  now TEXT,
  previous TEXT,
  dateTime TIMESTAMP,
  fieldCode TEXT
);

CREATE TABLE eventlogs(
  ID BIGSERIAL PRIMARY KEY,
  method_name TEXT,
  description TEXT,
  logLevel INTEGER,
  logAction INTEGER,
  dateTime TIMESTAMP
);