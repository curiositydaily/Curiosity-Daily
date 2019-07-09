-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1
-- 生成日期： 2019-07-09 03:08:11
-- 服务器版本： 10.1.36-MariaDB
-- PHP 版本： 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `curiosity_daily`
--

-- --------------------------------------------------------

--
-- 表的结构 `administrator_info`
--

CREATE TABLE `administrator_info` (
  `administrator_id` int(10) NOT NULL,
  `number` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `administrator_info`
--

INSERT INTO `administrator_info` (`administrator_id`, `number`, `password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- 表的结构 `article_comment`
--

CREATE TABLE `article_comment` (
  `comment_id` int(10) NOT NULL,
  `article_id` int(10) NOT NULL,
  `comment_user_id` int(10) NOT NULL,
  `comment` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `article_comment`
--

INSERT INTO `article_comment` (`comment_id`, `article_id`, `comment_user_id`, `comment`) VALUES
(1, 1, 1, '好棒呀'),
(2, 2, 1, '非常真实');

-- --------------------------------------------------------

--
-- 表的结构 `design_content`
--

CREATE TABLE `design_content` (
  `url_id` int(255) NOT NULL,
  `design_id` int(10) NOT NULL,
  `image_url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `design_content`
--

INSERT INTO `design_content` (`url_id`, `design_id`, `image_url`) VALUES
(1, 1, 'http://image.baidu.com/search/detailct=503316480&z=0&ipn=false&word=%E5%A3%81%E7%BA%B8&hs=0&pn=1&spn=0&di=146960&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf8&oe=utf8&cl=2&lm=-1&cs=4290679757%2C2250633988&os=3799354600%2C4216505291&simid=0%2C0&adpicid=0&'),
(2, 1, 'http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E5%A3%81%E7%BA%B8&hs=0&pn=0&spn=0&di=105710&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=2668764066%2C1478918522&os=371931939%2C4203106999&simid=3453787766%2C3'),
(3, 2, 'http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E5%A4%B4%E5%83%8F&hs=0&pn=9&spn=0&di=58410&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf-8&oe=utf-8&cl=2&lm=-1&cs=400062461%2C2874561526&os=3840264708%2C1067864259&simid=0%2C0&adpicid=0'),
(4, 2, 'http://image.baidu.com/search/detailct=503316480&z=0&ipn=false&word=%E5%A4%B4%E5%83%8F%20%E4%BA%BA%E7%89%A9%E5%BD%A2%E8%B1%A1&hs=0&pn=5&spn=0&di=120780&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=&ie=utf-8&oe=utf-8&in=3354&cl=2&lm=-1%2C&st=&cs=288128968');

-- --------------------------------------------------------

--
-- 表的结构 `user_article`
--

CREATE TABLE `user_article` (
  `article_id` int(10) NOT NULL,
  `user_id` int(10) NOT NULL,
  `title` varchar(30) NOT NULL,
  `content` mediumtext NOT NULL,
  `commendation` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_article`
--

INSERT INTO `user_article` (`article_id`, `user_id`, `title`, `content`, `commendation`) VALUES
(1, 1, '几个本科生画的“城中村地图”，关于高楼大厦之外的北京', '水磨有几个小小的、临街的入口，坐车路过很难发现在这样一排小餐馆和彩票店的后面，还藏着一片密集的屋舍。唯一暴露了其身份的是一架拱形的铁门，这是北京城中村的标配，上面有四个不太明显的大字：水磨社区。\r\n\r\n再往里，路越走越窄，两侧三四层高的自建楼房紧挨着，头顶电线管道交缠在一起，划出一些黑洞洞的院落。到处能看到房屋出租的告示， 1000 元就可以在这里租到几平米的单间，里面衣橱桌子床头柜床挤成一团。卫生间在另一层。楼道里很安静，租客们到傍晚才会陆续回来。房东驾轻就熟地引人上楼，说：“这是最后一间了。”', 20),
(2, 1, '忙碌悖论', '闲下来就彻底不想动，几件事同时进行反而更有效率。虽然知道“工作生活应该平衡”，却总是无法真正实现。很忙是没错，但闲下来之后却完全不记得自己忙了啥。忙完之后并没有感到轻松，反而觉得有点不适应。把忙碌跟“有价值”挂钩，但其实多数忙碌都没啥价值。专攻“见缝插针式忙碌”，真正有大把时间反而更想摸鱼。忙碌时总盘算着“闲了要做啥”，闲下来之后又不想做了。虽然人人都说想休息，但没人愿意承认\"其实我很闲\"。', 30);

-- --------------------------------------------------------

--
-- 表的结构 `user_attention`
--

CREATE TABLE `user_attention` (
  `id` int(10) NOT NULL,
  `attention_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_attention`
--

INSERT INTO `user_attention` (`id`, `attention_id`) VALUES
(1, '2,3'),
(2, '1,3'),
(3, '1,2');

-- --------------------------------------------------------

--
-- 表的结构 `user_design`
--

CREATE TABLE `user_design` (
  `design_id` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` int(2) NOT NULL,
  `introduction` varchar(255) NOT NULL,
  `commendation` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_design`
--

INSERT INTO `user_design` (`design_id`, `name`, `type`, `introduction`, `commendation`) VALUES
(1, '好看的壁纸万里挑一', 0, '为你提供全新壁纸！！！！', 20),
(2, '换头像是个上瘾的事', 1, '每周推出原创头像，给社交加点新鲜感，从这里开始。', 30);

-- --------------------------------------------------------

--
-- 表的结构 `user_feedback`
--

CREATE TABLE `user_feedback` (
  `id` int(10) NOT NULL,
  `content` varchar(255) NOT NULL,
  `status` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_feedback`
--

INSERT INTO `user_feedback` (`id`, `content`, `status`) VALUES
(1, '界面不友好', 0),
(2, '希望多点图片', 0);

-- --------------------------------------------------------

--
-- 表的结构 `user_info`
--

CREATE TABLE `user_info` (
  `id` int(10) NOT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(30) NOT NULL,
  `introduction` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_info`
--

INSERT INTO `user_info` (`id`, `image`, `name`, `introduction`) VALUES
(1, 'http://image.baidu.com/search/detailct=503316480&z=0&ipn=false&word=%E5%A4%B4%E5%83%8F&hs=0&pn=14&spn=0&di=124520&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf8&oe=utf8&cl=2&lm=-1&cs=2961748425%2C612527933&os=252736824%2C354496982&simid=3469948073%2C43', 'Susan', 'An optimistic and cheerful girl who loves to share life!'),
(2, 'http://image.baidu.com/search/detailct=503316480&z=0&ipn=false&word=%E5%A4%B4%E5%83%8F&hs=0&pn=0&spn=0&di=47630&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf8&oe=utf8&cl=2&lm=-1&cs=3024387196%2C1621670548&os=142540563%2C40081414&simid=3531055946%2C5834', 'Bob', 'Like basketball, full of curiosity about the world.'),
(3, 'http://image.baidu.com/search/detailct=503316480&z=0&ipn=false&word=%E5%A4%B4%E5%83%8F&hs=0&pn=10&spn=0&di=28600&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&ie=utf8&oe=utf8&cl=2&lm=-1&cs=4248235115%2C2201748940&os=1231334229%2C3632238359&simid=0%2C0&adpicid', '梦楠', '爱吃草莓');

-- --------------------------------------------------------

--
-- 表的结构 `user_login`
--

CREATE TABLE `user_login` (
  `id` int(10) NOT NULL,
  `number` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `phonenumber` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user_login`
--

INSERT INTO `user_login` (`id`, `number`, `password`, `phonenumber`) VALUES
(1, 'Susan', '123456', '13263180001'),
(2, 'Bob', '123456', '13263180002'),
(3, 'mengnan', '123456', '13263180003');

--
-- 转储表的索引
--

--
-- 表的索引 `administrator_info`
--
ALTER TABLE `administrator_info`
  ADD PRIMARY KEY (`administrator_id`);

--
-- 表的索引 `article_comment`
--
ALTER TABLE `article_comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `article_id` (`article_id`);

--
-- 表的索引 `design_content`
--
ALTER TABLE `design_content`
  ADD PRIMARY KEY (`url_id`),
  ADD KEY `design_id` (`design_id`);

--
-- 表的索引 `user_article`
--
ALTER TABLE `user_article`
  ADD PRIMARY KEY (`article_id`),
  ADD KEY `user_id` (`user_id`);

--
-- 表的索引 `user_attention`
--
ALTER TABLE `user_attention`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user_design`
--
ALTER TABLE `user_design`
  ADD PRIMARY KEY (`design_id`);

--
-- 表的索引 `user_feedback`
--
ALTER TABLE `user_feedback`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user_login`
--
ALTER TABLE `user_login`
  ADD PRIMARY KEY (`id`);

--
-- 限制导出的表
--

--
-- 限制表 `article_comment`
--
ALTER TABLE `article_comment`
  ADD CONSTRAINT `article_comment_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `user_article` (`article_id`);

--
-- 限制表 `design_content`
--
ALTER TABLE `design_content`
  ADD CONSTRAINT `design_content_ibfk_1` FOREIGN KEY (`design_id`) REFERENCES `user_design` (`design_id`);

--
-- 限制表 `user_article`
--
ALTER TABLE `user_article`
  ADD CONSTRAINT `user_article_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
