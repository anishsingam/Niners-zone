-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: niners_zone
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.04.1

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
-- Table structure for table `Test`
--

DROP TABLE IF EXISTS `Test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Test`
--

LOCK TABLES `Test` WRITE;
/*!40000 ALTER TABLE `Test` DISABLE KEYS */;
INSERT INTO `Test` VALUES (1,'twst');
/*!40000 ALTER TABLE `Test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `academic_board`
--

DROP TABLE IF EXISTS `academic_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `academic_board` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `academic_board_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `faculty_course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_board`
--

LOCK TABLES `academic_board` WRITE;
/*!40000 ALTER TABLE `academic_board` DISABLE KEYS */;
INSERT INTO `academic_board` VALUES (1,1),(2,3),(4,4),(5,5),(6,6),(3,7),(7,11),(8,12),(9,13),(10,14),(11,15),(12,16),(13,17),(14,18),(15,19),(16,20),(17,21),(18,22),(19,23);
/*!40000 ALTER TABLE `academic_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `academic_material`
--

DROP TABLE IF EXISTS `academic_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `academic_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `material_id` (`material_id`),
  CONSTRAINT `academic_material_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_material`
--

LOCK TABLES `academic_material` WRITE;
/*!40000 ALTER TABLE `academic_material` DISABLE KEYS */;
INSERT INTO `academic_material` VALUES (6,17),(8,23),(9,24),(11,25),(12,26);
/*!40000 ALTER TABLE `academic_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  KEY `username` (`username`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (100,'admin');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment_material`
--

DROP TABLE IF EXISTS `assignment_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_material` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `material_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `material_id` (`material_id`),
  CONSTRAINT `assignment_material_ibfk_1` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_material`
--

LOCK TABLES `assignment_material` WRITE;
/*!40000 ALTER TABLE `assignment_material` DISABLE KEYS */;
INSERT INTO `assignment_material` VALUES (3,9),(4,10),(5,11),(6,12),(9,16),(10,18),(11,22),(12,23),(13,26),(14,27);
/*!40000 ALTER TABLE `assignment_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignment_post`
--

DROP TABLE IF EXISTS `assignment_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignment_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `academic_board_id` int(11) DEFAULT NULL,
  `assignment_material_id` int(11) DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  `description` text,
  `creation_date` datetime DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `academic_board_id` (`academic_board_id`),
  KEY `assignment_material_id` (`assignment_material_id`),
  CONSTRAINT `assignment_post_ibfk_1` FOREIGN KEY (`academic_board_id`) REFERENCES `academic_board` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `assignment_post_ibfk_2` FOREIGN KEY (`assignment_material_id`) REFERENCES `assignment_material` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_post`
--

LOCK TABLES `assignment_post` WRITE;
/*!40000 ALTER TABLE `assignment_post` DISABLE KEYS */;
INSERT INTO `assignment_post` VALUES (31,2,10,'New Assignment for DB','Ok Here u go','2015-04-28 11:00:36','2015-04-29 12:00:00'),(36,1,6,'The standard Lorem Ipsum passage','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum','2015-05-03 15:45:35','2015-05-04 17:00:00'),(37,1,3,' Written by Cicero in 45 BC','Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur','2015-05-03 15:47:13','2015-05-14 19:00:00'),(38,1,12,'1914 translation by H. Rackham','But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical exercise, except to obtain some advantage from it? But who has any right to find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no resultant pleasure?','2015-05-03 15:48:51','2015-05-29 20:00:00'),(39,1,9,'who seeks after it and wants to have it','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent pretium ultricies maximus. In mollis id orci nec interdum. Vestibulum quis ipsum suscipit ante elementum venenatis et in massa. Quisque pharetra luctus metus. Fusce a aliquam tortor. Duis non tempus urna, id accumsan nulla. Duis gravida et elit ut pharetra. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Suspendisse nec dui sagittis, pharetra erat non, ornare orci. Aenean maximus quis diam a iaculis. Proin pretium neque et est fermentum facilisis. Sed tellus mauris, gravida sit amet convallis at, sollicitudin luctus felis. Vivamus eget mollis leo. Donec efficitur varius sapien, in accumsan urna egestas ac.','2015-05-03 15:50:18','2015-05-26 15:49:00'),(40,17,14,'Checking','check','2015-05-03 16:33:09','2015-05-03 19:00:00');
/*!40000 ALTER TABLE `assignment_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `college` (
  `college_id` int(11) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(50) NOT NULL,
  PRIMARY KEY (`college_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES (1,'Computing'),(2,'Electrical'),(3,'Mechanical'),(4,'Business College');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` varchar(10) NOT NULL,
  `course_name` varchar(50) NOT NULL,
  `course_level` varchar(10) NOT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('ITCS5187','Mobile Application Development','Masters',2),('ITCS6112','SSDI','Masters',2),('ITCS6114','Algorithms and Data Structures','Masters',2),('ITCS6160','Database Systems','Masters',2),('ITIS5120','Network Based Application Development','Masters',3),('ITIS5187','Mobile Application Development','Masters',3),('ITIS5250','Computer Forensics','Masters',3),('ITIS6112','SSDI','Masters',3),('MEGR6181','Engineering Metrology','Masters',4),('MEGR6201','Fluid Mechanics','Masters',4),('MEGR7020','Theory of Elasticity-I','Masters',4),('MEGR7021','Theory of Elasticity-I','Masters',4);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(50) NOT NULL,
  `college_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`department_id`),
  KEY `college_id` (`college_id`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (2,'CS',1),(3,'SIS',1),(4,'Mech',3),(5,'Mech-1',3);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussion_forum_comments`
--

DROP TABLE IF EXISTS `discussion_forum_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussion_forum_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `comment_made` text,
  `owner_type` varchar(255) DEFAULT NULL,
  `owner_username` varchar(255) DEFAULT NULL,
  `display_type` varchar(255) DEFAULT NULL,
  `date_of_comment` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_comments_forum_casacade` (`post_id`),
  CONSTRAINT `FK_comments_forum_casacade` FOREIGN KEY (`post_id`) REFERENCES `discussion_forum_post` (`id`),
  CONSTRAINT `discussion_forum_comments_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `discussion_forum_post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussion_forum_comments`
--

LOCK TABLES `discussion_forum_comments` WRITE;
/*!40000 ALTER TABLE `discussion_forum_comments` DISABLE KEYS */;
INSERT INTO `discussion_forum_comments` VALUES (1,21,'Hello','faculty','shehab','username','2015-04-26 02:23:50'),(2,21,'gjgjghjghj','faculty','shehab','anonymous_to_all','2015-04-26 02:46:24'),(3,21,'Comment 3','faculty','shehab','anonymous_to_all','2015-04-26 11:54:46'),(4,21,'Test','faculty','shehab','username','2015-04-26 13:58:30'),(5,21,'Test','faculty','shehab','anonymous_to_all','2015-04-26 13:58:42'),(6,21,'Test','faculty','shehab','anonymous_to_all_but_faculty','2015-04-26 13:58:50'),(12,21,'test as student\r\n','student','rohana','username','2015-04-26 17:03:34'),(13,21,'Test','student','rohana','username','2015-04-26 17:30:06'),(14,21,'Testing','faculty','shehab','anonymous_to_all','2015-04-26 18:18:40'),(16,21,'Student post 1','student','rohana','username','2015-04-26 18:30:31'),(17,21,'Student Post 2','student','rohana','anonymous_to_all','2015-04-26 18:30:46'),(18,21,'Student Post 3','student','rohana','anonymous_to_all_but_faculty','2015-04-26 18:30:58'),(25,34,'Faculty Comment 1','faculty','shehab','username','2015-04-26 19:05:37'),(26,34,'Student Comment 1 (as user)','student','chandar','username','2015-04-26 19:06:00'),(27,34,'Student Comment as anonymous to all','student','chandar','anonymous_to_all','2015-04-26 19:06:31'),(28,34,'Faculty Comment 1','faculty','shehab','username','2015-04-26 19:06:42'),(29,34,'Student Comment as anonymous to all but faculty','student','chandar','anonymous_to_all_but_faculty','2015-04-26 19:07:25'),(30,34,'Faculty Comment 1','faculty','shehab','username','2015-04-26 19:07:39'),(31,34,'Hi Check','faculty','shehab','username','2015-04-27 09:29:40'),(32,34,'Hi Check','faculty','shehab','username','2015-04-27 09:29:47'),(33,34,'test','faculty','shehab','username','2015-04-27 09:38:29'),(34,34,'test','faculty','shehab','username','2015-04-27 09:38:35'),(35,35,'fdsd','faculty','shehab','username','2015-04-27 09:49:19'),(36,35,'fdsd','faculty','shehab','username','2015-04-27 09:49:27'),(43,21,'Testing','faculty','shehab','anonymous_to_all','2015-04-28 13:51:00'),(44,21,'Testing','faculty','shehab','anonymous_to_all','2015-04-28 13:59:50'),(45,21,'Testing','faculty','shehab','anonymous_to_all','2015-04-28 14:26:16'),(46,21,'Testing','faculty','shehab','anonymous_to_all','2015-04-28 14:29:44'),(47,40,'Comment by faculty','faculty','shehab','username','2015-04-28 14:53:23'),(48,40,'Student comment anonymous','student','rohana','anonymous_to_all','2015-04-28 14:54:12'),(49,21,'Testing','faculty','shehab','anonymous_to_all','2015-04-28 14:58:24'),(50,21,'Testing','faculty','shehab','anonymous_to_all','2015-04-28 15:23:53'),(51,21,'Testing','faculty','shehab','anonymous_to_all','2015-05-03 04:24:24'),(52,42,'Cras in magna eu lorem cursus efficitur sit amet eu mi. Etiam justo dolor, ultricies vel dolor in, dictum interdum velit. Integer consectetur purus at mi posuere accumsan. Donec vitae erat a eros ornare tincidunt eget a diam. Morbi lacus augue, gravida a felis vel, sodales suscipit nulla. Duis in dolor id libero congue interdum quis ac nibh. Aliquam porttitor urna velit, eget bibendum leo volutpat sit amet. Aenean lacinia sed sem vitae vestibulum. Sed quis turpis a nisi tristique eleifend. Praesent tortor turpis, pharetra a elit ac, elementum dapibus lectus. Nullam vitae leo sit amet nisi vestibulum placerat a vitae diam. Fusce nec ante vulputate, venenatis metus congue, dictum purus. Vestibulum eget fringilla erat, in sagittis urna. Phasellus sed diam consequat, tincidunt quam vitae, aliquam ante. Curabitur sodales metus dictum lorem aliquet tincidunt. Nulla finibus vel felis vel dignissim.','student','srinivas','username','2015-05-03 16:03:05'),(53,42,'Sed id cursus massa, ac fringilla augue. Quisque orci eros, vulputate vitae suscipit ut, pharetra nec elit. Aliquam eget finibus dui. Nam eget tristique massa, ac volutpat dui. Vestibulum sit amet tincidunt ipsum, sit amet sodales nibh. Mauris vulputate ultrices lacus, vel laoreet eros facilisis a. Quisque venenatis est quis scelerisque lacinia. Nulla rhoncus purus id elit iaculis fermentum. Nam condimentum ante in mauris bibendum, in semper lacus euismod. Ut turpis libero, aliquet id sapien non, faucibus pellentesque diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;','student','srinivas','anonymous_to_all','2015-05-03 16:03:18'),(54,42,'Donec eget ipsum scelerisque lectus rhoncus condimentum. Suspendisse potenti. Curabitur sed odio lacus. Praesent dictum faucibus nisi. Curabitur quis sapien id ex volutpat feugiat. Duis pellentesque dolor in tellus pellentesque, ac eleifend nisl sollicitudin. Nulla non imperdiet mauris. Curabitur mattis orci a cursus gravida. Suspendisse tempus, risus et mollis posuere, felis turpis mattis ex, sed viverra nulla leo eu odio. Nulla vel nunc vitae enim luctus semper. Donec eget leo fringilla, laoreet ligula nec, lobortis magna. Aliquam sit amet sapien interdum, pulvinar nisi et, hendrerit eros. Vivamus rhoncus, urna convallis ultrices euismod, orci massa auctor turpis, sed volutpat mi leo ut lacus. Proin ut elit volutpat, lacinia dolor ornare, fermentum lacus. Suspendisse volutpat interdum est mollis rutrum.','student','srinivas','anonymous_to_all_but_faculty','2015-05-03 16:03:29'),(55,42,'Aliquam nec felis at ipsum semper efficitur dictum eu massa. Nulla a tristique elit. Sed egestas pretium condimentum. Duis molestie nisl eget risus pharetra, vitae viverra magna convallis. Quisque luctus, neque pellentesque volutpat consectetur, odio erat ultrices quam, nec congue quam mi sed ante. Praesent maximus orci eros, sed lobortis eros cursus ut. Cras dictum ex sem, sed faucibus turpis bibendum a. Nam dapibus turpis purus, non tincidunt nibh scelerisque nec. Sed sapien enim, rutrum sed efficitur ut, pharetra sit amet elit. Aliquam at neque rhoncus, blandit est sit amet, aliquet mi. Nam congue viverra ipsum non imperdiet. Donec in felis nisi. Phasellus porttitor, justo sit amet hendrerit egestas, orci elit convallis ips','faculty','shehab','username','2015-05-03 16:09:13'),(56,48,'dfdf','faculty','troy','username','2015-05-03 16:33:32'),(57,48,'dfdf','faculty','troy','username','2015-05-03 16:33:36');
/*!40000 ALTER TABLE `discussion_forum_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussion_forum_post`
--

DROP TABLE IF EXISTS `discussion_forum_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussion_forum_post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `board_id` int(11) DEFAULT NULL,
  `owner_type` varchar(20) NOT NULL,
  `owner_username` varchar(50) NOT NULL,
  `display_type` varchar(50) NOT NULL,
  `title` varchar(200) NOT NULL,
  `content` text NOT NULL,
  `date_of_post` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `board_id` (`board_id`),
  CONSTRAINT `discussion_forum_post_ibfk_1` FOREIGN KEY (`board_id`) REFERENCES `academic_board` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussion_forum_post`
--

LOCK TABLES `discussion_forum_post` WRITE;
/*!40000 ALTER TABLE `discussion_forum_post` DISABLE KEYS */;
INSERT INTO `discussion_forum_post` VALUES (21,4,'faculty','shehab','username','SSDI POST 2','SSDI','2015-04-26 18:10:48'),(27,4,'faculty','shehab','username','I am gonna see this','Lets try to Edit','2015-04-26 13:47:39'),(28,4,'faculty','shehab','username','new one from she','gjgh','2015-04-26 14:20:26'),(30,4,'faculty','shehab','username','Testing Faculty add','Yr','2015-04-26 17:57:34'),(31,4,'faculty','shehab','username','Prof. post','Prof.post','2015-04-26 18:09:38'),(32,4,'student','rohana','anonymous_to_all_but_faculty','Student post1','Student post1','2015-04-26 18:17:57'),(34,2,'faculty','shehab','username','Faculty Post 1','Faculty Post 1 as user always','2015-04-26 18:59:58'),(35,2,'student','chandar','anonymous_to_all_but_faculty','Student Post 2','Student Post 2 as anonymous to all (Edited)','2015-04-26 19:04:46'),(36,2,'student','chandar','anonymous_to_all_but_faculty','Student Post 3','Student Post 3 as anonymous to all but faculty','2015-04-26 19:01:35'),(37,2,'student','rohana','username','New Check','Deleting this','2015-04-26 19:03:31'),(40,4,'faculty','shehab','username','Test edit 1','Test edit 1','2015-04-28 14:52:41'),(41,4,'student','rohana','anonymous_to_all_but_faculty','Sample','Sample','2015-04-28 14:51:19'),(42,1,'faculty','shehab','username','Neque porro quisquam est qui dolorem ipsum quia dolor sit amet','Interdum et malesuada fames ac ante ipsum primis in faucibus. Proin cursus sem convallis molestie pellentesque. Sed luctus consectetur urna sed fringilla. Quisque malesuada est imperdiet, interdum tellus sit amet, hendrerit elit. Aliquam commodo lectus nisi, id euismod elit pretium vel. Curabitur eu ligula odio. Aliquam nisl urna, accumsan id neque et, lacinia cursus lorem. Sed venenatis ante a lorem vehicula ornare. Nullam malesuada commodo massa, sed rutrum nisl placerat sit amet. Duis bibendum aliquet felis at tempus. Maecenas eget metus odio. Sed consectetur dolor lectus, quis pellentesque arcu scelerisque vel. Morbi sollicitudin dui vel pellentesque laoreet. Donec sed ligula eget est laoreet convallis. Integer justo odio, tincidunt eu fringilla et, luctus sed metus. Sed mi lectus, auctor semper dignissim in, ultrices nec enim.','2015-05-03 15:56:42'),(43,1,'faculty','shehab','username','Fusce volutpat venenatis tortor a porta','In quis volutpat odio. Nam et malesuada mauris. Etiam lacinia condimentum nisi, eu fermentum neque maximus ut. Nam posuere erat ut venenatis malesuada. Aenean at metus non nunc vestibulum luctus. Quisque fringilla scelerisque convallis. Aenean vestibulum pharetra orci eget tincidunt. Etiam eget consectetur mauris.','2015-05-03 15:57:47'),(44,1,'faculty','shehab','username','Nullam vulputate neque a sem tempor dignissim','Integer bibendum vulputate urna, eu eleifend nisl cursus eu. Vivamus eleifend ut orci nec vulputate. Curabitur ultricies at lacus at venenatis. Morbi viverra laoreet ante venenatis semper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Etiam vitae lacus tincidunt, accumsan est id, dictum nunc. Aenean venenatis massa vitae lacinia aliquam. Integer nec lacus scelerisque, luctus nulla viverra, hendrerit est. Nullam dolor magna, vehicula eget ipsum eget, sodales ultricies ex. Etiam quis dignissim mauris, ac malesuada ante. Integer iaculis maximus tortor, in aliquam quam porttitor in. Aenean nec quam quis purus dapibus finibus. Praesent a velit tempus augue viverra mattis. Morbi sit amet quam sem. Sed congue malesuada erat non accumsan.','2015-05-03 15:58:21'),(45,1,'student','srinivas','username','Aenean egestas, velit id maximus laoreet','sapien velit porttitor nunc, id molestie arcu tellus eget enim. Ut ipsum velit, suscipit vel volutpat et, laoreet non nunc. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nulla pharetra suscipit gravida. Quisque volutpat efficitur dolor, sed maximus enim. Suspendisse vel congue nibh. Phasellus ut massa in ante tincidunt congue. Suspendisse ullamcorper orci vel fermentum finibus. Aliquam eu vestibulum magna, eu rhoncus turpis. Sed porttitor ex leo, non elementum purus luctus non. Pellentesque dui mauris, egestas in odio ut, ultrices bibendum lorem.','2015-05-03 16:01:25'),(46,1,'student','srinivas','anonymous_to_all','Maecenas lobortis ipsum id condimentum pulvinar','Nunc consectetur vestibulum eros. Donec vel finibus odio, at viverra nisi. Nullam pellentesque pellentesque lacus. Nam blandit euismod sem, id faucibus nisi mattis vitae. Vivamus elementum risus magna, sit amet eleifend nisi bibendum sed. Fusce urna nisl, faucibus sit amet malesuada vitae, suscipit at velit. Pellentesque non augue vel eros pharetra consectetur. Quisque vehicula feugiat scelerisque. In commodo nulla nec nisi dictum lobortis. Vestibulum id laoreet arcu. Quisque et nulla leo. Curabitur eu viverra nisi, posuere viverra quam. Nullam feugiat eu libero id vehicula.','2015-05-03 16:02:20'),(47,1,'student','srinivas','anonymous_to_all_but_faculty','Nam ligula orci, euismod ut pharetra sed,',' interdum eget libero. Integer varius porta quam, eget sollicitudin neque cursus sed. Phasellus mollis dapibus magna sed egestas. Donec ullamcorper volutpat tortor, eget accumsan tortor sodales et. Aliquam erat volutpat. Aenean in sodales augue, a mollis augue. Cras fringilla ligula eu tempor elementum. Aenean non auctor justo, a auctor mi. Fusce et vestibulum dolor. Quisque eros neque, pretium non hendrerit sit amet, blandit commodo arcu.','2015-05-03 16:02:49'),(48,17,'faculty','troy','username','test','sdsfs','2015-05-03 16:33:22');
/*!40000 ALTER TABLE `discussion_forum_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `faculty_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`faculty_id`),
  KEY `username` (`username`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `faculty_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=408 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (400,'sever123',3),(401,'shehab',2),(402,'pamela',2),(403,'wang',2),(404,'phoenix',2),(405,'sekar',2),(406,'har123',2),(407,'troy',3);
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_course`
--

DROP TABLE IF EXISTS `faculty_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` varchar(10) DEFAULT NULL,
  `faculty_id` int(11) DEFAULT NULL,
  `course_appr_flag` int(1) DEFAULT '0',
  `faculty_username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `faculty_id` (`faculty_id`),
  CONSTRAINT `faculty_course_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `faculty_course_ibfk_2` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`faculty_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_course`
--

LOCK TABLES `faculty_course` WRITE;
/*!40000 ALTER TABLE `faculty_course` DISABLE KEYS */;
INSERT INTO `faculty_course` VALUES (1,'ITCS5187',401,1,'shehab'),(3,'ITCS6160',401,1,'shehab'),(4,'ITCS6112',401,1,'shehab'),(5,'ITCS6160',402,1,'pamela'),(6,'ITCS6112',403,1,'wang'),(7,'ITCS6114',403,1,'wang'),(8,'ITCS5187',403,0,'wang'),(9,'ITCS6112',404,1,'phoenix'),(10,'ITCS6114',404,1,'phoenix'),(11,'ITCS5187',404,1,'phoenix'),(12,'ITCS5187',405,1,'sekar'),(13,'ITCS6112',405,1,'sekar'),(14,'ITCS6114',405,1,'sekar'),(15,'ITCS6160',405,1,'sekar'),(16,'ITCS6160',404,1,'phoenix'),(17,'ITCS6114',401,1,'shehab'),(18,'ITIS5120',400,1,'sever123'),(19,'ITIS6112',400,1,'sever123'),(20,'ITIS5120',407,1,'troy'),(21,'ITIS5187',407,1,'troy'),(22,'ITIS5250',407,1,'troy'),(23,'ITIS6112',407,1,'troy');
/*!40000 ALTER TABLE `faculty_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_credential`
--

DROP TABLE IF EXISTS `login_credential`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_credential` (
  `username` varchar(50) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `approval_flag` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_credential`
--

LOCK TABLES `login_credential` WRITE;
/*!40000 ALTER TABLE `login_credential` DISABLE KEYS */;
INSERT INTO `login_credential` VALUES ('admin','admin',1),('anish','123456',1),('chandar','chandar',1),('deds','dfadas',1),('deepakrohan','123456',1),('har123','123456',0),('harini','harini',1),('pamela','pamela',1),('phoenix','123456',1),('prabhu','prabhu',0),('ram2391','ram2391',1),('rohan123','rohan123',1),('rohana','123456',1),('sashank146','qwerty',1),('sekar','123456',1),('sever123','sever123',1),('shehab','shehab',1),('srinivas','srinivas',1),('troy','123456',1),('vathsan','vathsan',0),('vinoth','vinoth',1),('wang','wang123',1);
/*!40000 ALTER TABLE `login_credential` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material` (
  `material_id` int(11) NOT NULL AUTO_INCREMENT,
  `academic_board_id` int(11) DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  `description` text,
  `link` varchar(255) NOT NULL,
  `type` varchar(20) NOT NULL,
  `file_key` varchar(255) NOT NULL,
  PRIMARY KEY (`material_id`),
  KEY `academic_board_id` (`academic_board_id`),
  CONSTRAINT `material_ibfk_1` FOREIGN KEY (`academic_board_id`) REFERENCES `academic_board` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (9,1,'Here is a test','Checking the second assignment to test radios','https://s3.amazonaws.com/uncccsboard1/jquery.validate.min.js','assignment','jquery.validate.min.js'),(10,4,'test','dscscsdc','https://s3.amazonaws.com/uncccsboard4/contest-2.rtf','assignment','contest-2.rtf'),(11,4,'test','checj','https://s3.amazonaws.com/uncccsboard4/DB.png','assignment','DB.png'),(12,1,'contest','Check this','https://s3.amazonaws.com/uncccsboard1/JavaBasics-notes.pdf','assignment','JavaBasics-notes.pdf'),(16,1,'Test y','kj','https://s3.amazonaws.com/uncccsboard1/Project.sql','assignment','Project.sql'),(17,2,'Test y','sjkdhjshdls','https://s3.amazonaws.com/uncccsboard2/linear_search.java','academic','linear_search.java'),(18,2,'first Work','Sweet Dreams!','https://s3.amazonaws.com/uncccsboard2/Java+Basics.txt','assignment','Java Basics.txt'),(22,1,'Assign1','Assign1','https://s3.amazonaws.com/uncccsboard1/Java+Basics.txt','assignment','Java Basics.txt'),(23,1,'Algo doc','The algodoc','https://s3.amazonaws.com/uncccsboard1/algo.docx','assignment','algo.docx'),(24,1,'test dee','Morbi ac congue enim. Curabitur scelerisque ipsum eget urna efficitur viverra. In volutpat, sem non laoreet viverra, nunc erat fringilla lectus, id mollis est mi vel lorem. Aenean ac libero eget nunc accumsan vehicula et non lectus. Mauris sagittis erat ac aliquam molestie. Proin sagittis odio diam, in laoreet lacus blandit in. Ut eget consectetur justo, vitae ornare nisi. Fusce eget finibus ligula. Cras sollicitudin mi vel sodales malesuada.','https://s3.amazonaws.com/uncccsboard1/algo.docx','academic','algo.docx'),(25,1,'simply because it is pain','Maecenas tristique nulla sed elit blandit, non elementum dui laoreet. Sed egestas, felis sed fermentum porttitor, libero nibh consequat turpis, vitae auctor velit augue id dolor. Integer in velit non ligula interdum tristique commodo sit amet eros. Mauris feugiat mi metus. In est justo, efficitur id congue quis, malesuada sed lectus. Nullam porta sem sed urna rutrum, eu consequat purus condimentum. Sed sit amet justo facilisis mi tempus viverra sed quis tortor. Maecenas at viverra libero, eu suscipit est. Nulla dapibus volutpat velit, id auctor quam iaculis vel. Vivamus ante eros, venenatis id augue vehicula, vestibulum posuere lorem. Quisque pulvinar, orci vitae volutpat hendrerit, tortor metus malesuada mauris, ut semper ante nisl nec mi','https://s3.amazonaws.com/uncccsboard1/Algorithms+-+Chapter+22.doc','academic','Algorithms - Chapter 22.doc'),(26,17,'Test','Check','https://s3.amazonaws.com/uncccsboard17/algo.docx','academic','algo.docx'),(27,17,'First','check','https://s3.amazonaws.com/uncccsboard17/algo.docx','assignment','algo.docx');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `roll_number` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`roll_number`),
  KEY `username` (`username`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=810 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (800,NULL,NULL),(801,'ram2391',2),(802,'rohana',2),(803,'anish',2),(804,'sashank146',2),(805,'chandar',3),(806,'vathsan',3),(807,'vinoth',3),(808,'srinivas',2),(809,'prabhu',2);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_class_assignment_grade`
--

DROP TABLE IF EXISTS `student_class_assignment_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_class_assignment_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_roll_number` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `assignment_post_id` int(11) DEFAULT NULL,
  `grade` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_roll_number` (`student_roll_number`),
  KEY `class_id` (`class_id`),
  KEY `assignment_post_id` (`assignment_post_id`),
  CONSTRAINT `student_class_assignment_grade_ibfk_1` FOREIGN KEY (`student_roll_number`) REFERENCES `student` (`roll_number`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `student_class_assignment_grade_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `faculty_course` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `student_class_assignment_grade_ibfk_3` FOREIGN KEY (`assignment_post_id`) REFERENCES `assignment_post` (`post_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_class_assignment_grade`
--

LOCK TABLES `student_class_assignment_grade` WRITE;
/*!40000 ALTER TABLE `student_class_assignment_grade` DISABLE KEYS */;
INSERT INTO `student_class_assignment_grade` VALUES (1,804,1,NULL,NULL),(2,808,1,NULL,NULL),(3,809,1,NULL,NULL),(4,804,1,NULL,NULL),(5,808,1,NULL,NULL),(6,809,1,NULL,NULL),(7,801,4,NULL,NULL),(8,802,4,NULL,NULL);
/*!40000 ALTER TABLE `student_class_assignment_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stud_roll_number` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `stud_roll_number` (`stud_roll_number`),
  KEY `class_id` (`class_id`),
  CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`stud_roll_number`) REFERENCES `student` (`roll_number`),
  CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `faculty_course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (1,801,3),(2,802,3),(3,802,4),(4,807,3),(5,805,3),(7,801,4),(8,804,1),(9,808,1),(10,809,1);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `user_type` varchar(10) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `date_of_birth` date NOT NULL,
  `email_id` varchar(50) NOT NULL,
  `gender` varchar(6) DEFAULT NULL,
  UNIQUE KEY `username` (`username`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`username`) REFERENCES `login_credential` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','admin','admin','admin','2015-03-11','d@gm.com','male'),('anish','student','anish','anish','1990-03-06','anish@gmail.com','male'),('chandar','student','Chandra','Sekar','1991-03-06','chandar@gmail.com','male'),('deds','student','sd','sd','2015-03-11','Sweet Dreams!@ds','male'),('deepakrohan','faculty','Dee','Rohan','0000-00-00','d@f.cim','male'),('har123','faculty','Harini','Ramaprasad','2005-03-03','harini@gmail.com','female'),('harini','student','Harini','Ramaprasad','2015-03-10','harini@gmail.com','female'),('pamela','faculty','Pamela','Thompson','1973-03-07','pamela@gmail.com','female'),('phoenix','faculty','phoenix','phoenix','1990-03-24','s@g.com','male'),('prabhu','student','prabhu','prabhu','1965-03-04','p@g.com','male'),('ram2391','student','Ram Prasad','N','2005-03-02','ram@gmail.com','male'),('rohan123','student','Deepak','Rohan','0183-11-07','rohan@gmail.com','male'),('rohana','student','Deepak Rohan','Sekar','1990-03-24','d@gmail.com','male'),('sashank146','student','Sashank','Santhanam','1992-03-01','sashank@gmail.com','male'),('sekar','faculty','sekar','sekar','2005-03-11','d@gmail.com','male'),('sever123','faculty','Ali','Sever','1976-03-02','sever@gmail.com','male'),('shehab','faculty','Mohammad','Shehab','1977-03-09','shehab@gmail.com','male'),('srinivas','student','Srinivasan','Nambi','1989-07-14','srini@gmail.com','male'),('troy','faculty','troy','troy','1916-05-31','d@gmail.com','male'),('vathsan','student','Vathsan','Sri','1989-07-13','vathsan@gmail.com','male'),('vinoth','student','Vinoth','Kumar','1991-02-16','vinoth@gmail.com','male'),('wang','faculty','Yu','Wang','1977-09-15','wang@gmail.com','male');
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

-- Dump completed on 2015-05-03 17:55:27
