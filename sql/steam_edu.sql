/*
 Navicat Premium Data Transfer

 Source Server         : 本机MySQL
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : steam_edu

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 10/02/2023 17:48:17
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
-- Table structure for course_advantage
-- ----------------------------
DROP TABLE IF EXISTS `course_advantage`;
CREATE TABLE `course_advantage`  (
  `course_advantage_id` int(0) NOT NULL COMMENT '课程优势ID-主键;课程外键',
  `course_id` int(0) NOT NULL COMMENT '课程ID-主键;课程外键',
  PRIMARY KEY (`course_advantage_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for slideshow
-- ----------------------------
DROP TABLE IF EXISTS `slideshow`;
CREATE TABLE `slideshow`  (
  `slideshow_id` int(0) NOT NULL COMMENT '轮播图id-主键',
  `slideshow_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片-存图片路径',
  PRIMARY KEY (`slideshow_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL COMMENT '用户ID-主键',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名-默认值(应该是随机生成)',
  `user_role` tinyint(0) NOT NULL DEFAULT 0 COMMENT '用户角色-0:平台管理员;1:机构管理员;2:老师;3:学生;(默认为0)',
  `user_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像-默认（固定默认头像）',
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
-- Table structure for user_coupon
-- ----------------------------
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon`  (
  `coupon_id` int(0) NOT NULL COMMENT '优惠券ID-外键',
  `user_id` int(0) NOT NULL COMMENT '用户ID-外键',
  PRIMARY KEY (`coupon_id`, `user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
