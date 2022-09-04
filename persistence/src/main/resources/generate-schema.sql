-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema chat
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema chat
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chat` ;
USE `chat` ;

-- -----------------------------------------------------
-- Table `chat`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`user` (
  `id` INT NOT NULL,
  `created_at` DATETIME NOT NULL,
  `birth_date` DATE NOT NULL,
  `full_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `email_confirmed` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`.`phone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`phone` (
  `id` INT NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `area_code` VARCHAR(45) NOT NULL,
  `country_code` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_phone_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_phone_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `chat`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`.`device`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`device` (
  `id` INT NOT NULL,
  `manufacturer` VARCHAR(45) NOT NULL,
  `system_operation` VARCHAR(45) NOT NULL,
  `system_version` VARCHAR(45) NOT NULL,
  `ip` VARCHAR(20) NULL,
  `created_at` DATETIME NULL,
  `phone_id` INT NOT NULL,
  `phone_user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `phone_id`, `phone_user_id`),
  INDEX `fk_device_phone1_idx` (`phone_id` ASC, `phone_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_device_phone1`
    FOREIGN KEY (`phone_id` , `phone_user_id`)
    REFERENCES `chat`.`phone` (`id` , `user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
