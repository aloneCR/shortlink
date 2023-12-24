package com.xpluo.shortlink.admin;

/**
 * @author luoxiaopeng
 * @date 2023/12/18
 */
public class UserTableShardingTest {
    public static final String CREATE_TABLE_SQL = "CREATE TABLE `t_user_%d` (\n" +
            "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
            "  `username` varchar(255) DEFAULT NULL COMMENT '用户名',\n" +
            "  `password` varchar(512) DEFAULT NULL COMMENT '密码',\n" +
            "  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',\n" +
            "  `phone` varchar(128) DEFAULT NULL COMMENT '手机号',\n" +
            "  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',\n" +
            "  `deletion_time` bigint(20) DEFAULT NULL COMMENT '注销时间',\n" +
            "  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
            "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',\n" +
            "  `del_tag` tinyint(1) DEFAULT '0' COMMENT '删除标识 0:未删除 1:已删除',\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE KEY `idx_unique_username` (`username`) USING BTREE COMMENT '用户名唯一索引'\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;";

    public static final String TRUNCATE_TABLE_SQL = "TRUNCATE t_user_%d;";

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((TRUNCATE_TABLE_SQL) + "%n", i);
        }
    }
}
