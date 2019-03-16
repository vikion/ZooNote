-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Hostiteľ: 127.0.0.1
-- Čas generovania: Pi 15.Mar 2019, 10:03
-- Verzia serveru: 5.6.26
-- Verzia PHP: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáza: `zoonote`
--

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `id_message` int(255) NOT NULL,
  `message_type` varchar(45) COLLATE utf8_slovak_ci NOT NULL,
  `title` mediumtext COLLATE utf8_slovak_ci NOT NULL,
  `osah` mediumtext COLLATE utf8_slovak_ci NOT NULL,
  `cena` double DEFAULT NULL,
  `prijemca` varchar(45) COLLATE utf8_slovak_ci NOT NULL,
  `odosielatel` varchar(45) COLLATE utf8_slovak_ci NOT NULL,
  `datum` datetime NOT NULL,
  `priloha` varchar(45) COLLATE utf8_slovak_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `uzivatel`
--

CREATE TABLE IF NOT EXISTS `uzivatel` (
  `id_uzivatel` int(255) NOT NULL,
  `username` char(255) COLLATE utf8_slovak_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_slovak_ci NOT NULL,
  `user_type` char(255) COLLATE utf8_slovak_ci NOT NULL COMMENT 'admin, osetrovatel alebo opravar',
  `name` char(255) COLLATE utf8_slovak_ci NOT NULL,
  `surname` char(255) COLLATE utf8_slovak_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

--
-- Sťahujem dáta pre tabuľku `uzivatel`
--

INSERT INTO `uzivatel` (`id_uzivatel`, `username`, `password`, `user_type`, `name`, `surname`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', 'admin'),
(2, 'mochnacky', 'mochnackym', 'osetrovatel', 'Martin', 'Mochnacky'),
(3, 'pilat', 'pilatm', 'opravar', 'Martin', 'Pilat'),
(4, 'ondrejova', 'ondrejovav', 'osetrovatel', 'Viktoria', 'Ondrejova'),
(5, 'bily', 'bilyb', 'opravar', 'Branislav', 'Bily');

-- --------------------------------------------------------

--
-- Štruktúra tabuľky pre tabuľku `zviera`
--

CREATE TABLE IF NOT EXISTS `zviera` (
  `id_zviera` int(11) NOT NULL,
  `datum_narodenia` date NOT NULL,
  `meno` varchar(45) COLLATE utf8_slovak_ci DEFAULT NULL,
  `zdravotna_karta` longtext COLLATE utf8_slovak_ci,
  `stav` varchar(45) COLLATE utf8_slovak_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_slovak_ci;

--
-- Kľúče pre exportované tabuľky
--

--
-- Indexy pre tabuľku `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id_message`);

--
-- Indexy pre tabuľku `uzivatel`
--
ALTER TABLE `uzivatel`
  ADD PRIMARY KEY (`id_uzivatel`),
  ADD UNIQUE KEY `UNIQUE` (`username`);

--
-- Indexy pre tabuľku `zviera`
--
ALTER TABLE `zviera`
  ADD PRIMARY KEY (`id_zviera`),
  ADD UNIQUE KEY `id_zviera_UNIQUE` (`id_zviera`);

--
-- AUTO_INCREMENT pre exportované tabuľky
--

--
-- AUTO_INCREMENT pre tabuľku `message`
--
ALTER TABLE `message`
  MODIFY `id_message` int(255) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pre tabuľku `uzivatel`
--
ALTER TABLE `uzivatel`
  MODIFY `id_uzivatel` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pre tabuľku `zviera`
--
ALTER TABLE `zviera`
  MODIFY `id_zviera` int(11) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
