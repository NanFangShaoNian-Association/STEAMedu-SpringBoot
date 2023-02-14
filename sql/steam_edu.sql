/*
 Navicat Premium Data Transfer

 Source Server         : AtnibamAitay
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : steam_edu

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 14/02/2023 15:57:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for add_friends
-- ----------------------------
DROP TABLE IF EXISTS `add_friends`;
CREATE TABLE `add_friends`  (
  `request_user_id` int(0) NOT NULL COMMENT '请求增添的用户ID-主键;用户外键',
  `requested_user_id` int(0) NOT NULL COMMENT '被请求的用户ID-主键;用户外键',
  `addfriends_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请理由',
  `remark_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称备注',
  `addfriends_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '添加状态码-0:未处理;1:已增添为好友;(默认为0)',
  PRIMARY KEY (`request_user_id`, `requested_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of add_friends
-- ----------------------------
INSERT INTO `add_friends` VALUES (1, 2, '因为你的专业很棒，想和你交个朋友', '程序员', 0);
INSERT INTO `add_friends` VALUES (2, 3, '我喜欢你的音乐，想和你交朋友', '音乐家', 0);
INSERT INTO `add_friends` VALUES (3, 1, '我们在同一个城市，想结交一下朋友', '同城人', 0);
INSERT INTO `add_friends` VALUES (4, 5, '你的旅游照片很漂亮，想和你交个朋友', '旅行爱好者', 0);
INSERT INTO `add_friends` VALUES (5, 4, '你的生活很有趣，想和你交个朋友', '生活达人', 0);

-- ----------------------------
-- Table structure for advantage
-- ----------------------------
DROP TABLE IF EXISTS `advantage`;
CREATE TABLE `advantage`  (
  `course_advantage_id` int(0) NOT NULL COMMENT '课程优势ID-主键',
  `course_advantage` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_advantage_introduction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`course_advantage_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of advantage
-- ----------------------------
INSERT INTO `advantage` VALUES (1, '专业知识', '课程包含了业内最前沿的专业知识');
INSERT INTO `advantage` VALUES (2, '实际操作', '课程通过大量实际操作来提高学生实际操作能力');
INSERT INTO `advantage` VALUES (3, '行业认证', '课程内容符合行业认证标准，有助于学生通过行业认证考试');
INSERT INTO `advantage` VALUES (4, '实战案例', '课程通过实际案例来提高学生的实际应用能力');
INSERT INTO `advantage` VALUES (5, '行业专家', '课程由行业专家授课，保证教学质量');
INSERT INTO `advantage` VALUES (6, '技能提升', '课程可以帮助学生提高实际技能，提高就业竞争力');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cart_id` int(0) NOT NULL COMMENT '购物车id-主键',
  `user_id` int(0) NOT NULL COMMENT '用户id-用户外键',
  `course_id` int(0) NOT NULL COMMENT '课程id-课程外键',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1, 1001, 5001);
INSERT INTO `cart` VALUES (2, 1001, 5002);
INSERT INTO `cart` VALUES (3, 1002, 5001);
INSERT INTO `cart` VALUES (4, 1002, 5003);
INSERT INTO `cart` VALUES (5, 1003, 5002);

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `chat_id` int(0) NOT NULL COMMENT '聊天记录ID-主键',
  `sender_user_id` int(0) NOT NULL COMMENT '发送者ID-外键',
  `receiver_user_id` int(0) NOT NULL COMMENT '接受者ID-外键',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
  `send_time` datetime(0) NOT NULL COMMENT '发送时间',
  `receive_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '接受状态-0为未接收，1为已接收',
  PRIMARY KEY (`chat_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (1, 1001, 1002, '你好，我们可以聊天吗？', '2023-02-13 10:00:00', 0);
INSERT INTO `chat` VALUES (2, 1002, 1001, '当然可以！', '2023-02-13 10:05:00', 1);
INSERT INTO `chat` VALUES (3, 1001, 1002, '你喜欢什么类型的音乐？', '2023-02-13 10:10:00', 0);
INSERT INTO `chat` VALUES (4, 1002, 1001, '我喜欢爵士和蓝调。你呢？', '2023-02-13 10:15:00', 1);
INSERT INTO `chat` VALUES (5, 1001, 1002, '我喜欢流行音乐和摇滚。', '2023-02-13 10:20:00', 0);

-- ----------------------------
-- Table structure for choose
-- ----------------------------
DROP TABLE IF EXISTS `choose`;
CREATE TABLE `choose`  (
  `choose_id` int(0) NOT NULL COMMENT '订单ID-主键',
  `choose_number` int(0) NOT NULL COMMENT '订单编号',
  `user_id` int(0) NOT NULL COMMENT '用户ID-用户外键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-课程外键',
  `pay_time` datetime(0) NOT NULL COMMENT '付款时间',
  `choose_commit_time` datetime(0) NOT NULL COMMENT '提交时间',
  `coupon_id` int(0) NOT NULL COMMENT '优惠券id',
  `pay_money` int(0) NOT NULL COMMENT '实际付款金额',
  `pay_way` tinyint(0) NOT NULL DEFAULT 0 COMMENT '支付方式-0:微信支付;1:支付宝支付;2银行卡支付;...(默认为0)',
  `choose_handle_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '机构处理状态-0:未处理;1:已处理;(默认为0)',
  `pay_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '付款状态-0:未付款;1:已付款;2:已退款;(默认为0)',
  PRIMARY KEY (`choose_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of choose
-- ----------------------------
INSERT INTO `choose` VALUES (1, 1001, 1, 101, '2022-12-01 10:00:00', '2022-12-01 09:00:00', 1, 100, 0, 1, 1);
INSERT INTO `choose` VALUES (2, 1002, 2, 102, '2022-12-02 11:00:00', '2022-12-02 10:00:00', 2, 200, 1, 0, 1);
INSERT INTO `choose` VALUES (3, 1003, 3, 103, '2022-12-03 12:00:00', '2022-12-03 11:00:00', 3, 300, 2, 0, 0);
INSERT INTO `choose` VALUES (4, 1004, 4, 104, '2022-12-04 13:00:00', '2022-12-04 12:00:00', 4, 400, 0, 0, 2);

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `coupon_id` int(0) NOT NULL COMMENT '优惠券ID-主键',
  `coupon_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '优惠券兑换码',
  `value` int(0) NOT NULL COMMENT '优惠金额',
  `start_date` datetime(0) NULL DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime(0) NOT NULL COMMENT '失效日期',
  `min_order_amount` int(0) NOT NULL COMMENT '最低订单金额',
  `designated_course_id` int(0) NOT NULL COMMENT '指定优惠课程ID',
  `issuer_user_id` int(0) NOT NULL COMMENT '发卷人-外键',
  `status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '优惠券状态-0为存在，1为不存在',
  PRIMARY KEY (`coupon_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coupon
-- ----------------------------
INSERT INTO `coupon` VALUES (1, 'COUPON1', 50, '2022-12-01 00:00:00', '2023-01-01 00:00:00', 100, 1, 1, 0);
INSERT INTO `coupon` VALUES (2, 'COUPON2', 30, '2022-11-01 00:00:00', '2023-01-15 00:00:00', 200, 2, 2, 0);
INSERT INTO `coupon` VALUES (3, 'COUPON3', 20, '2022-10-01 00:00:00', '2023-01-31 00:00:00', 150, 3, 3, 0);
INSERT INTO `coupon` VALUES (4, 'COUPON4', 40, '2022-12-15 00:00:00', '2023-01-30 00:00:00', 180, 4, 4, 0);
INSERT INTO `coupon` VALUES (5, 'COUPON5', 10, '2022-12-01 00:00:00', '2023-01-15 00:00:00', 200, 5, 5, 0);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `course_id` int(0) NOT NULL COMMENT '课程ID-主键',
  `course_cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程封面-存图片路径',
  `course_distributor` int(0) NOT NULL COMMENT '开课机构ID-外键',
  `course_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名',
  `course_price` int(0) NOT NULL COMMENT '价格',
  `course_type` tinyint(0) NOT NULL COMMENT '课程类型-0:精选推荐;1:普通',
  `course_position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上课地点',
  `longitude` double NULL DEFAULT NULL COMMENT '经度',
  `latitude` double NULL DEFAULT NULL COMMENT '纬度',
  `course_start_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程开始时间',
  `course_duration` int(0) NOT NULL COMMENT '每节课时长',
  `course_enrolment` int(0) NOT NULL COMMENT '报名人数',
  `course_section_number` int(0) NOT NULL COMMENT '课时数',
  `course_limited_purchase_volume` int(0) NOT NULL COMMENT '课程限报人数-一门课能报名多少人(比如线下课，可能教室不够)',
  `entering_time` datetime(0) NOT NULL COMMENT '课程信息录入时间',
  `course_text_introduction` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程文字简介-只有精选课程才会显示文字简介，其他课程都没有这个',
  `course_delete_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '课程状态码-0:未删除;1:已删除;(默认为0)',
  `course_final_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '结课状态-0:未结课;1:已结课;(默认为0)',
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'course_cover_1.jpg', 1, '课程1', 100, 0, '北京市海淀区', 116.397128, 39.916527, '2023-03-01 10:00:00', 120, 50, 10, 100, '2022-12-01 12:00:00', '这是一门很好的课程', 0, 0);
INSERT INTO `course` VALUES (2, 'course_cover_2.jpg', 1, '课程2', 150, 0, '上海市黄浦区', 121.487899, 31.219203, '2023-03-05 10:00:00', 120, 60, 10, 50, '2022-12-02 12:00:00', '这是一门很热门的课程', 0, 0);
INSERT INTO `course` VALUES (3, 'course_cover_3.jpg', 1, '课程3', 200, 1, '广州市越秀区', 113.264435, 23.129112, '2023-03-10 10:00:00', 120, 70, 10, 50, '2022-12-03 12:00:00', '', 0, 0);
INSERT INTO `course` VALUES (4, 'course_cover_4.jpg', 1, '课程4', 250, 1, '深圳市南山区', 113.92943, 22.495101, '2023-03-15 10:00:00', 120, 80, 10, 50, '2022-12-04 12:00:00', '', 0, 0);
INSERT INTO `course` VALUES (5, 'course_cover_5.jpg', 1, '课程5', 300, 0, '天津市河西区', 117.223372, 39.109563, '2023-03-20 10:00:00', 120, 90, 10, 50, '2022-12-05 12:00:00', '这是一门很难的课程', 0, 0);

-- ----------------------------
-- Table structure for course_advantage
-- ----------------------------
DROP TABLE IF EXISTS `course_advantage`;
CREATE TABLE `course_advantage`  (
  `course_advantage_id` int(0) NOT NULL COMMENT '课程优势ID-主键;课程外键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-主键;课程外键',
  PRIMARY KEY (`course_advantage_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_advantage
-- ----------------------------
INSERT INTO `course_advantage` VALUES (1, 1);
INSERT INTO `course_advantage` VALUES (2, 1);
INSERT INTO `course_advantage` VALUES (3, 3);

-- ----------------------------
-- Table structure for course_detail
-- ----------------------------
DROP TABLE IF EXISTS `course_detail`;
CREATE TABLE `course_detail`  (
  `course_detail_id` int(0) NOT NULL COMMENT '课程详细介绍ID-主键',
  `course_id` int(0) NOT NULL COMMENT '课程ID',
  `picture` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片-存图片路径',
  `display_order` int(0) NOT NULL COMMENT '显示序号',
  PRIMARY KEY (`course_detail_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_detail
-- ----------------------------
INSERT INTO `course_detail` VALUES (1, 1, 'image_path_1', 1);
INSERT INTO `course_detail` VALUES (2, 1, 'image_path_2', 2);
INSERT INTO `course_detail` VALUES (3, 1, 'image_path_3', 3);
INSERT INTO `course_detail` VALUES (4, 1, 'image_path_4', 4);
INSERT INTO `course_detail` VALUES (5, 1, 'image_path_5', 5);

-- ----------------------------
-- Table structure for course_hour
-- ----------------------------
DROP TABLE IF EXISTS `course_hour`;
CREATE TABLE `course_hour`  (
  `course_hour_id` int(0) NOT NULL COMMENT '课时ID-主键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-课程外键',
  `course_start_time` datetime(0) NOT NULL COMMENT '上课时间-开始时间',
  `course_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课时名-其实也是课时的内容',
  `course_room` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上课地点-考虑机构可能存在多个校区，学生要求调换校区上课的情况，默认是课程的上课地点',
  PRIMARY KEY (`course_hour_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_hour
-- ----------------------------
INSERT INTO `course_hour` VALUES (1, 1001, '2023-02-14 09:00:00', '第一课时-数学基础', '校区1-教室1');
INSERT INTO `course_hour` VALUES (2, 1001, '2023-02-16 09:00:00', '第二课时-数学进阶', '校区1-教室2');
INSERT INTO `course_hour` VALUES (3, 1002, '2023-02-17 14:00:00', '第一课时-物理基础', '校区2-教室1');
INSERT INTO `course_hour` VALUES (4, 1003, '2023-02-20 09:00:00', '第一课时-生物基础', '校区1-教室3');

-- ----------------------------
-- Table structure for course_rating
-- ----------------------------
DROP TABLE IF EXISTS `course_rating`;
CREATE TABLE `course_rating`  (
  `course_rating_id` int(0) NOT NULL COMMENT '评价id-主键',
  `user_id` int(0) NOT NULL COMMENT '用户id',
  `course_id` int(0) NOT NULL COMMENT '课程id',
  `rating` int(0) NOT NULL COMMENT '打分-0代表好评，1代表一般，2代表差评',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价内容',
  `comment_time` datetime(0) NOT NULL,
  PRIMARY KEY (`course_rating_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_rating
-- ----------------------------
INSERT INTO `course_rating` VALUES (1, 1, 1, 0, '课程很好，内容丰富，讲得非常清晰！', '2022-12-01 12:00:00');
INSERT INTO `course_rating` VALUES (2, 2, 1, 1, '课程还行，内容简单易懂，但有些地方不够详细。', '2022-11-22 10:30:00');
INSERT INTO `course_rating` VALUES (3, 3, 1, 2, '课程不好，内容太简单了，浪费了我的时间。', '2022-11-12 09:00:00');

-- ----------------------------
-- Table structure for course_student
-- ----------------------------
DROP TABLE IF EXISTS `course_student`;
CREATE TABLE `course_student`  (
  `course_student_id` int(0) NOT NULL COMMENT '课程学生ID-主键',
  `student_message_id` int(0) NOT NULL COMMENT '学生信息ID-学生信息外键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-课程外键',
  PRIMARY KEY (`course_student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_student
-- ----------------------------
INSERT INTO `course_student` VALUES (1, 1, 1);
INSERT INTO `course_student` VALUES (2, 2, 2);
INSERT INTO `course_student` VALUES (3, 3, 3);

-- ----------------------------
-- Table structure for course_teacher_rel
-- ----------------------------
DROP TABLE IF EXISTS `course_teacher_rel`;
CREATE TABLE `course_teacher_rel`  (
  `course_id` int(0) NOT NULL COMMENT '课程ID-主键',
  `teacher_user_id` int(0) NOT NULL COMMENT '老师ID-主键',
  `teacher_role` int(0) NOT NULL COMMENT '老师在这门课中的角色-0表示授课老师，1表示助教',
  PRIMARY KEY (`course_id`, `teacher_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_teacher_rel
-- ----------------------------
INSERT INTO `course_teacher_rel` VALUES (1, 1, 0);
INSERT INTO `course_teacher_rel` VALUES (1, 2, 1);
INSERT INTO `course_teacher_rel` VALUES (2, 3, 0);
INSERT INTO `course_teacher_rel` VALUES (3, 4, 0);
INSERT INTO `course_teacher_rel` VALUES (3, 5, 1);

-- ----------------------------
-- Table structure for examination
-- ----------------------------
DROP TABLE IF EXISTS `examination`;
CREATE TABLE `examination`  (
  `examination_id` int(0) NOT NULL COMMENT '考试ID-主键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-课程外键',
  `examination_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '考试名',
  `teacher_id` int(0) NOT NULL COMMENT '命题人ID-用户外键',
  `examination_start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `examin_finall_time` datetime(0) NOT NULL COMMENT '截止时间',
  PRIMARY KEY (`examination_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examination
-- ----------------------------
INSERT INTO `examination` VALUES (1, 1, '数学期末考试', 1, '2022-12-01 10:00:00', '2022-12-02 16:00:00');
INSERT INTO `examination` VALUES (2, 2, '语文期末考试', 2, '2022-12-01 10:00:00', '2022-12-02 16:00:00');
INSERT INTO `examination` VALUES (3, 3, '英语期末考试', 3, '2022-12-01 10:00:00', '2022-12-02 16:00:00');

-- ----------------------------
-- Table structure for examination_subject
-- ----------------------------
DROP TABLE IF EXISTS `examination_subject`;
CREATE TABLE `examination_subject`  (
  `examination_subject_id` int(0) NOT NULL COMMENT '考试题目ID-主键;考试外键',
  `subject_id` int(0) NOT NULL COMMENT '题目ID-主键;题目外键',
  `examination_grade` int(0) NOT NULL COMMENT '考试分数',
  `examination_subject_number` int(0) NOT NULL COMMENT '考试题目序号',
  PRIMARY KEY (`examination_subject_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of examination_subject
-- ----------------------------
INSERT INTO `examination_subject` VALUES (1, 1001, 10, 1);
INSERT INTO `examination_subject` VALUES (2, 1002, 20, 2);
INSERT INTO `examination_subject` VALUES (3, 1003, 30, 3);
INSERT INTO `examination_subject` VALUES (4, 1004, 40, 4);
INSERT INTO `examination_subject` VALUES (5, 1005, 50, 5);

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends`  (
  `friend_user_id` int(0) NOT NULL COMMENT '朋友ID-主键;用户外键',
  `self_user_id` int(0) NOT NULL COMMENT '自己的ID-主键;用户外键',
  `remark_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注昵称-用户名',
  PRIMARY KEY (`friend_user_id`, `self_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES (1, 2, '小明');
INSERT INTO `friends` VALUES (3, 2, '小红');
INSERT INTO `friends` VALUES (4, 2, '小刚');

-- ----------------------------
-- Table structure for leave
-- ----------------------------
DROP TABLE IF EXISTS `leave`;
CREATE TABLE `leave`  (
  `student_id` int(0) NOT NULL COMMENT '学生信息ID-主键;用户外键',
  `course_id` int(0) NOT NULL COMMENT '课时ID-主键;课程外键',
  `leave_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请假理由',
  `leave_approval_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '批准状态-0:未批准;1:已批准;(默认为未批准)',
  PRIMARY KEY (`student_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leave
-- ----------------------------
INSERT INTO `leave` VALUES (1, 100, '生病请假', 0);
INSERT INTO `leave` VALUES (2, 101, '家庭紧急事件请假', 1);
INSERT INTO `leave` VALUES (3, 102, '其他私人原因请假', 0);
INSERT INTO `leave` VALUES (4, 103, '旅游请假', 1);

-- ----------------------------
-- Table structure for option
-- ----------------------------
DROP TABLE IF EXISTS `option`;
CREATE TABLE `option`  (
  `option_id` int(0) NOT NULL COMMENT '选项ID-主键',
  `subject_id` int(0) NOT NULL COMMENT '题目ID-题目外键',
  `option_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项值',
  `option` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项序号',
  PRIMARY KEY (`option_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of option
-- ----------------------------
INSERT INTO `option` VALUES (1, 1, 'A car', 'A');
INSERT INTO `option` VALUES (2, 1, 'A plane', 'B');
INSERT INTO `option` VALUES (3, 1, 'A bike', 'C');
INSERT INTO `option` VALUES (4, 2, 'New York', 'A');
INSERT INTO `option` VALUES (5, 2, 'London', 'B');
INSERT INTO `option` VALUES (6, 2, 'Paris', 'C');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `report_id` int(0) NOT NULL COMMENT '举报信息ID-主键',
  `user_report_id` int(0) NOT NULL COMMENT '举报用户ID-用户外键',
  `user_reported_id` int(0) NOT NULL COMMENT '被举报用户ID-用户外键',
  `report_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '举报理由',
  `report_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '举报状态码-0:举报失败;1:举报成功;(默认为0)',
  PRIMARY KEY (`report_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES (1, 1, 2, '该用户发布了不当内容', 1);
INSERT INTO `report` VALUES (2, 3, 4, '该用户辱骂他人', 0);
INSERT INTO `report` VALUES (3, 5, 6, '该用户侵犯了他人隐私', 1);
INSERT INTO `report` VALUES (4, 7, 8, '该用户发布了违法信息', 0);
INSERT INTO `report` VALUES (5, 9, 10, '该用户恶意评论', 1);

-- ----------------------------
-- Table structure for sign
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign`  (
  `sign_id` int(0) NOT NULL COMMENT '签到信息ID-主键',
  `sign_sender` int(0) NOT NULL COMMENT '签到发起人ID-用户外键',
  `course_id` int(0) NOT NULL COMMENT '课时ID-课程外键',
  `sign_finall_time` datetime(0) NOT NULL COMMENT '截止时间',
  `sign_infomation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '签到信息-定位签到存储精准定位±范围，二维码签到存储对应校验信息，手势签到和验证码签到存储对应字符即可',
  PRIMARY KEY (`sign_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sign
-- ----------------------------
INSERT INTO `sign` VALUES (1, 1001, 101, '2022-01-01 12:00:00', 'Location: +-0.5km, QR Code: 123456789');
INSERT INTO `sign` VALUES (2, 1002, 102, '2022-01-02 12:00:00', 'Location: +-0.5km, QR Code: 987654321');
INSERT INTO `sign` VALUES (3, 1003, 103, '2022-01-03 12:00:00', 'Location: +-0.5km, QR Code: 111111111');
INSERT INTO `sign` VALUES (4, 1004, 104, '2022-01-04 12:00:00', 'Location: +-0.5km, QR Code: 222222222');
INSERT INTO `sign` VALUES (5, 1005, 105, '2022-01-05 12:00:00', 'Location: +-0.5km, QR Code: 333333333');

-- ----------------------------
-- Table structure for sign_record
-- ----------------------------
DROP TABLE IF EXISTS `sign_record`;
CREATE TABLE `sign_record`  (
  `sign_record_id` int(0) NOT NULL COMMENT '签到记录ID-主键',
  `sign_id` int(0) NOT NULL COMMENT '签到信息ID-签到信息外键',
  `student_id` int(0) NOT NULL COMMENT '学生信息ID-用户外键',
  `sign_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '签到状态-0:未签到;1：已签到（默认未签到）',
  PRIMARY KEY (`sign_record_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sign_record
-- ----------------------------
INSERT INTO `sign_record` VALUES (1, 1, 0, 0);
INSERT INTO `sign_record` VALUES (2, 2, 1, 1);
INSERT INTO `sign_record` VALUES (3, 3, 0, 0);
INSERT INTO `sign_record` VALUES (4, 4, 1, 1);
INSERT INTO `sign_record` VALUES (5, 5, 0, 0);

-- ----------------------------
-- Table structure for slideshow
-- ----------------------------
DROP TABLE IF EXISTS `slideshow`;
CREATE TABLE `slideshow`  (
  `slideshow_id` int(0) NOT NULL COMMENT '轮播图id-主键',
  `slideshow_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片-存图片路径',
  PRIMARY KEY (`slideshow_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of slideshow
-- ----------------------------
INSERT INTO `slideshow` VALUES (1, '/images/slideshow1.jpg');
INSERT INTO `slideshow` VALUES (2, '/images/slideshow2.jpg');
INSERT INTO `slideshow` VALUES (3, '/images/slideshow3.jpg');
INSERT INTO `slideshow` VALUES (4, '/images/slideshow4.jpg');
INSERT INTO `slideshow` VALUES (5, '/images/slideshow5.jpg');

-- ----------------------------
-- Table structure for student_message
-- ----------------------------
DROP TABLE IF EXISTS `student_message`;
CREATE TABLE `student_message`  (
  `student_message_id` int(0) NOT NULL COMMENT '学生信息ID-主键',
  `user_id` int(0) NOT NULL COMMENT '用户ID-用户外键',
  `student_message_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生名',
  `student_sex` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `school` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学校',
  `birthday` datetime(0) NOT NULL COMMENT '生日',
  `phone_number` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系方式',
  `student_photo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生照片-真人图片',
  `grade` int(0) NOT NULL COMMENT '年级',
  `student_message_status` tinyint(0) NOT NULL COMMENT '学生信息状态码',
  PRIMARY KEY (`student_message_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_message
-- ----------------------------
INSERT INTO `student_message` VALUES (1, 1, 'Tom', 'Male', 'Beijing No.1 HS', '2000-01-01 00:00:00', '12345678910', 'Tom_1.jpg', 3, 0);
INSERT INTO `student_message` VALUES (2, 2, 'Jerry', 'Male', 'Shanghai No.2 HS', '2001-02-01 00:00:00', '23456789101', 'Jerry_1.jpg', 4, 0);

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`  (
  `subject_id` int(0) NOT NULL COMMENT '题目ID-主键',
  `subject_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目',
  `answer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '答案',
  `subject_type` tinyint(0) NOT NULL DEFAULT 0 COMMENT '所属题集-0:未分类;1:Java;2:Python;3:C语言;...(默认为0)',
  `subject_analysis` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '题目解析',
  PRIMARY KEY (`subject_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------

-- ----------------------------
-- Table structure for submit_examination
-- ----------------------------
DROP TABLE IF EXISTS `submit_examination`;
CREATE TABLE `submit_examination`  (
  `submit_examination_id` int(0) NOT NULL COMMENT '考试ID-主键;考试外键',
  `student_id` int(0) NOT NULL COMMENT '学生信息ID-用户外键',
  `examination_student_answer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生答案-需要 分割符',
  `examination_submit_status` tinyint(0) NOT NULL COMMENT '试卷提交状态',
  PRIMARY KEY (`submit_examination_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submit_examination
-- ----------------------------

-- ----------------------------
-- Table structure for submit_task
-- ----------------------------
DROP TABLE IF EXISTS `submit_task`;
CREATE TABLE `submit_task`  (
  `submit_task_id` int(0) NOT NULL COMMENT '作业ID-主键;考试外键',
  `student_id` int(0) NOT NULL COMMENT '学生信息ID-用户外键',
  `task_answer` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生作业答案',
  `task_student_grade` int(0) NOT NULL COMMENT '学生分数-需要 分割符',
  `submit_task_status` tinyint(0) NOT NULL COMMENT '作业提交状态',
  PRIMARY KEY (`submit_task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submit_task
-- ----------------------------
INSERT INTO `submit_task` VALUES (1, 1, 'Answer 1', 90, 1);
INSERT INTO `submit_task` VALUES (2, 2, 'Answer 2', 80, 1);
INSERT INTO `submit_task` VALUES (3, 3, 'Answer 3', 70, 1);
INSERT INTO `submit_task` VALUES (4, 4, 'Answer 4', 60, 1);
INSERT INTO `submit_task` VALUES (5, 5, 'Answer 5', 50, 1);

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `task_id` int(0) NOT NULL COMMENT '作业ID-主键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-课程外键',
  `task_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业名',
  `teacher_id` int(0) NOT NULL COMMENT '命题人ID-用户外键',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `finall_time` datetime(0) NOT NULL COMMENT '截止时间',
  PRIMARY KEY (`task_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------

-- ----------------------------
-- Table structure for task_subject
-- ----------------------------
DROP TABLE IF EXISTS `task_subject`;
CREATE TABLE `task_subject`  (
  `task_subject_id` int(0) NOT NULL COMMENT '作业题目ID-主键;作业外键',
  `subject_id` int(0) NOT NULL COMMENT '题目ID-主键;题目外键',
  `subject_grade` int(0) NOT NULL COMMENT '题目分数',
  `task_subject_number` int(0) NOT NULL COMMENT '作业题目序号',
  PRIMARY KEY (`task_subject_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task_subject
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL COMMENT '用户ID-主键',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名-默认值(应该是随机生成)',
  `user_role` tinyint(0) NOT NULL DEFAULT 0 COMMENT '用户角色-0:平台管理员;1:机构管理员;2:老师;3:学生;(默认为0)',
  `user_avatar` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像-默认（固定默认头像）',
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `phone_number` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号-唯一',
  `user_introduction` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户简介',
  `user_location_province` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省份',
  `user_location_city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `user_location_region` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域',
  `longitude` double NULL DEFAULT NULL COMMENT '经度',
  `latitude` double NULL DEFAULT NULL COMMENT '纬度',
  `wx_openID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'wx_openID',
  `qq_openID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'qq_openID',
  `login_last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次上线时间',
  `off_line_last_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次下线时间',
  `login_last_time_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后一次登录ip',
  `wx_unionid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信unionid',
  `QQ_unionid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQunionid',
  `user_regist_time` datetime(0) NOT NULL COMMENT '账号注册时间-非空',
  `user_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '用户状态码-0:未注销;1:已注销;2:暂时被冻结;(默认为0)',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `phone_number`(`phone_number`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '用户1', 3, 'user_avatar_1.jpg', 'user1@example.com', '13325315242', '这是一个学生', '北京市', '海淀区', '中关村', 116.397128, 39.916527, 'wx_openID_1', 'qq_openID_1', '2022-05-10 10:00:00', '2022-05-11 10:00:00', '192.168.0.1', 'wx_unionid_1', 'QQ_unionid_1', '2022-05-10 10:00:00', 0);
INSERT INTO `user` VALUES (2, '用户2', 2, 'user_avatar_2.jpg', 'user2@example.com', '13325315243', '这是一个老师', '北京市', '朝阳区', '望京', 116.481488, 39.990464, 'wx_openID_2', 'qq_openID_2', '2022-05-11 10:00:00', '2022-05-12 10:00:00', '192.168.0.2', 'wx_unionid_2', 'QQ_unionid_2', '2022-05-11 10:00:00', 0);
INSERT INTO `user` VALUES (3, '用户3', 1, 'user_avatar_3.jpg', 'user3@example.com', '13325315244', '这是一个机构管理员', '上海市', '浦东新区', '世纪公园', 121.501606, 31.230416, 'wx_openID_3', 'qq_openID_3', '2022-05-12 10:00:00', '2022-05-13 10:00:00', '192.168.0.3', 'wx_unionid_3', 'QQ_unionid_3', '2022-05-12 10:00:00', 0);

-- ----------------------------
-- Table structure for user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon`  (
  `coupon_id` int(0) NOT NULL COMMENT '优惠券ID-外键',
  `user_id` int(0) NOT NULL COMMENT '用户ID-外键',
  PRIMARY KEY (`coupon_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_coupon
-- ----------------------------
INSERT INTO `user_coupon` VALUES (1, 1001);
INSERT INTO `user_coupon` VALUES (2, 1001);
INSERT INTO `user_coupon` VALUES (3, 1002);
INSERT INTO `user_coupon` VALUES (4, 1003);

SET FOREIGN_KEY_CHECKS = 1;
