use sportshub;
drop table registration;
select * from registration;

create table games( id varchar(25) NOT NULL,
name varchar(25) NOT NULL,
price double,
type varchar(25) NOT NULL,
image varchar(50) NOT NULL);

drop table ticket;
select * from ticket;

select * from games;
update games set id="2" where name = "game 2";
 show tables;
insert into games values(5,"game 5",100,"scocer","images/bg_1");

Delete from ticket where ticketId=1 and eventTitle='ee';
INSERT INTO ticket(username,eventTitle,amount,customerAddress,creditCardNum, eventDateTime) values ('ee','ee',55,'dfsdf','asdsa','adfas');
drop table Eventdetails;
select * from Eventdetails where venuesCity='Chicago';
<<<<<<< HEAD
select eventName from Eventdetails where eventid='G5vYZ4JBrsV7J';
select * from Eventdetails;
select c.eventTitle,p.maxPrice ,count(c.ticketID) count, c.ticketID*maxPrice sales from ticket c, Eventdetails p where c.eventTitle = p.eventName group by c.eventTitle;
SELECT subGenre FROM Eventdetails group by subGenre;
delete from ticket;
drop table ticket;
select * from Eventdetails;
select max(ticketId) from ticket;
select c.eventTitle,p.maxPrice ,count(c.ticketID) count, c.ticketID*maxPrice sales from ticket c, Eventdetails p where c.eventTitle = p.eventName group by c.eventTitle;
select * from registration;
=======
select eventName from Eventdetails where eventid='1234';
select * from Eventdetails;
delete from ticket;
drop table ticket;
select * from ticket;
select max(ticketId) from ticket;
>>>>>>> c58765f65207cc00fd20e3dcc71371769e1d3520
