DROP database `spicy_flavour`;
CREATE DATABASE  IF NOT EXISTS `spicy_flavour`;
USE `spicy_flavour`;


DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
                            `custId` varchar(10) NOT NULL,
                            `custName` varchar(30) NOT NULL,
                            `contactNo` varchar(15) NOT NULL,
                            `address` text NOT NULL,
                            `email` varchar(50) DEFAULT NULL,
                            PRIMARY KEY (`custId`)
);



DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
                            `empId` varchar(10) NOT NULL,
                            `empName` varchar(30) NOT NULL,
                            `addrsss` text NOT NULL,
                            `dob` date NOT NULL,
                            `contactNo` varchar(15) NOT NULL,
                            `email` varchar(50) DEFAULT NULL,
                            `nic` varchar(15) NOT NULL,
                            `jobTitle` varchar(15) NOT NULL,
                            PRIMARY KEY (`empId`),
                            UNIQUE KEY `contactNo` (`contactNo`),
                            UNIQUE KEY `nic` (`nic`)
);


DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
                        `itemCode` varchar(10) NOT NULL,
                        `itemName` varchar(30) NOT NULL,
                        `unitPrice` decimal(7,2) NOT NULL,
                        `category` varchar(30) DEFAULT NULL,
                        `qtyOnHand` int NOT NULL,
                        PRIMARY KEY (`itemCode`)
);

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
                          `orderId` varchar(10) NOT NULL,
                          `custId` varchar(10) DEFAULT NULL,
                          `payment` decimal(8,2) NOT NULL,
                          `time` time NOT NULL,
                          `date` date NOT NULL,
                          `deliveryStatus` varchar(10) DEFAULT 'Pending',
                          PRIMARY KEY (`orderId`),
                          KEY `custId` (`custId`),
                          CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`custId`) REFERENCES `customer` (`custId`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
                               `orderId` varchar(10) NOT NULL,
                               `itemCode` varchar(10) NOT NULL,
                               `qty` int NOT NULL,
                               `unitPrice` decimal(10,0) DEFAULT NULL,
                               PRIMARY KEY (`orderId`,`itemCode`),
                               KEY `itemCode` (`itemCode`),
                               CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE CASCADE ON UPDATE CASCADE,
                               CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`itemCode`) REFERENCES `item` (`itemCode`) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS `salary`;

CREATE TABLE `salary` (
                          `salaryId` varchar(20) NOT NULL,
                          `empId` varchar(10) DEFAULT NULL,
                          `amount` varchar(10) DEFAULT NULL,
                          `paymentmethod` varchar(30) NOT NULL,
                          PRIMARY KEY (`salaryId`),
                          KEY `empId` (`empId`),
                          CONSTRAINT `salary_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
                            `supId` varchar(10) NOT NULL,
                            `supName` varchar(30) NOT NULL,
                            `address` text,
                            `email` varchar(50) NOT NULL,
                            `contactNo` varchar(15) NOT NULL,
                            PRIMARY KEY (`supId`),
                            UNIQUE KEY `email` (`email`),
                            UNIQUE KEY `contactNo` (`contactNo`)
);



DROP TABLE IF EXISTS `supplyloaddetail`;

CREATE TABLE `supplyloaddetail` (
                                    `loadId` varchar(10) NOT NULL,
                                    `itemCode` varchar(10) NOT NULL,
                                    `supId` varchar(10) NOT NULL,
                                    `date` date NOT NULL,
                                    `price` decimal(9,2) NOT NULL,
                                    `qty` int NOT NULL,
                                    `time` time NOT NULL,
                                    PRIMARY KEY (`loadId`,`itemCode`,`supId`),
                                    KEY `itemCode` (`itemCode`),
                                    KEY `supId` (`supId`),
                                    CONSTRAINT `supplyloaddetail_ibfk_1` FOREIGN KEY (`itemCode`) REFERENCES `item` (`itemCode`) ON DELETE CASCADE ON UPDATE CASCADE,
                                    CONSTRAINT `supplyloaddetail_ibfk_2` FOREIGN KEY (`supId`) REFERENCES `supplier` (`supId`) ON DELETE CASCADE ON UPDATE CASCADE
);


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
                        `userName` varchar(20) NOT NULL,
                        `empId` varchar(10) DEFAULT NULL,
                        `password` varchar(100) NOT NULL,
                        `email` varchar(50) NOT NULL,
                        `jobTitle` varchar(30) NOT NULL,
                        PRIMARY KEY (`userName`),
                        UNIQUE KEY `email` (`email`),
                        UNIQUE KEY `empId` (`empId`),
                        CONSTRAINT `user_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE
);



DROP TABLE IF EXISTS `vehicle`;

CREATE TABLE `vehicle` (
                           `vehiId` varchar(10) NOT NULL,
                           `vehiNo` varchar(10) NOT NULL,
                           `vehiType` varchar(30) NOT NULL,
                           PRIMARY KEY (`vehiId`),
                           UNIQUE KEY `vehiNo` (`vehiNo`)
);


DROP TABLE IF EXISTS `delivery`;

CREATE TABLE `delivery` (
                            `deliveryId` varchar(10) NOT NULL,
                            `empId` varchar(10) DEFAULT NULL,
                            `orderId` varchar(10) NOT NULL,
                            `vehiId` varchar(10) DEFAULT NULL,
                            `location` text,
                            `deliveryDate` date NOT NULL,
                            `dueDate` date NOT NULL,
                            `deliveryStatus` varchar(20) DEFAULT 'Pending',
                            PRIMARY KEY (`deliveryId`),
                            UNIQUE KEY `orderId` (`orderId`),
                            KEY `empId` (`empId`),
                            KEY `vehiId` (`vehiId`),
                            CONSTRAINT `delivery_ibfk_1` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT `delivery_ibfk_2` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE CASCADE ON UPDATE CASCADE,
                            CONSTRAINT `delivery_ibfk_3` FOREIGN KEY (`vehiId`) REFERENCES `vehicle` (`vehiId`) ON DELETE CASCADE ON UPDATE CASCADE
);