USE test;

SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

/*DROP TABLE IF EXISTS DOCUMENT;
DROP TABLE IF EXISTS DOCUMENT_AUDIT;
DROP TABLE IF EXISTS DOC_REPO;
DROP TABLE IF EXISTS DOC_SUB_TAG_INFO;
DROP TABLE IF EXISTS DOC_TAG_INFO;
DROP TABLE IF EXISTS DOC_TAG_REL;
DROP TABLE IF EXISTS DOC_TAG_REL_AUDIT;
DROP TABLE IF EXISTS DOC_TAG_SUBTAG_MAP;
DROP TABLE IF EXISTS AGREEMENT_TYPE_TAG_MAPPING;
DROP TABLE IF EXISTS DOC_WF_DTL;
DROP TABLE IF EXISTS DOC_WF_DTL_AUDIT;
DROP TABLE IF EXISTS DOC_WF_GRP;*/
DROP TABLE IF EXISTS AGREEMENT_TYPE;
DROP TABLE IF EXISTS WF_STATUS;
DROP TABLE IF EXISTS ERR_REASON;
DROP TABLE IF EXISTS WF_PROCESS;
DROP TABLE IF EXISTS WF_PROCESS_AUDIT;
DROP TABLE IF EXISTS WF_COMMENT;
DROP TABLE IF EXISTS WF_COMMENT_AUDIT;
DROP TABLE IF EXISTS ROLE;
DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS USER_ROLE_MAP;
/*DROP TABLE IF EXISTS WF_GRP_ACTIVITY_MAP;*/
DROP TABLE IF EXISTS ROLE_STATUS_MAPPING;




/* Create Tables */

/*CREATE TABLE DOCUMENT
(
	ID_DOC bigint NOT NULL,
	NM_DOC varchar(200),
	ID_AGREEMENT_TYPE int,
	ID_DOC_REPO int,
	DOC_HYPERLINK varchar(250),
	DOC_LOCATION varchar(250),
	IS_DELETED char(1) DEFAULT 'N',
	IS_VALID_LINK char(1) DEFAULT 'Y',
	CREATED_BY varchar(10),
	CREATION_DT datetime,
	LAST_UPDATED_BY varchar(10),
	LAST_UPDATE_DT datetime,
	PRIMARY KEY (ID_DOC)
);


CREATE TABLE DOCUMENT_AUDIT
(
	ID_DOC bigint NOT NULL,
	ID_VER int NOT NULL,
	NM_DOC varchar(200),
	ID_AGREEMENT_TYPE int,
	ID_DOC_REPO int,
	DOC_HYPERLINK varchar(250),
	DOC_LOCATION varchar(250),
	IS_DELETED char(1) DEFAULT 'N',
	IS_VALID_LINK char(1) DEFAULT 'Y',
	CREATED_BY varchar(10),
	CREATION_DT datetime,
	LAST_UPDATED_BY varchar(10),
	LAST_UPDATE_DT datetime,
	PRIMARY KEY (ID_DOC, ID_VER)
);


CREATE TABLE DOC_REPO
(
	ID_DOC_REPO int NOT NULL,
	TX_DOC_REPO varchar(250),
	PRIMARY KEY (ID_DOC_REPO)
);


CREATE TABLE DOC_SUB_TAG_INFO
(
	ID_DOC_SUB_TAG int NOT NULL,
	TX_DOC_SUB_TAG varchar(250),
	PRIMARY KEY (ID_DOC_SUB_TAG)
);


CREATE TABLE DOC_TAG_INFO
(
	ID_DOC_TAG int NOT NULL,
	TX_DOC_TAG varchar(250),
	PRIMARY KEY (ID_DOC_TAG)
);


CREATE TABLE DOC_TAG_REL
(
	ID_DOC bigint NOT NULL,
	ID_AGREEMENT_TYPE int NOT NULL,
	ID_DOC_TAG int,
	ID_DOC_SUB_TAG int,
	CREATED_BY varchar(10),
	CREATION_DT datetime,
	LAST_UPDATED_BY varchar(10),
	LAST_UPDATE_DT datetime,
	PRIMARY KEY (ID_DOC, ID_AGREEMENT_TYPE, ID_DOC_TAG, ID_DOC_SUB_TAG)
);


CREATE TABLE DOC_TAG_REL_AUDIT
(
	ID_DOC bigint NOT NULL,
	ID_VER int NOT NULL,
	ID_AGREEMENT_TYPE int NOT NULL,
	ID_DOC_TAG int,
	ID_DOC_SUB_TAG int,
	CREATED_BY varchar(10),
	CREATION_DT datetime,
	LAST_UPDATED_BY varchar(10),
	LAST_UPDATE_DT datetime,
	PRIMARY KEY (ID_DOC, ID_VER, ID_AGREEMENT_TYPE, ID_DOC_TAG, ID_DOC_SUB_TAG)
);


CREATE TABLE DOC_TAG_SUBTAG_MAP
(
	ID_DOC_TAG int NOT NULL,
	ID_DOC_SUB_TAG int NOT NULL,
	PRIMARY KEY (ID_DOC_TAG, ID_DOC_SUB_TAG)
);*/


