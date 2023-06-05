-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2023 a las 07:12:47
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `coffee`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bebidas`
--

CREATE TABLE `bebidas` (
  `id_bebida` int(11) NOT NULL,
  `nombre_bebida` varchar(50) NOT NULL,
  `descripcion_bebida` varchar(200) NOT NULL,
  `precio_bebida` decimal(5,2) NOT NULL,
  `ing1` int(11) DEFAULT NULL,
  `ing2` int(11) DEFAULT NULL,
  `ing3` int(11) DEFAULT NULL,
  `ing4` int(11) DEFAULT NULL,
  `ing5` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `bebidas`
--

INSERT INTO `bebidas` (`id_bebida`, `nombre_bebida`, `descripcion_bebida`, `precio_bebida`, `ing1`, `ing2`, `ing3`, `ing4`, `ing5`) VALUES
(1, 'Cafe', 'Una bebida caliente y aromática hecha con granos de café molidos y agua caliente', 50.50, 1, 2, 8, NULL, NULL),
(2, 'Cafe Americano', 'Un café americano es una infusión de café diluida en agua caliente.', 45.00, 1, 3, 8, NULL, NULL),
(3, 'Cafe con leche', '\"Café con leche\" es una bebida caliente que combina café fuerte y leche caliente.', 75.00, 3, 4, 8, NULL, NULL),
(4, 'Cappucino', 'Café espresso con leche vaporizada y espuma de leche servido en taza pequeña.', 90.00, 3, 4, 5, 8, NULL),
(5, 'Latte', 'Café con leche caliente hecho con espresso y espuma de leche.\r\n\r\n\r\n\r\n\r\n', 90.00, 3, 4, 5, 8, NULL),
(6, 'Mocha', 'Café espresso con chocolate y leche al vapor coronado con espuma.', 90.00, 3, 4, 5, 8, NULL),
(7, 'Frappe', 'Bebida fría y espumosa hecha con hielo, leche y saborizante.', 90.00, 3, 4, 6, 8, NULL),
(8, 'Te', 'Infusión caliente de agua con hojas, flores o frutas que puede contener propiedades medicinales.', 45.00, 1, 7, 8, NULL, NULL),
(9, 'Te helado', 'Té frío y refrescante, dulce o natural, perfecto para cualquier ocasión.', 50.00, 3, 4, 6, 8, NULL),
(10, 'Chocolate caliente', 'Bebida caliente, cremosa y reconfortante, hecha con chocolate derretido y leche.', 65.00, 4, 5, 8, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bebidas_ingredientes`
--

CREATE TABLE `bebidas_ingredientes` (
  `id_bebida` int(11) NOT NULL,
  `id_ingrediente` int(11) NOT NULL,
  `cantidad` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `bebidas_ingredientes`
--

INSERT INTO `bebidas_ingredientes` (`id_bebida`, `id_ingrediente`, `cantidad`) VALUES
(1, 1, 0.15),
(1, 2, 0.10),
(1, 8, 0.05),
(2, 1, 0.20),
(2, 3, 0.03),
(2, 8, 0.05),
(3, 3, 0.03),
(3, 4, 0.15),
(3, 8, 0.05),
(4, 3, 0.03),
(4, 4, 0.15),
(4, 5, 0.03),
(4, 8, 0.05);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `contra` varchar(50) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(11) NOT NULL,
  `tipo` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `contra`, `nombre`, `telefono`, `tipo`) VALUES
