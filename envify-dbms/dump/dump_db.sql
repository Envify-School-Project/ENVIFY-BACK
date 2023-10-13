
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `envify_database`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `envify_database` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;

USE `envify_database`;

--
-- Table structure for table `config_packages`
--

DROP TABLE IF EXISTS `config_packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config_packages` (
  `config_id` int(11) NOT NULL,
  `package_version_id` int(11) NOT NULL,
  `configuration_scripts` longtext DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`config_id`,`package_version_id`),
  KEY `package_version_id` (`package_version_id`),
  CONSTRAINT `config_packages_ibfk_1` FOREIGN KEY (`config_id`) REFERENCES `configs` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `config_packages_ibfk_2` FOREIGN KEY (`package_version_id`) REFERENCES `package_versions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config_packages`
--

LOCK TABLES `config_packages` WRITE;
/*!40000 ALTER TABLE `config_packages` DISABLE KEYS */;
/*!40000 ALTER TABLE `config_packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configs`
--

DROP TABLE IF EXISTS `configs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `operating_system_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `operating_system_id` (`operating_system_id`),
  CONSTRAINT `configs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `configs_ibfk_2` FOREIGN KEY (`operating_system_id`) REFERENCES `operating_systems` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configs`
--

LOCK TABLES `configs` WRITE;
/*!40000 ALTER TABLE `configs` DISABLE KEYS */;
INSERT INTO `configs` VALUES
(3,'testFront','premier test front',1,1,'2023-10-11 12:16:13','2023-10-11 12:16:13'),
(4,'config test',NULL,1,1,'2023-10-11 18:44:52','2023-10-11 18:44:52'),
(5,'config test',NULL,1,1,'2023-10-11 18:46:20','2023-10-11 18:46:20'),
(6,'config test',NULL,1,1,'2023-10-11 18:46:57','2023-10-11 18:46:57');
/*!40000 ALTER TABLE `configs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_configs`
--

DROP TABLE IF EXISTS `favorite_configs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_configs` (
  `user_id` int(11) NOT NULL,
  `config_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`user_id`,`config_id`),
  KEY `config_id` (`config_id`),
  CONSTRAINT `favorite_configs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `favorite_configs_ibfk_2` FOREIGN KEY (`config_id`) REFERENCES `configs` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_configs`
--

LOCK TABLES `favorite_configs` WRITE;
/*!40000 ALTER TABLE `favorite_configs` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite_configs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operating_system_versions`
--

DROP TABLE IF EXISTS `operating_system_versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operating_system_versions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version_number` varchar(255) NOT NULL,
  `operating_system_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `operating_system_id` (`operating_system_id`),
  CONSTRAINT `operating_system_versions_ibfk_1` FOREIGN KEY (`operating_system_id`) REFERENCES `operating_systems` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operating_system_versions`
--

LOCK TABLES `operating_system_versions` WRITE;
/*!40000 ALTER TABLE `operating_system_versions` DISABLE KEYS */;
INSERT INTO `operating_system_versions` VALUES
(1,'20.04',1,'2023-10-07 14:39:34','2023-10-07 14:39:34'),
(2,'22.04',1,'2023-10-07 14:39:53','2023-10-07 14:39:53'),
(3,'23.04',1,'2023-10-07 14:41:16','2023-10-07 14:41:16'),
(4,'12',2,'2023-10-07 14:42:05','2023-10-07 14:42:05'),
(5,'11',2,'2023-10-07 14:42:10','2023-10-07 14:42:10'),
(6,'10',2,'2023-10-07 14:42:17','2023-10-07 14:42:17'),
(7,'8',3,'2023-10-07 14:58:24','2023-10-07 14:58:24'),
(8,'7',3,'2023-10-07 14:58:29','2023-10-07 14:58:29');
/*!40000 ALTER TABLE `operating_system_versions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operating_systems`
--

DROP TABLE IF EXISTS `operating_systems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operating_systems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operating_systems`
--

LOCK TABLES `operating_systems` WRITE;
/*!40000 ALTER TABLE `operating_systems` DISABLE KEYS */;
INSERT INTO `operating_systems` VALUES
(1,'Ubuntu'),
(2,'Debian'),
(3,'CentOs');
/*!40000 ALTER TABLE `operating_systems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_config_files`
--

DROP TABLE IF EXISTS `package_config_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `package_config_files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `properties` longtext DEFAULT NULL,
  `package_version_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `package_version_id` (`package_version_id`),
  CONSTRAINT `package_config_files_ibfk_1` FOREIGN KEY (`package_version_id`) REFERENCES `package_versions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_config_files`
--

LOCK TABLES `package_config_files` WRITE;
/*!40000 ALTER TABLE `package_config_files` DISABLE KEYS */;
INSERT INTO `package_config_files` VALUES
(7,'MariaDB 50-server.cnf config file','[{\"category\":\"mysqld\",\"field\":\"port\",\"type\":\"number\",\"label\":\"Port\",\"value\":\"3306\"},{\"category\":\"mysqld\",\"field\":\"datadir\",\"type\":\"text\",\"label\":\"Data directory\",\"value\":\"/var/lib/mysql\"},{\"category\":\"mysqld\",\"field\":\"max_connections\",\"type\":\"number\",\"label\":\"Max Connections\",\"value\":\"100\"},{\"category\":\"mysqld\",\"field\":\"defaultStorageEngine\",\"type\":\"text\",\"label\":\"Default Storage Engine\",\"value\":\"InnoDB\"},{\"category\":\"mysqld\",\"field\":\"bindAdress\",\"type\":\"text\",\"label\":\"Bind Adress\",\"value\":\"0.0.0.0\"}]',5),
(8,'MariaDB 50-server.cnf config file','[{\"category\":\"mysqld\",\"field\":\"port\",\"type\":\"number\",\"label\":\"Port\",\"value\":\"3306\"},{\"category\":\"mysqld\",\"field\":\"datadir\",\"type\":\"text\",\"label\":\"Data directory\",\"value\":\"/var/lib/mysql\"},{\"category\":\"mysqld\",\"field\":\"max_connections\",\"type\":\"number\",\"label\":\"Max Connections\",\"value\":\"100\"},{\"category\":\"mysqld\",\"field\":\"defaultStorageEngine\",\"type\":\"text\",\"label\":\"Default Storage Engine\",\"value\":\"InnoDB\"},{\"category\":\"mysqld\",\"field\":\"bindAdress\",\"type\":\"text\",\"label\":\"Bind Adress\",\"value\":\"0.0.0.0\"}]',6),
(9,'MariaDB 50-server.cnf config file','[{\"category\":\"mysqld\",\"field\":\"port\",\"type\":\"number\",\"label\":\"Port\",\"value\":\"3306\"},{\"category\":\"mysqld\",\"field\":\"datadir\",\"type\":\"text\",\"label\":\"Data directory\",\"value\":\"/var/lib/mysql\"},{\"category\":\"mysqld\",\"field\":\"max_connections\",\"type\":\"number\",\"label\":\"Max Connections\",\"value\":\"100\"},{\"category\":\"mysqld\",\"field\":\"defaultStorageEngine\",\"type\":\"text\",\"label\":\"Default Storage Engine\",\"value\":\"InnoDB\"},{\"category\":\"mysqld\",\"field\":\"bindAdress\",\"type\":\"text\",\"label\":\"Bind Adress\",\"value\":\"0.0.0.0\"}]',7),
(11,'Nginx nginx.conf config file','[{\"category\":\"events\",\"field\":\"workerConnection\",\"type\":\"number\",\"label\":\"Worker Connection\",\"value\":\"768\"},{\"category\":\"events\",\"field\":\"multiAccept\",\"type\":\"select\",\"label\":\"Multi Accept\",\"items\":[\"on\",\"off\"],\"value\":\"on\"},{\"category\":\"http\",\"field\":\"server\",\"type\":\"multiple\",\"label\":\"Servers\",\"properties\":[{\"label\":\"Listen\",\"field\":\"listen\",\"type\":\"number\",\"value\":\"80\"},{\"label\":\"Server Name\",\"field\":\"server_name\",\"type\":\"text\",\"value\":\"domain1.com www.domain1.com\"},{\"label\":\"Access Log\",\"field\":\"access_log\",\"type\":\"text\",\"value\":\"logs/domain1.access.log main\"},{\"label\":\"Root\",\"field\":\"root\",\"type\":\"text\",\"value\":\"html\"},{\"label\":\"Location path\",\"field\":\"location_path\",\"type\":\"text\",\"value\":\"/\"},{\"label\":\"Location root\",\"field\":\"location_root\",\"type\":\"text\",\"value\":\"/\"},{\"label\":\"Location index\",\"field\":\"location_index\",\"type\":\"text\",\"value\":\"index.html\"}],\"value\":[]},{\"category\":\"http\",\"field\":\"sendFile\",\"type\":\"select\",\"label\":\"Send File\",\"items\":[\"on\",\"off\"],\"value\":\"on\"},{\"category\":\"http\",\"field\":\"tcpNoPush\",\"type\":\"select\",\"label\":\"TCP No Push\",\"items\":[\"on\",\"off\"],\"value\":\"on\"},{\"category\":\"http\",\"field\":\"aliveTimeout\",\"type\":\"number\",\"label\":\"Alive Timeout\",\"value\":\"65\"},{\"category\":\"http\",\"field\":\"hashMaxSize\",\"type\":\"number\",\"label\":\"Hash Max Size\",\"value\":\"2048\"},{\"category\":\"http\",\"field\":\"serverTokens\",\"type\":\"select\",\"label\":\"Server Tokens\",\"items\":[\"on\",\"off\"],\"value\":\"off\"},{\"category\":\"http\",\"field\":\"hashBucketSize\",\"type\":\"number\",\"label\":\"Hash Bucket Size\",\"value\":\"64\"},{\"category\":\"http\",\"field\":\"redirection\",\"type\":\"select\",\"label\":\"Redirection\",\"items\":[\"on\",\"off\"],\"value\":\"off\"},{\"category\":\"http\",\"field\":\"sslCipher\",\"type\":\"select\",\"label\":\"SSL Cipher\",\"items\":[\"on\",\"off\"],\"value\":\"on\"},{\"category\":\"http\",\"field\":\"gzip\",\"type\":\"select\",\"label\":\"Gzip\",\"items\":[\"on\",\"off\"],\"value\":\"off\"}]',12),
(12,'Apache httpd.conf config file','[{\"category\":\"\",\"field\":\"keepAlive\",\"type\":\"select\",\"label\":\"Keep Alive\",\"items\":[\"On\",\"Off\"],\"value\":\"On\"},{\"category\":\"\",\"field\":\"maxKeepAliveRequests\",\"type\":\"number\",\"label\":\"Max Keep Alive Requests\",\"value\":\"100\"},{\"category\":\"\",\"field\":\"keepAliveTimeout\",\"type\":\"number\",\"label\":\"Keep Alive Timeout\",\"value\":\"5\"},{\"category\":\"\",\"field\":\"logLevel\",\"type\":\"select\",\"label\":\"Log Level\",\"items\":[\"warn\",\"error\",\"alert\",\"crit\",\"emerg\",\"notice\",\"info\",\"debug\"],\"value\":\"warn\"}]',26);
/*!40000 ALTER TABLE `package_config_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package_versions`
--

DROP TABLE IF EXISTS `package_versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `package_versions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version_number` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version_status_id` int(11) NOT NULL,
  `package_id` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `version_status_id` (`version_status_id`),
  KEY `package_id` (`package_id`),
  CONSTRAINT `package_versions_ibfk_1` FOREIGN KEY (`version_status_id`) REFERENCES `version_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `package_versions_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `packages` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package_versions`
--

LOCK TABLES `package_versions` WRITE;
/*!40000 ALTER TABLE `package_versions` DISABLE KEYS */;
INSERT INTO `package_versions` VALUES
(5,'11.1','https://mariadb.org/mariadb/all-releases/',1,5,'2023-10-08 17:21:53','2023-10-08 17:21:53'),
(6,'10.11','https://mariadb.org/mariadb/all-releases/',1,5,'2023-10-08 17:21:56','2023-10-08 17:21:56'),
(7,'5.5','https://mariadb.org/mariadb/all-releases/',1,5,'2023-10-08 17:21:59','2023-10-08 17:21:59'),
(8,'20','https://nodejs.org/en/download/releases',1,6,'2023-10-08 17:22:22','2023-10-08 17:22:22'),
(9,'18','https://nodejs.org/en/download/releases',1,6,'2023-10-08 17:22:31','2023-10-08 17:22:31'),
(10,'16','https://nodejs.org/en/download/releases',1,6,'2023-10-08 17:22:34','2023-10-08 17:22:34'),
(11,'14','https://nodejs.org/en/download/releases',1,6,'2023-10-08 17:22:37','2023-10-08 17:22:37'),
(12,'latest','http://nginx.org/en/download.html',1,7,'2023-10-08 17:24:46','2023-10-08 17:24:46'),
(13,'7.4','https://www.php.net/supported-versions.php',1,8,'2023-10-08 17:25:27','2023-10-08 17:25:27'),
(14,'8.0','https://www.php.net/supported-versions.php',1,8,'2023-10-08 17:25:33','2023-10-08 17:25:33'),
(15,'8.1','https://www.php.net/supported-versions.php',1,8,'2023-10-08 17:25:36','2023-10-08 17:25:36'),
(16,'8.2','https://www.php.net/supported-versions.php',1,8,'2023-10-08 17:25:39','2023-10-08 17:25:39'),
(17,'16','https://www.postgresql.org/docs/release/',1,9,'2023-10-08 17:26:19','2023-10-08 17:26:19'),
(18,'15','https://www.postgresql.org/docs/release/',1,9,'2023-10-08 17:26:23','2023-10-08 17:26:23'),
(19,'14','https://www.postgresql.org/docs/release/',1,9,'2023-10-08 17:26:26','2023-10-08 17:26:26'),
(20,'13','https://www.postgresql.org/docs/release/',1,9,'2023-10-08 17:26:30','2023-10-08 17:26:30'),
(21,'21','https://www.java.com/releases/',1,10,'2023-10-08 17:27:16','2023-10-08 17:27:16'),
(22,'20','https://www.java.com/releases/',1,10,'2023-10-08 17:27:19','2023-10-08 17:27:19'),
(23,'19','https://www.java.com/releases/',1,10,'2023-10-08 17:27:22','2023-10-08 17:27:22'),
(24,'18','https://www.java.com/releases/',1,10,'2023-10-08 17:27:26','2023-10-08 17:27:26'),
(25,'17','https://www.java.com/releases/',1,10,'2023-10-08 17:27:29','2023-10-08 17:27:29'),
(26,'latest','https://httpd.apache.org/',1,11,'2023-10-08 17:27:54','2023-10-08 17:27:54');
/*!40000 ALTER TABLE `package_versions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packages`
--

DROP TABLE IF EXISTS `packages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `packages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packages`
--

LOCK TABLES `packages` WRITE;
/*!40000 ALTER TABLE `packages` DISABLE KEYS */;
INSERT INTO `packages` VALUES
(5,'MariaDB','2023-10-08 17:11:06','2023-10-08 17:11:06'),
(6,'NodeJS','2023-10-08 17:11:13','2023-10-08 17:11:13'),
(7,'Nginx','2023-10-08 17:11:20','2023-10-08 17:11:20'),
(8,'Php','2023-10-08 17:11:45','2023-10-08 17:11:45'),
(9,'Postgres','2023-10-08 17:13:38','2023-10-08 17:13:38'),
(10,'Java','2023-10-08 17:13:41','2023-10-08 17:13:41'),
(11,'Apache','2023-10-08 17:13:48','2023-10-08 17:13:48'),
(12,'Certbot','2023-10-08 17:15:06','2023-10-08 17:15:06');
/*!40000 ALTER TABLE `packages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'user',
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES
(1,'envifyadmin','envify','admin','envifyadmin@gmail.com','$2a$10$DpE7LZBYkD2.zWZYxPI/lODnv8sxS7PZsAsP3GtB5TeFhycuFYMEu','Envify','user','2023-10-07 13:55:35','2023-10-07 13:55:35'),
(2,NULL,NULL,NULL,'teslmkltlmlilmimpjmllùwmtmmj00@gmail.com','$2a$10$lyOwyxzJX9DWxSDkf99ps.LY85L/zfqxwhIwJStDxB9CkKbqdxXTm',NULL,'user','2023-10-07 14:51:08','2023-10-07 14:51:08'),
(3,NULL,NULL,NULL,'teslmkltlmlilmimpyjmllùwmtmmj00@gmail.com','$2a$10$viUIeCM5UAeqwBNIpxtpGOnVZ4N3sM6QvveCh7fqekTfiV/c2O6y2',NULL,'user','2023-10-07 14:55:31','2023-10-07 14:55:31'),
(4,NULL,NULL,NULL,'teslmklwmtmmj00@gmail.com','$2a$10$pNpkB9OmlQap7EVb0ak1MO7lwJ73kbDWUnHY1jlX1xm2bonrRw7hW',NULL,'user','2023-10-07 14:57:20','2023-10-07 14:57:20'),
(5,NULL,NULL,NULL,'teslmklwmtmmj00@gmail.lcom','$2a$10$HnD22tA80/p6wMmLxkscBOy2ecV1pSVfB81jnYM6l93goSFZgHZFG',NULL,'user','2023-10-07 14:58:12','2023-10-07 14:58:12'),
(6,NULL,NULL,NULL,'jneaeaeaea@gmail.com','$2a$10$RS7SW95/w9TsHqCjMRcNku6.Zi.nVc0CQZEHKunFOTqWhEnUT3us.',NULL,'user','2023-10-08 12:57:43','2023-10-08 12:57:43'),
(7,NULL,NULL,NULL,'jneaeaeaeasss@gmail.com','$2a$10$KarAFE3lSXUeI3euwYGocuJoO20th2o.MbHYsHUJt4hZA0LslO2Iy',NULL,'user','2023-10-08 12:58:04','2023-10-08 12:58:04'),
(8,NULL,NULL,NULL,'jneaedsaeaeasss@gmail.com','$2a$10$KqtIzY0dN.ggy/eK6ID6CO8O6jMP2PzMIxk0k6ArpKwcsDuklECju',NULL,'user','2023-10-08 12:58:20','2023-10-08 12:58:20'),
(9,NULL,NULL,NULL,'souiharis@gmail.com','$2a$10$LAgRJKUtkZDf8a9E69kQCeVRWrgHM0bC7U86hK6D6HjV2KijfuYB2',NULL,'user','2023-10-11 12:08:57','2023-10-11 12:08:57'),
(10,NULL,NULL,NULL,'ddsd@gmail.com','$2a$10$xNTmgA/xrt5fqWAb0y.d0e3x2mP4v5F9QZcWS/rY/uYavK.SR3U6C',NULL,'user','2023-10-11 12:10:03','2023-10-11 12:10:03'),
(11,NULL,NULL,NULL,'iliestest2@gmail.com','$2a$10$GKnpvLFgv7SNlBJYzuKzfOTsrluDGZ1uByhOYngJ/qviZPGE6Qs.y',NULL,'user','2023-10-11 12:10:34','2023-10-11 12:10:34'),
(12,NULL,NULL,NULL,'idsliestest2@gmail.com','$2a$10$OxVCW7XaFkMOZBijltx6vOBG7A.exmi9/OEFVaDdtm8h8x9kxDzcu',NULL,'user','2023-10-11 12:14:36','2023-10-11 12:14:36'),
(13,NULL,NULL,NULL,'iddssliestest2@gmail.com','$2a$10$s7duscdul0B/QUrLTvl4T.ADJoI4J.PamR3zjnf3hJ1g9a/6cMTF.',NULL,'user','2023-10-11 12:14:57','2023-10-11 12:14:57'),
(14,NULL,NULL,NULL,'iliemùklmstest2@gmail.com','$2a$10$hS0WH2Yt5Hbjr8px08QW0.XuD0oPTN/MDLerpE3yxE7ctButcptdC',NULL,'user','2023-10-11 12:17:29','2023-10-11 12:17:29'),
(15,'angegoua',NULL,NULL,'ange.goua01@gmail.com','$2a$10$QMrO8s1vC7QNqAfWJP6qxeiklnlCYcKSTyGNX5wsfRUjdWfDJbkDa','','user','2023-10-12 13:08:14','2023-10-12 13:08:14'),
(16,'test',NULL,NULL,'test@gmail.com','$2a$10$v4vKBqeHwLJa7BeEOlsStO9gyABe6Jcr2tWZ4lJIcpaMEwpqeOlrO',NULL,'user','2023-10-12 13:46:00','2023-10-12 13:46:00'),
(17,'test','test','test','test@gamail.com','$2a$10$2dUvxT3fedR7xEdi/NDUweFdpMJg5jNNZDS8BYxclhdh3PzlaquW6','test','user','2023-10-12 15:07:05','2023-10-12 15:07:05'),
(18,'fzkafzafziubf@gmail.com','fzkafzafziubf@gmail.com','fzkafzafziubf@gmail.com','fzkafzafziubf@gmail.com','$2a$10$AyLkJJgIMNAZjhVwl/R9aeI2KOhL./GIqKbK60LWYLjiMlS.MBHp6','fzkafzafziubf@gmail.com','user','2023-10-12 15:15:20','2023-10-12 15:15:20'),
(19,'Simtax',NULL,NULL,'souici.haris@gmail.com','$2a$10$2LYUaxsKhYDgPkoXuEzYi.1fCDTaxLgKa7BBWL8glcRRm31rTGlpe',NULL,'user','2023-10-13 08:33:53','2023-10-13 08:33:53'),
(20,'SuggestedAccount','Souici','Haris','envifysuggestedaccount@envify.com','$2a$10$Ib4N9IfHbDBKHaHoAzPNIuE5TWS6i.ISDTXS1sdt6OYOn1OrzE9Ga','Envify','suggested','2023-10-13 08:35:16','2023-10-13 08:35:16');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER format_email_trigger
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    SET NEW.email = LOWER(NEW.email);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8mb3_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER format_role_trigger
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    SET NEW.role = LOWER(NEW.role);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `version_status`
--

DROP TABLE IF EXISTS `version_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `version_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `version_status`
--

LOCK TABLES `version_status` WRITE;
/*!40000 ALTER TABLE `version_status` DISABLE KEYS */;
INSERT INTO `version_status` VALUES
(1,'stable','2023-10-07 15:01:29','2023-10-07 15:01:29');
/*!40000 ALTER TABLE `version_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;