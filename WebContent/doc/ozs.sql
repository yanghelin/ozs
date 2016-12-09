
CREATE TABLE `ozs_in_meeting` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '会议名称',
  `type` tinyint(4) DEFAULT NULL COMMENT '会议类型：1、所内会议',
  `content` varchar(2000) DEFAULT NULL COMMENT '会议内容',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `room_name` varchar(100) DEFAULT NULL COMMENT '会议室名称',
  `participants` varchar(2000) DEFAULT NULL COMMENT '参会人员',
  `depart_name` varchar(50) DEFAULT NULL COMMENT '部门',
  `publisher` int(11) DEFAULT NULL COMMENT '发布人',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='所内会议管理表';

CREATE TABLE `ozs_in_meeting_briefing` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `meeting_id` int(11) DEFAULT NULL COMMENT '会议ID',
  `name` varchar(100) DEFAULT NULL COMMENT '简报名称',
  `attachment` int(11) DEFAULT NULL COMMENT '简报附件',
  `type` varchar(20) DEFAULT NULL COMMENT '文档类型',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作简报表';

CREATE TABLE `ozs_in_meeting_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `meeting_id` int(11) DEFAULT NULL COMMENT '会议ID',
  `attendee` int(11) DEFAULT NULL COMMENT '参加者',
  `sender` int(11) DEFAULT NULL COMMENT '会议发起人',
  `is_read` tinyint(4) DEFAULT NULL COMMENT '是否阅读：0、否；1、是',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议邀请明细表';

CREATE TABLE `ozs_in_meeting_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `meeting_id` int(11) DEFAULT NULL COMMENT '会议室ID',
  `meeting_time` datetime DEFAULT NULL COMMENT '会议时间',
  `item_name` varchar(50) DEFAULT NULL COMMENT '物品名称',
  `item_num` int(11) DEFAULT NULL COMMENT '物品数量',
  `liable_people` varchar(50) DEFAULT NULL COMMENT '责任人',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `position` varchar(50) DEFAULT NULL COMMENT '职位',
  `remark` varchar(500) DEFAULT NULL COMMENT '特殊要求/备注',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议物品管理';

CREATE TABLE `ozs_in_meeting_materials` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `meeting_id` int(11) DEFAULT NULL COMMENT '会议ID',
  `name` varchar(100) DEFAULT NULL COMMENT '材料名称',
  `type` varchar(20) DEFAULT NULL COMMENT '材料类型',
  `attachment` int(11) DEFAULT NULL COMMENT '材料附件',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议材料管理';

CREATE TABLE `ozs_in_meeting_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `meeting_id` int(11) DEFAULT NULL COMMENT '会议ID',
  `attendee_num` int(11) DEFAULT NULL COMMENT '参会人数两',
  `guest_num` int(11) DEFAULT NULL COMMENT '外宾数量',
  `meeting_room` tinyint(4) DEFAULT NULL COMMENT '会议室：1、大会议室；2、外宾接待室；3、大会议室东侧；4、大会议室西侧',
  `organizing_dept` varchar(50) DEFAULT NULL COMMENT '筹办部门',
  `hoster` varchar(50) DEFAULT NULL COMMENT '凑办负责人',
  `item_hoster` varchar(50) DEFAULT NULL COMMENT '物品负责人',
  `phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `position` varchar(20) DEFAULT NULL COMMENT '职位',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议室表';

CREATE TABLE `ozs_in_meeting_room_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `meeting_id` int(11) DEFAULT NULL COMMENT '会议ID',
  `room_id` int(11) DEFAULT NULL COMMENT '会议室ID',
  `items_id` int(11) DEFAULT NULL COMMENT '物品ID',
  `item_num` int(11) DEFAULT NULL COMMENT '物品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议室物品详细表';

CREATE TABLE `ozs_meeting_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '业务附件表ID',
  `type` int(11) DEFAULT NULL COMMENT '附件类型：1、国际会议-会议内容；2、国际会议-会议日程；3、国际会议-会议邀请函；4、国际会议-会议相关资料；5、速记/媒体报名-其他资料；6、所内会议-会议资料；7、所内会议-简报资料',
  `business_id` int(11) DEFAULT NULL COMMENT '业务ID',
  `path` varchar(200) DEFAULT NULL COMMENT '附件路径',
  `name` varchar(100) DEFAULT NULL COMMENT '附件名称',
  `filename` varchar(100) DEFAULT NULL COMMENT '文件名',
  `download_count` int(11) DEFAULT NULL COMMENT '下载次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议附件表（包含所内和所外会议以及会议相关的附件）';

