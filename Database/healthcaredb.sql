-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2020 at 06:49 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthcaredb`
--

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `pid` int(11) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` char(10) NOT NULL,
  `nic` char(10) NOT NULL,
  `dob` varchar(15) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`pid`, `fname`, `lname`, `address`, `email`, `phone`, `nic`, `dob`, `username`, `password`) VALUES
(22, 'Sachini', 'Uththara', 'Pallegama, Ampitiya', 'sachi.ni@gmail.com', '0715950814', '985545621V', '1998.09.10', 'sachi', 'sachi@123'),
(34, 'Ranmini', 'Dasanayaka', 'Ampitiya, Kandy', 'ranmini@gmail.com', '0714521658', '200401452V', '2004.04.24', 'ran', 'ran@123'),
(57, 'Tharaka', 'Dasanayaka', 'Ampitiya, Kandy', 'tharaka@gmail.com', '0714521584', '958852145V', '2004.02.05', 'tharaka', 'thara@123'),
(58, 'Mangala', 'Ranasinghe', 'Kandy', 'mangala@gmail.com', '0722164542', '645521452V', '1964.05.02', 'mangala', 'mangala@123'),
(59, 'Chethana', 'Dasanayaka', 'Pallegama, Ampitiya', 'chethanageeth@gmail.com', '0711420817', '982201241V', '1998.08.07', 'chethi', 'che@123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`pid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
