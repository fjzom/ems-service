CREATE MEMORY TABLE  PUBLIC.EMPLOYEE (
    EMPLOYEE_ID  CHARACTER VARYING(255) NOT NULL,
    ADDRESS  CHARACTER VARYING(255),
    EMP_NAME  CHARACTER VARYING(255),
    HEIGHT  DOUBLE PRECISION,
    WEIGHT  DOUBLE PRECISION,
    EMPLOYEE_SALARY_FK  CHARACTER VARYING(255)
);
ALTER TABLE  PUBLIC.EMPLOYEE  ADD CONSTRAINT  CONSTRAINT_7  PRIMARY KEY( EMPLOYEE_ID );

CREATE MEMORY TABLE  PUBLIC.EMPLOYEE_OFFICIAL (
    EMPLOYEE_OFFICIAL_ID  CHARACTER VARYING(255) NOT NULL,
    PROJECT_NAME  CHARACTER VARYING(255),
    EMPLOYEE_OFFICIAL_EMPLOYEE_ID  CHARACTER VARYING(255)
);
ALTER TABLE  PUBLIC.EMPLOYEE_OFFICIAL  ADD CONSTRAINT  CONSTRAINT_2  PRIMARY KEY( EMPLOYEE_OFFICIAL_ID );

CREATE MEMORY TABLE  PUBLIC.EMPLOYEE_SALARY (
    SALARY_ID  CHARACTER VARYING(255) NOT NULL,
    SALARY  INTEGER
);
ALTER TABLE  PUBLIC.EMPLOYEE_SALARY  ADD CONSTRAINT  CONSTRAINT_29  PRIMARY KEY( SALARY_ID );
ALTER TABLE  PUBLIC.EMPLOYEE  ADD CONSTRAINT  FK4DPB045JEWAQHOMO231KST1TY  FOREIGN KEY( EMPLOYEE_SALARY_FK ) REFERENCES  PUBLIC.EMPLOYEE_SALARY ( SALARY_ID ) NOCHECK;
ALTER TABLE  PUBLIC . EMPLOYEE_OFFICIAL  ADD CONSTRAINT  FKSH26X7P72DR8J0LLKNQNOLCB9  FOREIGN KEY( EMPLOYEE_OFFICIAL_EMPLOYEE_ID ) REFERENCES  PUBLIC.EMPLOYEE ( EMPLOYEE_ID ) NOCHECK;