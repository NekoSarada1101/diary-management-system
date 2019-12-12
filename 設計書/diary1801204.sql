-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 2019 年 12 月 11 日 00:20
-- サーバのバージョン： 5.7.25
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `diary1801204`
--

-- --------------------------------------------------------

--
-- テーブルの構造 `class`
--

CREATE TABLE `class` (
  `class_code` char(3) NOT NULL,
  `course_code` char(2) NOT NULL,
  `grade` int(11) NOT NULL,
  `class_name` varchar(2) DEFAULT NULL,
  `teacher_code` char(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- テーブルの構造 `course`
--

CREATE TABLE `course` (
  `course_code` char(2) NOT NULL,
  `course_name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- テーブルの構造 `diary`
--

CREATE TABLE `diary` (
  `class_code` char(3) NOT NULL,
  `insert_date` date NOT NULL,
  `student_id` char(7) NOT NULL,
  `good_point` varchar(30) DEFAULT NULL,
  `bad_point` varchar(30) DEFAULT NULL,
  `student_comment` varchar(30) DEFAULT NULL,
  `teacher_comment` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- テーブルの構造 `diary_duty`
--

CREATE TABLE `diary_duty` (
  `class_code` char(3) NOT NULL,
  `insert_date` date NOT NULL,
  `student_id` char(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- テーブルの構造 `student`
--

CREATE TABLE `student` (
  `student_id` char(7) NOT NULL,
  `class_code` char(3) NOT NULL,
  `student_name` varchar(128) NOT NULL,
  `student_password` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- テーブルの構造 `teacher`
--

CREATE TABLE `teacher` (
  `teacher_code` char(3) NOT NULL,
  `teacher_name` varchar(128) NOT NULL,
  `teacher_password` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`class_code`),
  ADD KEY `class_FK` (`course_code`) USING BTREE,
  ADD KEY `class_FK_1` (`teacher_code`) USING BTREE;

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_code`);

--
-- Indexes for table `diary`
--
ALTER TABLE `diary`
  ADD PRIMARY KEY (`class_code`,`insert_date`),
  ADD KEY `jounal_FK_1` (`student_id`) USING BTREE;

--
-- Indexes for table `diary_duty`
--
ALTER TABLE `diary_duty`
  ADD PRIMARY KEY (`class_code`,`insert_date`),
  ADD KEY `diary_duty_FK_1` (`student_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`),
  ADD KEY `student_FK` (`class_code`) USING BTREE;

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_code`);

--
-- ダンプしたテーブルの制約
--

--
-- テーブルの制約 `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_FK` FOREIGN KEY (`course_code`) REFERENCES `course` (`course_code`),
  ADD CONSTRAINT `class_FK_1` FOREIGN KEY (`teacher_code`) REFERENCES `teacher` (`teacher_code`);

--
-- テーブルの制約 `diary`
--
ALTER TABLE `diary`
  ADD CONSTRAINT `diary_FK` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  ADD CONSTRAINT `diary_FK_1` FOREIGN KEY (`class_code`) REFERENCES `class` (`class_code`);

--
-- テーブルの制約 `diary_duty`
--
ALTER TABLE `diary_duty`
  ADD CONSTRAINT `diary_duty_FK` FOREIGN KEY (`class_code`) REFERENCES `student` (`class_code`),
  ADD CONSTRAINT `diary_duty_FK_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`);

--
-- テーブルの制約 `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_FK` FOREIGN KEY (`class_code`) REFERENCES `class` (`class_code`);
