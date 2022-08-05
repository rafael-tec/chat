SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE DATABASE IF NOT EXISTS `chat`;

-- Schema `chat.db`
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `chat` DEFAULT CHARACTER SET utf8 ;
USE `chat` ;

-- -----------------------------------------------------
-- Table `chat`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created` DATE NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`id`, `person_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_user_person_idx` (`person_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_person`
    FOREIGN KEY (`person_id`)
    REFERENCES `chat`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `chat`.`phone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`phone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(10) NULL,
  `area_code` VARCHAR(4) NOT NULL,
  `country_code` VARCHAR(4) NOT NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`id`, `person_id`),
  INDEX `fk_phone_person1_idx` (`person_id` ASC) VISIBLE,
  CONSTRAINT `fk_phone_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `chat`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `chat`.`device`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `chat`.`device` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `manufacturer` VARCHAR(45) NOT NULL,
  `system_operation` VARCHAR(45) NOT NULL,
  `system_version` VARCHAR(45) NOT NULL,
  `ip` VARCHAR(10) NOT NULL,
  `phone_id` INT NOT NULL,
  `phone_person_id` INT NOT NULL,
  PRIMARY KEY (`id`, `phone_id`, `phone_person_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_device_phone1_idx` (`phone_id` ASC, `phone_person_id` ASC) VISIBLE,
  CONSTRAINT `fk_device_phone1`
    FOREIGN KEY (`phone_id` , `phone_person_id`)
    REFERENCES `chat`.`phone` (`id` , `person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
