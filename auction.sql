
CREATE TABLE AUCTION_ALERTS
(
  USERID          VARCHAR2(20 BYTE)             NOT NULL,
  MAX_AUCTIONAMT  NUMBER(10,5),
  PRODUCT_ITEM    VARCHAR2(15 BYTE)             NOT NULL
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE CATEGORY_MASTER
(
  CATID     VARCHAR2(10 BYTE),
  CAT_NAME  VARCHAR2(20 BYTE)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE UINFO_MASTER
(
  UNAME    VARCHAR2(20 BYTE),
  PWD      VARCHAR2(10 BYTE)                    NOT NULL,
  FNAME    VARCHAR2(10 BYTE),
  LNAME    VARCHAR2(10 BYTE),
  EMAIL    VARCHAR2(25 BYTE)                    NOT NULL,
  PHNO     VARCHAR2(15 BYTE),
  ADDRESS  VARCHAR2(50 BYTE),
  CITY     VARCHAR2(15 BYTE),
  STATE    VARCHAR2(15 BYTE),
  PIN      VARCHAR2(6 BYTE),
  COUNTRY  VARCHAR2(20 BYTE),
  CCARDNO  NUMBER(16)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE ITEM_MASTER
(
  ITEMID       VARCHAR2(6 BYTE),
  CATID        VARCHAR2(10 BYTE),
  ITEMNAME     VARCHAR2(20 BYTE),
  DESCRIPTION  VARCHAR2(200 BYTE),
  SUMMARY      VARCHAR2(50 BYTE),
  STARTPRICE   NUMBER(8),
  INCR_PRICE   NUMBER(5),
  STDATE       DATE,
  ENDDATE      DATE,
  SELLERID     VARCHAR2(20 BYTE),
  BIDCNT       NUMBER(4)
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


CREATE TABLE BIDDING_INFO
(
  BIDDERID  VARCHAR2(20 BYTE),
  BAMT      NUMBER(8),
  ITEMID    VARCHAR2(6 BYTE),
  BIDDATE   DATE
)
TABLESPACE SYSTEM
PCTUSED    40
PCTFREE    10
INITRANS   1
MAXTRANS   255
STORAGE    (
            INITIAL          64K
            MINEXTENTS       1
            MAXEXTENTS       2147483645
            PCTINCREASE      0
            FREELISTS        1
            FREELIST GROUPS  1
            BUFFER_POOL      DEFAULT
           )
LOGGING 
NOCACHE
NOPARALLEL
NOMONITORING;


ALTER TABLE CATEGORY_MASTER ADD (
  PRIMARY KEY
 (CATID)
    USING INDEX 
    TABLESPACE SYSTEM
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                FREELISTS        1
                FREELIST GROUPS  1
               ));


ALTER TABLE UINFO_MASTER ADD (
  PRIMARY KEY
 (UNAME)
    USING INDEX 
    TABLESPACE SYSTEM
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                FREELISTS        1
                FREELIST GROUPS  1
               ));


ALTER TABLE ITEM_MASTER ADD (
  PRIMARY KEY
 (ITEMID)
    USING INDEX 
    TABLESPACE SYSTEM
    PCTFREE    10
    INITRANS   2
    MAXTRANS   255
    STORAGE    (
                INITIAL          64K
                MINEXTENTS       1
                MAXEXTENTS       2147483645
                PCTINCREASE      0
                FREELISTS        1
                FREELIST GROUPS  1
               ));


ALTER TABLE ITEM_MASTER ADD (
  FOREIGN KEY (CATID) 
 REFERENCES CATEGORY_MASTER (CATID)
    ON DELETE CASCADE);

ALTER TABLE ITEM_MASTER ADD (
  FOREIGN KEY (SELLERID) 
 REFERENCES UINFO_MASTER (UNAME)
    ON DELETE CASCADE);


ALTER TABLE BIDDING_INFO ADD (
  FOREIGN KEY (BIDDERID) 
 REFERENCES UINFO_MASTER (UNAME)
    ON DELETE CASCADE);

ALTER TABLE BIDDING_INFO ADD (
  FOREIGN KEY (ITEMID) 
 REFERENCES ITEM_MASTER (ITEMID)
    ON DELETE CASCADE);



Insert into AUCTION_ALERTS
   (USERID, MAX_AUCTIONAMT, PRODUCT_ITEM)
 Values
   ('Manjunath', 15000, '10001');
Insert into AUCTION_ALERTS
   (USERID, MAX_AUCTIONAMT, PRODUCT_ITEM)
 Values
   ('Manjunath', 15000, '10001');
Insert into AUCTION_ALERTS
   (USERID, MAX_AUCTIONAMT, PRODUCT_ITEM)
 Values
   ('Manjunath', 20000, '20001');
COMMIT;

Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('sunil', 10500, '30001', TO_DATE('02/26/2009 16:54:35', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('sunil', 11000, '30001', TO_DATE('02/26/2009 17:01:48', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('seenu', 11500, '30001', TO_DATE('02/26/2009 17:01:48', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('Manjunath', 5500, '10001', TO_DATE('02/28/2009 17:17:25', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('Manjunath', 6000, '10001', TO_DATE('02/28/2009 17:18:09', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('Manjunath', 12000, '30001', TO_DATE('02/28/2009 17:21:22', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('Manjunath', 12800, '30001', TO_DATE('02/28/2009 17:22:35', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('sunil', 6500, '10001', TO_DATE('02/28/2009 17:26:16', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('Manjunath', 7000, '10001', TO_DATE('02/28/2009 17:26:16', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('Manjunath', 7500, '10001', TO_DATE('02/28/2009 17:26:16', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('sunil', 8000, '10001', TO_DATE('02/28/2009 17:27:42', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('Manjunath', 8500, '10001', TO_DATE('02/28/2009 17:27:42', 'MM/DD/YYYY HH24:MI:SS'));
Insert into BIDDING_INFO
   (BIDDERID, BAMT, ITEMID, BIDDATE)
 Values
   ('Manjunath', 9000, '10001', TO_DATE('02/28/2009 17:27:42', 'MM/DD/YYYY HH24:MI:SS'));
COMMIT;

Insert into CATEGORY_MASTER
   (CATID, CAT_NAME)
 Values
   ('111', 'Electronics');
Insert into CATEGORY_MASTER
   (CATID, CAT_NAME)
 Values
   ('222', 'Computers');
Insert into CATEGORY_MASTER
   (CATID, CAT_NAME)
 Values
   ('333', 'Mobiles');
Insert into CATEGORY_MASTER
   (CATID, CAT_NAME)
 Values
   ('444', 'Jewellery');
Insert into CATEGORY_MASTER
   (CATID, CAT_NAME)
 Values
   ('555', 'Collections');
Insert into CATEGORY_MASTER
   (CATID, CAT_NAME)
 Values
   ('666', 'Interiors');
COMMIT;

Insert into ITEM_MASTER
   (ITEMID, CATID, ITEMNAME, DESCRIPTION, SUMMARY, 
    STARTPRICE, INCR_PRICE, STDATE, ENDDATE, SELLERID, 
    BIDCNT)
 Values
   ('30001', '333', 'mobile', 'mobile', 'mobile', 
    10000, 500, TO_DATE('02/26/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/05/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'vijay', 
    5);
Insert into ITEM_MASTER
   (ITEMID, CATID, ITEMNAME, DESCRIPTION, SUMMARY, 
    STARTPRICE, INCR_PRICE, STDATE, ENDDATE, SELLERID, 
    BIDCNT)
 Values
   ('20001', '222', 'pc', 'good ........', 'good pc', 
    15000, 500, TO_DATE('02/28/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/05/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Sathya1', 
    0);
Insert into ITEM_MASTER
   (ITEMID, CATID, ITEMNAME, DESCRIPTION, SUMMARY, 
    STARTPRICE, INCR_PRICE, STDATE, ENDDATE, SELLERID, 
    BIDCNT)
 Values
   ('10001', '111', 'tv', 'plasma tv', 'tv', 
    5000, 500, TO_DATE('02/28/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), TO_DATE('03/07/2009 00:00:00', 'MM/DD/YYYY HH24:MI:SS'), 'Sathya1', 
    8);
COMMIT;

Insert into UINFO_MASTER
   (UNAME, PWD, FNAME, LNAME, EMAIL, 
    PHNO, ADDRESS, CITY, STATE, PIN, 
    COUNTRY, CCARDNO)
 Values
   ('vijay', 'kumar', 'vijay', 'kumar', 'vijay@yahoo.com', 
    '9985049551', '342 : 45', 'Hyderabad', 'Andhra Pradesh', '518004', 
    'India', 5.67657657657658E15);
Insert into UINFO_MASTER
   (UNAME, PWD, FNAME, LNAME, EMAIL, 
    PHNO, ADDRESS, CITY, STATE, PIN, 
    COUNTRY, CCARDNO)
 Values
   ('raghu', 'raghu', 'raghu', 'raghu', 'raghu@yahoo.com', 
    '8798798798', '876 : 87678', 'hyd', 'Andhra Pradesh', '87678', 
    'India', 8979879879);
Insert into UINFO_MASTER
   (UNAME, PWD, FNAME, LNAME, EMAIL, 
    PHNO, ADDRESS, CITY, STATE, PIN, 
    COUNTRY, CCARDNO)
 Values
   ('Sathya', 'Sathya', NULL, NULL, 'Sathya', 
    NULL, ' : ', NULL, 'Andhra Pradesh', NULL, 
    'India', NULL);
Insert into UINFO_MASTER
   (UNAME, PWD, FNAME, LNAME, EMAIL, 
    PHNO, ADDRESS, CITY, STATE, PIN, 
    COUNTRY, CCARDNO)
 Values
   ('sunil', 'sunil', 'sunil', 'sunil', 'sunil@yahoo.com', 
    '9809890809', '98 : 87', 'hyd', 'Andhra Pradesh', '879879', 
    'India', 9879879879);
Insert into UINFO_MASTER
   (UNAME, PWD, FNAME, LNAME, EMAIL, 
    PHNO, ADDRESS, CITY, STATE, PIN, 
    COUNTRY, CCARDNO)
 Values
   ('seenu', 'seenu', 'seenu', 'seenu', 'seenu@yahoo.com', 
    '878979879', '768 : 76', 'hyd', 'Andhra Pradesh', '878979', 
    'India', 879797979);
Insert into UINFO_MASTER
   (UNAME, PWD, FNAME, LNAME, EMAIL, 
    PHNO, ADDRESS, CITY, STATE, PIN, 
    COUNTRY, CCARDNO)
 Values
   ('Sathya1', 'Sathya1', 'Sathya', 'tech', 'Sathya@gmail.com', 
    '9885148211', '43 : ap', 'hyd', 'Andhra Pradesh', '518000', 
    'India', 1.23456789012346E15);
Insert into UINFO_MASTER
   (UNAME, PWD, FNAME, LNAME, EMAIL, 
    PHNO, ADDRESS, CITY, STATE, PIN, 
    COUNTRY, CCARDNO)
 Values
   ('Manjunath', 'Manjunath', 'Manjunath', 'Manjunath', 'Manjunath@gmail.com', 
    '221221', '12 : Manjunath', 'hyd', 'Andhra Pradesh', '544342', 
    'India', 1.23456789065432E15);
COMMIT;

