CREATE TABLE `rank_score` (
  `id` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `score` varchar(255) NOT NULL,
  `updateAt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