CREATE TABLE `ozs_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '站内消息ID',
  `title` varchar(200) DEFAULT NULL COMMENT '消息标题',
  `content` varchar(1000) DEFAULT NULL COMMENT '消息内容',
  `send_users` varchar(1000) DEFAULT NULL COMMENT '发送用户',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `send_by` int(11) DEFAULT NULL COMMENT '发送人',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站内消息';


CREATE TABLE `ozs_message_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `message_id` int(11) DEFAULT NULL COMMENT '关联消息ID',
  `message_title` varchar(200) DEFAULT NULL COMMENT '消息标题',
  `message_content` varchar(1000) DEFAULT NULL COMMENT '消息内容',
  `sender` int(11) DEFAULT NULL COMMENT '发送人',
  `receiver` int(11) DEFAULT NULL COMMENT '接收人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_read` tinyint(4) DEFAULT NULL COMMENT '是否阅读：0、否；1、是',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `is_reply` tinyint(4) DEFAULT NULL COMMENT '是否回复：0、否；1、是',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站内消息个人端';

CREATE TABLE `ozs_out_meeting` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '国际会议表主键',
  `name` varchar(200) DEFAULT NULL COMMENT '会议名称',
  `host_units` varchar(200) DEFAULT NULL COMMENT '主办单位',
  `sponsoring_units` varchar(200) DEFAULT NULL COMMENT '承办单位',
  `overseas_partners` varchar(200) DEFAULT NULL COMMENT '境外合作机构',
  `address` varchar(400) DEFAULT NULL COMMENT '召开地点',
  `meeting_size` varchar(50) DEFAULT NULL COMMENT '会议规模',
  `presenter` varchar(50) DEFAULT NULL COMMENT '主持人',
  `work_language` varchar(50) DEFAULT NULL COMMENT '工作语言',
  `contacts` varchar(50) DEFAULT NULL COMMENT '联系人',
  `start_time` datetime DEFAULT NULL COMMENT '召开开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '召开结束时间',
  `contact_phone` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `contact_email` varchar(100) DEFAULT NULL COMMENT '联系邮箱',
  `content` varchar(4000) DEFAULT NULL COMMENT '会议内容',
  `content_attachment` int(11) DEFAULT NULL COMMENT '会议内容附件',
  `agenda` varchar(4000) DEFAULT NULL COMMENT '会议日程',
  `agenda_attachment` int(11) DEFAULT NULL COMMENT '会议日程附件',
  `invitation` int(11) DEFAULT NULL COMMENT '会议邀请函',
  `related_data` int(11) DEFAULT NULL COMMENT '会议相关资料',
  `hoster` varchar(50) DEFAULT NULL COMMENT '会议负责人',
  `phone` varchar(50) DEFAULT NULL COMMENT '负责人电话',
  `position` varchar(50) DEFAULT NULL COMMENT '职位',
  `type` int(11) DEFAULT NULL COMMENT '会议类型:0、国际会议',
  `is_cn` tinyint(4) DEFAULT NULL COMMENT '是否中文：0、英文；1、中文',
  `is_stay` tinyint(4) DEFAULT NULL COMMENT '是否提供住宿：0、否；1、是',
  `stay_start` datetime DEFAULT NULL COMMENT '住宿开始时间',
  `stay_end` datetime DEFAULT NULL COMMENT '住宿结束时间',
  `stay_hotel` varchar(100) DEFAULT NULL COMMENT '住宿酒店',
  `is_foreign_ticket` tinyint(4) DEFAULT NULL COMMENT '是否提供外方机票：0、否；1、是',
  `foreign_start` datetime DEFAULT NULL COMMENT '外方机票开始时间',
  `foreign_end` datetime DEFAULT NULL COMMENT '外方机票结束时间',
  `is_domestic_ticket` tinyint(4) DEFAULT NULL COMMENT '是否提供中方机票：0、否；1、是',
  `domestic_start` datetime DEFAULT NULL COMMENT '中国机票开始时间',
  `domestic_end` datetime DEFAULT NULL COMMENT '中方机票结束时间',
  `is_food` tinyint(4) DEFAULT NULL COMMENT '是否提供餐：0、否；1、是',
  `breakfast_start` datetime DEFAULT NULL COMMENT '早饭开始时间',
  `breakfast_end` datetime DEFAULT NULL COMMENT '早饭结束时间',
  `lunch_start` datetime DEFAULT NULL COMMENT '午饭开始时间',
  `lunch_end` datetime DEFAULT NULL COMMENT '午饭结束时间',
  `dinner_start` datetime DEFAULT NULL COMMENT '晚饭开始时间',
  `dinner_end` datetime DEFAULT NULL COMMENT '晚饭开始时间',
  `is_drive` tinyint(4) DEFAULT NULL COMMENT '是否自驾：0、否；1、是',
  `publisher` int(11) DEFAULT NULL COMMENT '发布人',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国际会议表';

