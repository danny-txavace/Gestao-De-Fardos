-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: dbbluemoon
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbarmazem_beira`
--

DROP TABLE IF EXISTS `tbarmazem_beira`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbarmazem_beira` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `DESIGNAÇÃO` varchar(200) NOT NULL,
  `MARCA` varchar(100) DEFAULT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `UNIDADE` varchar(70) DEFAULT NULL,
  `QUANTIDADE` int NOT NULL,
  `PRECO_ARTIGO_MZN` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_MZN` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_MZN`)) VIRTUAL NOT NULL,
  `PRECO_ARTIGO_USD` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_USD` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_USD`)) VIRTUAL NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DESIGNAÇÃO` (`DESIGNAÇÃO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbarmazem_beira`
--

LOCK TABLES `tbarmazem_beira` WRITE;
/*!40000 ALTER TABLE `tbarmazem_beira` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbarmazem_beira` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbarmazem_maputo`
--

DROP TABLE IF EXISTS `tbarmazem_maputo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbarmazem_maputo` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `DESIGNAÇÃO` varchar(200) NOT NULL,
  `MARCA` varchar(100) DEFAULT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `UNIDADE` varchar(70) DEFAULT NULL,
  `QUANTIDADE` int NOT NULL,
  `PRECO_ARTIGO_MZN` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_MZN` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_MZN`)) VIRTUAL NOT NULL,
  `PRECO_ARTIGO_USD` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_USD` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_USD`)) VIRTUAL NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DESIGNAÇÃO` (`DESIGNAÇÃO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbarmazem_maputo`
--

LOCK TABLES `tbarmazem_maputo` WRITE;
/*!40000 ALTER TABLE `tbarmazem_maputo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbarmazem_maputo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbcategoria`
--

DROP TABLE IF EXISTS `tbcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbcategoria` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `CATEGORIA` varchar(100) NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CATEGORIA` (`CATEGORIA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbcategoria`
--

LOCK TABLES `tbcategoria` WRITE;
/*!40000 ALTER TABLE `tbcategoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbcliente`
--

DROP TABLE IF EXISTS `tbcliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbcliente` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `NOME` varchar(50) NOT NULL,
  `PHONE_1` varchar(50) DEFAULT NULL,
  `PHONE_2` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `LOJA` varchar(50) NOT NULL,
  `DESCRIÇÃO` varchar(500) DEFAULT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbcliente`
--

LOCK TABLES `tbcliente` WRITE;
/*!40000 ALTER TABLE `tbcliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbcliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbcontrolleruser`
--

DROP TABLE IF EXISTS `tbcontrolleruser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbcontrolleruser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cUser` varchar(80) NOT NULL,
  `cPerfil` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbcontrolleruser`
--

LOCK TABLES `tbcontrolleruser` WRITE;
/*!40000 ALTER TABLE `tbcontrolleruser` DISABLE KEYS */;
INSERT INTO `tbcontrolleruser` VALUES (1,'Ramadan Ibraimo IsmaeL','admin');
/*!40000 ALTER TABLE `tbcontrolleruser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbidioma`
--

DROP TABLE IF EXISTS `tbidioma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbidioma` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDIOMA` varchar(20) NOT NULL,
  `ESTADO` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDIOMA` (`IDIOMA`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbidioma`
--

LOCK TABLES `tbidioma` WRITE;
/*!40000 ALTER TABLE `tbidioma` DISABLE KEYS */;
INSERT INTO `tbidioma` VALUES (1,'Português',0),(2,'Inglês',1);
/*!40000 ALTER TABLE `tbidioma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblogin_attempts`
--

DROP TABLE IF EXISTS `tblogin_attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblogin_attempts` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `USUÁRIO` varchar(50) NOT NULL,
  `DATA_HORA` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblogin_attempts`
--

LOCK TABLES `tblogin_attempts` WRITE;
/*!40000 ALTER TABLE `tblogin_attempts` DISABLE KEYS */;
INSERT INTO `tblogin_attempts` VALUES (1,'ramadan','2024-03-05 05:29:55'),(2,'ramadan','2024-03-05 05:04:46'),(3,'dannyy','2024-03-04 11:04:54'),(4,'dannyy','2024-03-04 10:34:16');
/*!40000 ALTER TABLE `tblogin_attempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbloja_1`
--

DROP TABLE IF EXISTS `tbloja_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbloja_1` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `DESIGNAÇÃO` varchar(200) NOT NULL,
  `MARCA` varchar(100) DEFAULT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `UNIDADE` varchar(70) DEFAULT NULL,
  `QUANTIDADE` int NOT NULL,
  `PRECO_ARTIGO_MZN` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_MZN` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_MZN`)) VIRTUAL NOT NULL,
  `PRECO_ARTIGO_USD` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_USD` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_USD`)) VIRTUAL NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DESIGNAÇÃO` (`DESIGNAÇÃO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbloja_1`
--

LOCK TABLES `tbloja_1` WRITE;
/*!40000 ALTER TABLE `tbloja_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbloja_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbloja_2`
--

DROP TABLE IF EXISTS `tbloja_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbloja_2` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `DESIGNAÇÃO` varchar(200) NOT NULL,
  `MARCA` varchar(100) DEFAULT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `UNIDADE` varchar(70) DEFAULT NULL,
  `QUANTIDADE` int NOT NULL,
  `PRECO_ARTIGO_MZN` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_MZN` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_MZN`)) VIRTUAL NOT NULL,
  `PRECO_ARTIGO_USD` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_USD` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_USD`)) VIRTUAL NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DESIGNAÇÃO` (`DESIGNAÇÃO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbloja_2`
--

LOCK TABLES `tbloja_2` WRITE;
/*!40000 ALTER TABLE `tbloja_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbloja_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbloja_3`
--

DROP TABLE IF EXISTS `tbloja_3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbloja_3` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `DESIGNAÇÃO` varchar(200) NOT NULL,
  `MARCA` varchar(100) DEFAULT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `UNIDADE` varchar(70) DEFAULT NULL,
  `QUANTIDADE` int NOT NULL,
  `PRECO_ARTIGO_MZN` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_MZN` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_MZN`)) VIRTUAL NOT NULL,
  `PRECO_ARTIGO_USD` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_USD` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_USD`)) VIRTUAL NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DESIGNAÇÃO` (`DESIGNAÇÃO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbloja_3`
--

LOCK TABLES `tbloja_3` WRITE;
/*!40000 ALTER TABLE `tbloja_3` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbloja_3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbloja_jardim`
--

DROP TABLE IF EXISTS `tbloja_jardim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbloja_jardim` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `DESIGNAÇÃO` varchar(200) NOT NULL,
  `MARCA` varchar(100) DEFAULT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `UNIDADE` varchar(70) DEFAULT NULL,
  `QUANTIDADE` int NOT NULL,
  `PRECO_ARTIGO_MZN` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_MZN` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_MZN`)) VIRTUAL NOT NULL,
  `PRECO_ARTIGO_USD` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_USD` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_USD`)) VIRTUAL NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DESIGNAÇÃO` (`DESIGNAÇÃO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbloja_jardim`
--

LOCK TABLES `tbloja_jardim` WRITE;
/*!40000 ALTER TABLE `tbloja_jardim` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbloja_jardim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbmarca`
--

DROP TABLE IF EXISTS `tbmarca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbmarca` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `MARCA` varchar(100) NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `MARCA` (`MARCA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbmarca`
--

LOCK TABLES `tbmarca` WRITE;
/*!40000 ALTER TABLE `tbmarca` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbmarca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbusuario`
--

DROP TABLE IF EXISTS `tbusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbusuario` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `NOME` varchar(100) NOT NULL,
  `PHONE_1` varchar(50) DEFAULT NULL,
  `PHONE_2` varchar(50) DEFAULT NULL,
  `USUÁRIO` varchar(50) NOT NULL,
  `SENHA` varchar(50) NOT NULL,
  `PERFIL` varchar(30) NOT NULL,
  `LOJA` varchar(50) NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NOME` (`NOME`),
  UNIQUE KEY `USUÁRIO` (`USUÁRIO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbusuario`
--

LOCK TABLES `tbusuario` WRITE;
/*!40000 ALTER TABLE `tbusuario` DISABLE KEYS */;
INSERT INTO `tbusuario` VALUES (1,'Danny Ibraimo Ismael','(+258) 87 171 7834','','danny','5126','user','Shop 2','2024-03-04 11:43:13'),(2,'Ramadan Ibraimo IsmaeL','(+258) 84 962 6719','871717834','ramadan','5126','admin','Shop 1','2024-02-11 08:30:59');
/*!40000 ALTER TABLE `tbusuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbvenda_1`
--

DROP TABLE IF EXISTS `tbvenda_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbvenda_1` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `DESIGNAÇÃO` varchar(100) NOT NULL,
  `MARCA` varchar(100) DEFAULT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `UNIDADE` varchar(30) DEFAULT NULL,
  `QUANTIDADE` int NOT NULL,
  `PRECO_ARTIGO_MZN` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_MZN` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_MZN`)) VIRTUAL NOT NULL,
  `PRECO_ARTIGO_USD` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_USD` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_USD`)) VIRTUAL NOT NULL,
  `PRECO_VENDA` decimal(10,2) NOT NULL,
  `LUCRO` decimal(10,2) GENERATED ALWAYS AS ((`PRECO_VENDA` - `PRECO_TOTAL_MZN`)) VIRTUAL NOT NULL,
  `VENDIDO_POR` varchar(100) NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbvenda_1`
--

LOCK TABLES `tbvenda_1` WRITE;
/*!40000 ALTER TABLE `tbvenda_1` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbvenda_1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbvenda_2`
--

DROP TABLE IF EXISTS `tbvenda_2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbvenda_2` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `DESIGNAÇÃO` varchar(100) NOT NULL,
  `MARCA` varchar(100) DEFAULT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `UNIDADE` varchar(30) DEFAULT NULL,
  `QUANTIDADE` int NOT NULL,
  `PRECO_ARTIGO_MZN` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_MZN` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_MZN`)) VIRTUAL NOT NULL,
  `PRECO_ARTIGO_USD` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_USD` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_USD`)) VIRTUAL NOT NULL,
  `PRECO_VENDA` decimal(10,2) NOT NULL,
  `LUCRO` decimal(10,2) GENERATED ALWAYS AS ((`PRECO_VENDA` - `PRECO_TOTAL_MZN`)) VIRTUAL NOT NULL,
  `VENDIDO_POR` varchar(100) NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbvenda_2`
--

LOCK TABLES `tbvenda_2` WRITE;
/*!40000 ALTER TABLE `tbvenda_2` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbvenda_2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbvenda_3`
--

DROP TABLE IF EXISTS `tbvenda_3`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbvenda_3` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `DESIGNAÇÃO` varchar(100) NOT NULL,
  `MARCA` varchar(100) DEFAULT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `UNIDADE` varchar(30) DEFAULT NULL,
  `QUANTIDADE` int NOT NULL,
  `PRECO_ARTIGO_MZN` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_MZN` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_MZN`)) VIRTUAL NOT NULL,
  `PRECO_ARTIGO_USD` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_USD` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_USD`)) VIRTUAL NOT NULL,
  `PRECO_VENDA` decimal(10,2) NOT NULL,
  `LUCRO` decimal(10,2) GENERATED ALWAYS AS ((`PRECO_VENDA` - `PRECO_TOTAL_MZN`)) VIRTUAL NOT NULL,
  `VENDIDO_POR` varchar(100) NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbvenda_3`
--

LOCK TABLES `tbvenda_3` WRITE;
/*!40000 ALTER TABLE `tbvenda_3` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbvenda_3` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbvenda_jardim`
--

DROP TABLE IF EXISTS `tbvenda_jardim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbvenda_jardim` (
  `ID` int unsigned NOT NULL AUTO_INCREMENT,
  `DESIGNAÇÃO` varchar(100) NOT NULL,
  `MARCA` varchar(100) DEFAULT NULL,
  `CATEGORIA` varchar(100) NOT NULL,
  `UNIDADE` varchar(30) DEFAULT NULL,
  `QUANTIDADE` int NOT NULL,
  `PRECO_ARTIGO_MZN` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_MZN` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_MZN`)) VIRTUAL NOT NULL,
  `PRECO_ARTIGO_USD` decimal(10,2) NOT NULL,
  `PRECO_TOTAL_USD` decimal(10,2) GENERATED ALWAYS AS ((`QUANTIDADE` * `PRECO_ARTIGO_USD`)) VIRTUAL NOT NULL,
  `PRECO_VENDA` decimal(10,2) NOT NULL,
  `LUCRO` decimal(10,2) GENERATED ALWAYS AS ((`PRECO_VENDA` - `PRECO_TOTAL_MZN`)) VIRTUAL NOT NULL,
  `VENDIDO_POR` varchar(100) NOT NULL,
  `DATA_HORA` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbvenda_jardim`
--

LOCK TABLES `tbvenda_jardim` WRITE;
/*!40000 ALTER TABLE `tbvenda_jardim` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbvenda_jardim` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-06  8:26:43
