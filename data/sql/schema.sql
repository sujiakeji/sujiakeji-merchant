DROP TABLE IF EXISTS `t_mer`;

CREATE TABLE `t_mer` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mer_num` varchar(20) NOT NULL COMMENT '商户编号',
  `mer_name` varchar(30) NOT NULL COMMENT '商户全称',
  `mer_short_name` varchar(20) NOT NULL COMMENT '商户简称',
  `status` char(1) NOT NULL COMMENT '状态',
  `admin_id` bigint(11) NOT NULL COMMENT '商户管理员ID',
  `admin_auth_letter` varchar(200) NOT NULL COMMENT '管理员授权函',
  `mer_attr` char(1) NOT NULL COMMENT '商户属性（对公/对私）',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `create_by` bigint(11) NOT NULL COMMENT '创建人UserID',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` bigint(11) DEFAULT NULL COMMENT '修改人UserID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
