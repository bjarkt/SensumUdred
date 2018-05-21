DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS guardians CASCADE;
DROP TABLE IF EXISTS worksin CASCADE;
DROP TABLE IF EXISTS applies CASCADE;
DROP TABLE IF EXISTS elucidations CASCADE;
DROP TABLE IF EXISTS hastasks CASCADE;
DROP TABLE IF EXISTS tasks CASCADE;
DROP TABLE IF EXISTS inqueries CASCADE;
DROP TABLE IF EXISTS cases CASCADE;
DROP TABLE IF EXISTS casehasthirdpartyinformations CASCADE;
DROP TABLE IF EXISTS casehasthemes CASCADE;
DROP TABLE IF EXISTS themes CASCADE;
DROP TABLE IF EXISTS offerings CASCADE;
DROP TABLE IF EXISTS grantings CASCADE;
DROP TABLE IF EXISTS logs CASCADE;
DROP TABLE IF EXISTS changelogs CASCADE;
DROP TABLE IF EXISTS eventlogs CASCADE;
DROP TABLE IF EXISTS eventlogs CASCADE;

CREATE TABLE users(
  SSN VARCHAR(10) NOT NULL,
  firstName TEXT NOT NULL,
  lastName TEXT NOT NULL,
  phoneNumber VARCHAR(8),
  email TEXT NOT NULL ,
  PRIMARY KEY (SSN, email)
);

CREATE TABLE guardians(
  citizenSSN  VARCHAR(10),
  guardianSSN VARCHAR(10),
  representation TEXT,
  PRIMARY KEY(citizenSSN, guardianSSN)
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
  PRIMARY KEY (ID, cases_ID)
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
  
);

