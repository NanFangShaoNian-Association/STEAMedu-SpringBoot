/*
 Navicat Premium Data Transfer

 Source Server         : 本机MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : steam

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 07/02/2023 14:26:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for about
-- ----------------------------
DROP TABLE IF EXISTS `about`;
CREATE TABLE `about`  (
  `about_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '关于ID-主键',
  `LOGO_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'LOGO路径',
  `software_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '软件名',
  `version` int(0) NOT NULL COMMENT '版本号',
  `development_team` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '开发团队',
  PRIMARY KEY (`about_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for add_friends
-- ----------------------------
DROP TABLE IF EXISTS `add_friends`;
CREATE TABLE `add_friends`  (
  `id` int(0) NOT NULL COMMENT '好友添加ID',
  `request_user_id` int(0) NOT NULL COMMENT '申请人ID-用户外键',
  `requested_user_id` int(0) NOT NULL COMMENT '对方ID-用户外键',
  `addfriends_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '申请理由',
  `name_remark` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '名称备注',
  `addfriends_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '好友添加状态码-0:非好友状态;1:好友状态;(默认为0)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `applicanted_id`(`requested_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for advantage
-- ----------------------------
DROP TABLE IF EXISTS `advantage`;
CREATE TABLE `advantage`  (
  `id` int(0) NOT NULL COMMENT '具有优势ID-主键',
  `course_advantage_id` int(0) NOT NULL COMMENT '课程优势ID-课程外键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-课程外键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for agreement
-- ----------------------------
DROP TABLE IF EXISTS `agreement`;
CREATE TABLE `agreement`  (
  `agreement_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '协议ID-主键',
  `agreement_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '协议名',
  `agreement_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '协议内容',
  PRIMARY KEY (`agreement_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `chat_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '聊天记录ID-主键',
  `sender_user_id` int(0) NOT NULL COMMENT '发送者ID-用户外键',
  `receiver_user_id` int(0) NOT NULL COMMENT '接受者ID-用户外键',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '消息内容',
  `send_time` datetime(0) NOT NULL COMMENT '发送时间',
  `receive_status` tinyint(0) NOT NULL COMMENT '接受状态',
  PRIMARY KEY (`chat_id`) USING BTREE,
  INDEX `my_id`(`sender_user_id`) USING BTREE,
  INDEX `friend_id`(`receiver_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for choose
-- ----------------------------
DROP TABLE IF EXISTS `choose`;
CREATE TABLE `choose`  (
  `choose_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '选课订单ID-主键',
  `choose_number` int(0) NOT NULL COMMENT '订单编号',
  `user_id` int(0) NOT NULL COMMENT '用户ID-用户外键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-课程外键',
  `pay_time` datetime(0) NOT NULL COMMENT '付款时间',
  `choose_comit_time` datetime(0) NOT NULL COMMENT '提交时间',
  `pay_money` int(0) NOT NULL COMMENT '付款金额',
  `pay_way` tinyint(0) NOT NULL DEFAULT 0 COMMENT '支付方式-0:微信支付;1:支付宝支付;2银行卡支付;...(默认为0)',
  `choose_handle_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '机构处理状态-0:未处理;1:已处理;(默认为0)',
  `pay_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '付款状态-0:未付款;1:已付款;2:已退款;(默认为0)',
  PRIMARY KEY (`choose_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `course_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程封面-存图片路径',
  `course_distributor` int(0) NOT NULL COMMENT '开课者ID-用户外键',
  `course_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名',
  `course_price` int(0) NOT NULL COMMENT '价格',
  `course_type` tinyint(0) NOT NULL DEFAULT 0 COMMENT '课程类型-0:精选推荐;1:普通',
  `course_position` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '上课地点',
  `course_start_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程开始时间',
  `course_duration` int(0) NULL DEFAULT NULL COMMENT '每节课时长',
  `course_enrolment` int(0) NULL DEFAULT NULL COMMENT '报名人数',
  `course_section_number` int(0) NOT NULL COMMENT '课时数',
  `course_limited_purchase_volume` int(0) NOT NULL COMMENT '课程限购数-一门课能报名多少人(比如线下课，可能教室不够)',
  `entering_time` datetime(0) NOT NULL COMMENT '录入时间',
  `course_text_introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程文字简介-只有精选课程才会显示文字简介，其他课程都没有这个',
  `course_delete_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '课程状态码-0:未删除;1:已删除;(默认为0)',
  `course_final_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '结课状态-0:未结课;1:已结课;(默认为0)',
  PRIMARY KEY (`course_id`) USING BTREE,
  INDEX `course_distributor`(`course_distributor`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_advantage
-- ----------------------------
DROP TABLE IF EXISTS `course_advantage`;
CREATE TABLE `course_advantage`  (
  `course_advantage_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '课程优势ID-主键',
  `course_advantage` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '优势',
  `course_advantage_introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '优势简介',
  PRIMARY KEY (`course_advantage_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_detail
-- ----------------------------
DROP TABLE IF EXISTS `course_detail`;
CREATE TABLE `course_detail`  (
  `course_detail_id` int(0) NOT NULL COMMENT '课程详细介绍id',
  `course_id` int(0) NULL DEFAULT NULL,
  `picture` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `display_order` int(0) NULL DEFAULT NULL COMMENT '存图片路径',
  PRIMARY KEY (`course_detail_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_hour
-- ----------------------------
DROP TABLE IF EXISTS `course_hour`;
CREATE TABLE `course_hour`  (
  `course_hour_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '课时ID',
  `course_id` int(0) NOT NULL COMMENT '课程ID-课程外键',
  `course_start_time` datetime(0) NOT NULL COMMENT '上课时间-开始时间',
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名',
  `course_room` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教室号',
  PRIMARY KEY (`course_hour_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_student
-- ----------------------------
DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student`  (
  `course_student_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '课程学生ID-主键',
  `student_message_id` int(0) NOT NULL COMMENT '学生信息ID-学生信息外键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-课程外键',
  PRIMARY KEY (`course_student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for course_teacher
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher`;
CREATE TABLE `course_teacher`  (
  `course_id` int(0) NOT NULL COMMENT '这个关系表由两个外键构成组合主键来唯一标识一条记录',
  `teacher_user_id` int(0) NULL DEFAULT NULL,
  `teacher_role` int(0) NULL DEFAULT NULL COMMENT '0表示授课老师，1表示助教',
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate`  (
  `id` int(0) NOT NULL COMMENT '课程评价id',
  `student_id` int(0) NOT NULL COMMENT '用户ID-用户外键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-用户外键',
  `evaluate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评价',
  `evaluate_time` datetime(0) NOT NULL COMMENT '评价时间',
  `evaluate_level` tinyint(0) NOT NULL DEFAULT 0 COMMENT '评价级别-0:好评;1:一般;2:差评;(默认为0)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for examination_subject
-- ----------------------------
DROP TABLE IF EXISTS `examination_subject`;
CREATE TABLE `examination_subject`  (
  `examination_subject_id` int(0) NOT NULL COMMENT '考试题目ID-主键',
  `subject_id` int(0) NOT NULL COMMENT '题目ID-题目外键',
  `examination_grade` int(0) NOT NULL COMMENT '考试分数',
  `task_subject_id` int(0) NOT NULL COMMENT '考试题目序号',
  `task_id` int(0) NOT NULL COMMENT '考试ID-考试外键',
  PRIMARY KEY (`examination_subject_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `id` int(0) NOT NULL COMMENT '好友表ID',
  `friend_user_id` int(0) NOT NULL COMMENT '朋友ID-用户外键',
  `self_user_id` int(0) NOT NULL COMMENT '自己的ID-用户外键',
  `remark_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注昵称',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `self_id`(`self_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for privacy
-- ----------------------------
DROP TABLE IF EXISTS `privacy`;
CREATE TABLE `privacy`  (
  `privacy_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户身份信息id',
  `user_id` int(0) NOT NULL COMMENT '用户ID-用户外键',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `id_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号码-18位数',
  `sex` tinyint(0) NOT NULL DEFAULT 0 COMMENT '性别-0:男生;1:女生;(默认为0)',
  `birthday` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '生日-默认根据当前时间戳更新',
  `personal_province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
  `personal_profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人简介',
  `personal_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `personal_region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区域',
  PRIMARY KEY (`privacy_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `report_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '举报信息ID-主键',
  `user_report_id` int(0) NOT NULL COMMENT '举报用户ID-用户外键',
  `user_reported_id` int(0) NOT NULL COMMENT '被举报用户ID-用户外键',
  `report_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '举报理由',
  `report_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '举报状态码-0:举报失败;1:举报成功;(默认为0)',
  PRIMARY KEY (`report_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_message
-- ----------------------------
DROP TABLE IF EXISTS `student_message`;
CREATE TABLE `student_message`  (
  `student_message_id` int(0) NOT NULL COMMENT '学生信息ID-主键',
  `user_id` int(0) NOT NULL COMMENT '用户ID-用户外键',
  `student_message_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生名',
  `student_sex` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `school` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学校',
  `phone_number` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系方式',
  `student_photo` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生照片路径-真人图片',
  `grade` int(0) NOT NULL COMMENT '年级',
  `student_message_status` tinyint(0) NOT NULL COMMENT '学生信息状态码',
  PRIMARY KEY (`student_message_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名-默认值（随机生成）',
  `user_role` tinyint(0) NOT NULL DEFAULT 0 COMMENT '用户角色-0:平台管理员;1:机构管理员;2:老师;3:学生;(默认为0)',
  `user_avatar` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像路径-默认（固定默认头像）',
  `email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone_number` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `wx_openID` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'wx_openID',
  `user_location_region` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区域',
  `user_location_city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `user_location_province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
  `qq_openID` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'qq_openID',
  `login_last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次上线时间',
  `off_line_last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次下线时间',
  `login_last_time_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '最后一次登录ip',
  `user_regist_time` datetime(0) NOT NULL COMMENT '账号注册时间',
  `user_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '用户状态码-0:未注销;1:已注销;2:暂时被冻结;(默认为0)',
  `wx_unionid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信unionid',
  `QQ_unionid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'QQunionid',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
