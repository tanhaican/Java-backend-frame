-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: duxact
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blockindexs`
--

DROP TABLE IF EXISTS `blockindexs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blockindexs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `pageBlockId` int(11) NOT NULL,
  `state` varchar(2) DEFAULT NULL COMMENT '状态 Y/N',
  `comment` varchar(150) DEFAULT NULL,
  `createdBy` varchar(15) DEFAULT NULL,
  `createdDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatedBy` varchar(15) DEFAULT NULL,
  `updatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `order` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `fk_blockindexs_pageblocks` (`pageBlockId`),
  CONSTRAINT `fk_blockindexs_pageblocks` FOREIGN KEY (`pageBlockId`) REFERENCES `pageblocks` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='块链接目标信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blockindexs`
--

LOCK TABLES `blockindexs` WRITE;
/*!40000 ALTER TABLE `blockindexs` DISABLE KEYS */;
INSERT INTO `blockindexs` VALUES (1,'jenkins系统','http://123.207.116.203:8081/jenkins/',2,'1',NULL,'admin','2017-03-01 16:46:01','admin','2017-03-01 16:46:01',1),(2,'Bug系统','http://123.207.116.203:8082/cynthia',2,'1',NULL,'admin','2017-03-01 16:46:01','admin','2017-03-01 16:46:01',2);
/*!40000 ALTER TABLE `blockindexs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(50) NOT NULL COMMENT '菜单名称',
  `englishName` varchar(255) DEFAULT NULL COMMENT '英文名',
  `parentMenuId` int(11) DEFAULT NULL COMMENT '父菜单id',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `level` int(11) DEFAULT NULL COMMENT '菜单级别',
  `order` int(11) DEFAULT NULL COMMENT '排序',
  `createdTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立时间',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `comments` varchar(255) DEFAULT NULL COMMENT '说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'我的工作台','My workbench',NULL,'mine',1,1,'2017-03-01 07:50:54','Y',NULL);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pageblocks`
--

DROP TABLE IF EXISTS `pageblocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pageblocks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blockName` varchar(50) DEFAULT NULL,
  `user` varchar(15) DEFAULT NULL COMMENT '系统则是system，用户则是um',
  `imgUrl` varchar(300) DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL COMMENT '（Y/N）',
  `comment` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pageblock_user` (`user`),
  CONSTRAINT `fk_pageblock_user` FOREIGN KEY (`user`) REFERENCES `userinfo` (`umCode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='PM 页分块信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pageblocks`
--

LOCK TABLES `pageblocks` WRITE;
/*!40000 ALTER TABLE `pageblocks` DISABLE KEYS */;
INSERT INTO `pageblocks` VALUES (1,'知识篇','admin',NULL,'1','知识篇'),(2,'开发用','tanhaican',NULL,'1','开发用');
/*!40000 ALTER TABLE `pageblocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolemenu`
--

DROP TABLE IF EXISTS `rolemenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolemenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rolemenu_menu` (`menuId`),
  KEY `fk_rolemenu_userrole` (`roleId`),
  CONSTRAINT `fk_rolemenu_menu` FOREIGN KEY (`menuId`) REFERENCES `menu` (`id`),
  CONSTRAINT `fk_rolemenu_userrole` FOREIGN KEY (`roleId`) REFERENCES `userrole` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolemenu`
--

LOCK TABLES `rolemenu` WRITE;
/*!40000 ALTER TABLE `rolemenu` DISABLE KEYS */;
INSERT INTO `rolemenu` VALUES (1,3,1),(2,2,1),(3,1,1);
/*!40000 ALTER TABLE `rolemenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createdDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `createdBy` varchar(15) DEFAULT NULL,
  `updatedDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatedBy` varchar(15) DEFAULT NULL,
  `umCode` varchar(15) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL COMMENT 'M男，F女',
  `birthday` date DEFAULT NULL,
  `phone` varchar(18) DEFAULT NULL,
  `level` varchar(10) DEFAULT NULL,
  `state` int(2) DEFAULT NULL COMMENT '状态 (0无效，1在职，2离职)',
  `roleId` int(11) NOT NULL DEFAULT '0',
  `password` varchar(100) NOT NULL COMMENT '登录密码',
  `salt` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `AK_user_info_um` (`umCode`),
  UNIQUE KEY `idx_user_info_um` (`umCode`),
  KEY `fk_userinfo_role` (`roleId`),
  CONSTRAINT `fk_userinfo_role` FOREIGN KEY (`roleId`) REFERENCES `userrole` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (1,'2017-03-01 16:05:01','admin','2017-03-01 16:05:01','admin','tanhaican','谭海灿','haican.tan@duxact.com','M','1990-06-27','18570635331','2',1,1,'d004c5c20858babc22c3c58118ae0971','b297cbcd8293b0ced635e6cdfac30b64',0),(3,'2017-03-01 16:05:01','admin','2017-03-01 16:05:01','admin','qixiao','齐潇','xiao.qi@duxact.com','M','1992-01-01','15388034366','2',1,1,'18812a7373ca6df099d420a1d4ebefa2','a3baa81e8ee514749d1081c3596436aa',0),(4,'2017-03-01 16:40:21','admin','2017-03-01 16:40:21','admin','admin','admin',NULL,NULL,NULL,NULL,'0',1,1,'9d480ca8979eb590b5b953326d8d1878','484c37a65b362a9303793be6fc88c609',0);
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `comment` varchar(50) DEFAULT NULL COMMENT '说明',
  `status` int(2) DEFAULT NULL COMMENT '状态(1正常，0停用)',
  `order` int(11) DEFAULT NULL COMMENT '排序',
  `isSysLevel` int(2) DEFAULT NULL COMMENT '是否系统级别   1是，0否',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,'SUPER_ADMIN','超级管理员',1,1,1,'超级管理员'),(2,'ADMIN','普通管理员',1,2,1,'普通管理员'),(3,'NORMAL','普通用户',1,3,0,'普通用户'),(4,'INIT','初始化人员',1,0,0,'初始化人员');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'duxact'
--

--
-- Dumping routines for database 'duxact'
--
/*!50003 DROP PROCEDURE IF EXISTS `del_idx` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `del_idx`(
		in p_tablename varchar(200),
		in p_idxname varchar(200) )
begin 
		declare str varchar(250);
		set @str = concat( ' drop index ', p_idxname, ' on ', p_tablename );

		select count(1) into @cnt
			from information_schema.statistics
			where table_name = p_tablename
				and index_name = p_idxname;

		if @cnt > 0 then 
			prepare stmt from @str;

			execute stmt;
		end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-03 13:39:09
