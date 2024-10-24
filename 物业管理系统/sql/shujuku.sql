-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: wuyeguanli
-- ------------------------------------------------------
-- Server version	5.7.31-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `id` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `number` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `park` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,1,'huguifif','小型车',NULL),(2,2,'sdasd','小型车',NULL),(3,2,'aisasd','小型车',NULL);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee`
--

DROP TABLE IF EXISTS `fee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee` (
  `id` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee`
--

LOCK TABLES `fee` WRITE;
/*!40000 ALTER TABLE `fee` DISABLE KEYS */;
INSERT INTO `fee` VALUES (1,1,'水费',11,'2024-10-22 16:00:00'),(4,2,'水费',1,'2024-10-22 16:00:00');
/*!40000 ALTER TABLE `fee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fix`
--

DROP TABLE IF EXISTS `fix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fix` (
  `id` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fix`
--

LOCK TABLES `fix` WRITE;
/*!40000 ALTER TABLE `fix` DISABLE KEYS */;
INSERT INTO `fix` VALUES (1,1,'水管维修',11,'2024-10-22 16:00:00','未处理'),(2,2,'门窗维修',11,'2024-10-23 16:00:00','未处理'),(3,2,'电路维修',11,'2024-10-23 16:00:00','未处理'),(4,2,'水管维修',11,'2024-10-23 16:00:00','未处理');
/*!40000 ALTER TABLE `fix` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ownerid` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `area` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1838665730 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
INSERT INTO `house` VALUES (1,1,'普通住宅',87,'爱互动啊实打实大','良好'),(2,1,'商业用房',100,'dasDASDAD','良好'),(3,1,'商业用房',100,'dasDASDAD','良好'),(4,1,'商业用房',12,'1212','良好'),(5,1,'普通住宅',121,'12121','良好'),(6,1,'普通住宅',12,'1212','良好'),(7,1,'普通住宅',121,'1211','良好'),(8,1,'商业用房',12,'1212','良好'),(9,1,'普通住宅',123,'1212','良好'),(10,1,'商业用房',111,'1111','良好'),(11,1,'商业用房',11,'11','一般'),(12,1,'商业用房',11,'111','良好'),(13,1,'普通住宅',11,'111','良好'),(14,1,'普通住宅',11,'11','良好'),(15,1,'普通住宅',11,'111','良好'),(16,1,'普通住宅',1,'1','良好'),(17,1,'普通住宅',11,'11','良好'),(18,1,'普通住宅',11111,'1','良好'),(19,1,'普通住宅',11,'11','良好'),(20,1,'商业用房',11,'sjada','良好'),(21,1,'普通住宅',11,'12','良好'),(22,1,'普通住宅',111,'111','良好'),(23,1,'普通住宅',111,'111','良好'),(24,1,'普通住宅',111,'11','良好'),(25,2,'商业用房',22,'22','良好'),(26,1,'普通住宅',11,'1111','良好'),(27,1,'普通住宅',111,'111','良好'),(28,1,'普通住宅',111,'11','良好'),(29,1,'商业用房',111,'1111','良好'),(30,1,'商业用房',111,'111','良好'),(31,1,'普通住宅',11,'111','良好'),(34,1,'普通住宅',11,'11','一般'),(35,1,'普通住宅',11,'11','良好'),(36,1,'普通住宅',111,'111','良好'),(38,1,'普通住宅',11,'11','一般'),(109,1,'普通住宅',1,'1','良好'),(111,11,'普通住宅',1,'1','良好'),(112,1,'普通住宅',1,'1','良好'),(113,1,'普通住宅',11,'11','良好'),(114,1,'普通住宅',11,'1','一般'),(115,1,'普通住宅',11,'11','良好'),(116,1,'普通住宅',1,'1','良好'),(212,1,'普通住宅',1,'1','良好'),(229,2,'商业用房',22,'22','一般'),(444,1,'普通住宅',111,'212饿2饿','良好'),(659,1,'普通住宅',1,'1','良好'),(765,1,'普通住宅',11,'1','良好'),(786,1,'普通住宅',1,'1','良好'),(888,1,'普通住宅',88,'77','良好'),(996,1,'商业用房',99,'kk','良好'),(997,1,'商业用房',88,'kk','良好'),(998,1,'普通住宅',900,'kkk','良好'),(999,1,'普通住宅',99,'99','良好'),(1000,1,'普通住宅',1,'1','良好'),(1111,1,'工业用房',1,'1','良好'),(1160,1,'工业用房',88,'99','良好'),(11601,1,'工业用房',88,'99','良好'),(111111,1,'商业用房',1,'1','良好'),(111112,1,'商业用房',1,'1','良好'),(111113,1,'商业用房',1,'1','较差'),(111114,1,'普通住宅',1,'1','良好'),(1645727745,2,'工业用房',12212,'2121','良好'),(1838665729,3,'商业用房',1212,'121ewe1','一般');
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` tinyint(1) DEFAULT '0',
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','女','12893182',24,'12345678',1,'120921@qq.com'),(2,'user2','男','12131413',25,'12345678',0,'121212@qq.com'),(3,'1212','男','1212',NULL,'1212',0,'1212');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-24 14:46:20