(1, 'manchas', 'Ana Karen Cuenca Esquivel', '1234567890', 1),
(2, 'saulcc', 'Saul Cervantes Candia', '0987654321', 0),
(3, 'lospatos3000', 'Alex Cerillo', '9351728391', 1),
(4, 'manchass', 'Manchas', '234666643', 0),
(5, 'weqeqw', 'Oscar', '1254774125', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingredientes`
--

CREATE TABLE `ingredientes` (
  `id_ingrediente` int(11) NOT NULL,
  `nombre_ingrediente` varchar(50) NOT NULL,
  `cant` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ingredientes`
--

INSERT INTO `ingredientes` (`id_ingrediente`, `nombre_ingrediente`, `cant`) VALUES
(1, 'Agua', 14.03),
(2, 'Cafe molido', 4.8),
(3, 'Cafe espresso', 4.88),
(4, 'Leche', 4.55),
(5, 'Cacao', 3),
(6, 'Hielo', 5),
(7, 'Bolsa de te', 135),
(8, 'Azucar', 4.55),
(9, 'pan_Croissant', 8),
(10, 'pan_Panini', 6),
(11, 'pan_Bagel', 6),
(12, 'jamon', 5),
(13, 'queso', 6),
(14, 'lechuga', 5),
(15, 'salsa', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id` int(11) NOT NULL,
  `mesa` int(11) NOT NULL,
  `nombreC` varchar(20) NOT NULL,
  `cantP` int(11) DEFAULT NULL,
  `cantB` int(11) DEFAULT NULL,
  `id_platillo` int(11) DEFAULT NULL,
  `id_bebida` int(11) DEFAULT NULL,
  `fecha_pedido` date NOT NULL,
  `estado` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id`, `mesa`, `nombreC`, `cantP`, `cantB`, `id_platillo`, `id_bebida`, `fecha_pedido`, `estado`) VALUES
(1, 1, 'Saul', 2, 3, 1, 1, '2023-04-23', 'Finalizado'),
(2, 2, 'Norma', 4, 1, 1, 1, '2023-04-23', 'Finalizado'),
(3, 4, 'Marco', 1, 1, 1, 1, '2023-05-07', 'Cancelado'),
(4, 5, 'Alejandro', 1, 1, 1, 1, '2023-05-07', 'Finalizado'),
(5, 3, 'Lupe', 1, 1, 1, 1, '2023-05-09', 'En progreso'),
(6, 6, 'Miguel', 1, 2, 1, 1, '2023-05-10', 'Finalizado'),
(7, 2, 'Juan', 1, 1, 3, 10, '2023-05-15', 'Finalizado'),
(8, 1, 'Alejandra', 2, 2, 3, 3, '2023-05-15', 'En progreso');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `platillos`
--

CREATE TABLE `platillos` (
  `id_platillo` int(11) NOT NULL,
  `nombre_platillo` varchar(30) NOT NULL,
  `descripcion_platillo` varchar(100) NOT NULL,
  `precio_platillo` float DEFAULT NULL,
  `id_ingrediente1` int(11) DEFAULT NULL,
  `id_ingrediente2` int(11) DEFAULT NULL,
  `id_ingrediente3` int(11) DEFAULT NULL,
  `id_ingrediente4` int(11) DEFAULT NULL,
  `id_ingrediente5` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `platillos`
--

INSERT INTO `platillos` (`id_platillo`, `nombre_platillo`, `descripcion_platillo`, `precio_platillo`, `id_ingrediente1`, `id_ingrediente2`, `id_ingrediente3`, `id_ingrediente4`, `id_ingrediente5`) VALUES
(1, 'Croissant', 'Pan francés en forma de media luna, \nhojaldrado y crujiente en su exterior.', 110.5, 9, 12, 13, 14, 15),
(2, 'Bagel', 'Pan redondo, suave y ligeramente dulce con agujero en el centro. Ideal para sandwiches.', 150, 10, 12, 13, 14, 15),
(3, 'Panini', 'Un sándwich caliente italiano hecho con pan crujiente y relleno variado.', 130, 10, 12, 13, 14, 15),
(4, 'Ensalada Cesar', 'Lechuga, crutones, queso parmesano, aderezo cesar. Clásica y deliciosa ensalada.', 80, 13, 14, 15, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `platilloss_ingredientes`
--

CREATE TABLE `platilloss_ingredientes` (
  `id_platillo` int(11) NOT NULL,
  `id_ingrediente` int(11) NOT NULL,
  `cantidad` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `platilloss_ingredientes`
--

INSERT INTO `platilloss_ingredientes` (`id_platillo`, `id_ingrediente`, `cantidad`) VALUES
(1, 9, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bebidas`
--
ALTER TABLE `bebidas`
  ADD PRIMARY KEY (`id_bebida`),
  ADD KEY `ing1` (`ing1`),
  ADD KEY `ing2` (`ing2`),
  ADD KEY `ing3` (`ing3`),
  ADD KEY `ing4` (`ing4`),
  ADD KEY `ing5` (`ing5`);

--
-- Indices de la tabla `bebidas_ingredientes`
--
ALTER TABLE `bebidas_ingredientes`
  ADD KEY `id_bebida` (`id_bebida`),
  ADD KEY `id_ingrediente` (`id_ingrediente`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ingredientes`
--
ALTER TABLE `ingredientes`
  ADD PRIMARY KEY (`id_ingrediente`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `bebida` (`id_bebida`),
  ADD KEY `id_platillo` (`id_platillo`);

--
-- Indices de la tabla `platillos`
--
ALTER TABLE `platillos`
  ADD PRIMARY KEY (`id_platillo`),
  ADD KEY `id_ingrediente1` (`id_ingrediente1`),
  ADD KEY `id_ingrediente2` (`id_ingrediente2`),
  ADD KEY `id_ingrediente3` (`id_ingrediente3`),
  ADD KEY `id_ingrediente4` (`id_ingrediente4`),
  ADD KEY `id_ingrediente5` (`id_ingrediente5`);

--
-- Indices de la tabla `platilloss_ingredientes`
--
ALTER TABLE `platilloss_ingredientes`
  ADD KEY `id_platillo` (`id_platillo`),
  ADD KEY `id_ingrediente` (`id_ingrediente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bebidas_ingredientes`
--
ALTER TABLE `bebidas_ingredientes`
  ADD CONSTRAINT `bebidas_ingredientes_ibfk_1` FOREIGN KEY (`id_bebida`) REFERENCES `bebidas` (`id_bebida`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_bebida`) REFERENCES `bebidas` (`id_bebida`),
  ADD CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`id_platillo`) REFERENCES `platillos` (`id_platillo`);

--
-- Filtros para la tabla `platillos`
--
ALTER TABLE `platillos`
  ADD CONSTRAINT `platillos_ibfk_1` FOREIGN KEY (`id_ingrediente1`) REFERENCES `ingredientes` (`id_ingrediente`),
  ADD CONSTRAINT `platillos_ibfk_2` FOREIGN KEY (`id_ingrediente2`) REFERENCES `ingredientes` (`id_ingrediente`),
  ADD CONSTRAINT `platillos_ibfk_3` FOREIGN KEY (`id_ingrediente3`) REFERENCES `ingredientes` (`id_ingrediente`),
  ADD CONSTRAINT `platillos_ibfk_4` FOREIGN KEY (`id_ingrediente4`) REFERENCES `ingredientes` (`id_ingrediente`),
  ADD CONSTRAINT `platillos_ibfk_5` FOREIGN KEY (`id_ingrediente5`) REFERENCES `ingredientes` (`id_ingrediente`);

--
-- Filtros para la tabla `platilloss_ingredientes`
--
ALTER TABLE `platilloss_ingredientes`
  ADD CONSTRAINT `platilloss_ingredientes_ibfk_1` FOREIGN KEY (`id_platillo`) REFERENCES `platillos` (`id_platillo`),
  ADD CONSTRAINT `platilloss_ingredientes_ibfk_2` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingredientes` (`id_ingrediente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
