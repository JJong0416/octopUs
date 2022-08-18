-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 34.64.233.91    Database: octopus
-- ------------------------------------------------------
-- Server version	8.0.26-google

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'fc45a21a-1615-11ed-9228-42010ab20006:1-192718';

--
-- Table structure for table `BATCH_JOB_EXECUTION`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_EXECUTION` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `VERSION` bigint DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint NOT NULL,
  `CREATE_TIME` datetime(6) NOT NULL,
  `START_TIME` datetime(6) DEFAULT NULL,
  `END_TIME` datetime(6) DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime(6) DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `BATCH_JOB_INSTANCE` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION`
--

LOCK TABLES `BATCH_JOB_EXECUTION` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION` VALUES (1,2,1,'2022-08-16 09:01:08.468000','2022-08-16 09:01:08.835000','2022-08-16 09:01:12.709000','COMPLETED','COMPLETED','','2022-08-16 09:01:12.742000',NULL),(2,2,2,'2022-08-16 10:02:00.042000','2022-08-16 10:02:00.124000','2022-08-16 10:02:00.635000','COMPLETED','COMPLETED','','2022-08-16 10:02:00.636000',NULL),(3,2,3,'2022-08-16 10:03:00.021000','2022-08-16 10:03:00.039000','2022-08-16 10:03:00.299000','COMPLETED','COMPLETED','','2022-08-16 10:03:00.300000',NULL),(4,2,4,'2022-08-16 10:04:00.021000','2022-08-16 10:04:00.039000','2022-08-16 10:04:00.278000','COMPLETED','COMPLETED','','2022-08-16 10:04:00.279000',NULL),(5,2,5,'2022-08-16 10:06:00.071000','2022-08-16 10:06:00.148000','2022-08-16 10:06:00.645000','COMPLETED','COMPLETED','','2022-08-16 10:06:00.647000',NULL),(6,2,6,'2022-08-16 10:07:00.023000','2022-08-16 10:07:00.042000','2022-08-16 10:07:00.289000','COMPLETED','COMPLETED','','2022-08-16 10:07:00.290000',NULL),(7,2,7,'2022-08-16 10:08:00.021000','2022-08-16 10:08:00.039000','2022-08-16 10:08:00.239000','COMPLETED','COMPLETED','','2022-08-16 10:08:00.240000',NULL),(8,2,8,'2022-08-16 10:12:00.078000','2022-08-16 10:12:00.169000','2022-08-16 10:12:00.612000','COMPLETED','COMPLETED','','2022-08-16 10:12:00.613000',NULL),(9,2,10,'2022-08-16 10:13:00.026000','2022-08-16 10:13:00.133000','2022-08-16 10:13:00.510000','COMPLETED','COMPLETED','','2022-08-16 10:13:00.511000',NULL),(10,2,12,'2022-08-16 10:14:00.027000','2022-08-16 10:14:00.048000','2022-08-16 10:14:00.257000','COMPLETED','COMPLETED','','2022-08-16 10:14:00.258000',NULL),(11,2,14,'2022-08-16 10:15:00.020000','2022-08-16 10:15:00.037000','2022-08-16 10:15:00.229000','COMPLETED','COMPLETED','','2022-08-16 10:15:00.229000',NULL),(12,2,16,'2022-08-16 10:16:00.020000','2022-08-16 10:16:00.038000','2022-08-16 10:16:00.207000','COMPLETED','COMPLETED','','2022-08-16 10:16:00.207000',NULL),(13,2,18,'2022-08-16 10:17:00.020000','2022-08-16 10:17:00.036000','2022-08-16 10:17:00.204000','COMPLETED','COMPLETED','','2022-08-16 10:17:00.204000',NULL),(14,2,20,'2022-08-17 01:10:00.195000','2022-08-17 01:10:00.336000','2022-08-17 01:10:01.048000','COMPLETED','COMPLETED','','2022-08-17 01:10:01.052000',NULL),(15,2,21,'2022-08-17 02:34:00.091000','2022-08-17 02:34:00.187000','2022-08-17 02:34:00.628000','COMPLETED','COMPLETED','','2022-08-17 02:34:00.629000',NULL),(16,2,23,'2022-08-17 02:35:00.027000','2022-08-17 02:35:00.130000','2022-08-17 02:35:00.521000','COMPLETED','COMPLETED','','2022-08-17 02:35:00.522000',NULL),(17,2,25,'2022-08-17 02:36:00.031000','2022-08-17 02:36:00.053000','2022-08-17 02:36:00.280000','COMPLETED','COMPLETED','','2022-08-17 02:36:00.281000',NULL),(18,2,27,'2022-08-17 02:37:00.022000','2022-08-17 02:37:00.042000','2022-08-17 02:37:00.267000','COMPLETED','COMPLETED','','2022-08-17 02:37:00.268000',NULL),(19,2,29,'2022-08-17 02:38:00.021000','2022-08-17 02:38:00.038000','2022-08-17 02:38:00.267000','COMPLETED','COMPLETED','','2022-08-17 02:38:00.268000',NULL),(20,2,30,'2022-08-17 02:39:00.021000','2022-08-17 02:39:00.041000','2022-08-17 02:39:00.201000','COMPLETED','COMPLETED','','2022-08-17 02:39:00.202000',NULL),(21,2,31,'2022-08-17 02:40:00.021000','2022-08-17 02:40:00.041000','2022-08-17 02:40:00.221000','COMPLETED','COMPLETED','','2022-08-17 02:40:00.222000',NULL),(22,2,32,'2022-08-17 02:41:00.019000','2022-08-17 02:41:00.035000','2022-08-17 02:41:00.178000','COMPLETED','COMPLETED','','2022-08-17 02:41:00.179000',NULL);
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_EXECUTION_CONTEXT`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_CONTEXT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_EXECUTION_CONTEXT` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION_CONTEXT`
--

