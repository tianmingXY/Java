CREATE TABLE WEBSITES 
   (	"ID" NUMBER(11,0), 
	"NAME" VARCHAR2(255 BYTE), 
	"URL" VARCHAR2(255 BYTE), 
	"ALEXA" NUMBER(11,0), 
	"COUNTRY" VARCHAR2(255 BYTE)
   );


Insert into WEBSITES (ID,NAME,URL,ALEXA,COUNTRY) values (5,'Facebook     ',' https://www.facebook.com/ ',3,'USA');
Insert into WEBSITES (ID,NAME,URL,ALEXA,COUNTRY) values (1,'Google       ',' https://www.google.cm/',1,'USA');
Insert into WEBSITES (ID,NAME,URL,ALEXA,COUNTRY) values (2,'ÌÔ±¦','https://www.taobao.com/ ',12,'CN');
Insert into WEBSITES (ID,NAME,URL,ALEXA,COUNTRY) values (3,'²ËÄñ½Ì³Ì','http://www.runoob.com/',4689,'CN');
Insert into WEBSITES (ID,NAME,URL,ALEXA,COUNTRY) values (4,'Î¢²©          ',' http://weibo.com/ ',20,'CN');
Insert into WEBSITES (ID,NAME,URL,ALEXA,COUNTRY) values (10,'ÌìÃ÷Ô´´úÂë','www.yydhy.com',123,'CN');


  CREATE TABLE ACCESS_LOG
   (	"AID" NUMBER(11,0), 
	"SITE_ID" NUMBER(11,0), 
	"COUNT" NUMBER(11,0), 
	"DATE1" VARCHAR2(50 BYTE)
   );
   
Insert into ACCESS_LOG (AID,SITE_ID,COUNT,DATE1) values (1,1,45,'2016-05-10');
Insert into ACCESS_LOG (AID,SITE_ID,COUNT,DATE1) values (2,3,100,'2016-05-13');
Insert into ACCESS_LOG (AID,SITE_ID,COUNT,DATE1) values (3,1,230,'2016-05-14');
Insert into ACCESS_LOG (AID,SITE_ID,COUNT,DATE1) values (4,2,10,'2016-05-14');
Insert into ACCESS_LOG (AID,SITE_ID,COUNT,DATE1) values (5,5,205,'2016-05-14');
Insert into ACCESS_LOG (AID,SITE_ID,COUNT,DATE1) values (6,4,13,'2016-05-15');
Insert into ACCESS_LOG (AID,SITE_ID,COUNT,DATE1) values (7,3,220,'2016-05-15');
Insert into ACCESS_LOG (AID,SITE_ID,COUNT,DATE1) values (8,5,545,'2016-05-16');
Insert into ACCESS_LOG (AID,SITE_ID,COUNT,DATE1) values (9,3,201,'2016-05-17');
Insert into ACCESS_LOG (AID,SITE_ID,COUNT,DATE1) values (14,7,45,'2016-05-10');


CREATE TABLE apps 
   (	
   	"ID" NUMBER(11,0), 
	"app_name" VARCHAR2(255 BYTE), 
	"URL" VARCHAR2(255 BYTE), 
	"COUNTRY" VARCHAR2(255 BYTE)
   );
Insert into apps values (1,'QQ APP','http://im.qq.com/','CN');
Insert into apps values (2,'Î¢²© APP','http://weibo.com/','CN');
Insert into apps values (3,'ÌÔ±¦ APP','https://www.taobao.com/','CN');
