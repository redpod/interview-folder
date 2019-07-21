/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : db_gd

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-10-31 09:53:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_cultivate
-- ----------------------------
DROP TABLE IF EXISTS `t_cultivate`;
CREATE TABLE `t_cultivate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `guidedogId` int(11) NOT NULL COMMENT '导盲犬ID',
  `institutionId` int(11) NOT NULL COMMENT '培养基地ID',
  `cultivatedate` varchar(45) NOT NULL COMMENT '培养了多少时间',
  `content` text NOT NULL COMMENT '培养导盲犬训练的内容',
  `effect` text NOT NULL COMMENT '培养的导盲犬成效怎么样，能和领养人有很好的契合度',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cultivate
-- ----------------------------
INSERT INTO `t_cultivate` VALUES ('1', '1', '1', '2016年12月1日', '基本科目：基本服从训练（坐、卧、定），随行等\r\n解决问题行为：定点大小便、止吠、防止乱咬、防止扑人等，这个要根据犬主人的需求进行，就是狗狗有什么问题，训犬师就解决什么。\r\n各种技能：握手、打滚、接球之类杂耍，扑咬、防卫，在外国还有训犬师把你的狗训练成搜救犬之类的专业工作犬。导盲犬训练基地就训练导盲犬。', '已经成为导盲犬毕业一年，和主人回家后相处融洽，是一只十分受人喜爱的导盲犬。');
INSERT INTO `t_cultivate` VALUES ('2', '2', '2', '2017年12月12日', '在出生后40多天接种过一次疫苗后，它们将离开产房，被寄养在爱心志愿者的家里，这个阶段主要在于培养它们适应环境、与人友善交流的能力。经过一年的喂养后，犬的身形和智商也接近成熟，它们会被重新带回基地，接受另一轮的挑选和培训。 ', '在非常顺利的训练中');
INSERT INTO `t_cultivate` VALUES ('3', '3', '3', '2015年10月10日', '具备的素质包括：掌握数十种指令，不惧声音、光、火、汽车等，无攻击性，无不良习惯，沉着冷静，能寻找到通道、交通工具等。', '已跟主人和谐生活至今');

-- ----------------------------
-- Table structure for t_dogtype
-- ----------------------------
DROP TABLE IF EXISTS `t_dogtype`;
CREATE TABLE `t_dogtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `desc` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dogtype
-- ----------------------------
INSERT INTO `t_dogtype` VALUES ('1', '金毛寻回猎犬（黄金猎犬）', '它的身高为51cm-61cm，体重为25kg-34kg，寿命最高可达15年。金毛寻回猎犬遗传免疫力高，体态健壮，容易饲养。它们的性格又善良友好，对主人十分忠诚。金毛也很聪明，在正常情况下，不会对其他人和犬吠叫，也不敌视。这些都成为金毛可以被选为导盲犬的原因。');
INSERT INTO `t_dogtype` VALUES ('2', '拉布拉多', '它的身高为54cm-62cm，体重为25kg-34kg，平均寿命为12年。拉布拉多聪明、警觉、善解人意，它们性格温顺、平稳，既不迟钝也不过于活跃，对人友善，喜欢跟人玩游戏。不难发现，这些特征跟金毛极为相似。因此它们都非常适合做导盲犬。');
INSERT INTO `t_dogtype` VALUES ('3', '德国牧羊犬', '　别名狼狗，身高为55cm-56cm，体重为34kg-43kg，平均寿命10年。德国牧羊犬的性格坚强、自信，神经稳定，不胆怯，在没有刺激的情况下很温顺、警惕且驯服。不过有些人，特别是小孩子会害怕德国牧羊犬，所以做导盲犬的德国牧羊犬较少。');

-- ----------------------------
-- Table structure for t_guidedog
-- ----------------------------
DROP TABLE IF EXISTS `t_guidedog`;
CREATE TABLE `t_guidedog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `birthdate` varchar(45) DEFAULT NULL,
  `photo` varchar(128) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `adoptersId` (`ownerId`),
  CONSTRAINT `adoptersId` FOREIGN KEY (`ownerId`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_guidedog
-- ----------------------------
INSERT INTO `t_guidedog` VALUES ('4', 'sunny', '2016年9月30日', 'photo/1540809531659.jpg', '4');
INSERT INTO `t_guidedog` VALUES ('5', 'nana', '2017年12月12日', 'photo/1540810952921.jpg', '2');
INSERT INTO `t_guidedog` VALUES ('6', 'coco', '2017年5月20日', 'photo/1540880464869.jpg', '3');
INSERT INTO `t_guidedog` VALUES ('7', '小Q', '2015年10月1日', 'photo/1540880502318.jpg', '9');
INSERT INTO `t_guidedog` VALUES ('8', 'JOJO', '2017年12月12日', 'photo/1540880961229.jpg', '9');

-- ----------------------------
-- Table structure for t_institution
-- ----------------------------
DROP TABLE IF EXISTS `t_institution`;
CREATE TABLE `t_institution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_institution
-- ----------------------------
INSERT INTO `t_institution` VALUES ('1', '大连医科大学');
INSERT INTO `t_institution` VALUES ('2', '中国导盲犬南方示范基地');
INSERT INTO `t_institution` VALUES ('3', '广州导盲犬基地');
INSERT INTO `t_institution` VALUES ('8', '中国北京');
INSERT INTO `t_institution` VALUES ('9', '中国澳门');

-- ----------------------------
-- Table structure for t_institution_dogtype
-- ----------------------------
DROP TABLE IF EXISTS `t_institution_dogtype`;
CREATE TABLE `t_institution_dogtype` (
  `institutionId` int(11) NOT NULL,
  `dogtypeId` int(11) NOT NULL,
  KEY `dogtypeId` (`dogtypeId`),
  CONSTRAINT `dogtypeId` FOREIGN KEY (`dogtypeId`) REFERENCES `t_dogtype` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_institution_dogtype
-- ----------------------------
INSERT INTO `t_institution_dogtype` VALUES ('1', '1');
INSERT INTO `t_institution_dogtype` VALUES ('2', '2');
INSERT INTO `t_institution_dogtype` VALUES ('3', '3');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `role` varchar(45) NOT NULL COMMENT '角色（admin系统管理员、adopter领养人）',
  `name` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '123456', '1234567890', '中国大连');
INSERT INTO `t_user` VALUES ('2', 'customer', 'Tom', '123456', '1234567890', '中国台湾');
INSERT INTO `t_user` VALUES ('3', 'customer', 'Mary', '123456', '1234567890', '中国深圳');
INSERT INTO `t_user` VALUES ('4', 'customer', '南北', '123456', '1234567890', '中国杭州');
INSERT INTO `t_user` VALUES ('9', 'customer', '司命', '123456', '110', '警察局干部大院');