LOCK TABLES `BATCH_JOB_EXECUTION_CONTEXT` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_CONTEXT` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION_CONTEXT` VALUES (1,'{\"@class\":\"java.util.HashMap\"}',NULL),(2,'{\"@class\":\"java.util.HashMap\"}',NULL),(3,'{\"@class\":\"java.util.HashMap\"}',NULL),(4,'{\"@class\":\"java.util.HashMap\"}',NULL),(5,'{\"@class\":\"java.util.HashMap\"}',NULL),(6,'{\"@class\":\"java.util.HashMap\"}',NULL),(7,'{\"@class\":\"java.util.HashMap\"}',NULL),(8,'{\"@class\":\"java.util.HashMap\"}',NULL),(9,'{\"@class\":\"java.util.HashMap\"}',NULL),(10,'{\"@class\":\"java.util.HashMap\"}',NULL),(11,'{\"@class\":\"java.util.HashMap\"}',NULL),(12,'{\"@class\":\"java.util.HashMap\"}',NULL),(13,'{\"@class\":\"java.util.HashMap\"}',NULL),(14,'{\"@class\":\"java.util.HashMap\"}',NULL),(15,'{\"@class\":\"java.util.HashMap\"}',NULL),(16,'{\"@class\":\"java.util.HashMap\"}',NULL),(17,'{\"@class\":\"java.util.HashMap\"}',NULL),(18,'{\"@class\":\"java.util.HashMap\"}',NULL),(19,'{\"@class\":\"java.util.HashMap\"}',NULL),(20,'{\"@class\":\"java.util.HashMap\"}',NULL),(21,'{\"@class\":\"java.util.HashMap\"}',NULL),(22,'{\"@class\":\"java.util.HashMap\"}',NULL);
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_CONTEXT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_EXECUTION_PARAMS`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_PARAMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_EXECUTION_PARAMS` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime(6) DEFAULT NULL,
  `LONG_VAL` bigint DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION_PARAMS`
--

LOCK TABLES `BATCH_JOB_EXECUTION_PARAMS` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_PARAMS` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION_PARAMS` VALUES (1,'STRING','requestDate','2022-08-16 09:01:07','1970-01-01 09:00:00.000000',0,0,'Y'),(2,'STRING','requestDate','2022-08-16 10:02:00','1970-01-01 00:00:00.000000',0,0,'Y'),(3,'STRING','requestDate','2022-08-16 10:03:00','1970-01-01 00:00:00.000000',0,0,'Y'),(4,'STRING','requestDate','2022-08-16 10:04:00','1970-01-01 00:00:00.000000',0,0,'Y'),(5,'STRING','requestDate','2022-08-16 10:06:00','1970-01-01 00:00:00.000000',0,0,'Y'),(6,'STRING','requestDate','2022-08-16 10:07:00','1970-01-01 00:00:00.000000',0,0,'Y'),(7,'STRING','requestDate','2022-08-16 10:08:00','1970-01-01 00:00:00.000000',0,0,'Y'),(8,'STRING','requestDate','2022-08-16 10:12:00','1970-01-01 00:00:00.000000',0,0,'Y'),(9,'STRING','requestDate','2022-08-16 10:13:00','1970-01-01 00:00:00.000000',0,0,'Y'),(10,'STRING','requestDate','2022-08-16 10:14:00','1970-01-01 00:00:00.000000',0,0,'Y'),(11,'STRING','requestDate','2022-08-16 10:15:00','1970-01-01 00:00:00.000000',0,0,'Y'),(12,'STRING','requestDate','2022-08-16 10:16:00','1970-01-01 00:00:00.000000',0,0,'Y'),(13,'STRING','requestDate','2022-08-16 10:17:00','1970-01-01 00:00:00.000000',0,0,'Y'),(14,'STRING','requestDate','2022-08-17 01:10:00','1970-01-01 09:00:00.000000',0,0,'Y'),(15,'STRING','requestDate','2022-08-17 02:34:00','1970-01-01 00:00:00.000000',0,0,'Y'),(16,'STRING','requestDate','2022-08-17 02:35:00','1970-01-01 00:00:00.000000',0,0,'Y'),(17,'STRING','requestDate','2022-08-17 02:36:00','1970-01-01 00:00:00.000000',0,0,'Y'),(18,'STRING','requestDate','2022-08-17 02:37:00','1970-01-01 00:00:00.000000',0,0,'Y'),(19,'STRING','requestDate','2022-08-17 02:38:00','1970-01-01 00:00:00.000000',0,0,'Y'),(20,'STRING','requestDate','2022-08-17 02:39:00','1970-01-01 00:00:00.000000',0,0,'Y'),(21,'STRING','requestDate','2022-08-17 02:40:00','1970-01-01 00:00:00.000000',0,0,'Y'),(22,'STRING','requestDate','2022-08-17 02:41:00','1970-01-01 00:00:00.000000',0,0,'Y');
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_PARAMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_EXECUTION_SEQ`
--

DROP TABLE IF EXISTS `BATCH_JOB_EXECUTION_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_EXECUTION_SEQ` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_EXECUTION_SEQ`
--

LOCK TABLES `BATCH_JOB_EXECUTION_SEQ` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_SEQ` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_EXECUTION_SEQ` VALUES (22,'0');
/*!40000 ALTER TABLE `BATCH_JOB_EXECUTION_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_INSTANCE`
--

DROP TABLE IF EXISTS `BATCH_JOB_INSTANCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_INSTANCE` (
  `JOB_INSTANCE_ID` bigint NOT NULL,
  `VERSION` bigint DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_INSTANCE`
--

