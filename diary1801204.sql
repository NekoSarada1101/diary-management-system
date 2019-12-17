-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: 2019 年 12 月 16 日 07:53
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

--
-- テーブルのデータのダンプ `class`
--

INSERT INTO `class` (`class_code`, `course_code`, `grade`, `class_name`, `teacher_code`) VALUES
('001', '01', 1, 'A', '001'),
('002', '02', 1, 'B', '001'),
('003', '02', 2, 'A', '002');

-- --------------------------------------------------------

--
-- テーブルの構造 `course`
--

CREATE TABLE `course` (
  `course_code` char(2) NOT NULL,
  `course_name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- テーブルのデータのダンプ `course`
--

INSERT INTO `course` (`course_code`, `course_name`) VALUES
('01', '情報システム専攻科'),
('02', '情報システム科');

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

--
-- テーブルのデータのダンプ `diary`
--

INSERT INTO `diary` (`class_code`, `insert_date`, `student_id`, `good_point`, `bad_point`, `student_comment`, `teacher_comment`) VALUES
('001', '2019-11-11', '0000001', 'aaa', 'bbb', 'ccc', '<br>'),
('001', '2019-12-10', '0000001', '<br>', '<br>', '<br>', NULL);

-- --------------------------------------------------------

--
-- テーブルの構造 `diary_duty`
--

CREATE TABLE `diary_duty` (
  `class_code` char(3) NOT NULL,
  `insert_date` date NOT NULL,
  `student_id` char(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- テーブルのデータのダンプ `diary_duty`
--

INSERT INTO `diary_duty` (`class_code`, `insert_date`, `student_id`) VALUES
('001', '2019-12-03', '0000001'),
('001', '2019-12-06', '0000001'),
('001', '2019-12-09', '0000001'),
('001', '2019-12-10', '0000001'),
('001', '2019-12-12', '0000001');

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

--
-- テーブルのデータのダンプ `student`
--

INSERT INTO `student` (`student_id`, `class_code`, `student_name`, `student_password`) VALUES
('0000001', '001', '江成利平', 'ena'),
('0000002', '001', '下山守彦', 'shimo'),
('0000003', '002', '堀井亨', 'hori'),
('0000004', '002', '柳川莉歩', 'yana'),
('0000005', '003', '竹内裕司', 'take');

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
-- テーブルのデータのダンプ `teacher`
--

INSERT INTO `teacher` (`teacher_code`, `teacher_name`, `teacher_password`) VALUES
('001', '西野直幸', 'nishino'),
('002', '姫野マリ', 'himeno');

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
