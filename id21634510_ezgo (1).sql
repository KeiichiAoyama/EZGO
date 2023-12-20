-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 20, 2023 at 01:25 PM
-- Server version: 10.5.20-MariaDB
-- PHP Version: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id21634510_ezgo`
--

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

CREATE TABLE `city` (
  `cityID` int(11) NOT NULL,
  `cName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `city`
--

INSERT INTO `city` (`cityID`, `cName`) VALUES
(1, 'Jakarta'),
(2, 'Surabaya'),
(3, 'Bandung'),
(4, 'Medan'),
(5, 'Semarang'),
(6, 'Makassar'),
(7, 'Palembang'),
(8, 'Depok'),
(9, 'Tangerang'),
(10, 'South Tangerang'),
(11, 'Bekasi'),
(12, 'Padang'),
(13, 'Denpasar'),
(14, 'Balikpapan'),
(15, 'Yogyakarta');

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

CREATE TABLE `hotel` (
  `hotelID` varchar(500) NOT NULL,
  `productID` varchar(500) NOT NULL,
  `cityID` int(11) NOT NULL,
  `hName` varchar(200) NOT NULL,
  `hAddress` varchar(500) NOT NULL,
  `hRoomType` varchar(100) NOT NULL,
  `hPrice` int(11) NOT NULL,
  `hNights` int(11) NOT NULL,
  `hImage` varchar(100) NOT NULL,
  `hVendor` varchar(500) NOT NULL,
  `hRating` double DEFAULT NULL,
  `hDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`hotelID`, `productID`, `cityID`, `hName`, `hAddress`, `hRoomType`, `hPrice`, `hNights`, `hImage`, `hVendor`, `hRating`, `hDate`) VALUES
('HOTEL1', 'Hotel1', 1, 'Luxury Hotel Jakarta', 'Jl. Luxury No. 1, Jakarta', 'Deluxe Room', 1000000, 3, 'hotel_image1.jpg', 'Luxury Hotels International', 5, '2023-12-10'),
('HOTEL2', 'Hotel2', 2, 'Seaside Resort Surabaya', 'Jl. Seaside No. 2, Surabaya', 'Ocean View Suite', 800000, 2, 'hotel_image2.jpg', 'Surabaya Resorts', 4.7, '2023-12-11'),
('HOTEL3', 'Hotel3', 3, 'Mountain Retreat Bandung', 'Jl. Mountain No. 3, Bandung', 'Cabin Room', 1200000, 4, 'hotel_image3.jpg', 'Bandung Retreats', 5, '2023-12-12'),
('HOTEL4', 'Hotel4', 4, 'City Center Hotel Medan', 'Jl. City Center No. 4, Medan', 'Standard Room', 600000, 1, 'hotel_image4.jpg', 'Medan Hotels', 4.4, '2023-12-13'),
('HOTEL5', 'Hotel5', 5, 'Historic Inn Semarang', 'Jl. Historic No. 5, Semarang', 'Vintage Suite', 900000, 2, 'hotel_image5.jpg', 'Semarang Heritage Inns', 4.5, '2023-12-14');

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE `locations` (
  `locID` int(11) NOT NULL,
  `cityID` int(11) NOT NULL,
  `lName` varchar(500) NOT NULL,
  `lDesc` mediumtext NOT NULL,
  `lImage` varchar(100) NOT NULL,
  `lLikes` int(11) NOT NULL,
  `lLat` double NOT NULL,
  `lLong` double NOT NULL,
  `lLink` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`locID`, `cityID`, `lName`, `lDesc`, `lImage`, `lLikes`, `lLat`, `lLong`, `lLink`) VALUES
(1, 1, 'National Monument (Monas)', 'Iconic monument in Jakarta.', 'monas_image.jpeg', 501, -6.1754, 106.8272, 'https://www.youtube.com/watch?v=r_pRpeEJCuw&pp=ygUIbW9uYXMgNEs%3D'),
(2, 1, 'Istiqlal Mosque', 'Largest mosque in Southeast Asia.', 'istiqlal_mosque.jpg', 800, -6.1704, 106.832, 'https://www.youtube.com/watch?v=68Oer4CKrIc&pp=ygUSTWFzamlkIGlzdGlxbGFsIDRL'),
(3, 1, 'Taman Mini Indonesia Indah', 'Cultural and recreational park.', 'taman_mini.jpg', 700, -6.3022, 106.892, 'https://www.youtube.com/watch?v=ji8Fp8_7VzI&pp=ygUHVE1JSSA0Sw%3D%3D'),
(4, 2, 'House of Sampoerna', 'Historical museum and cigarette factory.', 'house_sampoerna.jpg', 300, -7.2491, 112.7508, 'https://www.youtube.com/watch?v=6IupnVRnPIQ&pp=ygUVaG91c2Ugb2Ygc2FtcG9lcm5hIDRr'),
(5, 2, 'Suramadu National Bridge', 'Longest bridge in Indonesia.', 'suramadu_bridge.jpg', 600, -7.214, 112.7613, 'https://www.youtube.com/watch?v=9lNqc67HIs4&pp=ygUSc3VyYW1hZHUgYnJpZGdlIDRr'),
(6, 2, 'Heroes Monument (Tugu Pahlawan)', 'Monument to honor heroes.', 'heroes_monument.jpg', 400, -7.2845, 112.7318, 'https://www.youtube.com/watch?v=nBd9ncOGLCM&pp=ygUQVHVndSBQYWhsYXdhbiA0aw%3D%3D'),
(7, 3, 'Tangkuban Perahu', 'Stratovolcano with a distinctive shape.', 'tangkuban_perahu.jpg', 901, -6.7598, 107.6062, 'https://www.youtube.com/watch?v=hjiAbR_oRJ4&pp=ygUTVGFuZ2t1YmFuIFBlcmFodSA0aw%3D%3D'),
(8, 3, 'Gedung Sate', 'Governor\'s office with unique architecture.', 'gedung_sate.jpg', 750, -6.9074, 107.6184, 'https://www.youtube.com/watch?v=OuyH5sqUtYc&pp=ygUOR2VkdW5nIFNhdGUgNGs%3D'),
(9, 3, 'Braga Street', 'Historical street with shops and cafes.', 'braga_street.jpg', 550, -6.9175, 107.6106, 'https://www.youtube.com/watch?v=_cTXfYXDvWg&pp=ygUPQnJhZ2EgU3RyZWV0IDRr'),
(10, 4, 'Maimun Palace', 'Royal palace with Malay, Mogul, and Italian architecture.', 'maimun_palace.jpg', 400, 3.594, 98.6733, 'https://www.youtube.com/watch?v=7W3PGjP55fM&pp=ygUQTWFpbXVuIFBhbGFjZSA0aw%3D%3D'),
(11, 4, 'Tjong A Fie Mansion', 'Historical mansion with Chinese, Malay, and Art Deco influences.', 'tjong_a_fie.jpg', 300, 3.5897, 98.671, 'https://www.youtube.com/watch?v=Ld3EHmhNE18&pp=ygUWVGpvbmcgQSBGaWUgTWFuc2lvbiA0aw%3D%3D'),
(12, 4, 'Great Mosque of Medan', 'Grand mosque with unique architectural elements.', 'great_mosque.jpg', 600, 3.5861, 98.6709, 'https://www.youtube.com/watch?v=CMTKxCdToMg&pp=ygUQTWFzamlkIE1lZGFuICA0aw%3D%3D'),
(13, 5, 'Lawang Sewu', 'Historical building with Dutch colonial architecture.', 'lawang_sewu.jpg', 700, -6.9904, 110.4228, 'https://www.youtube.com/watch?v=xzmY9Iha1as&pp=ygUPTGF3YW5nIFNld3UgIDRr'),
(14, 5, 'Sam Poo Kong Temple', 'Ancient Chinese temple with cultural significance.', 'sam_poo_kong.jpg', 450, -7.2407, 110.428, 'https://www.youtube.com/watch?v=wGKQO5tUlf4&pp=ygUVc2FtIHBvIGtvbmcgdGVtcGxlIDRL'),
(15, 5, 'Blenduk Church', 'Protestant church with colonial architecture.', 'blenduk_church.jpg', 350, -6.9707, 110.4178, 'https://www.youtube.com/watch?v=VRT4LPtE7SE&pp=ygURR2VyZWphIEJsZW5kdWsgNEs%3D'),
(16, 6, 'Fort Rotterdam', 'Historical fortress with Dutch architecture.', 'fort_rotterdam.jpg', 250, -5.1476, 119.4306, 'https://www.youtube.com/watch?v=GyOKDzxh93k&pp=ygURRm9ydCBSb3R0ZXJkYW0gNEs%3D'),
(17, 6, 'Losari Beach', 'Popular city beach with vibrant atmosphere.', 'losari_beach.jpg', 800, -5.5553, 119.4136, 'https://www.youtube.com/watch?v=KIvtIyPbeyA&pp=ygUPTG9zYXJpIEJlYWNoIDRL'),
(18, 6, 'Trans Studio Makassar', 'Large indoor theme park and entertainment complex.', 'trans_studio.jpg', 700, -5.1394, 119.4308, 'https://www.youtube.com/watch?v=KyOG8AOhkH4&pp=ygUYdHJhbnMgc3R1ZGlvIG1ha2Fzc2FyIDRL'),
(19, 7, 'Ampera Bridge', 'Iconic bridge across the Musi River.', 'ampera_bridge.jpg', 500, -2.994, 104.7567, 'https://www.youtube.com/watch?v=JhEZjQZApYk&pp=ygUQQW1wZXJhIEJyaWRnZSA0Sw%3D%3D'),
(20, 7, 'Kemaro Island', 'Island with a Chinese temple and pagoda.', 'kemaro_island.jpg', 300, -2.9765, 104.7593, 'https://www.youtube.com/watch?v=E0QtL_es8F0&pp=ygUQS2VtYXJvIElzbGFuZCA0Sw%3D%3D'),
(21, 7, 'Sultan Mahmud Badaruddin II Museum', 'Museum showcasing local history and culture.', 'sultan_museum.jpg', 400, -3.0021, 104.4118, 'https://www.youtube.com/watch?v=2E1YikShSEI&pp=ygUXU3VsdGFuIG1haG11ZCBtdXNldW0gNEs%3D'),
(22, 8, 'Indonesia University (UI)', 'Prominent university campus.', 'ui_campus.jpg', 600, -6.3666, 106.824, 'https://www.youtube.com/watch?v=bHyHFFB78p8&pp=ygUMVUkgY2FtcHVzIDRL'),
(23, 8, 'Depok Beach', 'Coastal area with recreational facilities.', 'depok_beach.jpg', 350, -6.3938, 106.824, 'https://www.youtube.com/watch?v=O8i03GdGmIc&pp=ygUORGVwb2sgQmVhY2ggNEs%3D'),
(24, 9, 'Benteng Heritage Museum', 'Museum showcasing local history and culture.', 'benteng_museum.jpg', 400, -7.2647, 112.7429, 'https://www.youtube.com/watch?v=nPGh4N4_RC0&pp=ygURQmVudGVuZyBNdXNldW0gNEs%3D'),
(25, 9, 'Alam Sutera', 'Integrated residential and business area.', 'alam_sutera.jpg', 650, -6.2244, 106.6393, 'https://www.youtube.com/watch?v=A5mZj1T4qko&pp=ygUOQWxhbSBTdXRlcmEgNEs%3D'),
(26, 9, 'AEON Mall BSD City', 'Shopping mall with various amenities.', 'aeon_mall.jpg', 550, 0, 0, ''),
(27, 10, 'Ocean Park BSD City', 'Amusement park and marine-themed attraction.', 'ocean_park.jpg', 500, 0, 0, ''),
(28, 10, 'AEON Mall BSD City', 'Shopping mall with various amenities.', 'aeon_mall2.jpg', 550, 0, 0, ''),
(29, 11, 'Grand Metropolitan Mall', 'Shopping mall with a variety of retail outlets.', 'grand_metropolitan.jpg', 300, 0, 0, ''),
(30, 11, 'Summarecon Bekasi', 'Integrated residential and business area.', 'summarecon_bekasi.jpg', 450, 0, 0, ''),
(31, 12, 'Padang Beach', 'Popular beach with vibrant local atmosphere.', 'padang_beach.jpg', 600, 0, 0, ''),
(32, 12, 'Siti Nurbaya Bridge', 'Iconic bridge with beautiful architecture.', 'siti_nurbaya_bridge.jpg', 350, 0, 0, ''),
(33, 13, 'Bajra Sandhi Monument', 'Monument commemorating Balinese struggle.', 'bajra_sandhi.jpg', 500, 0, 0, ''),
(34, 13, 'Pura Jagatnatha', 'Balinese Hindu temple in the city center.', 'pura_jagatnatha.jpg', 400, 0, 0, ''),
(35, 14, 'Kemala Beach', 'Popular beach with scenic views.', 'kemala_beach.jpg', 450, 0, 0, ''),
(36, 14, 'Manggar Segarasari Beach', 'Beach known for its tranquility and clear water.', 'manggar_segarasari.jpg', 300, 0, 0, ''),
(37, 15, 'Borobudur Temple', 'Largest Buddhist temple in the world.', 'borobudur_temple.jpg', 900, 0, 0, ''),
(38, 15, 'Prambanan Temple', 'Hindu temple compound with intricate architecture.', 'prambanan_temple.jpg', 800, 0, 0, ''),
(39, 15, 'Malioboro Street', 'Famous shopping street with cultural charm.', 'malioboro_street.jpg', 700, 0, 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `productID` varchar(500) NOT NULL,
  `pKey` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`productID`, `pKey`) VALUES
('Hotel1', 2),
('Hotel2', 2),
('Hotel3', 2),
('Hotel4', 2),
('Hotel5', 2),
('Ticket1', 1),
('Ticket2', 1),
('Ticket3', 1),
('Ticket4', 1),
('Ticket5', 1),
('Tour1', 3),
('Tour2', 3),
('Tour3', 3),
('Tour4', 3),
('Tour5', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `ticketID` varchar(500) NOT NULL,
  `productID` varchar(500) NOT NULL,
  `tcType` varchar(10) NOT NULL,
  `tcName` varchar(300) NOT NULL,
  `tcDate` date NOT NULL,
  `tcDepartureTime` time NOT NULL,
  `tcFrom` int(11) NOT NULL,
  `tcDestination` int(11) NOT NULL,
  `tcTravelTime` time NOT NULL,
  `tcPrice` int(11) NOT NULL,
  `tcSeat` int(11) NOT NULL,
  `tcImage` varchar(500) NOT NULL,
  `tcVendor` varchar(500) NOT NULL,
  `tcRating` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`ticketID`, `productID`, `tcType`, `tcName`, `tcDate`, `tcDepartureTime`, `tcFrom`, `tcDestination`, `tcTravelTime`, `tcPrice`, `tcSeat`, `tcImage`, `tcVendor`, `tcRating`) VALUES
('TICKET1', 'Ticket1', 'Plane', 'Plane Ticket Jakarta to Surabaya', '2023-12-10', '08:00:00', 1, 2, '02:30:00', 500000, 50, 'plane_image1.jpg', 'Garuda Indonesia', 4.2),
('TICKET2', 'Ticket2', 'Train', 'Train Ticket Surabaya to Bandung', '2023-12-11', '10:30:00', 2, 3, '05:00:00', 250000, 50, 'train_image1.jpg', 'Kereta Api Indonesia', 3.8),
('TICKET3', 'Ticket3', 'Plane', 'Plane Ticket Bandung to Medan', '2023-12-12', '12:45:00', 3, 4, '03:30:00', 700000, 40, 'plane_image2.jpg', 'Citilink Indonesia', 4.5),
('TICKET4', 'Ticket4', 'Train', 'Train Ticket Medan to Semarang', '2023-12-13', '15:00:00', 4, 5, '06:00:00', 300000, 60, 'train_image2.jpg', 'Kereta Api Indonesia', 3.2),
('TICKET5', 'Ticket5', 'Plane', 'Plane Ticket Semarang to Makassar', '2023-12-14', '18:30:00', 5, 6, '04:00:00', 600000, 45, 'plane_image3.jpg', 'Sriwijaya Air', 4.7);

-- --------------------------------------------------------

--
-- Table structure for table `tour_package`
--

CREATE TABLE `tour_package` (
  `tourID` varchar(500) NOT NULL,
  `productID` varchar(500) NOT NULL,
  `cityID` int(11) NOT NULL,
  `tpName` varchar(300) NOT NULL,
  `tpDate` date NOT NULL,
  `tpSchedulePlace` varchar(200) NOT NULL,
  `tpMeetingTime` time NOT NULL,
  `tpPrice` int(11) NOT NULL,
  `tpSlot` int(11) NOT NULL,
  `tpImage` varchar(100) NOT NULL,
  `tpVendor` varchar(500) NOT NULL,
  `tpRating` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tour_package`
--

INSERT INTO `tour_package` (`tourID`, `productID`, `cityID`, `tpName`, `tpDate`, `tpSchedulePlace`, `tpMeetingTime`, `tpPrice`, `tpSlot`, `tpImage`, `tpVendor`, `tpRating`) VALUES
('TOUR1', 'Tour1', 1, 'Historical Jakarta Tour', '2023-12-20', 'National Museum Jakarta', '09:00:00', 500000, 20, 'tour_image1.jpg', 'Jakarta Tours', 4.3),
('TOUR2', 'Tour2', 2, 'Cultural Surabaya Experience', '2023-12-21', 'House of Sampoerna', '10:30:00', 600000, 15, 'tour_image2.jpg', 'Surabaya Explorations', 4),
('TOUR3', 'Tour3', 3, 'Scenic Bandung Nature Trip', '2023-12-22', 'Tangkuban Perahu Crater', '08:00:00', 700000, 18, 'tour_image3.jpg', 'Bandung Adventures', 4.5),
('TOUR4', 'Tour4', 4, 'Historic Medan Heritage Tour', '2023-12-23', 'Maimun Palace', '11:00:00', 550000, 22, 'tour_image4.jpg', 'Medan Heritage Tours', 4.2),
('TOUR5', 'Tour5', 5, 'Cultural Semarang Exploration', '2023-12-24', 'Lawang Sewu', '09:30:00', 650000, 25, 'tour_image5.jpg', 'Semarang Discoveries', 4.1);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_details`
--

CREATE TABLE `transaction_details` (
  `transDetailsID` int(11) NOT NULL,
  `userID` varchar(500) NOT NULL,
  `productID` varchar(500) NOT NULL,
  `tdAmount` int(11) NOT NULL,
  `tdTotalPrice` int(11) NOT NULL,
  `paymentMethod` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction_details`
--

INSERT INTO `transaction_details` (`transDetailsID`, `userID`, `productID`, `tdAmount`, `tdTotalPrice`, `paymentMethod`) VALUES
(2, 'tes', 'Ticket5', 1, 602000, 'BCA'),
(3, 'tes', 'Ticket5', 1, 602000, 'BCA'),
(4, 'tes', 'Ticket1', 3, 1502000, 'BCA'),
(5, 'admin', 'Ticket1', 1, 502000, 'BCA'),
(6, 'admin', 'Ticket5', 20, 12002000, 'Mandiri'),
(7, 'admin', 'Ticket1', 1, 502000, 'BCA'),
(8, 'tes', 'Ticket1', 2, 1002000, 'BCA'),
(9, 'tes', 'Ticket1', 2, 1002000, 'BCA'),
(10, 'tes', 'Ticket1', 2, 1002000, 'BCA'),
(11, 'admin', 'Hotel1', 2, 2002000, 'BCA');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` varchar(500) NOT NULL,
  `uName` varchar(150) NOT NULL,
  `uPassword` varchar(100) NOT NULL,
  `uAddress` varchar(2000) DEFAULT NULL,
  `uPhone` varchar(100) DEFAULT NULL,
  `uEmail` varchar(100) DEFAULT NULL,
  `uBirthdate` date DEFAULT NULL,
  `uProfilePicture` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `uName`, `uPassword`, `uAddress`, `uPhone`, `uEmail`, `uBirthdate`, `uProfilePicture`) VALUES
('admin', 'admin', 'admin', NULL, '', '', NULL, NULL),
('tes', 'tes', 'tes', NULL, '', '', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`cityID`);

--
-- Indexes for table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`hotelID`),
  ADD KEY `product_fk_hotel` (`productID`),
  ADD KEY `cityID` (`cityID`);

--
-- Indexes for table `locations`
--
ALTER TABLE `locations`
  ADD PRIMARY KEY (`locID`),
  ADD KEY `city_fk` (`cityID`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`productID`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`ticketID`),
  ADD KEY `product_fk` (`productID`),
  ADD KEY `tcFrom` (`tcFrom`),
  ADD KEY `tcDestination` (`tcDestination`);

--
-- Indexes for table `tour_package`
--
ALTER TABLE `tour_package`
  ADD PRIMARY KEY (`tourID`),
  ADD KEY `product_fk_tour` (`productID`),
  ADD KEY `cityID` (`cityID`);

--
-- Indexes for table `transaction_details`
--
ALTER TABLE `transaction_details`
  ADD PRIMARY KEY (`transDetailsID`),
  ADD KEY `product_fk_transDetail` (`productID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `cityID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `locations`
--
ALTER TABLE `locations`
  MODIFY `locID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `transaction_details`
--
ALTER TABLE `transaction_details`
  MODIFY `transDetailsID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`cityID`) REFERENCES `city` (`cityID`),
  ADD CONSTRAINT `product_fk_hotel` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`);

--
-- Constraints for table `locations`
--
ALTER TABLE `locations`
  ADD CONSTRAINT `city_fk` FOREIGN KEY (`cityID`) REFERENCES `city` (`cityID`),
  ADD CONSTRAINT `locations_ibfk_1` FOREIGN KEY (`cityID`) REFERENCES `city` (`cityID`);

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `product_fk` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`),
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`tcFrom`) REFERENCES `city` (`cityID`),
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`tcDestination`) REFERENCES `city` (`cityID`);

--
-- Constraints for table `tour_package`
--
ALTER TABLE `tour_package`
  ADD CONSTRAINT `product_fk_tour` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`),
  ADD CONSTRAINT `tour_package_ibfk_1` FOREIGN KEY (`cityID`) REFERENCES `city` (`cityID`);

--
-- Constraints for table `transaction_details`
--
ALTER TABLE `transaction_details`
  ADD CONSTRAINT `product_fk_transDetail` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