CREATE TABLE AGREEMENT_TYPE
(
	ID_AGREEMENT_TYPE int NOT NULL,
	TX_AGREEMENT_TYPE varchar(250),
	PRIMARY KEY (ID_AGREEMENT_TYPE)
);


/*CREATE TABLE AGREEMENT_TYPE_TAG_MAPPING
(
	ID_AGREEMENT_TYPE int NOT NULL,
	ID_DOC_TAG int NOT NULL,
	PRIMARY KEY (ID_AGREEMENT_TYPE, ID_DOC_TAG)
);


CREATE TABLE DOC_WF_DTL
(
	ID_DOC bigint NOT NULL,
	TX_OVRWRT_REASON varchar(250),
	TGT_LOCATION varchar(250),
	LAST_UPDATED_BY varchar(10),
	LAST_UPDATE_DT datetime,
	PRIMARY KEY (ID_DOC)
);


CREATE TABLE DOC_WF_DTL_AUDIT
(
	ID_DOC bigint NOT NULL,
	ID_VER int NOT NULL,
	TX_OVRWRT_REASON varchar(250),
	TGT_LOCATION varchar(250),
	LAST_UPDATED_BY varchar(10),
	LAST_UPDATE_DT datetime,
	PRIMARY KEY (ID_DOC, ID_VER)
);


CREATE TABLE DOC_WF_GRP
(
	ID_WF_GRP int NOT NULL,
	NM_WF_GRP varchar(25),
	PRIMARY KEY (ID_WF_GRP)
);*/


CREATE TABLE WF_STATUS
(
	ID_WF_STATUS int NOT NULL,
	NM_WF_STATUS varchar(25),
	TX_WF_STATUS varchar(250),
	IS_CLOCK_START char(1),
	IS_CLOCK_STOP char(1),
	PRIMARY KEY (ID_WF_STATUS)
);

CREATE TABLE ERR_REASON
(
	ID_ERR_REASON int NOT NULL,
	NM_ERR_REASON varchar(25),
	TX_ERR_REASON varchar(250),
	PRIMARY KEY (ID_ERR_REASON)
);


CREATE TABLE WF_PROCESS
(
	-- Agreement ID
	ID_AGRMT bigint NOT NULL COMMENT 'Agreement ID',
	-- Agreement Type
	ID_AGREEMENT_TYPE int NOT NULL COMMENT 'Agreement Type',
	-- LOB
	LOB varchar(10) null COMMENT 'LOB',
	-- Workflow Status ID
	ID_WF_STATUS int COMMENT 'Workflow Status ID',
	-- User who initiated the workflow
	CREATED_BY varchar(10) COMMENT 'User who initiated the workflow',
	-- Initiation date time
	CREATED_DT datetime COMMENT 'Initiation date time',
	-- Assigned to
	ASSIGNED_TO varchar(10) null COMMENT 'User to whom workflow is assigned',	
	-- Last Updated by
	LAST_UPDATED_BY varchar(10) COMMENT 'Last Updated by',
	-- Last Updated Timestamp
	LAST_UPDATE_DT datetime COMMENT 'Last Updated Timestamp',
	PRIMARY KEY (ID_AGRMT)
);


