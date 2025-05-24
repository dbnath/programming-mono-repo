create table TRANSACTION (
  TRANSACTION_ID numeric,
  ACCOUNT_NUM  varchar(50),
  AMOUNT numeric,
  constraint pk_client primary key (TRANSACTION_ID)
);