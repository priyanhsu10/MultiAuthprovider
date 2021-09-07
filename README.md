# MultiAuthprovider

create database secdb

grant all on database secdb to <username>
	
GRANT all ON DATABASE secdb TO tclocal_migratoruser;
	
create tables
	
CREATE TABLE public.users (
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	id serial NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
);


CREATE TABLE public.tblotp (
	id serial NOT NULL,
	username varchar NOT NULL,
	otp varchar NOT NULL
);
