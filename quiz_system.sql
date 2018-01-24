drop table studentresponses;
drop table registration;
drop table answer;
drop table question;
drop table quiz;
drop table student;
drop table professor;

create table professor (id int primary key, firstname varchar2(20), lastname varchar2(20), email varchar2(30), username varchar2(15), password varchar2(15));
create table student (id int primary key, firstname varchar2(20), lastname varchar2(20), email varchar2(30), username varchar2(15), password varchar2(15));
create table quiz (id int primary key, name varchar2(50), numquestions int, duration int, showanswers varchar2(1), professorid int, foreign key(professorid) references professor(id));
create table question (id int primary key, quizid int, foreign key(quizid) references quiz(id), description varchar2(500));
create table answer (id int primary key, questionid int, foreign key(questionid) references question(id), answer varchar2(500));
create table registration(id int primary key, quizid int, foreign key(quizid) references quiz(id), studentid int, foreign key(studentid) references student(id), quizdate date, score int, status varchar(1));
create table studentresponses(id int primary key, questionid int, foreign key(questionid) references question(id), studentid int, foreign key(studentid) references student(id), answers varchar2(10));
