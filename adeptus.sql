-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 01, 2025 at 08:48 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `adeptus`
--

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `class_name` varchar(20) NOT NULL,
  `price_per_session` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  `staff_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`id`, `created_at`, `is_active`, `updated_at`, `class_name`, `price_per_session`, `teacher_id`, `staff_id`) VALUES
(1, '2025-01-30 23:03:24.000000', b'1', NULL, 'v101', 100000, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `class_history`
--

CREATE TABLE `class_history` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `date` datetime(6) NOT NULL,
  `class_id` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `class_history_student`
--

CREATE TABLE `class_history_student` (
  `class_history_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `class_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee_salaries`
--

CREATE TABLE `employee_salaries` (
  `id` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `bonus` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `employee_type` enum('STAFF','TEACHER') NOT NULL,
  `note` text DEFAULT NULL,
  `total` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `expenses_category`
--

CREATE TABLE `expenses_category` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `invalid_token`
--

CREATE TABLE `invalid_token` (
  `token_id` varchar(255) NOT NULL,
  `expiry_time` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `invalid_token`
--

INSERT INTO `invalid_token` (`token_id`, `expiry_time`) VALUES
('24f8c83f-d905-43f4-b5fd-b99fab808e95', '2025-01-26 17:26:00.000000'),
('a9c96b42-ca2d-4283-83a7-4b54bf44e18a', '2025-01-31 04:09:26.000000'),
('e6c3980f-df1d-4e3f-a9d5-0c788e8b0c43', '2025-01-28 01:48:16.000000');

-- --------------------------------------------------------

--
-- Table structure for table `mark`
--

CREATE TABLE `mark` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `exam_name` varchar(100) DEFAULT NULL,
  `mark` float NOT NULL,
  `course_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `other_expenses`
--

CREATE TABLE `other_expenses` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `date` date NOT NULL,
  `description` text DEFAULT NULL,
  `total` bigint(20) NOT NULL,
  `expenses_category_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `permission`
--

CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `description` tinytext DEFAULT NULL,
  `permission_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `description` tinytext DEFAULT NULL,
  `role_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `created_at`, `is_active`, `updated_at`, `description`, `role_name`) VALUES
(1, '2025-01-26 16:44:54.000000', b'1', NULL, ' Manages everything.', 'CEO'),
(2, '2025-01-26 16:46:08.000000', b'1', NULL, 'Education Manager - Manages students', 'EM'),
(3, '2025-01-26 16:46:08.000000', b'1', NULL, 'Product Manager - Manages teachers', 'PM'),
(4, '2025-01-26 16:46:08.000000', b'1', NULL, 'Handles sales', 'Sales'),
(5, '2025-01-26 16:47:10.000000', b'1', NULL, 'Handles advertising to attract customers', ' Marketing');

-- --------------------------------------------------------

--
-- Table structure for table `role_permission`
--

CREATE TABLE `role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `thumbnail_public_id` varchar(255) DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `username` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`id`, `created_at`, `is_active`, `updated_at`, `dob`, `email`, `full_name`, `password`, `phone`, `thumbnail_public_id`, `thumbnail_url`, `username`) VALUES
(1, '2025-01-26 16:48:05.000000', b'1', '2025-01-26 16:48:05.000000', '2000-05-15', 'john.doe9@email.com', 'John Doe', '$2a$10$VfCyyoIcRf2S/ZVzGFek7uUoqJKNEQ3QB2Udo4fT0qtnQpyiXKBvm', '0987654329', 'm3ibkhlwo8rgm7q89uz1', 'http://res.cloudinary.com/dt6ukgxqc/image/upload/v1737884884/m3ibkhlwo8rgm7q89uz1.jpg', 'qweqw1239'),
(3, '2025-02-01 00:01:25.000000', b'1', '2025-02-01 00:01:25.000000', '2025-01-07', 'a35wd1a3w5d1@gmail.com', '35a1d3a5w', '$2a$10$nR.nduoo7FhXzK73BqsyfOrZZSJzghTwmdoeIxU0xCp8u.ebiVDXG', '0363430180', 'r741zaxpmvqb7njfywm2', 'http://res.cloudinary.com/dt6ukgxqc/image/upload/v1738342883/r741zaxpmvqb7njfywm2.jpg', 'qweqw1238'),
(4, '2025-02-01 00:11:32.000000', b'1', '2025-02-01 00:11:32.000000', '2012-01-01', 'a5d3aw5@gmail.com', 'a35wd1a3w5', '$2a$10$NTmQQNf/MWhDh.cT1jBOLu1sCAEssTTuN0joxJhlMfsuRwyB91ZKy', '0363450210', 'rbiaqixowgwl0xr3fdvs', 'http://res.cloudinary.com/dt6ukgxqc/image/upload/v1738343491/rbiaqixowgwl0xr3fdvs.jpg', 'qweqw1237'),
(5, '2025-02-01 00:14:58.000000', b'1', '2025-02-01 00:14:58.000000', '2011-05-05', '3a51d3aw@gmail.com', '6a5sd16as5', '$2a$10$3rE9lBkf7IYBby4AgJPOHu4qmKtg0xDpv8rWduIO/zylJiH7nwLhO', '0363240150', 'quzvygp2kwk7robu7uqw', 'http://res.cloudinary.com/dt6ukgxqc/image/upload/v1738343697/quzvygp2kwk7robu7uqw.jpg', 'qweqw1236'),
(6, '2025-02-01 00:17:22.000000', b'1', '2025-02-01 00:17:22.000000', '2000-01-01', '35ad15@gmail.com', '16ad51sd5a3', '$2a$10$m5srwXMookg40c3z8sbAzOwsuxSTMt7UncJWGpOEf2lPS5LpajXNC', '0363210521', 'x4ianlrn52aoqfksb9z1', 'http://res.cloudinary.com/dt6ukgxqc/image/upload/v1738343841/x4ianlrn52aoqfksb9z1.jpg', 'qweqw1235'),
(7, '2025-02-01 00:25:35.000000', b'1', '2025-02-01 00:25:35.000000', '2010-01-01', '3a51d5w@gmail.com', '15as3d15', '$2a$10$DSYKMhECeFnLMEKS248qMe3ziOMNRLxsPZf.TdORFORO0CKVRTl4C', '0363212020', 'v1he1vmfgnebqa9ripx4', 'http://res.cloudinary.com/dt6ukgxqc/image/upload/v1738344334/v1he1vmfgnebqa9ripx4.jpg', 'qweqw1234'),
(8, '2025-02-01 00:47:13.000000', b'1', '2025-02-01 00:47:13.000000', '2001-01-01', 'asdasds@gmail.com', 'asdasds', '$2a$10$.KDSjA8pZJSt7m135OjeB.uPM2sf4I0Ex29BhlQulyYm5qmr8/iAq', '0363215125', 'ymetj0rbixph6byhupzg', 'http://res.cloudinary.com/dt6ukgxqc/image/upload/v1738345632/ymetj0rbixph6byhupzg.jpg', 'qweqw1233');

-- --------------------------------------------------------

--
-- Table structure for table `staff_role`
--

CREATE TABLE `staff_role` (
  `staff_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff_role`
--

INSERT INTO `staff_role` (`staff_id`, `role_id`) VALUES
(1, 1),
(3, 1),
(3, 2),
(3, 3),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(4, 5),
(5, 1),
(5, 2),
(5, 3),
(6, 1),
(6, 2),
(7, 1),
(7, 2),
(8, 1),
(8, 2),
(8, 3);

-- --------------------------------------------------------

--
-- Table structure for table `staff_salary`
--

CREATE TABLE `staff_salary` (
  `id` bigint(20) NOT NULL,
  `end_date` date NOT NULL,
  `salary` bigint(20) NOT NULL,
  `start_date` date NOT NULL,
  `staff_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff_salary`
--

INSERT INTO `staff_salary` (`id`, `end_date`, `salary`, `start_date`, `staff_id`) VALUES
(1, '2055-01-26', 599, '2025-01-26', 1),
(3, '2055-02-01', 5020, '2025-02-01', 3),
(4, '2055-02-01', 1351, '2025-02-01', 4),
(5, '2055-02-01', 3511, '2025-02-01', 5),
(6, '2055-02-01', 23232, '2025-02-01', 6),
(7, '2055-02-01', 3500, '2025-02-01', 7),
(8, '2055-02-01', 5666, '2025-02-01', 8);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `dob` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `lesson_remain` int(11) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `student_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `student_class`
--

CREATE TABLE `student_class` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `class_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `lesson_remain` int(11) NOT NULL,
  `total_paid` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `is_active` bit(1) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`id`, `created_at`, `is_active`, `updated_at`, `name`) VALUES
(1, '2025-01-30 23:03:01.000000', b'1', NULL, 'Minh');

-- --------------------------------------------------------

--
-- Table structure for table `teacher_salary`
--

CREATE TABLE `teacher_salary` (
  `id` bigint(20) NOT NULL,
  `end_date` date NOT NULL,
  `salary` bigint(20) NOT NULL,
  `start_date` date NOT NULL,
  `teacher_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tuition_payment`
--

CREATE TABLE `tuition_payment` (
  `id` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `discount` int(11) NOT NULL,
  `lesson_purchased` int(11) NOT NULL,
  `note` text DEFAULT NULL,
  `total` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKqg069ic794ac86iuvkbeokrds` (`class_name`),
  ADD KEY `FK28f8ba9n0feejnamfay479ae1` (`teacher_id`),
  ADD KEY `FKsquxymc2labsj70id2rsapb02` (`staff_id`);

--
-- Indexes for table `class_history`
--
ALTER TABLE `class_history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpbxr429i6t934qqwmgtntl6wo` (`class_id`),
  ADD KEY `FKo37i0i27oycsp48uof32g51mn` (`teacher_id`);

--
-- Indexes for table `class_history_student`
--
ALTER TABLE `class_history_student`
  ADD PRIMARY KEY (`class_history_id`,`student_id`),
  ADD KEY `FK48f892cyf5ufbttaekycy0gwa` (`student_id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2hf2d44kmlk0aiuh6sx75gn3j` (`class_id`);

--
-- Indexes for table `employee_salaries`
--
ALTER TABLE `employee_salaries`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `expenses_category`
--
ALTER TABLE `expenses_category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKm85w5t8e7vbxaidrara0qqdkf` (`name`);

--
-- Indexes for table `invalid_token`
--
ALTER TABLE `invalid_token`
  ADD PRIMARY KEY (`token_id`);

--
-- Indexes for table `mark`
--
ALTER TABLE `mark`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5cgaq206d1ghutx2xe9vcamrp` (`course_id`),
  ADD KEY `FKcwocngy0rfmqdhqwm3qlrfamx` (`student_id`);

--
-- Indexes for table `other_expenses`
--
ALTER TABLE `other_expenses`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8pfwpmgrp6pnp5p8sw9paxq25` (`expenses_category_id`);

--
-- Indexes for table `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKl3pmqryh8vgle52647itattb9` (`permission_name`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKiubw515ff0ugtm28p8g3myt0h` (`role_name`);

--
-- Indexes for table `role_permission`
--
ALTER TABLE `role_permission`
  ADD PRIMARY KEY (`role_id`,`permission_id`),
  ADD KEY `FKf8yllw1ecvwqy3ehyxawqa1qp` (`permission_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKpvctx4dbua9qh4p4s3gm3scrh` (`email`),
  ADD UNIQUE KEY `UKoqg5g7ejg0vk2ew0thf2asi4` (`phone`),
  ADD UNIQUE KEY `UKn5ib031h2ipdsj507srabt3kf` (`username`);

--
-- Indexes for table `staff_role`
--
ALTER TABLE `staff_role`
  ADD PRIMARY KEY (`staff_id`,`role_id`),
  ADD KEY `FKmu5qbw925ra8earuud0kqc1iq` (`role_id`);

--
-- Indexes for table `staff_salary`
--
ALTER TABLE `staff_salary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj1micg90jyv7wyk6bi7xnvvst` (`staff_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKfe0i52si7ybu0wjedj6motiim` (`email`),
  ADD UNIQUE KEY `UK5s5e6lj1siq6ef1tm7p2g4uul` (`phone`);

--
-- Indexes for table `student_class`
--
ALTER TABLE `student_class`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfyryxclt2okb0bxjfhct0pv5u` (`class_id`),
  ADD KEY `FK2f81ovfviq7rv4jhpdr46dk3e` (`student_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `teacher_salary`
--
ALTER TABLE `teacher_salary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmt5dgj2d22u010js1urdx1unx` (`teacher_id`);

--
-- Indexes for table `tuition_payment`
--
ALTER TABLE `tuition_payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn9ory39w1uckibml583a05sy9` (`student_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `class_history`
--
ALTER TABLE `class_history`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employee_salaries`
--
ALTER TABLE `employee_salaries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `expenses_category`
--
ALTER TABLE `expenses_category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mark`
--
ALTER TABLE `mark`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `other_expenses`
--
ALTER TABLE `other_expenses`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permission`
--
ALTER TABLE `permission`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `staff_salary`
--
ALTER TABLE `staff_salary`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `student_class`
--
ALTER TABLE `student_class`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `teacher_salary`
--
ALTER TABLE `teacher_salary`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tuition_payment`
--
ALTER TABLE `tuition_payment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `FK28f8ba9n0feejnamfay479ae1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  ADD CONSTRAINT `FKsquxymc2labsj70id2rsapb02` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`);

--
-- Constraints for table `class_history`
--
ALTER TABLE `class_history`
  ADD CONSTRAINT `FKo37i0i27oycsp48uof32g51mn` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  ADD CONSTRAINT `FKpbxr429i6t934qqwmgtntl6wo` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`);

--
-- Constraints for table `class_history_student`
--
ALTER TABLE `class_history_student`
  ADD CONSTRAINT `FK48f892cyf5ufbttaekycy0gwa` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `FKllmni49n08oeu13iocayh18be` FOREIGN KEY (`class_history_id`) REFERENCES `class_history` (`id`);

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `FK2hf2d44kmlk0aiuh6sx75gn3j` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`);

--
-- Constraints for table `mark`
--
ALTER TABLE `mark`
  ADD CONSTRAINT `FK5cgaq206d1ghutx2xe9vcamrp` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `FKcwocngy0rfmqdhqwm3qlrfamx` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `other_expenses`
--
ALTER TABLE `other_expenses`
  ADD CONSTRAINT `FK8pfwpmgrp6pnp5p8sw9paxq25` FOREIGN KEY (`expenses_category_id`) REFERENCES `expenses_category` (`id`);

--
-- Constraints for table `role_permission`
--
ALTER TABLE `role_permission`
  ADD CONSTRAINT `FKa6jx8n8xkesmjmv6jqug6bg68` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKf8yllw1ecvwqy3ehyxawqa1qp` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`);

--
-- Constraints for table `staff_role`
--
ALTER TABLE `staff_role`
  ADD CONSTRAINT `FKmu5qbw925ra8earuud0kqc1iq` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKpcx2le3larpufip8hbowjqjfb` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`);

--
-- Constraints for table `staff_salary`
--
ALTER TABLE `staff_salary`
  ADD CONSTRAINT `FKj1micg90jyv7wyk6bi7xnvvst` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`id`);

--
-- Constraints for table `student_class`
--
ALTER TABLE `student_class`
  ADD CONSTRAINT `FK2f81ovfviq7rv4jhpdr46dk3e` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `FKfyryxclt2okb0bxjfhct0pv5u` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`);

--
-- Constraints for table `teacher_salary`
--
ALTER TABLE `teacher_salary`
  ADD CONSTRAINT `FKmt5dgj2d22u010js1urdx1unx` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`);

--
-- Constraints for table `tuition_payment`
--
ALTER TABLE `tuition_payment`
  ADD CONSTRAINT `FKn9ory39w1uckibml583a05sy9` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
