create database sportshub;
use sportshub;

CREATE TABLE sportshub.`registration` (
   `userId` int auto_increment primary key,
  `firstName` varchar(40) DEFAULT NULL,
  `lastName` varchar(40) DEFAULT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `usertype` varchar(40) DEFAULT NULL
);




CREATE TABLE sportshub.`ticket` (
   `ticketId` int auto_increment primary key,
   `eventTitle` varchar(1500) DEFAULT NULL,
   `username` varchar(40) DEFAULT NULL,
  `creditCardNum` varchar(40) DEFAULT NULL,
  `customerAddress` varchar(200) DEFAULT NULL,
  `amount` varchar(40) DEFAULT NULL,
  `eventDateTime` varchar(40) DEFAULT NULL
);

CREATE TABLE sportshub.Eventdetails
(eventId varchar(40),
 eventName varchar(100),
 eventImage varchar(500),
 salesStartDate varchar(20),
 salesEndDate varchar(20),
 eventDate varchar(20),
 eventTime varchar(20),
 eventSaleStatus varchar(20),
 genre varchar(40),
 subGenre varchar(40),
 venuesName varchar(100),
 venuesPostalCode varchar(20),
 venuesCity varchar(40),
 venuesState varchar(40),
 stateCode varchar(5),
 countryName varchar(40),
 countryCode varchar(5),
 address varchar(500),
 locationLongitude double,
 locationLatitude double,
 phoneNumber varchar(1000),
 officeHours varchar(1000),
 paytmentDetails varchar(2000),
 willCallDetail varchar(2000),
 parkingDetails varchar(3000),
 accessibleSeatingDetail varchar(3000),
 minPrice double,
 maxPrice double,
 seatMap varchar(200),
 pleaseNote varchar(2000),
 promoterName varchar(50),
 promoterDescription varchar(50));