CREATE TABLE WF_PROCESS_AUDIT
(
	-- Agreement ID
	ID_AGRMT bigint NOT NULL COMMENT 'Agreement ID',
	-- Version ID
	ID_VER int NOT NULL COMMENT 'Version Id',
	-- Agreement Type
	ID_AGREEMENT_TYPE int NOT NULL COMMENT 'Agreement Type',
	-- LOB
	LOB varchar(10) null COMMENT 'LOB',
	-- Workflow Status ID
	ID_WF_STATUS int COMMENT 'Workflow Status ID',
	-- User who initiated the workflow
	CREATED_BY varchar(10) COMMENT 'User who initiated the workflow',
	-- Initiation date time
	CREATED_DT datetime COMMENT 'Initiation date time',
	-- Assigned to
	ASSIGNED_TO varchar(10) null COMMENT 'User to whom workflow is assigned',	
	-- Last Updated by
	LAST_UPDATED_BY varchar(10) COMMENT 'Last Updated by',
	-- Last Updated Timestamp
	LAST_UPDATE_DT datetime COMMENT 'Last Updated Timestamp',
	PRIMARY KEY (ID_AGRMT,ID_VER)
);

CREATE TABLE WF_COMMENT
(
	-- Agreement ID
	ID_AGRMT bigint NOT NULL COMMENT 'Agreement ID',
	-- User Comments
	COMMENTS varchar(255) NULL COMMENT 'User Comments',
	-- User Role
	ID_ROLE int NOT NULL COMMENT 'User Role' ,
	-- Error REason
	ID_ERR_REASON int NOT NULL COMMENT 'Error Reason',
	-- Workflow Status ID
	ID_WF_STATUS int COMMENT 'Workflow Status ID',
	-- Last Updated by
	LAST_UPDATED_BY varchar(10) COMMENT 'Last Updated by',
	-- Last Updated Timestamp
	LAST_UPDATE_DT datetime COMMENT 'Last Updated Timestamp',
	PRIMARY KEY (ID_AGRMT)
);

CREATE TABLE WF_COMMENT_AUDIT
(
	-- Agreement ID
	ID_AGRMT bigint NOT NULL COMMENT 'Agreement ID',
	-- Version ID
	ID_VER int NOT NULL COMMENT 'Version Id',	
	-- User Comments
	COMMENTS varchar(255) NULL COMMENT 'User Comments',
	-- User Role
	ID_ROLE int NOT NULL COMMENT 'User Role' ,
	-- Error REason
	ID_ERR_REASON int NOT NULL COMMENT 'Error Reason',
	-- Workflow Status ID
	ID_WF_STATUS int COMMENT 'Workflow Status ID',
	-- Last Updated by
	LAST_UPDATED_BY varchar(10) COMMENT 'Last Updated by',
	-- Last Updated Timestamp
	LAST_UPDATE_DT datetime COMMENT 'Last Updated Timestamp',
	PRIMARY KEY (ID_AGRMT,ID_VER)
);


CREATE TABLE ROLE
(
	ID_ROLE int NOT NULL,
	NM_ROLE varchar(250) NOT NULL,
	TX_ROLE varchar(250) NOT NULL,
	PRIMARY KEY (ID_ROLE)
);

CREATE TABLE ROLE_STATUS_MAPPING
(
	ID_ROLE int NOT NULL,
	ID_WF_STATUS int NOT NULL,
	PRIMARY KEY (ID_ROLE,ID_WF_STATUS)
);


CREATE TABLE USER
(
	ID_USER varchar(10) NOT NULL,
	NM_USER varchar(255) NOT NULL,
	PASSWORD varchar(255) NOT NULL,
	PASSWORD_TYPE varchar(4) NOT NULL,
	USER_STATUS varchar(20) NOT NULL,
	CREATED_BY varchar(50) NOT NULL,
	CREATION_DT datetime NOT NULL,
	LAST_LOGIN datetime NOT NULL,
	UPDATED_BY varchar(50) NOT NULL,
	UPDATE_DT datetime NOT NULL,
	PRIMARY KEY (ID_USER)
);

CREATE TABLE USER_ROLE_MAP
(
	ID_USER varchar(10) NOT NULL,
	ID_ROLE int NOT NULL,
	CREATED_BY varchar(50) NOT NULL,
	CREATION_DT datetime NOT NULL,
	UPDATED_BY varchar(50) NOT NULL,
	UPDATE_DT datetime NOT NULL,
	PRIMARY KEY (ID_USER, ID_ROLE)
);

/*CREATE TABLE WF_GRP_ACTIVITY_MAP
(
	ID_WF_GRP int NOT NULL,
	ID_WF_STATUS int NOT NULL,
	TX_ACTIVITY_DESC varchar(250),
	PRIMARY KEY (ID_WF_GRP, ID_WF_STATUS)
);*/



