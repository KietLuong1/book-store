-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: book_store
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `ward` varchar(45) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_customer_id` bigint DEFAULT NULL,
  `district` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `UK_s673mhnxcvjr5oevend5biles` (`user_customer_id`),
  CONSTRAINT `FKh2j8eo9wbeawdub7xhaom5j06` FOREIGN KEY (`user_customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,NULL,NULL,NULL,NULL,NULL),(4,NULL,'',NULL,NULL,NULL),(5,NULL,'',NULL,NULL,NULL),(6,NULL,'',NULL,NULL,NULL),(7,'','','Tổ 8, Phú Thọ, Phú Chánh, thị xã Tân Uyên, Bình Dương',NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL,NULL),(11,NULL,NULL,NULL,NULL,NULL),(12,NULL,NULL,NULL,NULL,NULL),(13,'0','0','Tổ 8, khu phố Phú Thọ,',NULL,'0'),(14,NULL,NULL,NULL,NULL,NULL),(15,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `author_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `dob` date DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `profile_image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Đức Trọng','2002-01-01','Việt Nam','Quê Quán: Bình Dương\r\n',NULL,NULL),(2,'Anh Kiệt','2002-02-02','Việt Nam','Quê Quán: Bình Dương',NULL,NULL),(3,'Hương Duyên','2002-03-03','Việt Nam','Quê Quán: Bình Dương',NULL,NULL),(4,'Văn A','2003-06-30','Việt Nam','New Author',NULL,NULL),(5,'Peter','1985-07-02','USA','History Author',NULL,NULL),(6,'Tom','1975-06-09','Italy','Author From Italy',NULL,NULL),(7,'Ben','1995-06-09','Thai Lan','Young Author From Thai Lan',NULL,NULL),(10,'TRỢ GIẢNG THỬ NGHIỆM 1','2024-04-29','VN','',NULL,'http://res.cloudinary.com/dggujnlln/image/upload/v1716858888/Admin/authors/10/72557f3f-1d88-4f31-ac25-505504a00340.png');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `quantity` varchar(255) NOT NULL,
  `language` varchar(255) NOT NULL,
  `publication_date` varchar(255) NOT NULL,
  `num_pages` int NOT NULL,
  `sale_price` float NOT NULL,
  `book_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Apprentice to the Villain','Romance ','Hannah Nicole Maehrer ','LIMITED FIRST PRINT RUN―featuring spray-painted edges, while supplies last in the US and Canada only.',15.99,'210','English','August 6, 2023',352,10.99,NULL),(2,'Theory of Macroeconomic Hysteresis','Econometrics & Statistics','Isaak D Mayergoyz','This book deals with the mathematical theory of macroeconomic hysteresis, which is the theory of aggregation of microeconomic hysteresis. Microeconomic sunk cost hysteresis is usually represented by relatively simple hysteresis loops with no discrete memo',88,'560','English','June 20, 2022',200,70,NULL),(3,'The Trading Game: A Confession','Banks & Banking','Gary Stevenson ','#1 SUNDAY TIMES BESTSELLER • A “vivid” (Financial Times) rags-to-riches memoir that takes readers inside the high-stakes drama and hubris of the trading floor, a “darkly funny” (Guardian) tale of Citibank’s one-time most profitable trader, and why he gave',19.99,'320','English','March 5, 2023',352,15.99,NULL),(4,'Debugging','Computer & Technology','David J. Agans','The 9 Indispensable Rules for Finding Even the Most Elusive Software and Hardware Problems. When the pressure is on to resolve an elusive software or hardware glitch, what’s needed is a cool head courtesy of a set of rules guaranteed to work on any system',12.99,'130','English','September 23, 2002',202,8.99,NULL),(5,'JavaScript from Beginner to Professional','Computer & Technology',' Laurence Lars Svekis ','Learn JavaScript quickly by building fun, interactive, and dynamic web apps, games, and pages. Start your journey towards becoming a JavaScript developer with the help of more than 100 fun exercises and projects',37.99,'650','English','December 15, 2021',546,30.99,NULL),(6,'Why We Swim','Exercise & Fitness','Bonnie Tsui ','“A fascinating and beautifully written love letter to water. I was enchanted by this book.\" —Rebecca Skloot, bestselling author of The Immortal Life of Henrietta Lacks',11.29,'432','English','April 13, 2021',288,8.99,NULL),(7,'The Inheritance Games','Children\'s Books','Jennifer Lynn Barnes ','Don\'t miss this New York Times bestselling \"impossible to put down\" (Buzzfeed) novel with deadly stakes, thrilling twists, and juicy secrets—perfect for fans of One of Us is Lying and Knives Out.',10.99,'73','English','September 1, 2020',385,5.99,NULL),(8,'Lost Landmarks of Orange County','History',' Chris Epting','Since forming in 1889, Orange County, California has become famous all over the world for being home to such popular attractions as Disneyland, Knott’s Berry Farm, and some of the most beautiful beaches in the world. But there are also many other places t',18.19,'86','English','April 16, 2020',234,15.99,NULL),(9,'Introduction to Linear Algebra','Science & Math',' Gilbert Strang','Linear algebra now rivals or surpasses calculus in importance for people working in quantitative fields of all kinds: engineers, scientists, economists and business people. Gilbert Strang has taught linear algebra at MIT for more than 50 years and the cou',83.13,'35','English','April 30, 2023',440,76.99,NULL),(10,'Silent Spring','Environment & Nature',' Rachel Carson ','“Rachel Carson is a pivotal figure of the twentieth century…people who thought one way before her essential 1962 book Silent Spring thought another way after it.”—Margaret Atwood',13.49,'56','English','February 1, 2022',400,10.79,NULL),(11,'The Lost Art of Finding Our Way','Environment & Nature',' John Edward Huth ','Long before GPS, Google Earth, and global transit, humans traveled vast distances using only environmental clues and simple instruments. John Huth asks what is lost when modern technology substitutes for our innate capacity to find our way. Encyclopedic i',22.16,'220','English','November 16, 2015',544,18.99,NULL),(12,'Who Was Leonardo da Vinci? ','Technology',' Roberta Edwards ','Leonardo da Vinci was a gifted painter, talented musician, and dedicated scientist and inventor, designing flying machines, submarines, and even helicopters.  Yet he had a hard time finishing things, a problem anyone can relate to.  Only thirteen painting',4.79,'20','English','September 8, 2005',112,2.99,NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` bigint DEFAULT NULL,
  `book_id` int DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKioh3c0mo0al2kswtnk5r09y7f` (`customer_id`),
  KEY `FK5n0sq8ulj6ykdnrh4dnk0vfmw` (`book_id`),
  CONSTRAINT `FK5n0sq8ulj6ykdnrh4dnk0vfmw` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `FKioh3c0mo0al2kswtnk5r09y7f` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Architecture','Architecture'),(2,'Art','Art'),(3,'Action','Action'),(4,'Biography','Biography'),(5,'Body, Mind & Spirit','Body, Mind & Spirit'),(6,'Business & Economics','Business & Economics'),(7,'Children Fiction','Children Fiction'),(8,'Children Non-Fiction','Children Non-Fiction'),(9,'Comics & Graphics','Comics & Graphics'),(10,'Cooking','Cooking'),(11,'Crafts & Hobbies','Crafts & Hobbies'),(12,'Design','Design'),(13,'Drama','Drama'),(14,'Education','Education'),(15,'Family & Relationships','Family & Relationships'),(16,'Fiction','Fiction'),(17,'Foreign Language','Foreign Language'),(18,'Games','Games'),(19,'History','History'),(20,'House & Home','House & Home');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkout`
--

DROP TABLE IF EXISTS `checkout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkout` (
  `checkout_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `shipping_cost_total` float NOT NULL,
  `subtotal` float NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`checkout_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkout`
--

LOCK TABLES `checkout` WRITE;
/*!40000 ALTER TABLE `checkout` DISABLE KEYS */;
/*!40000 ALTER TABLE `checkout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_address`
--

DROP TABLE IF EXISTS `customer_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_address` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `note` varchar(255) NOT NULL,
  `province` varchar(255) NOT NULL,
  `ward` varchar(255) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_address`
--

LOCK TABLES `customer_address` WRITE;
/*!40000 ALTER TABLE `customer_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `address_id` int DEFAULT NULL,
  `age` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `occupation` varchar(50) DEFAULT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_customer_address_idx` (`address_id`),
  CONSTRAINT `fk_customer_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (8,'Duyen','Van','test1@gmail.com','123','$2a$10$qC1MM7F22RtlavgPUNFrleCMngR2uO7g7yc/UDQUMC02uJr4eMRzW',6,22,'','',NULL,_binary '\0',NULL),(9,'Trong','Nguyen','123123@gmail.com','123123123','$2a$10$QD3sn0YL8DJpOgxyci8XKuxJdgqJg1hk7gBaMHu45XLONKgtUinom',7,22,'hehehehe','Giao su',NULL,_binary '\0',NULL),(15,'Kiet','Luong','kietluong.071002@gmail.com','3134567687','$2a$10$dJBTEzUqoTJXF4bFOcP6FumHGRF/RChCDLgbuWGyUb.85JpiuThKW',13,23,'','student','6nCmhW',_binary '',NULL),(16,'Văn Thị ','Hương Duyên','huongduyenvan3012@gmail.com',NULL,'$2a$10$pGbd6AQkl1zOpT6aRikAQeyQUt8ugrxfkDBCeHq6DbVEKJRyswMxm',14,0,NULL,NULL,NULL,_binary '',NULL),(17,'Nguyễn Đức','Trọng','trong.nguyen.cit20@eiu.edu.vn',NULL,'$2a$10$/jh5kFH9p3zO3H30uTwKYuW.0uWx92/ay7e5brmIDUp8SIXP3CQpO',15,0,NULL,NULL,NULL,_binary '',NULL);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forgot_password`
--

DROP TABLE IF EXISTS `forgot_password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `forgot_password` (
  `forgot_id` int NOT NULL AUTO_INCREMENT,
  `expiration_time` datetime(6) NOT NULL,
  `otp` int NOT NULL,
  `user_customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`forgot_id`),
  UNIQUE KEY `UK_6xjowsfx44x0ri90kq6pn5ivd` (`user_customer_id`),
  CONSTRAINT `FKk19sao72oor2t3kv12hwuhcvd` FOREIGN KEY (`user_customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `forgot_password`
--

LOCK TABLES `forgot_password` WRITE;
/*!40000 ALTER TABLE `forgot_password` DISABLE KEYS */;
INSERT INTO `forgot_password` VALUES (1,'2024-05-03 13:23:54.685000',576192,7);
/*!40000 ALTER TABLE `forgot_password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `news_id` int NOT NULL AUTO_INCREMENT,
  `author_news` varchar(255) NOT NULL,
  `description_news` text NOT NULL,
  `news_image` varchar(255) DEFAULT NULL,
  `publication` date NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'Duc Trong','<div>Newwwww</div>','','2024-05-24','New 1');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `details_id` int NOT NULL AUTO_INCREMENT,
  `orders_id` int NOT NULL,
  `book_id` int DEFAULT NULL,
  `total_price` bigint DEFAULT NULL,
  PRIMARY KEY (`details_id`),
  KEY `fk_order_update_idx` (`orders_id`),
  KEY `fk_details_book_idx` (`book_id`),
  CONSTRAINT `fk_details_order` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`orders_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_items_id` int NOT NULL AUTO_INCREMENT,
  `product_cost` float NOT NULL,
  `quantity` int DEFAULT NULL,
  `shipping_cost` float NOT NULL,
  `subtotal` float NOT NULL,
  `unit_price` float NOT NULL,
  `book_id` int DEFAULT NULL,
  `orders_id` int DEFAULT NULL,
  PRIMARY KEY (`order_items_id`),
  KEY `FKqscqcme08spiyt2guyqdj72eh` (`book_id`),
  KEY `FKqmxbj1e77sls50umaww7pkpnx` (`orders_id`),
  CONSTRAINT `FKqmxbj1e77sls50umaww7pkpnx` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`orders_id`),
  CONSTRAINT `FKqscqcme08spiyt2guyqdj72eh` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orders_id` int NOT NULL AUTO_INCREMENT,
  `order_date` date DEFAULT NULL,
  `customer_id` bigint NOT NULL,
  `shipping_method_id` int DEFAULT NULL,
  `dest_address_id` int DEFAULT NULL,
  `address` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `order_status` enum('CANCELLED','DELIVERED','NEW','PACKAGED','PAID','PICKED','PROCESSING','REFUNDED','RETURNED','SHIPPING') DEFAULT NULL,
  `payment_method` enum('COD','CREDIT_CARD') DEFAULT NULL,
  `product_cost` float NOT NULL,
  `shipping_cost` float NOT NULL,
  `subtotal` float NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`orders_id`),
  KEY `fk_order_ship` (`shipping_method_id`),
  KEY `fk_order_addr` (`dest_address_id`),
  KEY `FKpxtb8awmi0dk6smoh2vp1litg` (`customer_id`),
  CONSTRAINT `fk_order_ship` FOREIGN KEY (`shipping_method_id`) REFERENCES `shipping_method` (`method_id`),
  CONSTRAINT `FKpxtb8awmi0dk6smoh2vp1litg` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `publisher_id` int NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  `publisher_address` varchar(255) DEFAULT NULL,
  `publisher_email` varchar(255) DEFAULT NULL,
  `publisher_name` varchar(255) NOT NULL,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Vietnam','Tp.Ho Chi Minh','abc@gmail.com','Nha Nam'),(3,'Vietnam','Ha Noi','info@nxbkimdong.com.vn','Kim Dong');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_method`
--

DROP TABLE IF EXISTS `shipping_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_method` (
  `method_id` int NOT NULL,
  `method_name` varchar(255) DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_method`
--

LOCK TABLES `shipping_method` WRITE;
/*!40000 ALTER TABLE `shipping_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipping_method` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-28  9:24:20