LOCK TABLES `BATCH_JOB_INSTANCE` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_INSTANCE` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_INSTANCE` VALUES (1,0,'changeMissionStatusJob','2da95ea0cbedf390390c5e520187101b'),(2,0,'changeMissionStatusJob','f4bb969d811fccc2372322f46c78c8ca'),(3,0,'changeMissionStatusJob','9d7f6ce580e879f08b954ee2a0ef3a91'),(4,0,'changeMissionStatusJob','0c7a12fd024f68bed778d1f178793334'),(5,0,'changeMissionStatusJob','55d9c870189859ef433e2a1b54f5a049'),(6,0,'changeMissionStatusJob','fafc8095820bdcad45fa091440762443'),(7,0,'changeMissionStatusJob','84ec15c6eb9cc4e67bdd992d188be69e'),(8,0,'changeMissionStatusJob','d83f90c8feb2ec50e1e02e1fff1f804e'),(10,0,'changeMissionStatusJob','a213e67fdeb71f76de71d08ca19e6a95'),(12,0,'changeMissionStatusJob','e579448773700433b0c243c053cf6ffd'),(14,0,'changeMissionStatusJob','0b8124b592d82c3bce5d0f82e7946dfe'),(16,0,'changeMissionStatusJob','0a5aebee9f1d8c7dc53ccbdf210c4586'),(18,0,'changeMissionStatusJob','f42bbb130210e826a5cd005095ef9337'),(20,0,'changeMissionStatusJob','163e4b28593351189f5ff1908c86ca56'),(21,0,'changeMissionStatusJob','c5024d1ea8172a4ccd0b1fc179a646f3'),(23,0,'changeMissionStatusJob','78a93741de6ac109c561d20944e3fd8a'),(25,0,'changeMissionStatusJob','c44ae616b3de236ea7f065d962c89d8b'),(27,0,'changeMissionStatusJob','55db6b4e568b9da99c3415770cf295f5'),(29,0,'changeMissionStatusJob','48efd6d4bab30e1eded074531ab0fbce'),(30,0,'changeMissionStatusJob','ce765b244073284532913ba1fa9c365a'),(31,0,'changeMissionStatusJob','956dd0956d52af7c074bf788bf3d2c0b'),(32,0,'changeMissionStatusJob','174bfeb67ae9720c189aece9434c6351');
/*!40000 ALTER TABLE `BATCH_JOB_INSTANCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_JOB_SEQ`
--

DROP TABLE IF EXISTS `BATCH_JOB_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_JOB_SEQ` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_JOB_SEQ`
--

LOCK TABLES `BATCH_JOB_SEQ` WRITE;
/*!40000 ALTER TABLE `BATCH_JOB_SEQ` DISABLE KEYS */;
INSERT INTO `BATCH_JOB_SEQ` VALUES (32,'0');
/*!40000 ALTER TABLE `BATCH_JOB_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_STEP_EXECUTION`
--

DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_STEP_EXECUTION` (
  `STEP_EXECUTION_ID` bigint NOT NULL,
  `VERSION` bigint NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `START_TIME` datetime(6) NOT NULL,
  `END_TIME` datetime(6) DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint DEFAULT NULL,
  `READ_COUNT` bigint DEFAULT NULL,
  `FILTER_COUNT` bigint DEFAULT NULL,
  `WRITE_COUNT` bigint DEFAULT NULL,
  `READ_SKIP_COUNT` bigint DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint DEFAULT NULL,
  `ROLLBACK_COUNT` bigint DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `BATCH_JOB_EXECUTION` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_STEP_EXECUTION`
--

LOCK TABLES `BATCH_STEP_EXECUTION` WRITE;
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION` DISABLE KEYS */;
INSERT INTO `BATCH_STEP_EXECUTION` VALUES (1,3,'toOngoingMissionStatusStep',1,'2022-08-16 09:01:09.698000','2022-08-16 09:01:10.695000','COMPLETED',1,1,0,1,0,0,0,0,'COMPLETED','','2022-08-16 09:01:10.737000'),(2,3,'toCloseMissionStatusStep',1,'2022-08-16 09:01:11.615000','2022-08-16 09:01:12.401000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 09:01:12.433000'),(3,3,'toOngoingMissionStatusStep',2,'2022-08-16 10:02:00.251000','2022-08-16 10:02:00.507000','COMPLETED',1,2,0,2,0,0,0,0,'COMPLETED','','2022-08-16 10:02:00.509000'),(4,3,'toCloseMissionStatusStep',2,'2022-08-16 10:02:00.562000','2022-08-16 10:02:00.616000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:02:00.618000'),(5,3,'toOngoingMissionStatusStep',3,'2022-08-16 10:03:00.089000','2022-08-16 10:03:00.182000','COMPLETED',1,2,0,2,0,0,0,0,'COMPLETED','','2022-08-16 10:03:00.185000'),(6,3,'toCloseMissionStatusStep',3,'2022-08-16 10:03:00.237000','2022-08-16 10:03:00.285000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:03:00.286000'),(7,3,'toOngoingMissionStatusStep',4,'2022-08-16 10:04:00.089000','2022-08-16 10:04:00.175000','COMPLETED',1,2,0,2,0,0,0,0,'COMPLETED','','2022-08-16 10:04:00.176000'),(8,3,'toCloseMissionStatusStep',4,'2022-08-16 10:04:00.229000','2022-08-16 10:04:00.264000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:04:00.266000'),(9,3,'toOngoingMissionStatusStep',5,'2022-08-16 10:06:00.253000','2022-08-16 10:06:00.511000','COMPLETED',1,2,0,2,0,0,0,0,'COMPLETED','','2022-08-16 10:06:00.512000'),(10,3,'toCloseMissionStatusStep',5,'2022-08-16 10:06:00.564000','2022-08-16 10:06:00.625000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:06:00.626000'),(11,3,'toOngoingMissionStatusStep',6,'2022-08-16 10:07:00.088000','2022-08-16 10:07:00.173000','COMPLETED',1,2,0,2,0,0,0,0,'COMPLETED','','2022-08-16 10:07:00.180000'),(12,3,'toCloseMissionStatusStep',6,'2022-08-16 10:07:00.235000','2022-08-16 10:07:00.276000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:07:00.277000'),(13,3,'toOngoingMissionStatusStep',7,'2022-08-16 10:08:00.081000','2022-08-16 10:08:00.133000','COMPLETED',1,2,0,2,0,0,0,0,'COMPLETED','','2022-08-16 10:08:00.134000'),(14,3,'toCloseMissionStatusStep',7,'2022-08-16 10:08:00.186000','2022-08-16 10:08:00.227000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:08:00.228000'),(15,3,'toOngoingMissionStatusStep',8,'2022-08-16 10:12:00.299000','2022-08-16 10:12:00.460000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:12:00.462000'),(16,3,'toCloseMissionStatusStep',8,'2022-08-16 10:12:00.521000','2022-08-16 10:12:00.593000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:12:00.595000'),(17,3,'toOngoingMissionStatusStep',9,'2022-08-16 10:13:00.235000','2022-08-16 10:13:00.380000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:13:00.382000'),(18,3,'toCloseMissionStatusStep',9,'2022-08-16 10:13:00.442000','2022-08-16 10:13:00.494000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:13:00.495000'),(19,3,'toOngoingMissionStatusStep',10,'2022-08-16 10:14:00.094000','2022-08-16 10:14:00.146000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:14:00.147000'),(20,3,'toCloseMissionStatusStep',10,'2022-08-16 10:14:00.194000','2022-08-16 10:14:00.240000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:14:00.241000'),(21,3,'toOngoingMissionStatusStep',11,'2022-08-16 10:15:00.099000','2022-08-16 10:15:00.143000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:15:00.144000'),(22,3,'toCloseMissionStatusStep',11,'2022-08-16 10:15:00.183000','2022-08-16 10:15:00.217000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:15:00.218000'),(23,3,'toOngoingMissionStatusStep',12,'2022-08-16 10:16:00.076000','2022-08-16 10:16:00.115000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:16:00.116000'),(24,3,'toCloseMissionStatusStep',12,'2022-08-16 10:16:00.157000','2022-08-16 10:16:00.194000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:16:00.195000'),(25,3,'toOngoingMissionStatusStep',13,'2022-08-16 10:17:00.072000','2022-08-16 10:17:00.113000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:17:00.114000'),(26,3,'toCloseMissionStatusStep',13,'2022-08-16 10:17:00.150000','2022-08-16 10:17:00.191000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-16 10:17:00.192000'),(27,3,'toOngoingMissionStatusStep',14,'2022-08-17 01:10:00.516000','2022-08-17 01:10:00.724000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 01:10:00.730000'),(28,3,'toCloseMissionStatusStep',14,'2022-08-17 01:10:00.878000','2022-08-17 01:10:01.001000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 01:10:01.006000'),(29,3,'toOngoingMissionStatusStep',15,'2022-08-17 02:34:00.309000','2022-08-17 02:34:00.478000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:34:00.480000'),(30,3,'toCloseMissionStatusStep',15,'2022-08-17 02:34:00.547000','2022-08-17 02:34:00.611000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:34:00.612000'),(31,3,'toOngoingMissionStatusStep',16,'2022-08-17 02:35:00.239000','2022-08-17 02:35:00.380000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:35:00.384000'),(32,3,'toCloseMissionStatusStep',16,'2022-08-17 02:35:00.445000','2022-08-17 02:35:00.502000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:35:00.503000'),(33,3,'toOngoingMissionStatusStep',17,'2022-08-17 02:36:00.102000','2022-08-17 02:36:00.150000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:36:00.151000'),(34,3,'toCloseMissionStatusStep',17,'2022-08-17 02:36:00.216000','2022-08-17 02:36:00.264000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:36:00.265000'),(35,3,'toOngoingMissionStatusStep',18,'2022-08-17 02:37:00.104000','2022-08-17 02:37:00.149000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:37:00.150000'),(36,3,'toCloseMissionStatusStep',18,'2022-08-17 02:37:00.202000','2022-08-17 02:37:00.252000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:37:00.253000'),(37,3,'toOngoingMissionStatusStep',19,'2022-08-17 02:38:00.106000','2022-08-17 02:38:00.148000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:38:00.149000'),(38,3,'toCloseMissionStatusStep',19,'2022-08-17 02:38:00.198000','2022-08-17 02:38:00.245000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:38:00.246000'),(39,3,'toOngoingMissionStatusStep',20,'2022-08-17 02:39:00.075000','2022-08-17 02:39:00.111000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:39:00.112000'),(40,3,'toCloseMissionStatusStep',20,'2022-08-17 02:39:00.148000','2022-08-17 02:39:00.188000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:39:00.189000'),(41,3,'toOngoingMissionStatusStep',21,'2022-08-17 02:40:00.081000','2022-08-17 02:40:00.120000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:40:00.121000'),(42,3,'toCloseMissionStatusStep',21,'2022-08-17 02:40:00.164000','2022-08-17 02:40:00.207000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:40:00.208000'),(43,3,'toOngoingMissionStatusStep',22,'2022-08-17 02:41:00.068000','2022-08-17 02:41:00.100000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:41:00.101000'),(44,3,'toCloseMissionStatusStep',22,'2022-08-17 02:41:00.132000','2022-08-17 02:41:00.167000','COMPLETED',1,0,0,0,0,0,0,0,'COMPLETED','','2022-08-17 02:41:00.168000');
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_STEP_EXECUTION_CONTEXT`
--

DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION_CONTEXT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_STEP_EXECUTION_CONTEXT` (
  `STEP_EXECUTION_ID` bigint NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `BATCH_STEP_EXECUTION` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_STEP_EXECUTION_CONTEXT`
--

LOCK TABLES `BATCH_STEP_EXECUTION_CONTEXT` WRITE;
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_CONTEXT` DISABLE KEYS */;
INSERT INTO `BATCH_STEP_EXECUTION_CONTEXT` VALUES (1,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":2,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(2,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(3,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":3,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(4,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(5,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":3,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(6,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(7,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":3,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(8,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(9,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":3,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(10,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(11,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":3,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(12,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(13,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":3,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(14,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(15,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(16,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(17,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(18,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(19,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(20,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(21,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(22,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(23,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(24,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(25,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(26,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(27,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(28,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(29,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(30,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(31,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(32,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(33,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(34,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(35,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(36,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(37,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(38,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(39,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(40,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(41,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(42,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(43,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL),(44,'{\"@class\":\"java.util.HashMap\",\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"JpaPagingItemReader.read.count\":1,\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}',NULL);
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_CONTEXT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BATCH_STEP_EXECUTION_SEQ`
--

DROP TABLE IF EXISTS `BATCH_STEP_EXECUTION_SEQ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BATCH_STEP_EXECUTION_SEQ` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BATCH_STEP_EXECUTION_SEQ`
--

LOCK TABLES `BATCH_STEP_EXECUTION_SEQ` WRITE;
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_SEQ` DISABLE KEYS */;
INSERT INTO `BATCH_STEP_EXECUTION_SEQ` VALUES (44,'0');
/*!40000 ALTER TABLE `BATCH_STEP_EXECUTION_SEQ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authentication_info`
--

DROP TABLE IF EXISTS `authentication_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authentication_info` (
  `authentication_no` bigint NOT NULL AUTO_INCREMENT,
  `authentication_end_time` time DEFAULT NULL,
  `authentication_start_time` time DEFAULT NULL,
  `mission_no` bigint DEFAULT NULL,
  PRIMARY KEY (`authentication_no`),
  KEY `FKg5ecm6ex0ln7xweajvqwhp6o6` (`mission_no`),
  CONSTRAINT `FKg5ecm6ex0ln7xweajvqwhp6o6` FOREIGN KEY (`mission_no`) REFERENCES `mission` (`mission_no`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authentication_info`
--

LOCK TABLES `authentication_info` WRITE;
/*!40000 ALTER TABLE `authentication_info` DISABLE KEYS */;
INSERT INTO `authentication_info` VALUES (1,'23:59:00','07:59:00',1),(2,'23:54:00','06:00:00',1),(3,'23:06:00','01:05:00',2),(4,'06:00:00','05:30:00',3),(5,'23:59:00','00:00:00',4),(6,'07:30:00','06:30:00',5),(7,'23:59:00','00:00:00',6),(8,'07:30:00','06:30:00',7),(9,'22:30:00','21:30:00',7),(10,'23:58:00','07:00:00',8),(11,'23:59:00','00:00:00',9),(12,'22:30:00','21:30:00',10),(13,'23:59:00','22:30:00',11),(14,'22:00:00','09:00:00',12),(15,'22:00:00','19:00:00',14),(16,'08:40:00','08:30:00',13),(17,'22:00:00','19:00:00',14),(18,'23:59:00','00:01:00',15),(19,'23:59:00','06:00:00',16),(20,'23:57:00','06:01:00',16),(21,'23:20:00','00:02:00',17),(22,'23:01:00','10:00:00',18),(23,'12:01:00','00:01:00',19),(24,'23:59:00','00:00:00',20);
/*!40000 ALTER TABLE `authentication_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_token`
--

DROP TABLE IF EXISTS `email_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email_token` (
  `email_code` varchar(6) NOT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `expired` bit(1) NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_token`
--

LOCK TABLES `email_token` WRITE;
/*!40000 ALTER TABLE `email_token` DISABLE KEYS */;
INSERT INTO `email_token` VALUES ('2Lc4Gq','2022-08-17 13:19:40',_binary '\0','amysumi@naver.com'),('32Fcdd','2022-08-17 13:22:30',_binary '\0','lapaho8645@naver.com'),('5j5nJW','2022-08-18 04:19:51',_binary '\0','yth0909@nate.com'),('5j6wNX','2022-08-17 13:20:02',_binary '\0','alex950107@gmail.com'),('8EqVsx','2022-08-18 06:56:54',_binary '\0','jesu6747@naver.com'),('AJgLkj','2022-08-18 03:53:25',_binary '\0','powerrudwns@gmail.com'),('B48at7','2022-08-17 13:17:56',_binary '\0','jjong.dev@gmail.com'),('CJK98t','2022-08-18 01:58:47',_binary '\0','powerrudwns@naver.com'),('EIxb97','2022-08-17 13:30:04',_binary '\0','rudwns06@hanmail.net'),('EQBp68','2022-08-18 04:12:29',_binary '\0','yth0909@nate.com'),('gOQGSt','2022-08-18 04:43:57',_binary '\0','whdcks5681@naver.com'),('Gv5uwC','2022-08-17 13:41:42',_binary '\0','jjong.dev@gmail.com'),('H2DpQq','2022-08-18 07:11:53',_binary '\0','yth0909@nate.com'),('iTElDP','2022-08-18 07:24:17',_binary '\0','yth0909@nate.com'),('kSmrey','2022-08-17 13:41:35',_binary '\0','lapaho8645@naver.com'),('OVhCIj','2022-08-18 01:50:15',_binary '\0','powerrudwns@naver.com'),('P2nlv1','2022-08-18 06:19:17',_binary '\0','iasfk@naver.com'),('QytVMo','2022-08-18 04:52:15',_binary '\0','iasfk@naver.com'),('rcxwQp','2022-08-18 04:12:09',_binary '\0',NULL),('rysoXt','2022-08-17 13:21:44',_binary '\0','lapaho8645@naver.com'),('S51f1L','2022-08-18 07:08:11',_binary '\0','octopus.ssafy@gmail.com'),('SQRGLY','2022-08-17 13:41:30',_binary '\0','alex950107@gmail.com'),('ucTotl','2022-08-17 13:50:20',_binary '\0','rudwns06@hanmail.net'),('x7yddI','2022-08-18 07:30:33',_binary '\0','amysumi10@gmail.com'),('xctEpR','2022-08-17 13:41:33',_binary '\0','amysumi@naver.com'),('ZMWDWd','2022-08-18 04:16:26',_binary '\0','yth0909@nate.com'),('zuBsTk','2022-08-18 04:50:56',_binary '\0','whdcks7275@gmail.com');
/*!40000 ALTER TABLE `email_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mission`
--

DROP TABLE IF EXISTS `mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mission` (
  `mission_no` bigint NOT NULL AUTO_INCREMENT,
  `mission_code` varchar(8) NOT NULL,
  `mission_content` varchar(300) NOT NULL,
  `mission_leader_id` varchar(15) NOT NULL,
  `mission_limit_personnel` int NOT NULL,
  `mission_open` int NOT NULL,
  `mission_point` int NOT NULL,
  `mission_status` int NOT NULL,
  `mission_title` varchar(30) NOT NULL,
  `mission_type` int NOT NULL,
  `mission_users` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`mission_no`),
  UNIQUE KEY `UK_eabix3atfpv993g1o312c9g37` (`mission_code`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mission`
--

LOCK TABLES `mission` WRITE;
/*!40000 ALTER TABLE `mission` DISABLE KEYS */;
INSERT INTO `mission` VALUES (1,'i4w5EqET','혼자 운동하려니깐 마음도 안먹어지고 자꾸  게을러져서 함께 할 사람 구합니다!  2주 동안 일주일에 2번 운동하는 사진을 찍어 인증합니다! 함께 열심히 해서 보너스 포인트 받아봐요! ','lapaho8645',8,0,100,2,'일주일에 2번씩 운동한 후 인증해요.',1,'lapaho8645, soom, alex950107, jjong0416, 희희희희'),(2,'3Y7CGx6P','백준 1일1알고 골드5(dfs,bfs,dp문제들 위주)부터 풀거에용~ 관심있으신 분들 많이 참가해주세요~','alex950107',6,0,100,2,'1일1알고',2,'alex950107, lapaho8645, soom, jjong0416, 희희희희'),(3,'HVILMTOP','매일 아침 6시에 일어나서 흐르는 물에 손을 씻는 장면을 찍어 인증합니다~','amysumi',3,0,1000,2,'매일 아침 6시에 일어나봐여~',0,'soom, jjong0416'),(4,'KHaUNt8M','인프런에서 김영한님 강의 매주 7강의이상 듣고 인증하실 분 구합니다~! 강의는 스프링이면 단계는 상관없습니다! 같이 스프링 뽀개봐요~><','alex950107',5,0,1000,1,'Spring 뽀개기',2,'alex950107, soom, yikj9706, XUBXJ#15, jjong0416'),(5,'t1uYQfSy','월,목 7시에 일어나서 출근하는 사람 누구야~ 저에용~ 함께해요 미라클모닝~','alex950107',6,0,100,1,'미라클 모오닝',0,'alex950107, soom, XUBXJ#15, 하니하니, pfYvG#15, 예인예인'),(6,'3keiR376','매일.운동.득근.자신있는헬창.와라.','alex950107',8,0,20000,1,'자신있는 사람만 드루와',1,'alex950107, soom'),(7,'ov8tHfWj','요즘 공부를 너무 안하는거 같아서 동기부여용으로 모집합니다. 오전8시 공부시작인증, 오후10시 공부끝인증하면 됩니다. 식사랑 쉬는시간은 자율이지만 순공 12시간씩 찍으실 분 환영합니다','alex950107',8,0,15000,1,'cpa생 열렬모집중',2,'alex950107'),(8,'cCmRb9EW','혼자 운동하려니깐 마음도 안먹어지고 자꾸 게을러져서 함께 할 사람 구합니다! 2주 동안 일주일에 2번 운동하는 사진을 찍어 인증합니다! 함께 열심히 해서 보너스 포인트 받아봐요!\n','lapaho8645',5,0,150,1,'일주일에 2번 운동 인증하실 분 있으신가요?',1,'lapaho8645, soom'),(9,'7wFzaBzR','매일 롤만키면 정신을 못 차립니다. 일주일에 2일 각 3판씩만 딱 하겠습니다. 본인 op.gg 찍어서 인증하실 분 들어오십쇼','alex950107',8,0,2000,1,'하루에 딱 롤 세판만하자!',4,'alex950107, XUBXJ#15'),(10,'YVMYZaZQ','다같이 한 달 동안 금주하고 건강을 찾아봐요...\n밤 10시에 집에 있는 사진을 올리고 인증합니다!','amysumi',4,0,30000,1,'한 달 금주하기',4,'soom'),(11,'ug3kTw9D','최고의 술고래를 겨루기 위한 방입니다.\n내가 잘 먹는다 싶은 사람들만 오세요\n하루 저녁시간 (11시)에 다 마신 술병과 함께 시간을 찍어 올리세요','yikj9706',8,0,10000,1,'음주인증방입니다.',4,'yikj9706'),(12,'U4tIfygR','매주 토요일에 종각역에서 모각코해요! 종각역 nuguna 스터디룸에서 모각코 하실분 구합니다! 12층 카페에서 모각코하는 사진 찍어서 인증해주시면 됩니다! 시간은 자유! 같이 코딩 실력 향상시켜봐요!','lapaho8645',8,0,200,1,'매주 토요일에 종각역에서 모각코해요!',3,'lapaho8645, 예인예인'),(13,'Xrz1DwzM','오프라인 때도, 온라인 때도 지각안하기가 쉽지 않네요... 8시 30분부터 8시 40분까지 오프라인때는 자리 사진으로 인증하고 온라인때는 출석 후 모니터 사진으로 인증해요!  ','lapaho8645',8,0,1000,1,'ssafy 지각 안하기 함께 해요!',4,'lapaho8645, soom, XUBXJ#15, jjong0416, yikj9706'),(14,'0m575etl','리프레시를 위한 산책 방\n저녁 시간 산책 후 밖에서 운동화 사진을 찍어 올려요~','amysumi',2,1,5000,1,'일주일 두 번 산책하기',1,'soom, lapaho8645'),(15,'rQBRGXWy','행복하기만 하면 됩니다 웃고 있는 거 보여주세요 ^-^b','2393134562',5,0,814,3,'행복하기',0,'XUBXJ#15, 예인예인, octopus702'),(16,'A7oQKwkK','매일매일 스트레칭으로 굳은 몸을 풀어줍시다\n우리같은 개발자는 안움직이니까 이거라도…\nㅎㅎ','2393237387',7,0,120,1,'하루에 10분 스트레칭하기',1,'하니하니'),(17,'jRt3YAPy','자꾸 집에 가서 해야지, 나중에 해야지 미루는 사람들과 함께 하고 싶습니다 미루지 않고 할 일은 제때 하자는 마음에서 방 제목을 저렇게 잡았어요','yeinyein',8,0,500,1,'12시 전에 자기',0,'예인예인'),(18,'i5QeHJmy','진짜 하실 분만 구해요 kocw에 운영체제 검색하면 나오는 반효경 교수님 걸로 공부할 거고 요약해서 발표하는 것까지가 숙제입니다 중간에 나가실 거면 오지 마세요','yeinyein',4,0,3000,1,'운영체제 CS 스터디 하고 매일 10시에 인증하실 분',2,'예인예인'),(19,'2Q8lNAoL','일주일에 2번씩 헬스장 내부에 있는 사진을 찍어 인증해주세요. 진행기간은 2주이고 시간은 자유입니다!','octopus702',5,0,150,1,'2주동안 일주일에 2번 헬스장 인증하실분구합니다.',1,'octopus702'),(20,'STPmSxLu','한달 동안 독하게 6시에 일어나실분 구합니다! 평일 매일 5시 50분부터 6시 10분까지 인증해주시면됩니다.','octopus702',4,0,200,2,'평일 매일 6시에 일어나요',0,'octopus702, lapaho864');
/*!40000 ALTER TABLE `mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mission_time`
--

DROP TABLE IF EXISTS `mission_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mission_time` (
  `mission_time_no` bigint NOT NULL AUTO_INCREMENT,
  `mission_time_dpw` int DEFAULT NULL,
  `mission_time_end_time` datetime DEFAULT NULL,
  `mission_time_start_time` datetime DEFAULT NULL,
  `mission_time_tpd` int DEFAULT NULL,
  `mission_time_week` int DEFAULT NULL,
  `mission_no` bigint DEFAULT NULL,
  PRIMARY KEY (`mission_time_no`),
  KEY `FKssw2yo4r6bx9nqmbw5f041kl` (`mission_no`),
  CONSTRAINT `FKssw2yo4r6bx9nqmbw5f041kl` FOREIGN KEY (`mission_no`) REFERENCES `mission` (`mission_no`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mission_time`
--

LOCK TABLES `mission_time` WRITE;
/*!40000 ALTER TABLE `mission_time` DISABLE KEYS */;
INSERT INTO `mission_time` VALUES (1,3,'2022-08-24 00:00:00','2022-08-17 00:00:00',2,1,1),(2,7,'2022-08-23 00:00:00','2022-08-16 00:00:00',1,1,2),(3,7,'2022-08-24 00:00:00','2022-08-17 00:00:00',1,1,3),(4,1,'2022-08-29 00:00:00','2022-08-22 00:00:00',1,1,4),(5,2,'2022-10-31 00:00:00','2022-08-22 00:00:00',1,10,5),(6,7,'2022-09-26 00:00:00','2022-08-22 00:00:00',1,5,6),(7,5,'2022-09-26 00:00:00','2022-08-22 00:00:00',2,5,7),(8,2,'2022-09-02 00:00:00','2022-08-19 00:00:00',1,2,8),(9,2,'2022-09-18 00:00:00','2022-08-21 00:00:00',1,4,9),(10,7,'2022-09-15 00:00:00','2022-08-18 00:00:00',1,4,10),(11,7,'2022-09-01 00:00:00','2022-08-18 00:00:00',1,2,11),(12,1,'2022-09-17 00:00:00','2022-08-20 00:00:00',1,4,12),(13,5,'2022-09-19 00:00:00','2022-08-22 00:00:00',1,4,13),(14,2,'2022-09-15 00:00:00','2022-08-18 00:00:00',1,4,14),(15,1,'2022-09-19 00:00:00','2022-08-22 00:00:00',1,4,15),(16,5,'2022-09-15 00:00:00','2022-08-18 00:00:00',1,4,16),(17,1,'2022-08-31 00:00:00','2022-08-17 00:00:00',1,2,17),(18,7,'2022-09-05 00:00:00','2022-08-22 00:00:00',1,2,18),(19,2,'2022-09-05 00:00:00','2022-08-22 00:00:00',1,2,19),(20,7,'2022-09-01 00:00:00','2022-08-18 00:00:00',1,2,20);
/*!40000 ALTER TABLE `mission_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `octopus_table`
--

DROP TABLE IF EXISTS `octopus_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `octopus_table` (
  `user_no` bigint NOT NULL,
  `mission_no` bigint NOT NULL,
  PRIMARY KEY (`mission_no`,`user_no`),
  KEY `FKi1kyjcdx5eej79aol4oqw5dsd` (`user_no`),
  CONSTRAINT `FKi1kyjcdx5eej79aol4oqw5dsd` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
  CONSTRAINT `FKsclqbfu2uxo1h6ob4hb03gigg` FOREIGN KEY (`mission_no`) REFERENCES `mission` (`mission_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `octopus_table`
--

LOCK TABLES `octopus_table` WRITE;
/*!40000 ALTER TABLE `octopus_table` DISABLE KEYS */;
INSERT INTO `octopus_table` VALUES (5,1),(5,2),(5,8),(5,12),(5,13),(5,14),(6,1),(6,2),(6,3),(6,4),(6,5),(6,6),(6,8),(6,10),(6,13),(6,14),(7,1),(7,2),(7,4),(7,5),(7,6),(7,7),(7,9),(8,1),(8,2),(8,3),(8,4),(8,13),(9,1),(9,2),(11,4),(11,5),(11,9),(11,13),(11,15),(12,4),(12,11),(12,13),(13,5),(13,16),(14,5),(17,5),(17,12),(17,15),(17,17),(17,18),(18,20),(19,15),(19,19),(19,20);
/*!40000 ALTER TABLE `octopus_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `picture` (
  `picture_no` bigint NOT NULL AUTO_INCREMENT,
  `picture_date` datetime NOT NULL,
  `picture_url` varchar(255) NOT NULL,
  `mission_no` bigint DEFAULT NULL,
  `user_no` bigint DEFAULT NULL,
  PRIMARY KEY (`picture_no`),
  KEY `FK7bicegbdnhgn6olkfjlwoela6` (`mission_no`),
  KEY `FKf9a1vjp1agjqb37d9p6ln9htf` (`user_no`),
  CONSTRAINT `FK7bicegbdnhgn6olkfjlwoela6` FOREIGN KEY (`mission_no`) REFERENCES `mission` (`mission_no`),
  CONSTRAINT `FKf9a1vjp1agjqb37d9p6ln9htf` FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
INSERT INTO `picture` VALUES (1,'2022-08-17 13:55:59','https://storage.googleapis.com/octopus-702/2/alex950107/2022-08-17-13:55:58.png',2,7),(2,'2022-08-17 13:57:21','https://storage.googleapis.com/octopus-702/1/alex950107/2022-08-17-13:57:20.png',1,7),(3,'2022-08-17 13:57:45','https://storage.googleapis.com/octopus-702/2/amysumi/2022-08-17-13:57:44.png',2,6),(4,'2022-08-17 13:58:28','https://storage.googleapis.com/octopus-702/2/rudwns06/2022-08-17-13:58:26.png',2,9),(5,'2022-08-17 23:49:18','https://storage.googleapis.com/octopus-702/2/lapaho8645/2022-08-17-23:49:15.png',2,5),(6,'2022-08-17 23:49:20','https://storage.googleapis.com/octopus-702/1/alex950107/2022-08-17-23:49:18.png',1,7),(7,'2022-08-17 23:49:21','https://storage.googleapis.com/octopus-702/2/lapaho8645/2022-08-17-23:49:19.png',2,5),(8,'2022-08-17 23:49:54','https://storage.googleapis.com/octopus-702/1/amysumi/2022-08-17-23:49:52.png',1,6),(9,'2022-08-17 23:50:21','https://storage.googleapis.com/octopus-702/1/lapaho8645/2022-08-17-23:50:19.png',1,5),(10,'2022-08-17 23:51:38','https://storage.googleapis.com/octopus-702/1/rudwns06/2022-08-17-23:51:36.png',1,9),(11,'2022-08-18 11:05:13','https://storage.googleapis.com/octopus-702/2/amysumi/2022-08-18-11:05:10.png',2,6),(12,'2022-08-18 15:36:53','https://storage.googleapis.com/octopus-702/1/jjong0416/2022-08-18-15:36:52.png',1,8);
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_no` bigint NOT NULL AUTO_INCREMENT,
  `platform_access_token` bigint DEFAULT NULL,
  `platform_type` int DEFAULT NULL,
  `user_avatar` varchar(255) NOT NULL,
  `user_email` varchar(30) NOT NULL,
  `user_id` varchar(15) NOT NULL,
  `user_nickname` varchar(10) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_point` int DEFAULT NULL,
  PRIMARY KEY (`user_no`),
  UNIQUE KEY `UK_a3imlf41l37utmxiquukk8ajc` (`user_id`),
  UNIQUE KEY `UK_cr59axqya8utby3j37qi341rm` (`user_nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,NULL,0,'11, 7, 2, 2','lapaho8645@naver.com','lapaho8645','lapaho8645','$2a$10$2zCJm6KP83klzO2GaIvkw.eZa70gZzUzl6seIt9qTDRE28BIyOYAy',4950),(6,NULL,0,'1, 4, 1, 2','amysumi@naver.com','amysumi','soom','$2a$10$sgKBaB4p.qvh.ad0AFxCuOYzMzBBHsu6M8RZ4oOocR4ScwL/6vMVq',51550),(7,NULL,0,'13, 6, 16, 7','alex950107@gmail.com','alex950107','alex950107','$2a$10$Rm5siYxsBQN98yiUuG4Q3eMGXRxikydt4WfrrzIjZpMQubHZxgWyK',142200),(8,NULL,0,'11, 1, 15, 1','jjong.dev@gmail.com','jjong0416','jjong0416','$2a$10$S3c0TD0iMHxF7f5QMnmE1Or/YVneLweGdh7nJqLovbjDCD6lRgaoW',6200),(9,NULL,0,'9, 5, 0, 3','rudwns06@hanmail.net','rudwns06','희희희희','$2a$10$BG88HyMFfWfHKO7Nf8U1De2j26Gzy8DKw6e.KddNmX6EDTt/npWHK',5300),(10,NULL,1,'1, 0, 0, 0','alex8713@naver.com','2384429057','rzKid-15','$2a$10$CH0lU/YtsoIyNqRH8XgyPuVfGrRriCXG5QfUtAg/EGkoXrQ5NFNNK',1000),(11,NULL,1,'7, 1, 13, 3','cherry4180@naver.com','2393134562','XUBXJ-15','$2a$10$gk29CxqcGjsp.EOujJi3teQsKtZX2ZeEAcwCi.Sy72OREei9mh9GG',586),(12,NULL,0,'13, 2, 19, 3','powerrudwns@naver.com','yikj9706','yikj9706','$2a$10$/LECsBjCAwKM5W.SkPYYf.c4yQBpjeDyGVdlVtCqzb5Z7Al39iCIC',18500),(13,NULL,1,'1, 0, 0, 0','haney0y@gmail.com','2393237387','하니하니','$2a$10$/Q1OOlM4fUELeii5tOoA3.cVaSt9n/X2Ion3RPvcPAqKOm27UjqfO',780),(14,NULL,1,'1, 0, 0, 0','whdcks7275@gmail.com','2382898198','pfYvG-15','$2a$10$w/kJugP474EsQ1ylo.7dQOUo.pqlZUXNGTiZUDgzirXS9jRgl6nbS',900),(15,NULL,1,'1, 0, 0, 0','sumi1015@kakao.com','2393285537','ESSLU-15','$2a$10$KG8/QaF.NeF4IX3cxS/xae7jigDxK5CknMFBF5gHv8pC0nCBgZkXe',11000),(16,NULL,0,'10, 7, 10, 5','powerrudwns@gmail.com','qwerasdf','qwerasdf','$2a$10$aJSEz7a7.QBgFnGnW0FMjuXAyaUjci0KoW1IP1SEY84QGN5/FW5Ja',500),(17,NULL,0,'2, 7, 0, 0','iasfk@naver.com','yeinyein','예인예인','$2a$10$Ziep5WDTj3x7pqY6VMb6ju1TBNycHf3pRnEyehzwq7B03/T/vZ.TK',886),(18,NULL,0,'2, 0, 0, 0','jesu6747@naver.com','lapaho864','lapaho864','$2a$10$3pUNU1bLBliklV547OQucOsGr3gMioGmdXRT/1v9lajdmaBhq/uDW',800),(19,NULL,0,'10, 5, 10, 2','octopus.ssafy@gmail.com','octopus702','octopus702','$2a$10$0VO5J9HDbMw9ltHApaj4heX5I/yR/l3DqLhKu.FaGH3Sr5AuCvaCu',1836),(20,NULL,0,'3, 0, 0, 0','amysumi10@gmail.com','tnal1015','tnal','$2a$10$wVaGQpCm3wzFWHMaJoZefOe6BPk.D12XezqW09.yXdz38pb8Q5eV2',1000);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-18 16:51:42
