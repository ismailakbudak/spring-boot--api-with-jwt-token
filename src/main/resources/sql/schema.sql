CREATE TABLE merchants (
  id INT NOT NULL AUTO_INCREMENT,
  email varchar(150) NOT NULL,
  password varchar(255) NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT users UNIQUE (email)
);

CREATE TABLE acquirer_transactions (
  id INT NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  code varchar(255) NOT NULL,
  type varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE fx_transactions (
  id INT NOT NULL AUTO_INCREMENT,
  originalAmount BIGINT NOT NULL,
  originalCurrency varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE agent_infos (
  id INT NOT NULL AUTO_INCREMENT,
  customerIp varchar(255),
  customerUserAgent varchar(255),
  merchantIp varchar(255),
  merchantUserAgent varchar(255),
  createdAt DATETIME NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE ipn_transactions (
  id INT NOT NULL AUTO_INCREMENT,
  ipnSent BOOLEAN NOT NULL DEFAULT 1,
  ipnReceived BOOLEAN NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
);


CREATE TABLE customer_infos (
  id INT NOT NULL AUTO_INCREMENT,
  number varchar(255) NOT NULL,
  expiryMonth varchar(255) NOT NULL,
  expiryYear varchar(255) NOT NULL,
  startMonth varchar(255),
  startYear varchar(255),
  issueNumber varchar(255),
  email varchar(255) NOT NULL,
  birthday DATETIME,
  gender varchar(255),
  billingTitle varchar(255),
  billingFirstName varchar(255),
  billingLastName varchar(255),
  billingCompany varchar(255),
  billingAddress1 varchar(255),
  billingAddress2 varchar(255),
  billingCity varchar(255),
  billingPostcode varchar(255),
  billingState varchar(255),
  billingCountry varchar(255),
  billingPhone​ varchar(255),
  billingFax varchar(255),
  shippingTitle varchar(255),
  shippingFirstName varchar(255),
  shippingLastName​ varchar(255),
  shippingCompany varchar(255),
  shippingAddress1​ varchar(255),
  shippingAddress2​ varchar(255),
  shippingCity​ varchar(255),
  shippingPostcode​ varchar(255),
  shippingState varchar(255),
  shippingCountry varchar(255),
  shippingPhone varchar(255),
  shippingFax varchar(255),
  token varchar(255),
  createdAt DATETIME NOT NULL,
  updatedAt DATETIME,
  PRIMARY KEY (id)
);

CREATE TABLE transactions (
  id INT NOT NULL AUTO_INCREMENT,
  transactionId varchar(255) NOT NULL,
  referenceNo varchar(255) NOT NULL,
  status varchar(255) NOT NULL,
  channel varchar(255) NOT NULL,
  code varchar(255) NOT NULL,
  amount BIGINT NOT NULL,
  currency varchar(255) NOT NULL,
  customData varchar(255),
  chainId varchar(255) NOT NULL,
  returnUrl varchar(255),
  type varchar(255) NOT NULL,
  operation varchar(255) NOT NULL,
  message varchar(255) NOT NULL,
  refundable BOOLEAN NOT NULL DEFAULT 1,
  ipnTransactionId INT,
  fxTransactionId INT,
  merchantId INT,
  customerInfoId INT,
  agentInfoId INT,
  acquirerTransactionId INT,
  createdAt DATETIME,
  updatedAt DATETIME,
  deletedAt DATETIME,
  PRIMARY KEY (id),
  CONSTRAINT transactions UNIQUE (transactionId)
);