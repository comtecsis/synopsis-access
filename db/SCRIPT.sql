-- MySQL Workbench Synchronization
-- Generated: 2020-09-06 23:10
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Elvis

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA `softprogy_access`;
ALTER SCHEMA `softprogy_access`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_spanish2_ci ;

/**
 * Dependencies:
 * - None
 **/
CREATE TABLE IF NOT EXISTS `softprogy_access`.`user` (
  `PK_USER` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `TXT_EMAIL` VARCHAR(45) NOT NULL,
  `TXT_PASS` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PK_USER`),
  UNIQUE INDEX `TXT_EMAIL_UNIQUE` (`TXT_EMAIL` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;

/**
 * Dependencies:
 * - None
 **/
CREATE TABLE IF NOT EXISTS `softprogy_access`.`client` (
  `PK_CLIENT` INT(10) UNSIGNED NOT NULL,
  `TXT_NAME` VARCHAR(45) NOT NULL,
  `TXT_PHONE` VARCHAR(45) NULL DEFAULT NULL,
  `FILE_IMG` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`PK_CLIENT`),
  CONSTRAINT `fk_client_user`
    FOREIGN KEY (`PK_CLIENT`)
    REFERENCES `softprogy_access`.`user` (`PK_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;

CREATE TABLE IF NOT EXISTS `softprogy_access`.`role` (
  `PK_ROLE` INT(10) UNSIGNED NOT NULL,
  `TXT_ROLE` ENUM('ROLE_ADMIN', 'ROLE_CLIENT') NULL DEFAULT 'ROLE_CLIENT',
  PRIMARY KEY (`PK_ROLE`),
  CONSTRAINT `fk_rol_user_user`
    FOREIGN KEY (`PK_ROLE`)
    REFERENCES `softprogy_access`.`user` (`PK_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;

/**
 * Dependencies:
 * - None
 **/
CREATE TABLE IF NOT EXISTS `softprogy_access`.`document` (
  `PK_DOCUMENT` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `FK_USER` INT(10) UNSIGNED NOT NULL,
  `TXT_NOMB` VARCHAR(150) NULL DEFAULT NULL,
  `TXT_DESC` VARCHAR(150) NULL DEFAULT NULL,
  `FEC_UPLOAD` TIMESTAMP NULL DEFAULT NULL,
  `INT_FOLIOS` INT(10) NULL DEFAULT NULL,
  `TXT_EXT` VARCHAR(4) NULL DEFAULT NULL,
  PRIMARY KEY (`PK_DOCUMENT`),
  INDEX `fk_document_user_idx` (`FK_USER` ASC),
  CONSTRAINT `fk_document_user`
    FOREIGN KEY (`FK_USER`)
    REFERENCES `softprogy_access`.`user` (`PK_USER`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish2_ci;

/**
 * Dependencies:
 * - softprogy-access:
 *   :: Table: `softprogy_access`.`user`
 *   :: Table: `softprogy_access`.`client`
 *   :: Table: `softprogy_access`.`role`
 **/
DELIMITER $$
USE `softprogy_access`$$
CREATE PROCEDURE `SP_LOGIN` (
	OUT `status` VARCHAR(4),
  IN `email` VARCHAR(45),
	IN `password` VARCHAR(45)
)
BEGIN
    SELECT a.PK_USER, b.TXT_NAME, a.TXT_EMAIL, b.TXT_PHONE, c.TXT_ROLE, b.FILE_IMG IS NOT NULL as existImage
    FROM `user` a
    INNER JOIN `client` b
    ON a.PK_USER = b.PK_CLIENT
    INNER JOIN `role` c
    ON a.PK_USER = c.PK_ROLE
    WHERE a.TXT_EMAIL = email AND a.TXT_PASS = password;
    SET status = '0000';
END$$

DELIMITER ;

INSERT INTO `softprogy_access`.`user` (TXT_EMAIL, TXT_PASS) VALUES ('elvisperez.tec@gmail.com', '123456');
INSERT INTO `softprogy_access`.`role` (PK_ROLE, TXT_ROLE) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `softprogy_access`.`client` (PK_CLIENT, TXT_NAME) VALUES (1, 'Elvis Perez');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