CREATE TABLE `ozs_out_meeting_enroll` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '报名表ID',
  `out_meeting_id` int(11) DEFAULT NULL COMMENT '国际会议表ID',
  `login_id` int(11) DEFAULT NULL COMMENT '登录人ID',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录账号',
  `user_type` tinyint(4) DEFAULT NULL COMMENT '用户类型：0、发言嘉宾；1、参会人员；2、速记；3、媒体',
  `cn_name` varchar(20) DEFAULT NULL COMMENT '中文名称',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `national` varchar(20) DEFAULT NULL COMMENT '国籍',
  `en_name` varchar(50) DEFAULT NULL COMMENT '英文名称',
  `position` varchar(20) DEFAULT NULL COMMENT '职位',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `unit` varchar(100) DEFAULT NULL COMMENT '工作单位',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `out_name` varchar(50) DEFAULT NULL COMMENT '外方英文名称',
  `out_birthday` datetime DEFAULT NULL COMMENT '外方出生日期',
  `out_sex` tinyint(4) DEFAULT NULL COMMENT '外方性别',
  `out_national` varchar(20) DEFAULT NULL COMMENT '外方国籍',
  `passport` varchar(20) DEFAULT NULL COMMENT '护照号',
  `out_from` varchar(20) DEFAULT NULL COMMENT '外方出发地',
  `out_arrive` varchar(20) DEFAULT NULL COMMENT '外方目的地',
  `out_go_time` datetime DEFAULT NULL COMMENT '外方出发时间',
  `out_back_time` datetime DEFAULT NULL COMMENT '外方回程时间',
  `out_go_flight` varchar(20) DEFAULT NULL COMMENT '外方来程航班',
  `out_back_flight` varchar(20) DEFAULT NULL COMMENT '外方回程航班',
  `in_name` varchar(20) DEFAULT NULL COMMENT '中方姓名',
  `in_birthday` datetime DEFAULT NULL COMMENT '中方出生日期',
  `in_sex` tinyint(4) DEFAULT NULL COMMENT '中方性别',
  `in_national` varchar(20) DEFAULT NULL COMMENT '中方国籍',
  `card` varchar(20) DEFAULT NULL COMMENT '身份证',
  `in_from` varchar(20) DEFAULT NULL COMMENT '中方出发地',
  `in_arrive` varchar(20) DEFAULT NULL COMMENT '中方目的地',
  `in_go_time` datetime DEFAULT NULL COMMENT '中方出发时间',
  `in_back_time` datetime DEFAULT NULL COMMENT '中方回程时间',
  `in_go_flight` varchar(20) DEFAULT NULL COMMENT '中方来程航班',
  `in_back_flight` varchar(20) DEFAULT NULL COMMENT '中方回程航班',
  `is_breakfast` tinyint(4) DEFAULT NULL COMMENT '是否预定早餐：0、是；1、否',
  `is_lunch` tinyint(4) DEFAULT NULL COMMENT '是否预定午餐：0、是；1、否',
  `is_dinner` tinyint(4) DEFAULT NULL COMMENT '是否预定晚餐：0、是；1、否',
  `car_no` varchar(20) DEFAULT NULL COMMENT '自驾车牌号',
  `bank_no` varchar(50) DEFAULT NULL COMMENT '银行卡号',
  `bank_address` varchar(100) DEFAULT NULL COMMENT '开户行',
  `bank_name` varchar(100) DEFAULT NULL COMMENT '账号名称',
  `bank_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `other` int(11) DEFAULT NULL COMMENT '其他:存放附件id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_stay` tinyint(4) DEFAULT NULL COMMENT '是否住宿：0、否；1、是',
  `is_delete_stay` tinyint(4) DEFAULT NULL COMMENT '是否删除住宿：0、未删除；1、已删除',
  `is_foreign` tinyint(4) DEFAULT NULL COMMENT '是否外方机票：0、否；1、是',
  `is_domestic` tinyint(4) DEFAULT NULL COMMENT '是否中方机票：0、否；1、是',
  `is_delete_ticket` tinyint(4) DEFAULT NULL COMMENT '是否删除机票：0、未删除；1、已删除',
  `is_food` tinyint(4) DEFAULT NULL COMMENT '是否就餐：0、否；1、是',
  `is_drive` tinyint(4) DEFAULT NULL COMMENT '是否自驾车辆：0、否；1、是',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='国际会议报名表';

CREATE TABLE `ozs_work_meal` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `number` int(11) DEFAULT NULL COMMENT '点餐份数',
  `start_time` datetime DEFAULT NULL COMMENT '订餐开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '订餐结束时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` int(11) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新人',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除：0、未删除；1、已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订餐管理';


CREATE TABLE `ozs_meeting_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) DEFAULT NULL COMMENT '会议菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '会议菜单的url链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议菜单表，主要为用户授权使用';

CREATE TABLE `ozs_meeting_menu_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '会议菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会议菜单和用户关联表';


-- 0、参会人员
-- 1、发言嘉宾
-- 2、速记
-- 3、媒体
-- 0、participant
-- 1、speaker
-- 2、stenographer
-- 3、media

