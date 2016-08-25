/*
Navicat MySQL Data Transfer

Source Server         : KKys
Source Server Version : 50173
Source Host           : 121.42.8.85:3306
Source Database       : jfinal

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-08-25 22:10:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `album`
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES ('58', '/upload/5.jpg', '牛排猪排双拼', '2016-08-26 13:06:28');
INSERT INTO `album` VALUES ('4', '/upload/4.jpg', '干锅鱿鱼鸡', '2016-08-17 21:29:57');
INSERT INTO `album` VALUES ('1', '/upload/hello.jpg', 'hello world', '2016-08-01 15:00:47');
INSERT INTO `album` VALUES ('2', '/upload/1.jpg', '吴义全最爱的红烧肉', '2016-08-22 16:44:21');
INSERT INTO `album` VALUES ('3', '/upload/3.jpg', '去遵义做的四个菜', '2016-08-04 16:44:33');
INSERT INTO `album` VALUES ('5', '/upload/1.JPG', '头像', '2016-08-22 21:43:39');
INSERT INTO `album` VALUES ('6', '/upload/blog8.22.png', '今天博客长这样(8.22)', '2016-08-22 22:31:13');
INSERT INTO `album` VALUES ('7', '/upload/6.png', '阿里大数据证书', '2016-08-23 11:53:49');
INSERT INTO `album` VALUES ('8', '/upload/11.JPG', '新加坡帆船酒店', '2016-08-25 19:13:32');
INSERT INTO `album` VALUES ('9', '/upload/12.jpg', '巨好吃的潮州卤面', '2016-08-25 19:14:26');
INSERT INTO `album` VALUES ('10', '/upload/13.jpg', '东莞一哥周小强', '2016-08-25 19:14:51');
INSERT INTO `album` VALUES ('11', '/upload/15.jpg', '蔓藤', '2016-08-25 19:23:49');
INSERT INTO `album` VALUES ('12', '/upload/16.jpg', '亚龙湾', '2016-08-25 19:24:01');
INSERT INTO `album` VALUES ('13', '/upload/18.jpg', '田野', '2016-08-25 19:24:55');
INSERT INTO `album` VALUES ('14', '/upload/17.jpg', '装机  走线感人', '2016-08-25 19:25:38');
INSERT INTO `album` VALUES ('15', '/upload/19.jpg', '跟鑫弟在方所', '2016-08-25 19:26:22');
INSERT INTO `album` VALUES ('16', '/upload/20.jpg', 'color run', '2016-08-25 19:27:11');
INSERT INTO `album` VALUES ('17', '/upload/21.jpg', 'color run', '2016-08-25 19:27:25');

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `type` tinyint(4) DEFAULT NULL,
  `click_times` int(11) DEFAULT '0' COMMENT '阅读量',
  `create_time` datetime DEFAULT NULL,
  `comment_times` int(11) DEFAULT '0' COMMENT '评论数',
  `agree_with_times` int(11) DEFAULT '0' COMMENT '赞同数',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('33', 'Hello World！！！', '<p>一直想做个人的站点，苦与自己前端太水，花了几天才搭了这个博客，以后就在这上面发发文章啦！</p>\r\n\r\n\r\n<p>本站点十分轻量，前端仅用了bootstrap框架，后台用的jfinal框架，模板引擎用的freemarker，连接池用的duird，速度飞快.</p>\r\n\r\n\r\n<p>目前还在优化完善阶段，后期会开源到github，欢迎大家互相学习，共同进步！</p>\r\n', '1', '31', '2016-08-21 16:22:17', '1', '0', '/upload/2.jpg');
INSERT INTO `blog` VALUES ('35', 'freemarker模板引擎知识点回顾', '<p>本博客开发使用了freemarker模板引擎来，相比于JSP更加轻量与灵活，对使用者更加友好，下面就来巩固一下开发时所用到的freemarker知识点</p>\r\n\r\n<p><strong>1.freemarker思路：模板 + 数据模型 = 输出</strong></p>\r\n\r\n<p>加入一个指令如${user},这个user会在java中被赋值从而显示在页面上。这种处理方式更加具有MVC的思想，模板层不需要做任何改变，后台改变这个值，模板只是负责输出。</p>\r\n\r\n<p><strong>2.include 指令</strong></p>\r\n\r\n<p>在模板中插入其他文件的内容。如：&lt;#include &quot;/copyright_footer.html&quot;&gt;</p>\r\n\r\n<p><strong>3.处理不存在的变量</strong><br />\r\n如： ${user!&quot;visitor&quot;} 这里表示当user变量如果不存在或为空的时候，就取默认值&ldquo;visitor&rdquo;显示在页面上</p>\r\n\r\n<p><strong>4.自定义指令：宏</strong></p>\r\n\r\n<p>macro 指令自身不输出任何内容， 它只是用来创建宏变量，所以就会有一个名为 greet 的变量。在 &lt;#macro greet&gt; 和 &lt;/#macro&gt; 之间的内容 (称为 宏定义体) 将会在使用该变量作为指令时执行。</p>\r\n\r\n<ul>\r\n	<li>简单变量： 它能从模板中的任何位置来访问，或者从使用 include 指令引入的模板访问。可以使用 assign 指令来创建或替换这些变量。如 &lt;#assign x = &quot;plain&quot;&gt;</li>\r\n	<li>局部变量：它们只能被设置在 宏定义体内， 而且只在宏内可见。一个局部变量的生命周期只是宏的调用过程。可以使用 local指令 在宏定义体内创建或替换局部变量。如&lt;#local x = &quot;local&quot;&gt;</li>\r\n	<li>循环变量：循环变量是由如 list 指令自动创建的，而且它们只在指令的开始和结束标记内有效。宏的参数是局部变量而不是循环变量。</li>\r\n	<li>全局变量：全局变量被所有模板共享，全局变量通过 global指令来定义。(最好别用)</li>\r\n</ul>\r\n\r\n<p><strong>5.循环的索引</strong></p>\r\n\r\n<p>在循环变量名称后加_index 如&lt;#list alist as x&gt;${x_index}&lt;/#list&gt;</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>暂时就这些，以后用到新知识点了再修改。</p>\r\n', '0', '24', '2016-08-21 22:51:40', '1', '0', '/upload/22.jpg');
INSERT INTO `blog` VALUES ('36', 'this和super的用法与区别', '<p>今天做题遇到了this和super的区别，下面来总结一下：</p>\r\n\r\n<p><strong>this</strong></p>\r\n\r\n<p>this是自身的一个对象，代表对象本身，可以理解为：指向对象本身的一个指针。</p>\r\n\r\n<p>this的用法在java中大体可以分为3种：</p>\r\n\r\n<p><strong>1.普通的直接引用</strong></p>\r\n\r\n<p>this相当于是指向当前对象本身。</p>\r\n\r\n<p><strong>2.形参与成员名字重名，用this来区分</strong></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>super</strong></p>\r\n\r\n<p>super可以理解为是指向自己超（父）类对象的一个指针，而这个超类指的是离自己最近的一个父类。</p>\r\n\r\n<p>用法：</p>\r\n\r\n<p><strong>1.普通的直接引用</strong></p>\r\n\r\n<p>与this类似，super相当于是指向当前对象的父类，这样就可以用super.xxx来引用父类的成员。</p>\r\n\r\n<p><strong>2.子类中的成员变量或方法与父类中的成员变量或方法同名</strong></p>\r\n\r\n<p><strong>3.引用构造函数</strong></p>\r\n\r\n<p>super（参数）：调用父类中的某一个构造函数（应该为构造函数中的第一条语句）。</p>\r\n\r\n<p>this（参数）：调用本类中另一种形式的构造函数（应该为构造函数中的第一条语句）。</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p><strong>他们的区别：</strong></p>\r\n\r\n<p>1）调用super()必须写在子类构造方法的第一行，否则编译不通过。每个子类构造方法的第一条语句，都是隐含地调用super()，如果父类没有这种形式的构造函数，那么在编译的时候就会报错。</p>\r\n\r\n<p>2）super()和this()类似,区别是，super从子类中调用父类的构造方法，this()在同一类内调用其它方法。</p>\r\n\r\n<p>3）super()和this()均需放在构造方法内第一行。</p>\r\n\r\n<p>4）尽管可以用this调用一个构造器，但却不能调用两个。</p>\r\n\r\n<p>5）this和super不能同时出现在一个构造函数里面，因为this必然会调用其它的构造函数，其它的构造函数必然也会有super语句的存在，所以在同一个构造函数里面有相同的语句，就失去了语句的意义，编译器也不会通过。</p>\r\n\r\n<p>6）this()和super()都指的是对象，所以，均不可以在static环境中使用。包括：static变量,static方法，static语句块。</p>\r\n\r\n<p>7）从本质上讲，this是一个指向本对象的指针, 然而super是一个Java关键字。</p>\r\n', '0', '7', '2016-08-22 08:48:53', '0', '0', '/upload/201505272034.jpg');
INSERT INTO `blog` VALUES ('39', '阿里中间件比赛回顾', '<p>马上研二了，回顾这一学期，除了出去全职实习了两个月，最有意义的事情应该就是这个阿里天池大数据的中间件比赛了</p>\r\n\r\n<p>前前后后大概做了两个月，从初赛一步步搭建服务器到复赛的大数据查询，中间学习到了很多，也爬过了很多坑，在这里一起梳理一下，为下一步打算吧</p>\r\n\r\n<p>首先，中间件比赛在阿里甚至是IT行业都相当被看重，所以从一开始我们触及科技三人组小分队就牟足了劲想大干一场</p>\r\n\r\n<p>初赛的内容主要是学会搭建并使用Jstorm、RocketMQ、Tair这三款工具就好，从RocketMQ中拉取模拟双十一脱敏后的数据，利用Jstorm进行分布式流计算，算出每一分钟的特定值或是比值，分别存入Tair这个非关系型数据库，然后与标准值比较，取正确率与流式计算时间为成绩</p>\r\n\r\n<p>这也是我第一次略微接触到大数据相关的东西，原来看起来高大上的大数据，现在看来自己也能初步了解一下，想想还有些小激动呢，总体来说初赛难度不大，取所有队伍的前100名进行复赛，还是没什么压力的，最终我们以99%的正确率顺利晋级</p>\r\n\r\n<p>复赛的内容比较庞大，明天接着写吧，睡了......</p>\r\n\r\n<p>&nbsp;</p>\r\n', '0', '36', '2016-08-22 22:19:49', '0', '0', '/upload/1.png');
INSERT INTO `blog` VALUES ('40', '又是一年开学时', '<p>时间过得真快，从吴义权辞职考研到今天来报道，一年半就这么过了</p>\r\n\r\n<p>再看到父母们大包小包帮孩子拎着行李，仿佛时光回到了六年前</p>\r\n\r\n<p>六年时间一晃而过，我们也不再是曾经单纯懵懂的少年</p>\r\n\r\n<p>不禁感叹一句，那是我逝去的青春</p>\r\n\r\n<p>&nbsp;</p>\r\n', '1', '7', '2016-08-25 20:57:18', '2', '0', '/upload/23.jpg');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) DEFAULT NULL,
  `content` text,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `agree_with_times` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('50', '33', '测试评论！', '今晚打老虎', 'ysooys@163.com', '2016-08-21 16:55:13', '0');
INSERT INTO `comment` VALUES ('51', '35', '写得太好了...', '不是本人', 'ysooys@163.com', '2016-08-21 22:56:10', '0');
INSERT INTO `comment` VALUES ('52', '40', '大家好，我是吴义权~', '无情无义权', 'ysooys@163.com', '2016-08-25 21:01:57', '0');
INSERT INTO `comment` VALUES ('53', '40', '为什么只有我一个人测试评论.......', '今晚打老虎', 'ysooys@163.com', '2016-08-25 21:02:31', '0');

-- ----------------------------
-- Table structure for `info`
-- ----------------------------
DROP TABLE IF EXISTS `info`;
CREATE TABLE `info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `today_click_times` int(11) DEFAULT NULL,
  `history_click_times` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of info
-- ----------------------------
INSERT INTO `info` VALUES ('1', '294', '306');
