-- DROP USER JSP;

SELECT *
FROM DBA_USERS;

CREATE USER jsp IDENTIFIED BY jsp ACCOUNT UNLOCK;

GRANT CONNECT, RESOURCE, DBA TO jsp;

-- gfdgdfjldf
create table members(
    mno number not null,
    email varchar2(40) not null,
    pwd varchar2(100) not null,
    mname varchar2(50) not null,
    cre_date date not null,
    mod_date date not null
);


ALTER TABLE MEMBERS
ADD CONSTRAINT MEMBERS_MNO_PK PRIMARY KEY (MNO);


ALTER TABLE MEMBERS
ADD CONSTRAINT MEMBERS_EMAIL_UK UNIQUE(EMAIL);

CREATE SEQUENCE MEMBERS_MNO_SEQ
INCREMENT BY 1
START WITH 1;

-- drop SEQUENCE MEMBERS_MNO_SEQ;

DESC MEMBERS;


INSERT INTO MEMBERS
(MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)
VALUES(MEMBERS_MNO_SEQ.nextval, 's1@test.com', '1111'
, '홍길동', sysdate, sysdate);

INSERT INTO MEMBERS
(MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)
VALUES(MEMBERS_MNO_SEQ.nextval, 's2@test.com', '2222'
, '임꺽정', sysdate, sysdate);

select *
from members;


commit;


select mno, mname, email, cre_date
from members
order by mno asc;



