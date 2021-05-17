
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `gender` varchar(1) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `nickname` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci


CREATE TABLE `orders` (
  `order_num` varchar(12) COLLATE utf8mb4_general_ci NOT NULL,
  `payment_dt` datetime(6) NOT NULL,
  `product_nm` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`order_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

CREATE TABLE `member_order_trn` (
    `member_id` bigint NOT NULL,
    `order_num` varchar(12) COLLATE utf8mb4_general_ci NOT NULL,
    PRIMARY KEY (`member_id`,`order_num`),
    UNIQUE KEY `UK_6baybiryth5k2i37qdnk99jda` (`order_num`),
    CONSTRAINT `FK51babpawsvl50dp79qm9pv42x` FOREIGN KEY (`order_num`) REFERENCES `orders` (`order_num`),
    CONSTRAINT `FKs91jqhigw8eetl6i961yklfpf` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci