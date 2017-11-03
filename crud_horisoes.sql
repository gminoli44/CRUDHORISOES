-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-11-2017 a las 20:29:34
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `crud_horisoes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignaturas`
--

CREATE TABLE `asignaturas` (
  `asi_id` int(11) NOT NULL,
  `asi_nombre` varchar(40) NOT NULL,
  `asi_tipo` varchar(40) NOT NULL,
  `asi_creditos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargaacademica`
--

CREATE TABLE `cargaacademica` (
  `car_id` int(11) NOT NULL,
  `car_docente` int(11) NOT NULL,
  `car_asignatura` int(11) NOT NULL,
  `car_grupo` varchar(40) NOT NULL,
  `car_sala` varchar(40) NOT NULL,
  `car_dia` varchar(40) NOT NULL,
  `car_hora` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `docentes`
--

CREATE TABLE `docentes` (
  `doc_id` int(11) NOT NULL,
  `doc_programa` int(11) NOT NULL,
  `doc_documento` varchar(40) NOT NULL,
  `doc_nombres` varchar(40) NOT NULL,
  `doc_apellidos` varchar(40) NOT NULL,
  `doc_correo` varchar(40) NOT NULL,
  `doc_tipocontrato` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `docentes`
--

INSERT INTO `docentes` (`doc_id`, `doc_programa`, `doc_documento`, `doc_nombres`, `doc_apellidos`, `doc_correo`, `doc_tipocontrato`) VALUES
(1, 3, '10617774444', 'Andres', 'Ospina', 'aospina@gmail.com', 2),
(3, 3, '10617775555', 'German', 'Bonilla', 'gbonilla@gmail.com', 1),
(11, 3, '10617775544', 'Oscar', 'Lozano', 'olozano@gmail.com', 2),
(12, 6, '10617772222', 'Oscar', 'Medina', 'omedina@gmail.com', 3),
(25, 13, '123123', 'sdfsdf', 'sdfsdf', 'sdfsd', 1),
(26, 13, '5555', 'sdfsdf', 'sdfsdf', 'sdfsd', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `programa`
--

CREATE TABLE `programa` (
  `pro_id` int(11) NOT NULL,
  `pro_nombre` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `programa`
--

INSERT INTO `programa` (`pro_id`, `pro_nombre`) VALUES
(3, 'Sociales'),
(6, 'Fisica'),
(13, 'Quimica');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asignaturas`
--
ALTER TABLE `asignaturas`
  ADD PRIMARY KEY (`asi_id`);

--
-- Indices de la tabla `cargaacademica`
--
ALTER TABLE `cargaacademica`
  ADD PRIMARY KEY (`car_id`);

--
-- Indices de la tabla `docentes`
--
ALTER TABLE `docentes`
  ADD PRIMARY KEY (`doc_id`),
  ADD KEY `doc_programa` (`doc_programa`);

--
-- Indices de la tabla `programa`
--
ALTER TABLE `programa`
  ADD PRIMARY KEY (`pro_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asignaturas`
--
ALTER TABLE `asignaturas`
  MODIFY `asi_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `cargaacademica`
--
ALTER TABLE `cargaacademica`
  MODIFY `car_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `docentes`
--
ALTER TABLE `docentes`
  MODIFY `doc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT de la tabla `programa`
--
ALTER TABLE `programa`
  MODIFY `pro_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `docentes`
--
ALTER TABLE `docentes`
  ADD CONSTRAINT `docentes_ibfk_1` FOREIGN KEY (`doc_programa`) REFERENCES `programa` (`pro_id`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
