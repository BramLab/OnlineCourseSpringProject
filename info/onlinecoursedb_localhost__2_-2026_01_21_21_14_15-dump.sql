-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: onlinecoursedb
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `created_at` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `instructor_id` bigint DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc0xls9e7uqc9o08ae0t2ywr6n` (`instructor_id`),
  CONSTRAINT `FKc0xls9e7uqc9o08ae0t2ywr6n` FOREIGN KEY (`instructor_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`created_at`, `end_date`, `id`, `instructor_id`, `start_date`, `updated_at`, `description`, `title`) VALUES ('2026-01-21 21:12:46.027000','2026-09-01 00:00:00.000000',1,1,'2026-04-01 00:00:00.000000','2026-01-21 21:12:46.027000','OOP, preparation for OCA','Java');
INSERT INTO `course` (`created_at`, `end_date`, `id`, `instructor_id`, `start_date`, `updated_at`, `description`, `title`) VALUES ('2026-01-21 21:12:46.036000','2026-10-01 00:00:00.000000',2,2,'2026-09-02 00:00:00.000000','2026-01-21 21:12:46.036000','cv, brief, solliciteren','Tws');
INSERT INTO `course` (`created_at`, `end_date`, `id`, `instructor_id`, `start_date`, `updated_at`, `description`, `title`) VALUES ('2026-01-21 21:12:46.042000','2026-10-01 00:00:00.000000',3,3,'2026-09-02 00:00:00.000000','2026-01-21 21:12:46.042000','front end','html');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollment` (
  `course_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbhhcqkw1px6yljqg92m0sh2gt` (`course_id`),
  KEY `FK65os88xfjxr2tos3tksqeleg6` (`student_id`),
  CONSTRAINT `FK65os88xfjxr2tos3tksqeleg6` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKbhhcqkw1px6yljqg92m0sh2gt` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` (`course_id`, `created_at`, `id`, `student_id`, `updated_at`) VALUES (1,'2026-01-21 21:12:46.049000',1,4,'2026-01-21 21:12:46.049000');
INSERT INTO `enrollment` (`course_id`, `created_at`, `id`, `student_id`, `updated_at`) VALUES (2,'2026-01-21 21:12:46.057000',2,5,'2026-01-21 21:12:46.057000');
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `created_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `updated_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password_hashed` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','INSTRUCTOR','STUDENT') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`created_at`, `id`, `updated_at`, `email`, `password_hashed`, `username`, `role`) VALUES ('2026-01-21 21:12:45.404000',1,'2026-01-21 21:12:45.404000','user_instructor1@c.com','$2a$10$o6Fg7gH4rSQbYIKiy..o6ufBjnoaMaCeCDIxnfoNdmgfLLF8eixpa','user_instructor1','INSTRUCTOR');
INSERT INTO `user` (`created_at`, `id`, `updated_at`, `email`, `password_hashed`, `username`, `role`) VALUES ('2026-01-21 21:12:45.512000',2,'2026-01-21 21:12:45.512000','user_instructor2@c.com','$2a$10$.Qgk65wCwWMkSm08M3674usOBhqiwbHcbuKcaXtLn3ujB4dumJLW2','user_instructor2','INSTRUCTOR');
INSERT INTO `user` (`created_at`, `id`, `updated_at`, `email`, `password_hashed`, `username`, `role`) VALUES ('2026-01-21 21:12:45.599000',3,'2026-01-21 21:12:45.599000','user_instructor3@c.com','$2a$10$iAUUelCM9j14rQ8JTqYniuOEBiVXVcy9Q3uvJbYqC4szsuCrAcBgS','user_instructor3','INSTRUCTOR');
INSERT INTO `user` (`created_at`, `id`, `updated_at`, `email`, `password_hashed`, `username`, `role`) VALUES ('2026-01-21 21:12:45.681000',4,'2026-01-21 21:12:45.681000','user_student1@c.com','$2a$10$4N8k.Xzok4TtUgePOwrmteU3LqbRD9LuTu1twQ7CtR24./iqScTPq','user_student1','STUDENT');
INSERT INTO `user` (`created_at`, `id`, `updated_at`, `email`, `password_hashed`, `username`, `role`) VALUES ('2026-01-21 21:12:45.766000',5,'2026-01-21 21:12:45.766000','user_student2@c.com','$2a$10$ZJcoRBmrl.eZ3VOuzmrJSOEEhCZgJLBuvpz4BnfOCCAaHeGm5fOve','user_student2','STUDENT');
INSERT INTO `user` (`created_at`, `id`, `updated_at`, `email`, `password_hashed`, `username`, `role`) VALUES ('2026-01-21 21:12:45.848000',6,'2026-01-21 21:12:45.848000','user_student3@c.com','$2a$10$EDyk1qvZcylX8yuxph/D3OURLH5P.mEpd9zQolEUGBMaC18dtQg.C','user_student3','STUDENT');
INSERT INTO `user` (`created_at`, `id`, `updated_at`, `email`, `password_hashed`, `username`, `role`) VALUES ('2026-01-21 21:12:45.928000',7,'2026-01-21 21:12:45.928000','user_admin1@c.com','$2a$10$xnytOY/zAcg5.EOvZrAetOTpMMGUTHJU.EXbmPI7BdsZXt6zwPR/.','user_admin1','ADMIN');
INSERT INTO `user` (`created_at`, `id`, `updated_at`, `email`, `password_hashed`, `username`, `role`) VALUES ('2026-01-21 21:12:46.019000',8,'2026-01-21 21:12:46.019000','user_admin2@c.com','$2a$10$jmmwkovr9gmHPJWzcNLgo.1PRhwC39hpMoalqQAn9YjTaIngM78gm','user_admin2','ADMIN');
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

-- Dump completed on 2026-01-21 21:14:15
