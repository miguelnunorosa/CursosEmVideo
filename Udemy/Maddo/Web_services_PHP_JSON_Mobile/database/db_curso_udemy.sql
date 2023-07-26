-- phpMyAdmin SQL Dump
-- version 5.0.4deb2+deb11u1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 26, 2023 at 12:24 PM
-- Server version: 10.5.19-MariaDB-0+deb11u2
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_curso_udemy`
--
CREATE DATABASE IF NOT EXISTS `db_curso_udemy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db_curso_udemy`;

-- --------------------------------------------------------

--
-- Table structure for table `cidade`
--

CREATE TABLE IF NOT EXISTS `cidade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `estadoID` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `estadoID` (`estadoID`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `cidade`
--

INSERT INTO `cidade` (`id`, `estadoID`, `nome`) VALUES
(1, 1, 'Rio Branco'),
(2, 2, 'Maceió'),
(3, 3, 'Macapá'),
(4, 4, 'Manaus'),
(5, 5, 'Salvador'),
(6, 6, 'Fortaleza'),
(7, 7, 'Brasília'),
(8, 8, 'Vitória'),
(9, 9, 'Goiânia'),
(10, 10, 'São Luís'),
(11, 11, 'Cuiabá'),
(12, 12, 'Campo Grande'),
(13, 13, 'Belo Horizonte'),
(14, 14, 'Belém'),
(15, 15, 'João Pessoa'),
(16, 16, 'Curitiba'),
(17, 17, 'Recife'),
(18, 18, 'Teresina'),
(19, 19, 'Rio de Janeiro'),
(20, 20, 'Natal'),
(21, 21, 'Porto Alegre'),
(22, 22, 'Porto Velho'),
(23, 23, 'Boa Vista'),
(24, 24, 'Florianópolis'),
(25, 25, 'São Paulo'),
(26, 26, 'Aracaju'),
(27, 27, 'Palmas'),
(28, 1, 'Bujari'),
(29, 1, 'Jordão'),
(30, 1, 'Feijó'),
(31, 1, 'Acrelândia'),
(32, 1, 'Xapuri'),
(33, 2, 'Viçosa'),
(34, 2, 'Boca da Mata'),
(35, 2, 'Junqueiro'),
(36, 2, 'Maceió'),
(37, 3, 'Santan'),
(38, 3, 'Laranjal do Jari'),
(39, 3, 'Oiapoque'),
(40, 3, 'Porto Grande'),
(41, 4, 'Barcelos'),
(42, 4, 'Manicoré'),
(43, 4, 'Tefé'),
(44, 4, 'Alvarães'),
(45, 5, 'Barreiras'),
(46, 5, 'Simões Filho'),
(47, 5, 'Jequié'),
(48, 5, 'Bom Jesus da Lapa'),
(49, 6, 'Sobral'),
(50, 6, 'Crato'),
(51, 6, 'Acarape'),
(52, 7, 'Ceilândia'),
(53, 7, 'Samambaia'),
(54, 7, 'Plano Piloto'),
(55, 8, 'Itaúnas'),
(56, 8, 'Guaraparí'),
(57, 8, 'Serra'),
(58, 9, 'Aparecida de Goiânia'),
(59, 9, 'Anápolis'),
(60, 9, 'Jaraguá'),
(61, 10, 'Barreirinhas'),
(62, 10, 'Imperatriz'),
(63, 10, 'Carolina'),
(64, 11, 'Cáceres'),
(65, 11, 'Sinop'),
(66, 11, 'Poconé'),
(67, 12, 'Itaporã'),
(68, 12, 'Corumbá'),
(69, 12, 'Chapadão do Sul'),
(70, 15, 'Campina Grande'),
(71, 15, 'Cabedelo'),
(72, 15, 'Cajazeiras'),
(73, 13, 'Ipatinga'),
(74, 13, 'Sete Lagoas'),
(75, 13, 'Divinópolis'),
(76, 14, 'Marabá'),
(77, 14, 'Bragança'),
(78, 14, 'Almeirim'),
(79, 16, 'Londrina'),
(80, 16, 'Adrianópolis'),
(81, 16, 'Agudos do Sul'),
(82, 17, 'Jaboatão dos Guararapes'),
(83, 17, 'Olinda'),
(84, 17, 'Petrolina'),
(85, 18, 'Parnaíba'),
(86, 18, 'Picos'),
(87, 18, 'Piripiri'),
(88, 19, 'Angra dos Reis'),
(89, 19, 'Aperibé'),
(90, 19, 'Belford Roxo'),
(91, 20, 'Mossoró'),
(92, 20, 'Macau'),
(93, 20, 'Japi'),
(94, 21, 'Alvorada'),
(95, 21, 'Alpestre'),
(96, 21, 'Pelotas'),
(97, 22, 'Cacoal'),
(98, 22, 'Jaru'),
(99, 22, 'Ariquemes'),
(100, 23, 'Rorainópolis'),
(101, 23, 'Caroebe'),
(102, 23, 'Normandia'),
(103, 24, 'Blumenau'),
(104, 24, 'Joinville'),
(105, 24, 'Chapecó'),
(106, 25, 'Campinas'),
(107, 25, 'Guarulhos'),
(108, 25, 'Aguaí'),
(109, 26, 'Lagarto'),
(110, 26, 'Propriá'),
(111, 26, 'Boquim'),
(112, 27, 'Araguaína'),
(113, 27, 'Gurupi'),
(114, 27, 'Aguiarnópolis');

-- --------------------------------------------------------

--
-- Table structure for table `estado`
--

CREATE TABLE IF NOT EXISTS `estado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sigla` varchar(2) NOT NULL,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `estado`
--

INSERT INTO `estado` (`id`, `sigla`, `nome`) VALUES
(1, 'AC', 'Acre'),
(2, 'AL', 'Alagoas'),
(3, 'AP', 'Amapá'),
(4, 'AM', 'Amazonas'),
(5, 'BA', 'Bahia'),
(6, 'CE', 'Ceará'),
(7, 'DF', 'Distrito Federal'),
(8, 'ES', 'Espírito Santo'),
(9, 'GO', 'Goiás'),
(10, 'MA', 'Maranhão'),
(11, 'MT', 'Mato Grosso'),
(12, 'MS', 'Mato Grosso do Sul'),
(13, 'MG', 'Minas Gerais'),
(14, 'PA', 'Pará'),
(15, 'PB', 'Paraíba'),
(16, 'PR', 'Paraná'),
(17, 'PE', 'Pernambuco'),
(18, 'PI', 'Piauí'),
(19, 'RJ', 'Rio de Janeiro'),
(20, 'RN', 'Rio Grande do Norte'),
(21, 'RS', 'Rio Grande do Sul'),
(22, 'RO', 'Rondônia'),
(23, 'RR', 'Roraima'),
(24, 'SC', 'Santa Catarina'),
(25, 'SP', 'São Paulo'),
(26, 'SE', 'Sergipe'),
(27, 'TO', 'Tocantins');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cidade`
--
ALTER TABLE `cidade`
  ADD CONSTRAINT `cidade_ibfk_1` FOREIGN KEY (`estadoID`) REFERENCES `estado` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
