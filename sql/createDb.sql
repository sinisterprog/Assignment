drop database if exists intersoftkk;
create database intersoftkk;
use  intersoftkk;

create table experience (
	experience_id int NOT NULL AUTO_INCREMENT,
	description varchar(30),
	PRIMARY KEY(experience_id)
);

create table qualification (
	qualification_id int NOT NULL AUTO_INCREMENT,
	description varchar(30),
	PRIMARY KEY(qualification_id)
);
 
create table engineer (
	engineer_id int NOT NULL AUTO_INCREMENT,
	first_name varchar(100) NOT NULL,
	last_name varchar(100) NOT NULL,
	email_addr varchar(50) NOT NULL,
	mobile_number int(11) NOT NULL,
	skype_id varchar(50),
	gender char(1),
	dob DATE NOT NULL,
	website varchar(100),
	address VARCHAR(255),
	remarks varchar (255),
	technical_skills varchar(255),
	photo_id varchar(255),
	experience_id int default 0,
	qualification_id int default 0,
	PRIMARY KEY(engineer_id)
	)
;

create table gender(
	gender_id INT NOT NULL AUTO_INCREMENT,
	gender VARCHAR(1) NOT NULL UNIQUE,
	PRIMARY KEY(gender_id) 
);

alter table engineer
add foreign key (experience_id) REFERENCES experience (experience_id);
alter table engineer
add foreign key (qualification_id) REFERENCES qualification (qualification_id);

  