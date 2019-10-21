-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.8-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for ordermanagement
CREATE DATABASE IF NOT EXISTS `ordermanagement` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ordermanagement`;

-- Dumping structure for table ordermanagement.client
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `client_name` char(50) DEFAULT NULL,
  `client_age` int(11) DEFAULT NULL,
  KEY `PRIMARY KEY` (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table ordermanagement.client: ~4 rows (approximately)
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`id_client`, `client_name`, `client_age`) VALUES
	(1, 'Dan', 21),
	(2, 'Ion', 28),
	(3, 'Bogdan', 35),
	(4, 'Vlad', 45);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;

-- Dumping structure for table ordermanagement.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `order_client` char(50) DEFAULT NULL,
  `order_product` char(50) DEFAULT NULL,
  `order_price` int(11) DEFAULT NULL,
  KEY `PRIMARY KEY` (`id_order`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table ordermanagement.orders: ~0 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table ordermanagement.product
CREATE TABLE IF NOT EXISTS `product` (
  `id_product` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_name` char(50) DEFAULT NULL,
  `product_price` int(11) DEFAULT NULL,
  `product_count` int(11) DEFAULT NULL,
  KEY `PRIMARY KEY` (`id_product`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table ordermanagement.product: ~2 rows (approximately)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id_product`, `product_name`, `product_price`, `product_count`) VALUES
	(1, 'Cola', 5, 47),
	(2, 'Lays', 3, 66);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
