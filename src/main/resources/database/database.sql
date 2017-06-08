
CREATE ROLE "epamAdmin" LOGIN
  ENCRYPTED PASSWORD 'md560e39799cb8eda1114f95ef5c0f6591f'
  SUPERUSER INHERIT CREATEDB CREATEROLE REPLICATION;


CREATE DATABASE "epamADSM"
  WITH OWNER = "epamAdmin"
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;


CREATE SEQUENCE public.staff_staff_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 13
  CACHE 1;
ALTER TABLE public.staff_staff_id_seq
  OWNER TO "epamAdmin";

CREATE SEQUENCE public.research_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 25
  CACHE 1;
ALTER TABLE public.research_id_seq
  OWNER TO "epamAdmin";

-- Table: public.staff

-- DROP TABLE public.staff;

CREATE TABLE public.staff
(
  staff_id integer NOT NULL DEFAULT nextval('staff_staff_id_seq'::regclass),
  name character varying(255) NOT NULL,
  surname character varying(255) NOT NULL,
  phone_number character varying(50) NOT NULL,
  activity_status boolean NOT NULL DEFAULT true,
  password character varying(255) NOT NULL,
  role character varying(200) NOT NULL,
  delete_status boolean NOT NULL DEFAULT false,
  CONSTRAINT stuff_pkey PRIMARY KEY (staff_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.staff
  OWNER TO "epamAdmin";

-- Table: public.patient

-- DROP TABLE public.patient;

CREATE TABLE public.patient
(
  patient_id character varying(50) NOT NULL,
  date_of_birthday date NOT NULL,
  initial character varying(10) NOT NULL,
  patient_sex character varying(50) NOT NULL,
  patient_phone character varying(15) NOT NULL,
  patient_email character varying(50) NOT NULL,
  password character varying(255) NOT NULL,
  confirmed boolean NOT NULL DEFAULT true,
  staff_id integer NOT NULL,
  CONSTRAINT patient_pkey PRIMARY KEY (patient_id),
  CONSTRAINT patient_staff_fkey FOREIGN KEY (staff_id)
      REFERENCES public.staff (staff_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.patient
  OWNER TO "epamAdmin";

-- Table: public.diagnosis

-- DROP TABLE public.diagnosis;

CREATE TABLE public.diagnosis
(
  diagnosis_id integer NOT NULL DEFAULT nextval('diagnosis_diagnosis_id_seq'::regclass),
  risk_factor character varying(255) NOT NULL,
  localization_disease character varying(255) NOT NULL,
  prevalence character varying(255) NOT NULL,
  clinical_form character varying(255) NOT NULL,
  mbt_status character varying(255) NOT NULL,
  patient_type character varying(255) NOT NULL,
  dst_status character varying(255) NOT NULL,
  patient_id character varying(50) NOT NULL,
  CONSTRAINT diagnosis_pkey PRIMARY KEY (diagnosis_id),
  CONSTRAINT diagnosis_patient_fkey FOREIGN KEY (patient_id)
      REFERENCES public.patient (patient_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.diagnosis
  OWNER TO "epamAdmin";


-- Table: public.research

-- DROP TABLE public.research;
CREATE TABLE public.research
(
  research_id integer NOT NULL DEFAULT nextval('research_id_seq'::regclass),
  enrollment_date date NOT NULL,
  activation_date date NOT NULL,
  activation_status boolean NOT NULL DEFAULT true,
  patient_id character varying(250) NOT NULL,
  research_progress double precision NOT NULL,
  CONSTRAINT research_pkey PRIMARY KEY (research_id),
  CONSTRAINT research_patient_fkey FOREIGN KEY (patient_id)
      REFERENCES public.patient (patient_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.research
  OWNER TO "epamAdmin";



CREATE SEQUENCE public.protocol_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 361
  CACHE 1;
ALTER TABLE public.protocol_id_seq
  OWNER TO "epamAdmin";

-- Sequence: public.adverse_event_adverse_event_id_seq

-- DROP SEQUENCE public.adverse_event_adverse_event_id_seq;

CREATE SEQUENCE public.adverse_event_adverse_event_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 4
  CACHE 1;
ALTER TABLE public.adverse_event_adverse_event_id_seq
  OWNER TO "epamAdmin";

-- DROP SEQUENCE public.diagnosis_diagnosis_id_seq;

CREATE SEQUENCE public.diagnosis_diagnosis_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 7
  CACHE 1;
ALTER TABLE public.diagnosis_diagnosis_id_seq
  OWNER TO "epamAdmin";


-- Sequence: public.drug_admin_drug_admin_id_seq

-- DROP SEQUENCE public.drug_admin_drug_admin_id_seq;

CREATE SEQUENCE public.drug_admin_drug_admin_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1;
ALTER TABLE public.drug_admin_drug_admin_id_seq
  OWNER TO "epamAdmin";



-- Sequence: public.drug_drug_id_seq

-- DROP SEQUENCE public.drug_drug_id_seq;

CREATE SEQUENCE public.drug_drug_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 14
  CACHE 1;
ALTER TABLE public.drug_drug_id_seq
  OWNER TO "epamAdmin";

-- Sequence: public.event_event_id_seq

-- DROP SEQUENCE public.event_event_id_seq;

CREATE SEQUENCE public.event_event_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 290
  CACHE 1;
ALTER TABLE public.event_event_id_seq
  OWNER TO "epamAdmin";


-- Sequence: public.event_prototype_event_prototype_id_seq

-- DROP SEQUENCE public.event_prototype_event_prototype_id_seq;

CREATE SEQUENCE public.event_prototype_event_prototype_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 24
  CACHE 1;
ALTER TABLE public.event_prototype_event_prototype_id_seq
  OWNER TO "epamAdmin";

-- Sequence: public.list_date_id_seq

-- DROP SEQUENCE public.list_date_id_seq;

CREATE SEQUENCE public.list_date_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 15
  CACHE 1;
ALTER TABLE public.list_date_id_seq
  OWNER TO "epamAdmin";

-- Sequence: public.reciept_drug_id_seq

-- DROP SEQUENCE public.reciept_drug_id_seq;

CREATE SEQUENCE public.reciept_drug_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.reciept_drug_id_seq
  OWNER TO "epamAdmin";

-- Sequence: public.reciept_reciept_id_seq

-- DROP SEQUENCE public.reciept_reciept_id_seq;

CREATE SEQUENCE public.reciept_reciept_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
ALTER TABLE public.reciept_reciept_id_seq
  OWNER TO "epamAdmin";


-- Sequence: public.task_id_seq

-- DROP SEQUENCE public.task_id_seq;

CREATE SEQUENCE public.task_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2889
  CACHE 1;
ALTER TABLE public.task_id_seq
  OWNER TO "epamAdmin";

-- Sequence: public.task_prototype_task_prototype_id_seq

-- DROP SEQUENCE public.task_prototype_task_prototype_id_seq;

CREATE SEQUENCE public.task_prototype_task_prototype_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 29
  CACHE 1;
ALTER TABLE public.task_prototype_task_prototype_id_seq
  OWNER TO "epamAdmin";

-- Table: public.event_prototype

-- DROP TABLE public.event_prototype;


CREATE TABLE public.event_prototype
(
  event_prototype_id integer NOT NULL DEFAULT nextval('event_prototype_event_prototype_id_seq'::regclass),
  event_prototype_name character varying(255) NOT NULL,
  event_interval integer,
  CONSTRAINT event_prototype_pkey PRIMARY KEY (event_prototype_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.event_prototype
  OWNER TO "epamAdmin";


-- Table: public.task_prototype

-- DROP TABLE public.task_prototype;


CREATE TABLE public.task_prototype
(
  task_prototype_id integer NOT NULL DEFAULT nextval('task_prototype_task_prototype_id_seq'::regclass),
  task_name character varying(250) NOT NULL,
  CONSTRAINT task_prototype_pkey PRIMARY KEY (task_prototype_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.task_prototype
  OWNER TO "epamAdmin";

-- Table: public.event

-- DROP TABLE public.event;


CREATE TABLE public.event
(
  event_id integer NOT NULL DEFAULT nextval('event_event_id_seq'::regclass),
  event_date date NOT NULL,
  research_id integer NOT NULL,
  event_prototype_id integer NOT NULL,
  event_progress double precision NOT NULL,
  CONSTRAINT event_pkey PRIMARY KEY (event_id),
  CONSTRAINT event_prototype_fkey FOREIGN KEY (event_prototype_id)
      REFERENCES public.event_prototype (event_prototype_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT event_research_fkey FOREIGN KEY (research_id)
      REFERENCES public.research (research_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.event
  OWNER TO "epamAdmin";


-- Table: public.task

-- DROP TABLE public.task;


CREATE TABLE public.task
(
  task_id integer NOT NULL DEFAULT nextval('task_id_seq'::regclass),
  task_prototype_id integer NOT NULL,
  event_id integer NOT NULL,
  task_progress integer NOT NULL,
  CONSTRAINT task_pkey PRIMARY KEY (task_id),
  CONSTRAINT task_event_fkey FOREIGN KEY (event_id)
      REFERENCES public.event (event_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT task_prototype FOREIGN KEY (task_prototype_id)
      REFERENCES public.task_prototype (task_prototype_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.task
  OWNER TO "epamAdmin";


-- Table: public.drug

-- DROP TABLE public.drug;

CREATE TABLE public.drug
(
  drug_id integer NOT NULL DEFAULT nextval('drug_drug_id_seq'::regclass),
  drug_name character varying(250),
  CONSTRAINT drug_pkey PRIMARY KEY (drug_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.drug
  OWNER TO "epamAdmin";


-- Table: public.receipt

-- DROP TABLE public.receipt;

CREATE TABLE public.receipt
(
  reciept_id integer NOT NULL DEFAULT nextval('reciept_reciept_id_seq'::regclass),
  patient_id character varying(255) NOT NULL,
  drud_doze double precision NOT NULL,
  receipt_status boolean NOT NULL DEFAULT true,
  reciept_date date NOT NULL,
  drug_id integer NOT NULL DEFAULT nextval('reciept_drug_id_seq'::regclass),
  CONSTRAINT reciept_pkey PRIMARY KEY (reciept_id),
  CONSTRAINT reciept_drug_fkey FOREIGN KEY (drug_id)
      REFERENCES public.drug (drug_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT reciept_patient_fkey FOREIGN KEY (patient_id)
      REFERENCES public.patient (patient_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.receipt
  OWNER TO "epamAdmin";


-- Table: public.protocol_events_tasks

-- DROP TABLE public.protocol_events_tasks;


CREATE TABLE public.protocol_events_tasks
(
  protocol_id integer NOT NULL DEFAULT nextval('protocol_id_seq'::regclass),
  event_prototype_id integer NOT NULL,
  task_prototype_id integer NOT NULL,
  CONSTRAINT protocol_pkey PRIMARY KEY (protocol_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.protocol_events_tasks
  OWNER TO "epamAdmin";

-- Table: public.list_date

-- DROP TABLE public.list_date;

CREATE TABLE public.list_date
(
  id integer NOT NULL DEFAULT nextval('list_date_id_seq'::regclass),
  factor_risk character varying(255),
  localizatoin character varying(255),
  releavence character varying(255),
  clinical_form character varying(255),
  mbt_status character varying(255),
  patient_type character varying(255),
  dst_status character varying(255),
  administration_option character varying(255),
  gender character varying(255),
  role_option character varying(255),
  adverse_status_option character varying(255),
  activation_status_option character varying(255),
  CONSTRAINT date_list_pk PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.list_date
  OWNER TO "epamAdmin";

-- Table: public.drug_administration

-- DROP TABLE public.drug_administration;
CREATE TABLE public.drug_administration
(
  drug_administration_id integer NOT NULL DEFAULT nextval('drug_admin_drug_admin_id_seq'::regclass),
  recepiet_id integer NOT NULL,
  patient_id character varying(255) NOT NULL,
  drug_status character varying(255) NOT NULL,
  drug_administration_date date NOT NULL,
  drug_administration_comment character varying(255) DEFAULT 'no'::character varying,
  CONSTRAINT drug_administration_pkey PRIMARY KEY (drug_administration_id),
  CONSTRAINT drug_administration_patient_fkey FOREIGN KEY (patient_id)
      REFERENCES public.patient (patient_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT drug_administration_reciept_fkey FOREIGN KEY (recepiet_id)
      REFERENCES public.receipt (reciept_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.drug_administration
  OWNER TO "epamAdmin";
-- Index: public.fki_drug_administration_patient_fkey

-- DROP INDEX public.fki_drug_administration_patient_fkey;

CREATE INDEX fki_drug_administration_patient_fkey
  ON public.drug_administration
  USING btree
  (patient_id COLLATE pg_catalog."default");

-- Index: public.fki_drug_administration_reciept_fkey

-- DROP INDEX public.fki_drug_administration_reciept_fkey;

CREATE INDEX fki_drug_administration_reciept_fkey
  ON public.drug_administration
  USING btree
  (recepiet_id);


-- Table: public.adverse_event

-- DROP TABLE public.adverse_event;


CREATE TABLE public.adverse_event
(
  adverse_event_id integer NOT NULL DEFAULT nextval('adverse_event_adverse_event_id_seq'::regclass),
  adverse_event_name character varying(255) NOT NULL,
  adverse_event_date date NOT NULL,
  adverse_event_comment character varying(500),
  effect_of_alcohol boolean DEFAULT false,
  effect_of_drug boolean DEFAULT false,
  staff_id integer NOT NULL,
  patient_id character varying(255) NOT NULL,
  adverse_status character varying(255),
  CONSTRAINT adverse_event_pkey PRIMARY KEY (adverse_event_id),
  CONSTRAINT adverse_event_patient_fkey FOREIGN KEY (patient_id)
      REFERENCES public.patient (patient_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT adverse_event_staff_fkey FOREIGN KEY (staff_id)
      REFERENCES public.staff (staff_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.adverse_event
  OWNER TO "epamAdmin";

-- Index: public.fki_adverse_event_staff_fkey

-- DROP INDEX public.fki_adverse_event_staff_fkey;

CREATE INDEX fki_adverse_event_staff_fkey
  ON public.adverse_event
  USING btree
  (staff_id);

-- Index: public."fki_ф"

-- DROP INDEX public."fki_ф";

CREATE INDEX "fki_ф"
  ON public.adverse_event
  USING btree
  (patient_id COLLATE pg_catalog."default");
