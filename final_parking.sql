-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2024 at 05:24 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `final_parking`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `Username` varchar(250) NOT NULL,
  `Pass` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `Id` varchar(250) NOT NULL,
  `Name` varchar(250) NOT NULL,
  `Sex` varchar(250) NOT NULL,
  `Age` int(250) NOT NULL,
  `Address` varchar(250) NOT NULL,
  `Cellno` varchar(12) NOT NULL,
  `Email` varchar(250) NOT NULL,
  `Balance` int(250) NOT NULL,
  `Picture` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`Id`, `Name`, `Sex`, `Age`, `Address`, `Cellno`, `Email`, `Balance`, `Picture`) VALUES
('  03 14 76 0F\r', 'Kat Kat', 'Female', 19, 'Manga, Tabgilaran City', '09321312321', 'katkat@gmail.com', 500, 0x5b424063326138653364),
('  B3 48 60 0F\r', 'Gabe', 'Male', 1, 'Talamban, Cebu City', '09123456789', 'gabe@gmail.com', 1000, 0x5b42403734336138366230);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
