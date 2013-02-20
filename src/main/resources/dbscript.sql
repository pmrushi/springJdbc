CREATE TABLE department
(
  dept_id bigint NOT NULL,
  name character varying(255),
  creation_date timestamp without time zone,
  CONSTRAINT dept_pkey PRIMARY KEY (dept_id )
)

CREATE SEQUENCE department_dept_id_seq;

ALTER TABLE department 
    ALTER COLUMN dept_id 
        SET DEFAULT NEXTVAL('department_dept_id_seq');

CREATE TABLE employee
(
  emp_id bigint NOT NULL,
  firstname character varying(255),
  lastname character varying(255),
  birth_date timestamp without time zone,
  dept_id bigint,
  CONSTRAINT emp_pkey PRIMARY KEY (emp_id ),
  CONSTRAINT fk_dept_id FOREIGN KEY (dept_id)
      REFERENCES department (dept_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)

CREATE SEQUENCE employee_emp_id_seq;

ALTER TABLE employee 
    ALTER COLUMN emp_id 
        SET DEFAULT NEXTVAL('employee_emp_id_seq');

INSERT INTO department(name, creation_date) VALUES ('IT' , '12-02-2013');
